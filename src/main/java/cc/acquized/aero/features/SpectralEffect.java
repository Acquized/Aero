package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpectralEffect implements Listener {

    public static void registerListener() {
        if(Config.getConfig().getBoolean("Features.RemoveSpectralEffect")) {
            Bukkit.getPluginManager().registerEvents(new SpectralEffect(), Aero.getInstance());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(!e.getPlayer().getActivePotionEffects().isEmpty()) {
            for(PotionEffect p : e.getPlayer().getActivePotionEffects()) {
                if(p.getType() == PotionEffectType.GLOWING) {
                    e.getPlayer().getActivePotionEffects().clear();
                    e.getPlayer().updateInventory();
                }
            }
        }
    }

}
