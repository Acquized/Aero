package cc.acquized.aero;

import cc.acquized.aero.features.*;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Aero extends JavaPlugin {

    public static String pr = "§b[Aero] §7";
    private static Aero instance;

    @Override
    public void onEnable() {
        instance = this;
        Config.loadFile();
        Gapple.addRecipe();
        DisableOffHand.registerListener();
        EnderCrystals.registerListener();
        SpectralEffect.registerListener();
        HitCooldown.registerListener();
        Bukkit.getConsoleSender().sendMessage("[Aero] Aero v" + getDescription().getVersion() + " was enabled.");
    }

    @Override
    public void onDisable() {
        instance = null;
        Bukkit.getConsoleSender().sendMessage("[Aero] Aero v" + getDescription().getVersion() + " was disabled.");
    }

    public static Aero getInstance() {
        return instance;
    }

}
