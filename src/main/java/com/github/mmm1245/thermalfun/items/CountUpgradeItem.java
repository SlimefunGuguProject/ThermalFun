package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.EAbility;
import com.github.mmm1245.thermalfun.PlayerAbilityStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountUpgradeItem extends SlimefunItem {
    private final List<CountableStat> stats;
    private final EAbility unlocks;

    public CountUpgradeItem(EAbility unlocks, ItemGroup itemGroup, String id, Material material, String name, CountableStat... stats) {
        super(itemGroup, new SlimefunItemStack(id, material, name, Arrays.stream(stats).map(countableStat -> CountUpgradableItemUtil.getLoreForCountable(countableStat.name, countableStat.max)).toArray(String[]::new)), ThermalFunRecipes.TYPE_FORTRESS_LOOTTABLE, new ItemStack[9]);
        this.stats = Arrays.asList(stats);
        for (int i = 0; i < stats.length; i++)
            this.stats.get(i).index = i;
        this.unlocks = unlocks;
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) e -> {
            if (!isItemFinished(e.getItem()))
                return;
            PlayerAbilityStorage.AbilitiesList abilitiesList = ThermalFunMain.getAbilityStorage().forPlayer(e.getPlayer());
            if (abilitiesList.learn(unlocks))
                e.getPlayer().getInventory().setItem(e.getHand(), null);
        });
    }


    public void onEntityKill(ItemStack is, EntityType entityType) {
        if (!isItem(is))
            return;
        for (CountableStat stat : stats) {
            if (stat instanceof EntityKillStat entityKillStat) {
                if (entityKillStat.entityType == entityType) {
                    CountUpgradableItemUtil.updateCount(is, entityKillStat.storageKey, entityKillStat.name, entityKillStat.max, entityKillStat.index);
                }
            }
        }
    }

    public boolean isItemFinished(ItemStack stack) {
        return CountUpgradableItemUtil.getTotalFinished(stack) >= stats.size();
    }

    public static class CountableStat {
        public final NamespacedKey storageKey;
        public final String name;
        public final int max;
        protected int index;

        public CountableStat(NamespacedKey storageKey, String name, int max) {
            this.storageKey = storageKey;
            this.name = name;
            this.max = max;
        }
    }

    public static class EntityKillStat extends CountableStat {
        public final EntityType entityType;

        public EntityKillStat(NamespacedKey storageKey, int max, EntityType entityType) {
            super(storageKey, WordUtils.capitalize(entityType.toString().replace('_', ' ')), max);
            this.entityType = entityType;
        }
    }
}
