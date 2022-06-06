package com.github.mmm1245.thermalfun.listeners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {
    public final LootGenListener LOOT_GEN = new LootGenListener();
    public final KillEntityListener KILL_ENTITY = new KillEntityListener();
    public final PlayerJoinQuitListener JOIN_QUIT = new PlayerJoinQuitListener();
    public final KillMobForHeatListener KILL_MOB_FOR_HEAT = new KillMobForHeatListener();
    public final FireballDamageListener FIREBALL_DAMAGE = new FireballDamageListener();

    public void register(JavaPlugin plugin){
        PluginManager manager = plugin.getServer().getPluginManager();

        manager.registerEvents(LOOT_GEN, plugin);
        manager.registerEvents(KILL_ENTITY, plugin);
        manager.registerEvents(JOIN_QUIT, plugin);
        manager.registerEvents(KILL_MOB_FOR_HEAT, plugin);
        manager.registerEvents(FIREBALL_DAMAGE, plugin);
    }
}
