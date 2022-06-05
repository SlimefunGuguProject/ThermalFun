package com.github.mmm1245.thermalfun;

import org.bukkit.NamespacedKey;

public class Keys {
    public final NamespacedKey STORED_BLAZES;
    public final NamespacedKey STORED_MAGMA_SLIMES;
    public final NamespacedKey STORED_TOTAL;
    public final NamespacedKey HEAT_MAX;
    public final NamespacedKey HEAT_CURRENT;
    public Keys(ThermalFunMain plugin) {
        STORED_BLAZES = plugin.createKey("stored_blazes");
        STORED_MAGMA_SLIMES = plugin.createKey("stored_magma_slimes");
        STORED_TOTAL = plugin.createKey("stored_total");
        HEAT_MAX = plugin.createKey("heat_max");
        HEAT_CURRENT = plugin.createKey("heat_current");
    }
}
