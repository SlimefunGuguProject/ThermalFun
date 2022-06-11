package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.abilities.Ability;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class ThermalWand extends SlimefunItem {
    private final HashSet<Player> cooldowns;

    public ThermalWand(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.cooldowns = new HashSet<>();
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) event -> {
            if (event.getPlayer().isSneaking()) {
                ThermalFunMain.getAbilityStorage().forPlayer(event.getPlayer()).next();
            } else {
                if (cooldowns.contains(event.getPlayer()))
                    return;
                addCooldown(event.getPlayer());

                Ability ability = ThermalFunMain.getAbilityStorage().forPlayer(event.getPlayer()).getCurrent();
                if(ability != null && ability.useHandler() != null)
                    ability.useHandler().onRightClick(event);
            }
        });
        addItemHandler((EntityInteractHandler) (event, item, offHand) -> {
            if (event.getPlayer().isSneaking()) {
                ThermalFunMain.getAbilityStorage().forPlayer(event.getPlayer()).next();
            } else {
                if (cooldowns.contains(event.getPlayer()))
                    return;
                addCooldown(event.getPlayer());

                Ability ability = ThermalFunMain.getAbilityStorage().forPlayer(event.getPlayer()).getCurrent();
                if(ability != null && ability.entityInteractHandler() != null)
                    ability.entityInteractHandler().onInteract(event, item, offHand);
            }
        });
    }

    private void addCooldown(Player player){
        if(cooldowns.contains(player))
            return;
        cooldowns.add(player);
        ThermalFunMain.getInstance().getServer().getScheduler().runTaskLater(ThermalFunMain.getInstance(), () -> cooldowns.remove(player),5);
    }
}
