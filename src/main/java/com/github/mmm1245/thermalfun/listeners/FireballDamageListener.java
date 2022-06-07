package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataType;

public class FireballDamageListener implements Listener {
    @EventHandler
    public void onFireballDamage(EntityDamageByEntityEvent event){
        if(event.getDamager().getType()== EntityType.FIREBALL&&event.getDamager().getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().FIREBALL_CUSTOM, PersistentDataType.BYTE, ((byte)0)) > 0){
            event.setDamage(7);
        }
    }
}
