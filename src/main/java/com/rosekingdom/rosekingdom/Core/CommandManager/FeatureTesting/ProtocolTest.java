package com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting;

import com.mojang.authlib.GameProfile;
import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
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
import org.bukkit.craftbukkit.v1_20_R2.CraftServer;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Collections;
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
//        CraftServer craftServer = (CraftServer)Bukkit.getServer();
//        MinecraftServer server = craftServer.getServer();
//        ServerLevel world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
//        GameProfile profile = new GameProfile(UUID.randomUUID(), "Gosho");
//        ServerPlayer serverPlayer = new ServerPlayer(
//                server,
//                world,
//                profile,
//                new ClientInformation("en_us", 5, ChatVisiblity.FULL, false, 3, HumanoidArm.RIGHT, false, true));
//        Player npc = new CraftPlayer(craftServer, serverPlayer);
//        npc.teleport(player.getLocation());
//        ServerGamePacketListenerImpl listener = ((CraftPlayer) player).getHandle().connection;
//        listener.send(new ClientboundAddEntityPacket());
//        listener.send(new );
//        player.sendMessage(text(npc.getLocation().toString()));

        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel serverLevel = ((CraftWorld) location.getWorld()).getHandle();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "Gosho");
        ServerPlayer serverPlayer = new ServerPlayer(minecraftServer, serverLevel, profile, ClientInformation.createDefault());
        serverPlayer.setPos(location.getX(), location.getY(), location.getZ());
        SynchedEntityData synchedEntityData = serverPlayer.getEntityData();
        synchedEntityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);

        setValue(serverPlayer, "c", ((CraftPlayer) player).getHandle().connection);

        sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, serverPlayer), player);
        sendPacket(new ClientboundAddEntityPacket(serverPlayer), player);
        sendPacket(new ClientboundSetEntityDataPacket(serverPlayer.getId(), synchedEntityData.getNonDefaultValues()), player);
        Bukkit.getScheduler().runTaskLaterAsynchronously(JavaPlugin.getPlugin(RoseKingdom.class),
                () -> sendPacket(new ClientboundPlayerInfoRemovePacket(Collections.singletonList(serverPlayer.getUUID())), player), 40);

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
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

}
