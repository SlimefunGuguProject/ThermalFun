package com.github.mmm1245.thermalfun;

import com.github.mmm1245.thermalfun.items.ItemManager;
import com.github.mmm1245.thermalfun.listeners.ListenerManager;
import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityKillHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public final class ThermalFunMain extends AbstractAddon {
    private ItemManager itemManager;
    private ListenerManager listenerManager;
    private Random random;
    private Keys keys;
    private PlayerHeatStorage heatStorage;

    public ThermalFunMain() {
        super("mmm1245", "ThermalFun", "master", "auto-update");
    }

    @Override
    protected void enable() {
        Config cfg = new Config(this);

        this.random = new Random();
        this.keys = new Keys(this);
        this.heatStorage = new PlayerHeatStorage();

        this.itemManager = new ItemManager();
        this.itemManager.register(this);

        this.listenerManager = new ListenerManager();
        this.listenerManager.register(this);
    }

    @Override
    protected void disable() {

    }


    public static ThermalFunMain getInstance() {
        return JavaPlugin.getPlugin(ThermalFunMain.class);
    }
    public static NamespacedKey createKey(String key){
        return new NamespacedKey(getInstance(), key);
    }
    public static Random getRandom(){
        return getInstance().random;
    }
    public static ItemManager getItemManager(){
        return getInstance().itemManager;
    }
    public static ListenerManager getListenerManager(){
        return getInstance().listenerManager;
    }
    public static Keys getKeys(){
        return getInstance().keys;
    }
    public static PlayerHeatStorage getHeatStorage(){
        return getInstance().heatStorage;
    }
}
