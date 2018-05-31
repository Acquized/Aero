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
		if (e.getInventory().getType() == InventoryType.BREWING) {
			((BrewerInventory) e.getInventory()).getHolder().setFuelLevel(20);
		}
	}

	@EventHandler
	public void onBrew(BrewEvent e) {
		e.getContents().getHolder().setFuelLevel(20);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getInventory().getType() == InventoryType.BREWING) {
			((BrewerInventory) e.getInventory()).getHolder().setFuelLevel(20);
		}
	}
}
