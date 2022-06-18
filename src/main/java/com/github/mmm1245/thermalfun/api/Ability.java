package com.github.mmm1245.thermalfun.api;

import com.github.mmm1245.thermalfun.PlayerHeatStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public record Ability(NamespacedKey key, String userFriendlyName, int cost, ItemUseHandler useHandler,
                      EntityInteractHandler entityInteractHandler) {
    public void register() {
        ThermalFunMain.getAbilityRegistery().register(this);
    }

    public static boolean checkHasHeatAndDecrease(Player player, int heat) {
        PlayerHeatStorage.HeatValues heatVals = ThermalFunMain.getHeatStorage().forPlayer(player);
        if (heatVals.getCurrent() < heat) {
            player.sendMessage(ChatColor.YELLOW + "您没有足够生命值, 您目前仅有" + heatVals.getCurrent() + "/" + heat);
            player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1, 1);
            return false;
        }
        heatVals.increaseCurrent(-heat);
        return true;
    }
}
