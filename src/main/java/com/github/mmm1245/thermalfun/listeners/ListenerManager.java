package com.github.mmm1245.thermalfun.listeners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {
    public final LootGenListener LOOT_GEN = new LootGenListener();
    public final KillEntityListener KILL_ENTITY = new KillEntityListener();
    public final PlayerLoginQuitListener LOGIN_QUIT = new PlayerLoginQuitListener();

    public void register(JavaPlugin plugin){
        PluginManager manager = plugin.getServer().getPluginManager();
        manager.registerEvents(LOOT_GEN, plugin);
        manager.registerEvents(KILL_ENTITY, plugin);
        manager.registerEvents(LOGIN_QUIT, plugin);
    }
}
