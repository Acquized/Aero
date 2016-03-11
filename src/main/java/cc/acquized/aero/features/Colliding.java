package cc.acquized.aero.features;

import cc.acquized.aero.Aero;
import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Colliding implements Listener {

    public static void registerListener() {
        if(Config.getConfig().getBoolean("Features.UserColliding")) {
            Bukkit.getPluginManager().registerEvents(new Colliding(), Aero.getInstance());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e) {
        addToTeam(e.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent e) {
        removeFromTeam(e.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void onKick(PlayerKickEvent e) {
        removeFromTeam(e.getPlayer());
    }

    private void addToTeam(Player p) {
        Scoreboard s = p.getScoreboard() != null ? p.getScoreboard() : Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = s.getTeam("AeroCollide");
        if(t == null) {
            s.registerNewTeam("AeroCollide");
            t = s.getTeam("AeroCollide");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard teams options AeroCollide collisionRule never");
        }
        t.addEntry(p.getName());
        p.setScoreboard(s);
    }

    private void removeFromTeam(Player p) {
        Scoreboard s = p.getScoreboard() != null ? p.getScoreboard() : Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = s.getTeam("AeroCollide");
        t.removeEntry(p.getName());
    }

}
