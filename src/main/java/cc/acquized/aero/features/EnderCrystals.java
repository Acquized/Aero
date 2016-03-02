package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class EnderCrystals implements Listener {

    public static void registerListener() {
        Bukkit.getPluginManager().registerEvents(new HitCooldown(), Aero.getInstance());
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        if(Config.getConfig().getBoolean("BlockEnderCrystals.Crafting")) {
            if (e.getRecipe().getResult().getType() == Material.END_CRYSTAL) {
                e.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
    }

    @EventHandler
    public void onPlace(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(Config.getConfig().getBoolean("BlockEnderCrystals.Placing")) {
            boolean hand = p.getInventory().getItemInMainHand() != null || p.getInventory().getItemInOffHand() != null;
            if((hand) && (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if((e.getClickedBlock().getType() == Material.OBSIDIAN) || (e.getClickedBlock().getType() == Material.BEDROCK)) {
                    if((p.getInventory().getItemInMainHand().getType() == Material.END_CRYSTAL) || (p.getInventory().getItemInOffHand().getType() == Material.END_CRYSTAL)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

}
