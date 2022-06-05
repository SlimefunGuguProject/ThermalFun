package com.github.mmm1245.thermalfun;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ThermalWand extends SlimefunItem {
    public ThermalWand(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(((ItemUseHandler)ThermalWand::onRightClick));
    }

    public static void onRightClick(PlayerRightClickEvent event){
        if(!event.getPlayer().isSneaking())
            return;

        ItemStack is = event.getPlayer().getInventory().getItemInOffHand();
        if(is == null)
            return;

        if(ThermalFunMain.getItemManager().FIRE_UPGRADE.isItem(is) && ThermalFunMain.getItemManager().FIRE_UPGRADE.isItemFinished(is)){
            event.getPlayer().getInventory().setItemInOffHand(null);
            ItemMeta meta = event.getItem().getItemMeta();
            List<String> lore = meta.hasLore()?meta.getLore():new ArrayList<>();
            lore.add("Fire Upgrade");
            meta.setLore(lore);
            event.getItem().setItemMeta(meta);
        }
    }
}
