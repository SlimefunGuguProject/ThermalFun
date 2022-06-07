package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.EAbility;
import com.github.mmm1245.thermalfun.PlayerHeatStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class ThermalWand extends SlimefunItem {
    private HashSet<Player> cooldowns;

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

                EAbility ability = ThermalFunMain.getAbilityStorage().forPlayer(event.getPlayer()).getCurrent();
                if (ability == EAbility.FIRE) {
                    Optional<Block> clicked = event.getClickedBlock();
                    if (clicked.isPresent()) {
                        Block target = clicked.get().getRelative(event.getClickedFace());
                        if (target.isEmpty() && target.getRelative(0, -1, 0).getType().isSolid()) {
                            if (checkHasHeatAndDecrease(event.getPlayer(), 10))
                                target.setType(Material.FIRE);
                        }
                    }
                }
                if (ability == EAbility.LAVA) {
                    Optional<Block> clicked = event.getClickedBlock();
                    if (clicked.isPresent()) {
                        Block target = clicked.get().getRelative(event.getClickedFace());
                        if (target.isEmpty()) {
                            if (checkHasHeatAndDecrease(event.getPlayer(), 25))
                                target.setType(Material.LAVA);
                        }
                    }
                }
                if (ability == EAbility.FIREBALL) {
                    if (checkHasHeatAndDecrease(event.getPlayer(), 25)) {
                        Fireball fireball = (Fireball) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getEyeLocation(), EntityType.FIREBALL);
                        fireball.setYield(3);
                        fireball.setIsIncendiary(false);
                        fireball.setShooter(event.getPlayer());
                        fireball.setDirection(event.getPlayer().getLocation().getDirection());
                        fireball.getPersistentDataContainer().set(ThermalFunMain.getKeys().FIREBALL_CUSTOM, PersistentDataType.BYTE, ((byte) 1));
                    }
                }
                if (ability == EAbility.FIRE_RES) {
                    if (checkHasHeatAndDecrease(event.getPlayer(), 5))
                        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 4 * 60 * 20, 0));
                }
            }
        });
        addItemHandler((EntityInteractHandler) (event, item, offHand) -> {
            if (event.getPlayer().isSneaking()) {
                ThermalFunMain.getAbilityStorage().forPlayer(event.getPlayer()).next();
            } else {
                if (cooldowns.contains(event.getPlayer()))
                    return;
                addCooldown(event.getPlayer());

                EAbility ability = ThermalFunMain.getAbilityStorage().forPlayer(event.getPlayer()).getCurrent();
                if (ability == EAbility.FIRE) {
                    if (event.getRightClicked().getFireTicks() > 120)
                        return;
                    if (checkHasHeatAndDecrease(event.getPlayer(), 10))
                        event.getRightClicked().setFireTicks(240);
                }
                if (ability == EAbility.LAVA) {
                    if (event.getRightClicked() instanceof Slime slime) {
                        if (checkHasHeatAndDecrease(event.getPlayer(), 25)) {
                            MagmaCube magmaCube = (MagmaCube) slime.getWorld().spawnEntity(slime.getLocation(), EntityType.MAGMA_CUBE);
                            magmaCube.setSize(slime.getSize());
                            magmaCube.setHealth(slime.getHealth());
                            slime.remove();
                        }
                    }
                }
                if (ability == EAbility.FIRE_RES) {
                    if (event.getRightClicked() instanceof LivingEntity livingEntity) {
                        if (checkHasHeatAndDecrease(event.getPlayer(), 5))
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 4 * 60 * 20, 0));
                    }
                }
            }
        });
    }

    private void addCooldown(Player player){
        if(cooldowns.contains(player))
            return;
        cooldowns.add(player);
        ThermalFunMain.getInstance().getServer().getScheduler().runTaskLater(ThermalFunMain.getInstance(), () -> cooldowns.remove(player),5);
    }

    private static boolean checkHasHeatAndDecrease(Player player, int heat) {
        PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(player);
        if (heatVals.getCurrent() < heat) {
            player.sendMessage(ChatColor.YELLOW + "Not enough heat, you have " + heatVals.getCurrent() + "/" + heat);
            return false;
        }
        heatVals.increaseCurrent(-heat);
        return true;
    }
}
