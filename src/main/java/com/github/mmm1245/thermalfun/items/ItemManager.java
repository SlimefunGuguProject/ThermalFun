package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.EAbility;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class ItemManager {
    public final ItemGroup ITEM_GROUP = new ItemGroup(ThermalFunMain.createKey("thermalfun_category"), new CustomItemStack(Material.BLAZE_ROD, "&cThermalFun"));

    public final SlimefunItem INACTIVE_THERMAL_WAND = new SlimefunItem(ITEM_GROUP, ThermalFunStacks.INACTIVE_THERMAL_WAND, RecipeType.MAGIC_WORKBENCH, ThermalFunRecipes.INACTIVE_THERMAL_WAND);
    public final SlimefunItem THERMAL_WAND = new ThermalWand(ITEM_GROUP, ThermalFunStacks.THERMAL_WAND, ThermalFunRecipes.TYPE_KILL_BLAZE, new ItemStack[9]);
    public final CountUpgradeItem FIRE_UPGRADE = new CountUpgradeItem(EAbility.FIRE, ITEM_GROUP, "FIRE_UPGRADE", Material.SKULL_BANNER_PATTERN, "&cFire Upgrade", new CountUpgradeItem.EntityKillStat(ThermalFunMain.getKeys().STORED_BLAZES, 30, EntityType.BLAZE), new CountUpgradeItem.EntityKillStat(ThermalFunMain.getKeys().STORED_MAGMA_SLIMES, 10, EntityType.MAGMA_CUBE));
    public final CountUpgradeItem LAVA_UPGRADE = new CountUpgradeItem(EAbility.LAVA, ITEM_GROUP, "LAVA_UPGRADE", Material.SKULL_BANNER_PATTERN, "&cLava Upgrade", new CountUpgradeItem.EntityKillStat(ThermalFunMain.getKeys().STORED_MAGMA_SLIMES, 50, EntityType.MAGMA_CUBE));
    public final CountUpgradeItem FIREBALL_UPGRADE = new CountUpgradeItem(EAbility.FIREBALL, ITEM_GROUP, "FIREBALL_UPGRADE", Material.SKULL_BANNER_PATTERN, "&FireBall Upgrade", new CountUpgradeItem.EntityKillStat(ThermalFunMain.getKeys().STORED_GHASTS, 10, EntityType.GHAST), new CountUpgradeItem.EntityKillStat(ThermalFunMain.getKeys().STORED_BLAZES, 20, EntityType.BLAZE));
    public final CountUpgradeItem FIRE_RES_UPGRADE = new CountUpgradeItem(EAbility.FIRE_RES, ITEM_GROUP, "FIRE_RES_UPGRADE", Material.SKULL_BANNER_PATTERN, "&Fire Resistance Upgrade", new CountUpgradeItem.EntityKillStat(ThermalFunMain.getKeys().STORED_STRIDERS, 15, EntityType.STRIDER));
    public final HeatAddingItem BLAZING_SOUP = new HeatAddingItem(250, ITEM_GROUP, ThermalFunStacks.BLAZING_SOUP, RecipeType.MAGIC_WORKBENCH, ThermalFunRecipes.BLAZING_SOUP);
    public final HeatAddingItem BLAZING_APPLE = new HeatAddingItem(50, ITEM_GROUP, ThermalFunStacks.BLAZING_APPLE, RecipeType.MAGIC_WORKBENCH, ThermalFunRecipes.BLAZING_APPLE);

    public void register(SlimefunAddon addon) {
        INACTIVE_THERMAL_WAND.register(addon);
        THERMAL_WAND.register(addon);
        FIRE_UPGRADE.register(addon);
        LAVA_UPGRADE.register(addon);
        FIREBALL_UPGRADE.register(addon);
        FIRE_RES_UPGRADE.register(addon);
        BLAZING_SOUP.register(addon);
        BLAZING_APPLE.register(addon);
    }
}
