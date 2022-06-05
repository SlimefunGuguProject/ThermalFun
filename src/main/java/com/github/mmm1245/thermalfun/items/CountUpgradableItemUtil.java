package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class CountUpgradableItemUtil {
    public static void updateCount(ItemStack is, NamespacedKey valKey, String name, int max, int loreIndex){
        ItemMeta meta = is.getItemMeta();
        int count = meta.getPersistentDataContainer().getOrDefault(valKey, PersistentDataType.INTEGER, 0);

        count++;
        if(count == max){
            int total = meta.getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().STORED_TOTAL, PersistentDataType.INTEGER, 0);
            total++;
            meta.getPersistentDataContainer().set(ThermalFunMain.getKeys().STORED_TOTAL, PersistentDataType.INTEGER, total);
        }
        if(count > max)
            count = max;

        if(meta.hasLore()) {
            List<String> lore = meta.getLore();
            if(lore.size() > loreIndex)
                lore.set(loreIndex, name + ": " + count + "/" + max);
            meta.setLore(lore);
        }

        meta.getPersistentDataContainer().set(valKey, PersistentDataType.INTEGER, count);
        is.setItemMeta(meta);
    }
    public static int getCount(ItemStack is, NamespacedKey valKey){
        return is.getItemMeta().getPersistentDataContainer().getOrDefault(valKey, PersistentDataType.INTEGER, 0);
    }
    public static String getLoreForCountable(String name, int max){
        return name + ": 0/" + max;
    }
    public static int getTotalFinished(ItemStack is){
        return is.getItemMeta().getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().STORED_TOTAL, PersistentDataType.INTEGER, 0);
    }
}
