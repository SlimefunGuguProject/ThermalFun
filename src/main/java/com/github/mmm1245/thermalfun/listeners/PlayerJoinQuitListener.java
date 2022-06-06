package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        ThermalFunMain.getHeatStorage().loadPlayer(event.getPlayer());
    }
    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        ThermalFunMain.getHeatStorage().savePlayer(event.getPlayer());
    }
}
