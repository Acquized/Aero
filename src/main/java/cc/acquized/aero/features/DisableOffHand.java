package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class DisableOffHand implements Listener {

    public static void registerListener() {
        if(Config.getConfig().getBoolean("Features.DisableOffHandSlot")) {
            Bukkit.getPluginManager().registerEvents(new DisableOffHand(), Aero.getInstance());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getSlot() == -106) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if((p.getInventory().getItemInOffHand() != null) && (p.getInventory().getItemInOffHand().getType() != Material.AIR)) {
            if(!((Config.getConfig().getBoolean("Features.AddBlockingWithShield")) && (RightClickShield.currentlyHaveShield.contains(p)) && (p.getInventory().getItemInOffHand().getType() == Material.SHIELD))) {
                p.getWorld().dropItem(p.getLocation(), p.getInventory().getItemInOffHand());
                p.getInventory().setItemInOffHand(null);
            }
        }
        /*if((e.getPlayer().getInventory().getItemInOffHand() != null) && ((e.getPlayer().getInventory().getItemInOffHand().getType() != Material.SHIELD)
          && (RightClickShield.currentlyHaveShield.contains(e.getPlayer())))) {
            e.getPlayer().getInventory().addItem(e.getPlayer().getInventory().getItemInOffHand().clone());
            e.getPlayer().getInventory().setItemInOffHand(null);
        }*/
    }

}
