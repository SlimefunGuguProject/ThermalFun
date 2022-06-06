package com.github.mmm1245.thermalfun.items;

import com.github.mmm1245.thermalfun.PlayerHeatStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThermalWand extends SlimefunItem {
    public ThermalWand(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(((ItemUseHandler)ThermalWand::onRightClick));
        addItemHandler(((EntityInteractHandler)ThermalWand::onInteract));
    }

    public static void onInteract(PlayerInteractEntityEvent event, ItemStack item, boolean offHand){
        if(event.getPlayer().isSneaking()){

        } else {
            if(offHand)
                return;
            if(item.getItemMeta().getLore().contains("Fire Upgrade")){
                if(event.getRightClicked().getFireTicks() > 0)
                    return;
                PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(event.getPlayer());
                if(heatVals.getCurrent() < 10){
                    event.getPlayer().sendMessage("Not enough heat, you have " + heatVals.getCurrent() + "/10");
                    return;
                }
                heatVals.increaseCurrent(-10);
                event.getRightClicked().setFireTicks(240);
            }
        }
    }

    public static void onRightClick(PlayerRightClickEvent event){
        if(event.getPlayer().isSneaking()) {
            ItemStack is = event.getPlayer().getInventory().getItemInOffHand();
            if (is == null)
                return;

            if (ThermalFunMain.getItemManager().FIRE_UPGRADE.isItem(is) && ThermalFunMain.getItemManager().FIRE_UPGRADE.isItemFinished(is)) {
                event.getPlayer().getInventory().setItemInOffHand(null);
                ItemMeta meta = event.getItem().getItemMeta();
                List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                lore.add("Fire Upgrade");
                meta.setLore(lore);
                event.getItem().setItemMeta(meta);
            }
            if (ThermalFunMain.getItemManager().LAVA_UPGRADE.isItem(is) && ThermalFunMain.getItemManager().LAVA_UPGRADE.isItemFinished(is)) {
                event.getPlayer().getInventory().setItemInOffHand(null);
                ItemMeta meta = event.getItem().getItemMeta();
                List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                lore.add("Lava Upgrade");
                meta.setLore(lore);
                event.getItem().setItemMeta(meta);
            }
            if (ThermalFunMain.getItemManager().FIREBALL_UPGRADE.isItem(is) && ThermalFunMain.getItemManager().FIREBALL_UPGRADE.isItemFinished(is)) {
                event.getPlayer().getInventory().setItemInOffHand(null);
                ItemMeta meta = event.getItem().getItemMeta();
                List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                lore.add("FireBall Upgrade");
                meta.setLore(lore);
                event.getItem().setItemMeta(meta);
            }
            if (ThermalFunMain.getItemManager().FIRE_RES_UPGRADE.isItem(is) && ThermalFunMain.getItemManager().FIRE_RES_UPGRADE.isItemFinished(is)) {
                event.getPlayer().getInventory().setItemInOffHand(null);
                ItemMeta meta = event.getItem().getItemMeta();
                List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                lore.add("Fire Resistance Upgrade");
                meta.setLore(lore);
                event.getItem().setItemMeta(meta);
            }
        } else {
            if(event.getItem().getItemMeta().getLore().contains("Fire Upgrade")){
                Optional<Block> clicked = event.getClickedBlock();
                if(clicked.isPresent()) {
                    Block target = clicked.get().getRelative(event.getClickedFace());
                    if(target.isEmpty()&&target.getRelative(0, -1, 0).getType().isSolid()){
                        PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(event.getPlayer());
                        if(heatVals.getCurrent() < 10){
                            event.getPlayer().sendMessage("Not enough heat, you have " + heatVals.getCurrent() + "/10");
                            return;
                        }
                        heatVals.increaseCurrent(-10);
                        target.setType(Material.FIRE);
                    }
                }
            }
            if(event.getItem().getItemMeta().getLore().contains("Lava Upgrade")){
                Optional<Block> clicked = event.getClickedBlock();
                if(clicked.isPresent()) {
                    Block target = clicked.get().getRelative(event.getClickedFace());
                    if(target.isEmpty()){
                        PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(event.getPlayer());
                        if(heatVals.getCurrent() < 25){
                            event.getPlayer().sendMessage("Not enough heat, you have " + heatVals.getCurrent() + "/25");
                            return;
                        }
                        heatVals.increaseCurrent(-25);
                        target.setType(Material.LAVA);
                    }
                }
            }
            if(event.getItem().getItemMeta().getLore().contains("FireBall Upgrade")){
                PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(event.getPlayer());
                if(heatVals.getCurrent() < 30){
                    event.getPlayer().sendMessage("Not enough heat, you have " + heatVals.getCurrent() + "/30");
                    return;
                }
                heatVals.increaseCurrent(-30);
                Fireball fireball = (Fireball) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getEyeLocation(), EntityType.FIREBALL);
                fireball.setYield(3);
                fireball.setIsIncendiary(false);
                fireball.setShooter(event.getPlayer());
                fireball.setDirection(event.getPlayer().getLocation().getDirection());
                fireball.getPersistentDataContainer().set(ThermalFunMain.getKeys().FIREBALL_CUSTOM, PersistentDataType.BYTE, ((byte)1));
            }
            if(event.getItem().getItemMeta().getLore().contains("Fire Resistance Upgrade")){
                PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(event.getPlayer());
                if(heatVals.getCurrent() < 5){
                    event.getPlayer().sendMessage("Not enough heat, you have " + heatVals.getCurrent() + "/5");
                    return;
                }
                heatVals.increaseCurrent(-5);
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 4*60*20, 0));
            }
        }
    }
}
