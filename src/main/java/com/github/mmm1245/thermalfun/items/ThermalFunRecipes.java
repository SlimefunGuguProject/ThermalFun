package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ThermalFunRecipes {
    public static final RecipeType TYPE_KILL_BLAZE = new RecipeType(ThermalFunMain.createKey("kill_blaze"), ThermalFunStacks.KILL_BLAZE_HEAD);
    public static final RecipeType TYPE_FORTRESS_LOOTTABLE = new RecipeType(ThermalFunMain.createKey("loottable"), ThermalFunStacks.FORTRESS_LOOTTABLE_CHEST);


    public static final ItemStack[] INACTIVE_THERMAL_WAND = {
            null, SlimefunItems.CARBONADO, null,
            null, new ItemStack(Material.STICK), null,
            null, new ItemStack(Material.STICK), null
    };
}
