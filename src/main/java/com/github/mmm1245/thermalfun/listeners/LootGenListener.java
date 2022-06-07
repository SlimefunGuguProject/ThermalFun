package com.github.mmm1245.thermalfun.listeners;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.items.ItemManager;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;

public class LootGenListener implements Listener {
    @EventHandler
    public void generateLoot(LootGenerateEvent event){
        LootTable lootTable = event.getLootTable();
        if(lootTable.getKey().equals(LootTables.NETHER_BRIDGE.getKey())){
            int randomVal = ThermalFunMain.getRandom().nextInt(4);
            if(randomVal==0)
                event.getLoot().add(ThermalFunMain.getItemManager().FIRE_UPGRADE.getItem());
            else if(randomVal==1)
                event.getLoot().add(ThermalFunMain.getItemManager().FIRE_RES_UPGRADE.getItem());
            else if(randomVal==2)
                event.getLoot().add(ThermalFunMain.getItemManager().FIREBALL_UPGRADE.getItem());
            else if(randomVal==3)
                event.getLoot().add(ThermalFunMain.getItemManager().LAVA_UPGRADE.getItem());
        }
    }
}
