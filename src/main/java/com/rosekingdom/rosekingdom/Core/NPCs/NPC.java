package com.rosekingdom.rosekingdom.Core.NPCs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.UUID;

public class NPC {

    private String name;
    private int task;
    private Location location;
    private ServerPlayer npc;

    public NPC(String name, Location location) {
        //Server and World
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel serverLevel = ((CraftWorld) location.getWorld()).getHandle();

        //Setting the NPC's skin and name
        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        String[] skin = getSkin(name);
        profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));

        //The NPC itself and it's settings
        npc = new ServerPlayer(minecraftServer, serverLevel, profile, ClientInformation.createDefault());
        npc.setPos(location.getX(), location.getY(), location.getZ());

        //No idea what this does

    }

    public void spawn() {
        //Second skin layer
        SynchedEntityData synchedEntityData = npc.getEntityData();
        synchedEntityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);

        for(Player online : Bukkit.getOnlinePlayers()) {
            setValue(npc, "c", ((CraftPlayer) online).getHandle().connection);
            //Spawns the NPC
            sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc), online);
            //Adds it to the tablist
            sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED, npc), online);

            //Shows the NPC
            sendPacket(new ClientboundAddEntityPacket(npc), online);
            //Adds the second skin layer
            sendPacket(new ClientboundSetEntityDataPacket(npc.getId(), synchedEntityData.getNonDefaultValues()), online);

            //Sends remove packet (visually removes player from tablist)
//            Bukkit.getScheduler().runTaskLaterAsynchronously(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
//                sendPacket(new ClientboundPlayerInfoRemovePacket(Collections.singletonList(npc.getUUID())), online);
//            }, 40);
        }
    }

    private void sendPacket(Packet<?> packet, Player player) {
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    //Not sure what this does yet
    private void setValue(Object packet, String fieldName, Object value) {
        try {
            Field field = packet.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(packet, value);
        } catch (Exception e) {
            Message.Exception("Failed the setting of the field value", e);
        }
    }

    public void setRotation(Boolean enable){
        if(enable){
            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(calculateDistance(p, npc) > 5) continue;

                    // Calculate the Yaw for the NPC
                    Vector difference = p.getLocation().subtract(npc.getBukkitEntity().getLocation()).toVector().normalize();
                    byte yaw = (byte) Mth.wrapDegrees((Math.toDegrees(Math.atan2(difference.getZ(), difference.getX()) - Math.PI / 2) * 256.0F) / 360.0F);

                    // Calculate the Pitch for the NPC
                    Vector height = npc.getBukkitEntity().getLocation().subtract(p.getLocation()).toVector().normalize();
                    byte pitch = (byte) Mth.wrapDegrees((Math.toDegrees(Math.atan(height.getY())) * 256.0F) / 360.0F);

                    // Send the Packets to update the NPC
                    sendPacket(new ClientboundRotateHeadPacket(npc, yaw), p);
                    sendPacket(new ClientboundMoveEntityPacket.Rot(npc.getId(), yaw, pitch, true), p);
                }
            }, 10, 20);
        }else {
            Bukkit.getScheduler().cancelTask(task);
        }
    }

    private double calculateDistance(Player p, ServerPlayer npc) {
        double diffX = npc.getX() - p.getLocation().getX(), diffZ = npc.getZ() - p.getLocation().getZ();
        double x = diffX < 0 ? (diffX * -1) : diffX, z = diffZ < 0 ? (diffZ * -1) : diffZ;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
    }

    private String[] getSkin(String name) {
        try {
            // gets UUID from name
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = JsonParser.parseReader(reader).getAsJsonObject().get("id").getAsString();

            // gets textures for the account
            URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader2 = new InputStreamReader(url2.openStream());
            JsonObject property = JsonParser.parseReader(reader2).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = property.get("value").getAsString();
            String signature = property.get("signature").getAsString();
            return new String[] {texture, signature};
        } catch (IOException | IllegalStateException exception) {
            Bukkit.getLogger().warning("The player " + name + " does not exist.");
            String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY0MDUxODU2Njk1NiwKICAicHJvZmlsZUlkIiA6ICJlYzU2MTUzOGYzZmQ0NjFkYWZmNTA4NmIyMjE1NGJjZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJBbGV4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFhNGFmNzE4NDU1ZDRhYWI1MjhlN2E2MWY4NmZhMjVlNmEzNjlkMTc2OGRjYjEzZjdkZjMxOWE3MTNlYjgxMGIiCiAgICB9CiAgfQp9";
            String signature = "BchUKARlsKuXPJA7qXd2QKgnj3jR+F2EYHG5gwl4QW/+nK8Mb7MLKJDcKbKdxGRgCFfi7perJrDXZ8TpNrGxLgI+ocmjonH+ebwqv5NuRbGD0+Pkc1HCp0mq1dXnRPVgxFrlB+1pTSOnsYRJSJbLdIDvxbwL3RgQIkpKOFT7+Tpdx0VXEoHp2HCWtteAtjh1kEReHTJmnKwAzWmOU5j3Ro8e7xcuOOEG5p9CTbZyk2xxBDNHOJMq7jhPCMModKz15JdGm02r7k1al8GzdO9g0yx6GD8RlpzH0j1Ol+BHCnQ80TcrBvEOc9xgNN9q68Z2kVU7elNbXPHZYFsxalbpvwaHelDgTmx71NYfDzIqqvOY0s37kJsndWuY2bRhqNhJBFZi/SOvXFZHHhQcARGxBsizc5LKfIG3UqYHhuAJ/beErRvZLUM8hCgd5w8ISZNzPdM5pMGfe7ckaEWRRjhb7CmFHVZ9RQ+cHXGnUdSsrsDCT/gwZLIt8gHSIncE3H5m9zauhRmY2KYUZVVMKkbPB1TRfUbZdVWbEjJA7w4SXdyCN0Byh37pQl0ONvXtc5/eNRyuGHlkQj5qh/26zm/x4sawA+/7F4xfWiCib55DMLHFyXP3ooQIPmbwz+u4zLPnXymwJZG894ObapMlc1hWPmb2SbN28ZOuU1R67JwUqaI=";
            return new String[] {texture, signature};
        }
    }
}
