package com.github.mmm1245.thermalfun;

import org.bukkit.NamespacedKey;

public class Keys {
    public final NamespacedKey STORED_BLAZES;
    public final NamespacedKey STORED_MAGMA_SLIMES;
    public final NamespacedKey STORED_GHASTS;
    public final NamespacedKey STORED_STRIDERS;
    public final NamespacedKey STORED_TOTAL;
    public final NamespacedKey HEAT_MAX;
    public final NamespacedKey HEAT_CURRENT;
    public final NamespacedKey FIREBALL_CUSTOM;
    public final NamespacedKey PLAYER_ABILITIES;

    public Keys(ThermalFunMain plugin) {
        STORED_BLAZES = plugin.createKey("stored_blazes");
        STORED_MAGMA_SLIMES = plugin.createKey("stored_magma_slimes");
        STORED_GHASTS = plugin.createKey("stored_ghasts");
        STORED_STRIDERS = plugin.createKey("stored_striders");
        STORED_TOTAL = plugin.createKey("stored_total");
        HEAT_MAX = plugin.createKey("heat_max");
        HEAT_CURRENT = plugin.createKey("heat_current");
        FIREBALL_CUSTOM = plugin.createKey("fireball_custom");
        PLAYER_ABILITIES = plugin.createKey("player_abilities");
    }
}
