package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class HeatAddingItem extends SlimefunItem {
    private final int heat;
    public HeatAddingItem(int heat, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.heat = heat;
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemConsumptionHandler) (e, p, item) -> ThermalFunMain.getHeatStorage().forPlayer(p).increaseCurrent(heat));
    }
    public int getHeat() {
        return heat;
    }
}
