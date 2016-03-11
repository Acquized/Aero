package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RightClickShield implements Listener {

    public static List<Player> currentlyHaveShield = new ArrayList<Player>();

    public static void registerListener() {
        if(Config.getConfig().getBoolean("Features.AddBlockingWithShield")) {
            Bukkit.getPluginManager().registerEvents(new RightClickShield(), Aero.getInstance());
        }
    }

    private static void setShieldInOffHand(Player p) {
        ItemStack shield = new ItemStack(Material.SHIELD);
        shield.getItemMeta().spigot().setUnbreakable(true);
        shield.getItemMeta().addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        p.getInventory().setItemInOffHand(shield);
    }

    private static void clearOffHand(Player p) {
        p.getInventory().setItemInOffHand(null);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) {
            Material m = e.getItem() == null ? Material.AIR : e.getItem().getType();
            if((m == Material.DIAMOND_SWORD) || (m == Material.GOLD_SWORD) || (m == Material.IRON_SWORD) || (m == Material.STONE_SWORD) || (m == Material.WOOD_SWORD)) {
                if(!currentlyHaveShield.contains(p)) {
                    currentlyHaveShield.add(p);
                    setShieldInOffHand(p);
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(!p.isBlocking()) {
            clearOffHand(p);
            currentlyHaveShield.remove(p);
        }
    }

}
