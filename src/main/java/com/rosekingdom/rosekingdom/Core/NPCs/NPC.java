package com.rosekingdom.rosekingdom.Core.NPCs;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.rosekingdom.rosekingdom.Core.NPCs.Statements.NPCStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.UUID;

public class NPC {

    private final String name;
    private int task;
    private Location location;
    private boolean onTabList = false;
    private boolean shown = true;
    private final ServerPlayer npc;


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

        setRotation(true);

        this.location = location;
        this.name = name;
        NPCHandler.addNPC(this);
        NPCStatement.insertNPC(this);
    }

    public NPC(String name, Location location, String playerSkin) {
        //Server and World
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel serverLevel = ((CraftWorld) location.getWorld()).getHandle();

        //Setting the NPC's skin and name
        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        String[] skin = getSkin(playerSkin);
        profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));

        //The NPC itself and it's settings
        npc = new ServerPlayer(minecraftServer, serverLevel, profile, ClientInformation.createDefault());
        npc.setPos(location.getX(), location.getY(), location.getZ());

        this.location = location;
        this.name = name;
        NPCHandler.addNPC(this);
    }

    public NPC(String name, String texture, String signature) {
        //Server and World
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel serverLevel = ((CraftWorld) Bukkit.getWorlds().getFirst()).getHandle();

        //Setting the NPC's skin and name
        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        profile.getProperties().put("textures", new Property("textures", texture, signature));

        //The NPC itself and it's settings
        npc = new ServerPlayer(minecraftServer, serverLevel, profile, ClientInformation.createDefault());

        this.name = name;
        NPCHandler.addNPC(this);
    }

    public NPC(String name) {
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel serverLevel = ((CraftWorld) Bukkit.getWorlds().getFirst()).getHandle();
        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
        profile.getProperties().put("textures", new Property("textures",
                "ewogICJ0aW1lc3RhbXAiIDogMTcxMDg4NDQwNTE2MCwKICAicHJvZmlsZUlkIiA6ICI5ZDE1OGM1YjNiN2U0ZGNlOWU0OTA5MTdjNmJlYmM5MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTbm9uX1NTIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYyM2NmYWY0MjZiNTZhZmVjM2VmOWUxYjg4ZGVhZjU5YzlkYWMwMDU4ZDNiOTE4Nzk1MzgwNzFjNjRkODVkMWEiCiAgICB9CiAgfQp9",
                "nKIof7pfbAUmqs+uRtNSvWa0M2/ZuPNqHWEzpk8JKe5vvrnyWtM+Udw2ehEM8Lvpzf/2x+AGc+DIJGXOiMJfIaGEiZUjONHXjhouA6mDcCx+kRNZZTmIT6pLF0s1Uni801v56yAPJSKgQ7vhZOmODRZgkHbLVoXG1oVWMan1vUiv573ISQ2/MF6huOgh/3hbUZU0JhOGv/NMjPaDDnYwLDkAMMqYWPeWX4xULZ+bs9KidMDgXI4WquD2uqaXgSbfkhWPySxSC2VYAxgHrGPiCwGPh5dp6YPnzD1/k1Om4XCNxhvUPPXr25yqKuN354/U4GlApBdMiEJK+9WsruK0agiahr1ARcEGlgiS7LwK39nr7Z7nKQz9NxHUhDEW9K719x5CAXqpt9R4ihmROq+rnU1xWBNViUjzszdNdyEaEyPtpVTAjghd0Erop7qK9mNkl1akiZtWReYPjMFZy9uPuKp2zLaJo9iYzNUKtZgz43VtxrPHjCmfPg4hy1PZ7I+OdcW6nTJMZidle9NA/xaExjl9/w0vTzU1LXJ5Jeo6B09Pq/ebOBK152t4VjSx8/28/l3G8l4NPZiqonk8BM4k2NHcI1Ma8OO9jVUoFhcNN0M2NfSUsg4HlCsglp6FqPqxWOLniEna5yCl4ker+ljEOMsDw+WvRGTJ9vKEQXTHD88="));
        npc = new ServerPlayer(minecraftServer, serverLevel, profile, ClientInformation.createDefault());
        this.name = name;
        NPCHandler.addNPC(this);
    }


    public void spawn() {
        //Second skin layer
        SynchedEntityData synchedEntityData = npc.getEntityData();
        synchedEntityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);

        for(Player online : Bukkit.getOnlinePlayers()) {
            setValue(npc, "c", ((CraftPlayer) online).getHandle().connection);
            //Spawns the NPC
            sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc), online);
            if(onTabList){
                //Adds it to the tablist
                sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED, npc), online);
            }
            if(shown){
                //Shows the NPC
                sendPacket(new ClientboundAddEntityPacket(npc, 0, new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ())), online);
            }
            //Adds the second skin layer
            sendPacket(new ClientboundSetEntityDataPacket(npc.getId(), synchedEntityData.getNonDefaultValues()), online);
        }
    }

    public void despawn(){
        for(Player online : Bukkit.getOnlinePlayers()){
            setRotation(false);
            sendPacket(new ClientboundRemoveEntitiesPacket(npc.getId()), online);
            sendPacket(new ClientboundPlayerInfoRemovePacket(Collections.singletonList(npc.getUUID())), online);
        }
    }

    public void addToTabOnly() {
        SynchedEntityData synchedEntityData = npc.getEntityData();
        synchedEntityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);

        for(Player online : Bukkit.getOnlinePlayers()) {
            setValue(npc, "c", ((CraftPlayer) online).getHandle().connection);
            sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc), online);
            sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED, npc), online);
            sendPacket(new ClientboundSetEntityDataPacket(npc.getId(), synchedEntityData.getNonDefaultValues()), online);
        }
        onTabList = true;
        shown = false;
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
                    if(calculateDistance(p, npc) > 5) {
                        sendPacket(new ClientboundRotateHeadPacket(npc, (byte) location.getYaw()), p);
                        sendPacket(new ClientboundMoveEntityPacket.Rot(npc.getId(), (byte) location.getYaw(), (byte) location.getPitch(), true), p);
                        continue;
                    }

                    Location newLocation = location.setDirection(p.getLocation().subtract(location).toVector().normalize());
                    byte yaw = (byte) ((newLocation.getYaw() % 360.) * 256 / 360);
                    byte pitch = (byte) ((newLocation.getPitch() % 360.) * 256 / 360);

                    sendPacket(new ClientboundRotateHeadPacket(npc, yaw), p);
                    sendPacket(new ClientboundMoveEntityPacket.Rot(npc.getId(), yaw, pitch, true), p);
                }
            }, 10, 5);
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
           Message.Console("The player " + name + " does not exist.");
            String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY0MDUxODU2Njk1NiwKICAicHJvZmlsZUlkIiA6ICJlYzU2MTUzOGYzZmQ0NjFkYWZmNTA4NmIyMjE1NGJjZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJBbGV4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFhNGFmNzE4NDU1ZDRhYWI1MjhlN2E2MWY4NmZhMjVlNmEzNjlkMTc2OGRjYjEzZjdkZjMxOWE3MTNlYjgxMGIiCiAgICB9CiAgfQp9";
            String signature = "BchUKARlsKuXPJA7qXd2QKgnj3jR+F2EYHG5gwl4QW/+nK8Mb7MLKJDcKbKdxGRgCFfi7perJrDXZ8TpNrGxLgI+ocmjonH+ebwqv5NuRbGD0+Pkc1HCp0mq1dXnRPVgxFrlB+1pTSOnsYRJSJbLdIDvxbwL3RgQIkpKOFT7+Tpdx0VXEoHp2HCWtteAtjh1kEReHTJmnKwAzWmOU5j3Ro8e7xcuOOEG5p9CTbZyk2xxBDNHOJMq7jhPCMModKz15JdGm02r7k1al8GzdO9g0yx6GD8RlpzH0j1Ol+BHCnQ80TcrBvEOc9xgNN9q68Z2kVU7elNbXPHZYFsxalbpvwaHelDgTmx71NYfDzIqqvOY0s37kJsndWuY2bRhqNhJBFZi/SOvXFZHHhQcARGxBsizc5LKfIG3UqYHhuAJ/beErRvZLUM8hCgd5w8ISZNzPdM5pMGfe7ckaEWRRjhb7CmFHVZ9RQ+cHXGnUdSsrsDCT/gwZLIt8gHSIncE3H5m9zauhRmY2KYUZVVMKkbPB1TRfUbZdVWbEjJA7w4SXdyCN0Byh37pQl0ONvXtc5/eNRyuGHlkQj5qh/26zm/x4sawA+/7F4xfWiCib55DMLHFyXP3ooQIPmbwz+u4zLPnXymwJZG894ObapMlc1hWPmb2SbN28ZOuU1R67JwUqaI=";
            return new String[] {texture, signature};
        }
        //gray box
        //texture: ewogICJ0aW1lc3RhbXAiIDogMTcxMDg4NDQwNTE2MCwKICAicHJvZmlsZUlkIiA6ICI5ZDE1OGM1YjNiN2U0ZGNlOWU0OTA5MTdjNmJlYmM5MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTbm9uX1NTIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYyM2NmYWY0MjZiNTZhZmVjM2VmOWUxYjg4ZGVhZjU5YzlkYWMwMDU4ZDNiOTE4Nzk1MzgwNzFjNjRkODVkMWEiCiAgICB9CiAgfQp9
        //signature: nKIof7pfbAUmqs+uRtNSvWa0M2/ZuPNqHWEzpk8JKe5vvrnyWtM+Udw2ehEM8Lvpzf/2x+AGc+DIJGXOiMJfIaGEiZUjONHXjhouA6mDcCx+kRNZZTmIT6pLF0s1Uni801v56yAPJSKgQ7vhZOmODRZgkHbLVoXG1oVWMan1vUiv573ISQ2/MF6huOgh/3hbUZU0JhOGv/NMjPaDDnYwLDkAMMqYWPeWX4xULZ+bs9KidMDgXI4WquD2uqaXgSbfkhWPySxSC2VYAxgHrGPiCwGPh5dp6YPnzD1/k1Om4XCNxhvUPPXr25yqKuN354/U4GlApBdMiEJK+9WsruK0agiahr1ARcEGlgiS7LwK39nr7Z7nKQz9NxHUhDEW9K719x5CAXqpt9R4ihmROq+rnU1xWBNViUjzszdNdyEaEyPtpVTAjghd0Erop7qK9mNkl1akiZtWReYPjMFZy9uPuKp2zLaJo9iYzNUKtZgz43VtxrPHjCmfPg4hy1PZ7I+OdcW6nTJMZidle9NA/xaExjl9/w0vTzU1LXJ5Jeo6B09Pq/ebOBK152t4VjSx8/28/l3G8l4NPZiqonk8BM4k2NHcI1Ma8OO9jVUoFhcNN0M2NfSUsg4HlCsglp6FqPqxWOLniEna5yCl4ker+ljEOMsDw+WvRGTJ9vKEQXTHD88=

    }

    public void setOnTabList(boolean value){
        onTabList = value;
    }

    public void shown(boolean value){
        shown = value;
    }

    public int getId(){
        return npc.getId();
    }

    public UUID getUUID(){
        return npc.getUUID();
    }

    public String getName(){
        return name;
    }

    public Location getLocation(){
        return location;
    }
    public ServerPlayer getNPC(){
        return npc;
    }
    public boolean isOnTabList(){
        return onTabList;
    }
    public boolean isShown() {
        return shown;
    }
}
