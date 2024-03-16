package com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class ProtocolTest extends CommandRK {

    public ProtocolTest(){
        setName("pt");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        Location location = player.getLocation();
        if(args.length == 1){
            MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
            ServerLevel serverLevel = ((CraftWorld) location.getWorld()).getHandle();
            GameProfile profile = new GameProfile(UUID.randomUUID(), args[0]);
            String[] skin = getSkin(player.getName());
            profile.getProperties().put("textures", new Property("textures", skin[0], skin[1]));
            ServerPlayer serverPlayer = new ServerPlayer(minecraftServer, serverLevel, profile, ClientInformation.createDefault());
            serverPlayer.setPos(location.getX(), location.getY(), location.getZ());
            SynchedEntityData synchedEntityData = serverPlayer.getEntityData();
            synchedEntityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);

            setValue(serverPlayer, "c", ((CraftPlayer) player).getHandle().connection);

            for(Player online : Bukkit.getOnlinePlayers()) {
                //Spawns the player
                sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, serverPlayer), player);
                //Adds it to the tablist
                sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED, serverPlayer), player);
                //TODO: See what these do!
                sendPacket(new ClientboundAddEntityPacket(serverPlayer), player);
                sendPacket(new ClientboundSetEntityDataPacket(serverPlayer.getId(), synchedEntityData.getNonDefaultValues()), player);
            }
            //Removes packet (visually removes player from tablist)
//            Bukkit.getScheduler().runTaskLaterAsynchronously(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
//                sendPacket(new ClientboundPlayerInfoRemovePacket(Collections.singletonList(serverPlayer.getUUID())), player);
//            }, 40);
        }
    }

    public void sendPacket(Packet<?> packet, Player player) {
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    public void setValue(Object packet, String fieldName, Object value) {
        try {
            Field field = packet.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(packet, value);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
