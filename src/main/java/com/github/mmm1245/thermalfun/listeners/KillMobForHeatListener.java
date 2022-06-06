package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillMobForHeatListener implements Listener {
    @EventHandler
    public void onMobKill(EntityDeathEvent event){
        Player attacker = event.getEntity().getKiller();
        if(attacker==null)
            return;
        if(event.getEntityType() == EntityType.BLAZE || event.getEntityType() == EntityType.MAGMA_CUBE){
            ThermalFunMain.getHeatStorage().forPlayer(attacker).increaseMax(1);
            ThermalFunMain.getHeatStorage().forPlayer(attacker).increaseCurrent(1);
        }
    }
}
