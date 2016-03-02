package cc.acquized.aero.file;

import cc.acquized.aero.Aero;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Config {

    private static File file = new File(Aero.getInstance().getDataFolder(), "config.yml");
    private static FileConfiguration config;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void loadFile() {
        try {
            if(!Aero.getInstance().getDataFolder().isDirectory()) {
                Aero.getInstance().getDataFolder().mkdir();
            }
            if(!file.exists()) {
                Files.copy(Aero.getInstance().getResource("config.yml"), file.toPath());
            }
            config = YamlConfiguration.loadConfiguration(file);
            if(!config.getString("Aero.Version").equals(Aero.getInstance().getDescription().getVersion())) {
                Bukkit.getConsoleSender().sendMessage("[Aero] Your Config is outdated. Recreating it...");
                config = null;
                file.delete();
                Files.copy(Aero.getInstance().getResource("config.yml"), file.toPath());
                config = YamlConfiguration.loadConfiguration(file);
            }
            Aero.pr = ChatColor.translateAlternateColorCodes('&', config.getString("Aero.Prefix"));
            Bukkit.getConsoleSender().sendMessage("[Aero] The Config.yml File was successfully loaded.");
        } catch (IOException ex) {
            Bukkit.getConsoleSender().sendMessage("[Aero] §cCould not load config.yml File.");
        }
    }

    public static File getFile() {
        return file;
    }

    public static FileConfiguration getConfig() {
        return config;
    }

}
