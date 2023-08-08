package com.rosekingdom.rosekingdom.Utils;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ResourcePackLoader {
    String resourcePackPath = "https://www.dropbox.com/scl/fi/wayi9numrmddu9qgl2e9c/pack.zip?rlkey=x63n3vppd2xpazvii54mcdj79&dl=1";

    public void setResourcePack(Player player){
        try {
            player.setResourcePack(
                    resourcePackPath,
                    getHash(),
                    Component.text("This is required for things to work!")
                            .append(Component.text("\nFor help or more info contact the staff!")),
                    true
            );
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    //We hope this works
    private byte[] getHash() throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        final URLConnection urlConnection = new URL(resourcePackPath).openConnection();

        final InputStream fis = urlConnection.getInputStream();
        int n = 0;
        byte[] buffer = new byte[8192];
        while (n != -1) {
            n = fis.read(buffer);
            if (n > 0) {
                digest.update(buffer, 0, n);
            }
        }
        fis.close();
        return digest.digest();
    }
}
