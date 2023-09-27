package com.rosekingdom.rosekingdom.Core.Config;

import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.Map;

public class Config {
    protected JavaPlugin plugin;
    private static final Map<String, String> D_VAlUES = new LinkedHashMap<>();
    private static FileConfiguration config;
    private static final Config INST = new Config();

    public boolean USE_FULLURL;
    public String HOST;
    public String USERNAME;
    public String PASSWORD;
    public String DATABASE;
    public String JDBC;

    static {
        D_VAlUES.put("use_fullURL", "false");
        D_VAlUES.put("host", "localhost:3306");
        D_VAlUES.put("username", "root");
        D_VAlUES.put("password", "12345");
        D_VAlUES.put("database", "test");
        D_VAlUES.put("jdbc", "");
    }

    private void readValues(){
        USE_FULLURL = config.getBoolean("use_fullURL");
        HOST = config.getString("host");
        USERNAME = config.getString("username");
        PASSWORD = config.getString("password");
        DATABASE = config.getString("database");
        JDBC = config.getString("jdbc");
    }

    public Config(){
        createConfig();
        checkMissing();
        readValues();
    }

    private void checkMissing(){
        int default_values = D_VAlUES.size();
        for(Map.Entry<String, String> entry : D_VAlUES.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();

            final String configValue = config.getString(key);
            if (configValue != null) {
                continue;
            }
            config.addDefault(key, value);
        }
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static Config getInstance(){
        return INST;
    }

    private void createConfig(){
        plugin = JavaPlugin.getPlugin(RoseKingdom.class);
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }
}
