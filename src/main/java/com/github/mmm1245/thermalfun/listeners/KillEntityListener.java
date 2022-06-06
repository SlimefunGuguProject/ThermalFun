package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class KillEntityListener implements Listener {
    @EventHandler
    public void entityDeath(EntityDeathEvent event){
        Player player = event.getEntity().getKiller();
        if(player == null)
            return;

        ItemStack is = player.getInventory().getItemInOffHand();
        if(is == null)
            return;

        if(event.getEntity().getType() == EntityType.BLAZE){
            if(ThermalFunMain.getItemManager().INACTIVE_THERMAL_WAND.isItem(is)){
                player.getInventory().setItemInOffHand(ThermalFunMain.getItemManager().THERMAL_WAND.getItem());
            }
        }
        if(ThermalFunMain.getItemManager().FIRE_UPGRADE.isItem(is)){
            ThermalFunMain.getItemManager().FIRE_UPGRADE.onEntityKill(is, event.getEntityType());
        }
        if(ThermalFunMain.getItemManager().LAVA_UPGRADE.isItem(is)){
            ThermalFunMain.getItemManager().LAVA_UPGRADE.onEntityKill(is, event.getEntityType());
        }
        if(ThermalFunMain.getItemManager().FIREBALL_UPGRADE.isItem(is)){
            ThermalFunMain.getItemManager().FIREBALL_UPGRADE.onEntityKill(is, event.getEntityType());
        }
        if(ThermalFunMain.getItemManager().FIRE_RES_UPGRADE.isItem(is)){
            ThermalFunMain.getItemManager().FIRE_RES_UPGRADE.onEntityKill(is, event.getEntityType());
        }
    }
}
