package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        onJoin(event.getPlayer());
    }
    public static void onJoin(Player player){
        ThermalFunMain.getHeatStorage().loadPlayer(player);
        ThermalFunMain.getAbilityStorage().loadPlayer(player);
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        onQuit(event.getPlayer());
    }
    public static void onQuit(Player player){
        ThermalFunMain.getHeatStorage().savePlayer(player);
        ThermalFunMain.getAbilityStorage().savePlayer(player);
    }
}
