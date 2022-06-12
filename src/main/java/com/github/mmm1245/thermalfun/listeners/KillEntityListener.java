package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.api.CountUpgradeItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

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
        SlimefunItem sfItem = SlimefunItem.getByItem(is);
        if(sfItem instanceof CountUpgradeItem cntUpgrItem){
            cntUpgrItem.onEntityKill(is, event.getEntityType());
        }
    }
}
