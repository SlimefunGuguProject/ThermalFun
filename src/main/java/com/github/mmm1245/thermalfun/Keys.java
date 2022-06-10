package com.github.mmm1245.thermalfun;

import org.bukkit.NamespacedKey;

public class Keys {
    public final NamespacedKey STORED_TOTAL;
    public final NamespacedKey HEAT_MAX;
    public final NamespacedKey HEAT_CURRENT;
    public final NamespacedKey FIREBALL_CUSTOM;
    public final NamespacedKey PLAYER_ABILITIES;

    public Keys(ThermalFunMain plugin) {
        STORED_TOTAL = plugin.createKey("stored_total");
        HEAT_MAX = plugin.createKey("heat_max");
        HEAT_CURRENT = plugin.createKey("heat_current");
        FIREBALL_CUSTOM = plugin.createKey("fireball_custom");
        PLAYER_ABILITIES = plugin.createKey("player_abilities");
    }
}
