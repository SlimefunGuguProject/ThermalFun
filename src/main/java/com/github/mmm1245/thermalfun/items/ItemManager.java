package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.api.CountUpgradeItem;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManager {
    public final ItemGroup ITEM_GROUP = new ItemGroup(ThermalFunMain.createKey("thermalfun_category"), new CustomItemStack(Material.BLAZE_ROD, "&cThermalFun"));

    public final SlimefunItem INACTIVE_THERMAL_WAND;
    public final SlimefunItem THERMAL_WAND;
    public final CountUpgradeItem FIRE_UPGRADE;
    public final CountUpgradeItem LAVA_UPGRADE;
    public final CountUpgradeItem FIREBALL_UPGRADE;
    public final CountUpgradeItem FIRE_RES_UPGRADE;
    public final HeatAddingItem BLAZING_SOUP;
    public final HeatAddingItem BLAZING_APPLE;

    public ItemManager(Config cfg) {
        INACTIVE_THERMAL_WAND = new SlimefunItem(
                ITEM_GROUP,
                ThermalFunStacks.INACTIVE_THERMAL_WAND,
                RecipeType.MAGIC_WORKBENCH,
                ThermalFunRecipes.INACTIVE_THERMAL_WAND
        );

        THERMAL_WAND = new ThermalWand(
                ITEM_GROUP,
                ThermalFunStacks.THERMAL_WAND,
                ThermalFunRecipes.TYPE_KILL_BLAZE,
                ThermalFunRecipes.THERMAL_WAND
        );

        FIRE_UPGRADE = new CountUpgradeItem(
                ThermalFunMain.getAbilities().FIRE_ABILITY,
                ITEM_GROUP,
                "FIRE_UPGRADE",
                Material.SKULL_BANNER_PATTERN,
                ChatColor.RED + "火焰升级",
                ThermalFunRecipes.TYPE_FORTRESS_LOOTTABLE, new ItemStack[9],
                cfg.getStringList("fire_upgrade").stream().map(CountUpgradeItem.EntityKillStat::fromString).toArray(CountUpgradeItem.CountableStat[]::new)
        );

        LAVA_UPGRADE = new CountUpgradeItem(
                ThermalFunMain.getAbilities().LAVA_ABILITY,
                ITEM_GROUP,
                "LAVA_UPGRADE",
                Material.SKULL_BANNER_PATTERN,
                ChatColor.RED + "岩浆升级",
                ThermalFunRecipes.TYPE_FORTRESS_LOOTTABLE, new ItemStack[9],
                cfg.getStringList("lava_upgrade").stream().map(CountUpgradeItem.EntityKillStat::fromString).toArray(CountUpgradeItem.CountableStat[]::new)
        );

        FIREBALL_UPGRADE = new CountUpgradeItem(
                ThermalFunMain.getAbilities().FIREBALL_ABILITY,
                ITEM_GROUP,
                "FIREBALL_UPGRADE",
                Material.SKULL_BANNER_PATTERN,
                ChatColor.RED + "火球升级",
                ThermalFunRecipes.TYPE_FORTRESS_LOOTTABLE, new ItemStack[9],
                cfg.getStringList("fireball_upgrade").stream().map(CountUpgradeItem.EntityKillStat::fromString).toArray(CountUpgradeItem.CountableStat[]::new)
        );

        FIRE_RES_UPGRADE = new CountUpgradeItem(
                ThermalFunMain.getAbilities().FIRE_RES_ABILITY,
                ITEM_GROUP,
                "FIRE_RES_UPGRADE",
                Material.SKULL_BANNER_PATTERN,
                ChatColor.RED + "抗火升级",
                ThermalFunRecipes.TYPE_FORTRESS_LOOTTABLE, new ItemStack[9],
                cfg.getStringList("fire_res_upgrade").stream().map(CountUpgradeItem.EntityKillStat::fromString).toArray(CountUpgradeItem.CountableStat[]::new)
        );
        int blazing_soup_heat = cfg.getInt("blazing_soup_heat");
        BLAZING_SOUP = new HeatAddingItem(
            blazing_soup_heat,
            ITEM_GROUP,
            new SlimefunItemStack("BLAZING_SOUP", Material.SUSPICIOUS_STEW, "&c热汤", "给予你" + blazing_soup_heat + "生命值"),
            RecipeType.MAGIC_WORKBENCH,
            ThermalFunRecipes.BLAZING_SOUP
        );
        int blazing_apple_heat = cfg.getInt("blazing_apple_heat");
        BLAZING_APPLE = new HeatAddingItem(
            blazing_apple_heat,
            ITEM_GROUP,
            new SlimefunItemStack("BLAZING_APPLE", Material.APPLE, "&c烤苹果", "给予你" + blazing_apple_heat + "生命值"),
            RecipeType.MAGIC_WORKBENCH,
            ThermalFunRecipes.BLAZING_APPLE
        );
    }

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
