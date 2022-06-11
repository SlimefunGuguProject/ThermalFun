package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.PlayerAbilityStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.abilities.Ability;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CountUpgradeItem extends SlimefunItem {
    private final List<CountableStat> stats;
    private final Ability unlocks;

    public CountUpgradeItem(Ability unlocks, ItemGroup itemGroup, String id, Material material, String name, CountableStat[] stats) {
        super(itemGroup, new SlimefunItemStack(id, material, name, Arrays.stream(stats).map(countableStat -> getLoreForCountable(countableStat.name, countableStat.max)).toArray(String[]::new)), ThermalFunRecipes.TYPE_FORTRESS_LOOTTABLE, new ItemStack[9]);
        this.stats = Arrays.asList(stats);
        for (int i = 0; i < stats.length; i++) {
            this.stats.get(i).index = i;
        }
        this.unlocks = unlocks;
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) e -> {
            if (!isItemFinished(e.getItem()))
                return;
            PlayerAbilityStorage.AbilitiesList abilitiesList = ThermalFunMain.getAbilityStorage().forPlayer(e.getPlayer());
            if (abilitiesList.learn(unlocks)) {
                e.getPlayer().getInventory().setItem(e.getHand(), null);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
            } else {
                e.getPlayer().sendMessage("You already know this ability");
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1, 1);
            }
        });
    }


    public void onEntityKill(ItemStack is, EntityType entityType) {
        if (!isItem(is))
            return;
        for (CountableStat stat : stats) {
            if (stat instanceof EntityKillStat entityKillStat) {
                if (entityKillStat.entityType == entityType) {
                    updateCount(is, entityKillStat.storageKey, entityKillStat.name, entityKillStat.max, entityKillStat.index);
                }
            }
        }
    }

    public boolean isItemFinished(ItemStack stack) {
        return getTotalFinished(stack) >= stats.size();
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
        public static EntityKillStat fromString(String string){
            String[] strs = string.split(Pattern.quote(":"));
            if(strs.length != 2)
                throw new RuntimeException("Error while parsing config: invalid option: " + string);
            int count;
            try {
                count = Integer.parseInt(strs[1]);
            } catch (NumberFormatException e){
                throw new RuntimeException("Error while parsing config: entity count is not number: " + strs[1]);
            }
            EntityType type = EntityType.valueOf(strs[0]);
            if(type==null)
                throw new RuntimeException("Error while parsing config: entity type not found: " + strs[0]);
            return new EntityKillStat(ThermalFunMain.createKey("stored_" + type.name()), count, type);
        }
    }

    private static void updateCount(ItemStack is, NamespacedKey valKey, String name, int max, int loreIndex){
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
    private static String getLoreForCountable(String name, int max){
        return name + ": 0/" + max;
    }
    private static int getTotalFinished(ItemStack is){
        return is.getItemMeta().getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().STORED_TOTAL, PersistentDataType.INTEGER, 0);
    }
}
