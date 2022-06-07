package com.github.mmm1245.thermalfun.commands;

import org.bukkit.plugin.java.JavaPlugin;

public class Commands {
    public final HeatCommand HEAT = new HeatCommand();
    public final ThermalAbilitiesCommand THERMAL_ABILITIES = new ThermalAbilitiesCommand();

    public void register(JavaPlugin plugin){
        plugin.getCommand("heat").setExecutor(HEAT);
        plugin.getCommand("heat").setTabCompleter(HEAT);

        plugin.getCommand("thermalAbilities").setExecutor(THERMAL_ABILITIES);
        plugin.getCommand("thermalAbilities").setTabCompleter(THERMAL_ABILITIES);
    }
}
