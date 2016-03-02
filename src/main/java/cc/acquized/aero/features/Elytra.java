package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class Elytra implements Listener {

    public static void registerListener() {
        Bukkit.getPluginManager().registerEvents(new Elytra(), Aero.getInstance());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(!Config.getConfig().getBoolean("Features.Elytra.InInventory")) {
            if(p.getInventory().contains(Material.ELYTRA)) {
                p.getInventory().remove(Material.ELYTRA);
            }
        }
        if(!Config.getConfig().getBoolean("Features.Elytra.Flying")) {
            if((p.getInventory().getChestplate() != null) && (p.getInventory().getChestplate().getType() == Material.ELYTRA)) {
                p.getInventory().setChestplate(null);
                if(Config.getConfig().getBoolean("Features.Elytra.InInventory")) {
                    p.getInventory().addItem(new ItemStack(Material.ELYTRA));
                }
            }
        }
    }

}
