package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLoginQuitListener implements Listener {
    @EventHandler
    public void playerLogin(PlayerLoginEvent event){
        ThermalFunMain.getHeatStorage().loadPlayer(event.getPlayer());
    }
    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        ThermalFunMain.getHeatStorage().savePlayer(event.getPlayer());
    }
}
