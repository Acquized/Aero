package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public class BrewingRod implements Listener {

    public static void registerListener() {
        if(Config.getConfig().getBoolean("Features.BrewingStandPowder")) {
            Bukkit.getPluginManager().registerEvents(new BrewingRod(), Aero.getInstance());
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        if(e.getInventory().getType() == InventoryType.BREWING) {
            ((BrewerInventory)e.getInventory()).setFuel(new ItemStack(Material.BLAZE_POWDER, 64));
        }
    }

    @EventHandler
    public void onBrew(BrewEvent e) {
        e.getContents().setFuel(new ItemStack(Material.BLAZE_POWDER, 64));
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getCurrentItem().getType() == Material.BLAZE_POWDER) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if((e.getInventory().getType() == InventoryType.BREWING) && (e.getInventory().contains(Material.BLAZE_POWDER))) {
            e.getInventory().remove(Material.BLAZE_POWDER);
        }
    }

}
