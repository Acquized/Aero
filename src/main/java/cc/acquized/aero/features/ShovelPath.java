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

public class ShovelPath implements Listener {

    public static void registerListener() {
        if(Config.getConfig().getBoolean("Features.DisallowRightClickPath")) {
            Bukkit.getPluginManager().registerEvents(new ShovelPath(), Aero.getInstance());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if((e.getClickedBlock().getType() == Material.GRASS) || (e.getClickedBlock().getType() == Material.DIRT)) {
                e.setCancelled(true);
            }
        }
    }

}
