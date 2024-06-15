package com.rosekingdom.rosekingdom.Core.Utils;

import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ResourcePackLoader {
    static String resourcePackPath = "https://www.dropbox.com/scl/fi/nky6hoo7sldrwg0hy6tja/RoseKingdom-s-Pack.zip?rlkey=1mv7qh4vra9wawk58036ij9h8&dl=1";

    static List<String> tester = new ArrayList<>();

    public static void setResourcePack(Player player){
        if(tester.contains(player.getName())) return;
        try {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 0, false, false));
                ResourcePackInfo pack = ResourcePackInfo.resourcePackInfo()
                        .id(UUID.fromString("eb247a5a-c0ba-4058-8233-b3c3e198194f"))
                        .uri(URI.create(resourcePackPath))
                        .computeHashAndBuild().get();
                player.sendResourcePacks(ResourcePackRequest.resourcePackRequest()
                        .prompt(Component.text("This server requires a custom resource pack for things to work!", TextColor.fromHexString("#ffce0d"))
                                .append(Component.text("\nFor help or more info contact the staff!", TextColor.fromHexString("#c8c8c8")).decorate(TextDecoration.ITALIC)))
                        .packs(pack)
                        .required(true)
                        .build());
                player.removePotionEffect(PotionEffectType.BLINDNESS);
        } catch (ExecutionException | InterruptedException e){
            Message.Exception("Failed to set ResourcePack", e);
            player.kick(Component.text("Failed to set ResourcePack"));
        }
    }

    public static void removeResourcePack(Player player){
        try {
            player.removeResourcePack(UUID.fromString("eb247a5a-c0ba-4058-8233-b3c3e198194f"));
            tester.add(player.getName());
        } catch (IllegalArgumentException e) {
            Message.Exception("Failed to set ResourcePack", e);
        }
    }

    public static void removeTester(Player player) {
        tester.remove(player.getName());
        setResourcePack(player);
    }
}
