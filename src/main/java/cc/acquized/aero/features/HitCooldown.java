package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HitCooldown implements Listener {

    public static void registerListener() {
        if(Config.getConfig().getBoolean("Features.RemoveHitCooldown")) {
            Bukkit.getPluginManager().registerEvents(new HitCooldown(), Aero.getInstance());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0D);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0D);
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0D);
    }

}
