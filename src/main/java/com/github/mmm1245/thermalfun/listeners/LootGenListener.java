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
            if(ThermalFunMain.getRandom().nextBoolean())
                event.getLoot().add(ThermalFunMain.getItemManager().FIRE_UPGRADE.getItem());
        }
    }
}
