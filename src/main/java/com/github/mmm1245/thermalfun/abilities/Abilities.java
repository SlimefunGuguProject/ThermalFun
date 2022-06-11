package com.github.mmm1245.thermalfun.abilities;

import com.github.mmm1245.thermalfun.PlayerHeatStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class Abilities {
    public final Ability FIRE_ABILITY;
    public final Ability LAVA_ABILITY;
    public final Ability FIREBALL_ABILITY;
    public final Ability FIRE_RES_ABILITY;
    public Abilities(){
        FIRE_ABILITY = new Ability(
                ThermalFunMain.createKey("fire_upgrade"),
                "Fire Upgrade",
                10,
                event -> {
                    Optional<Block> clicked = event.getClickedBlock();
                    if (clicked.isPresent()) {
                        Block target = clicked.get().getRelative(event.getClickedFace());
                        if (target.isEmpty() && target.getRelative(0, -1, 0).getType().isSolid()) {
                            if (checkHasHeatAndDecrease(event.getPlayer(), 10)) {
                                target.setType(Material.FIRE);
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1);
                            }
                        }
                    }
                },
                (event, item, offHand) -> {
                    if (event.getRightClicked().getFireTicks() > 120)
                        return;
                    if (checkHasHeatAndDecrease(event.getPlayer(), 10)) {
                        event.getRightClicked().setFireTicks(240);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1);
                    }
                }
        );

        LAVA_ABILITY = new Ability(
                ThermalFunMain.createKey("lava_upgrade"),
                "Lava Upgrade",
                25,
                event -> {
                    Optional<Block> clicked = event.getClickedBlock();
                    if (clicked.isPresent()) {
                        Block target = clicked.get().getRelative(event.getClickedFace());
                        if (target.isEmpty()) {
                            if (checkHasHeatAndDecrease(event.getPlayer(), 25)) {
                                target.setType(Material.LAVA);
                                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BUCKET_EMPTY_LAVA, 1, 1);
                            }
                        }
                    }
                },
                (event, item, offHand) -> {
                    if (event.getRightClicked() instanceof Slime slime) {
                        if (checkHasHeatAndDecrease(event.getPlayer(), 25)) {
                            MagmaCube magmaCube = (MagmaCube) slime.getWorld().spawnEntity(slime.getLocation(), EntityType.MAGMA_CUBE);
                            magmaCube.setSize(slime.getSize());
                            magmaCube.setHealth(slime.getHealth());
                            slime.remove();
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_TRIDENT_THUNDER, 1, 1);
                        }
                    }
                }
        );

        FIREBALL_ABILITY = new Ability(
                ThermalFunMain.createKey("fireball_upgrade"),
                "Fireball Upgrade",
                30,
                event -> {
                    if (checkHasHeatAndDecrease(event.getPlayer(), 30)) {
                        Fireball fireball = (Fireball) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getEyeLocation(), EntityType.FIREBALL);
                        fireball.setYield(3);
                        fireball.setIsIncendiary(false);
                        fireball.setShooter(event.getPlayer());
                        fireball.setDirection(event.getPlayer().getLocation().getDirection());
                        fireball.getPersistentDataContainer().set(ThermalFunMain.getKeys().FIREBALL_CUSTOM, PersistentDataType.BYTE, ((byte) 1));
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST_FAR, 1, 1);
                    }
                },
                null
        );

        FIRE_RES_ABILITY = new Ability(
                ThermalFunMain.createKey("fire_res_upgrade"),
                "Fire Resistance Upgrade",
                5,
                event -> {
                    if (checkHasHeatAndDecrease(event.getPlayer(), 5)) {
                        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 4 * 60 * 20, 0));
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BOTTLE_EMPTY, 1, 1);
                    }
                },
                (event, item, offHand) -> {
                    if (event.getRightClicked() instanceof LivingEntity livingEntity) {
                        if (checkHasHeatAndDecrease(event.getPlayer(), 5)) {
                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 4 * 60 * 20, 0));
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BOTTLE_EMPTY, 1, 1);
                        }
                    }
                }
        );
    }
    public void register(){
        FIRE_ABILITY.register();
        LAVA_ABILITY.register();
        FIREBALL_ABILITY.register();
        FIRE_RES_ABILITY.register();
    }

    public static boolean checkHasHeatAndDecrease(Player player, int heat) {
        PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(player);
        if (heatVals.getCurrent() < heat) {
            player.sendMessage(ChatColor.YELLOW + "Not enough heat, you have " + heatVals.getCurrent() + "/" + heat);
            player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1, 1);
            return false;
        }
        heatVals.increaseCurrent(-heat);
        return true;
    }
}
