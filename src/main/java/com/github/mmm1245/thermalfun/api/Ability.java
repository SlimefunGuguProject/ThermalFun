package com.github.mmm1245.thermalfun.api;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.NamespacedKey;

public record Ability(NamespacedKey key, String userFriendlyName, int cost, ItemUseHandler useHandler,
                      EntityInteractHandler entityInteractHandler) {
    public void register() {
        ThermalFunMain.getAbilityRegistery().register(this);
    }
}
