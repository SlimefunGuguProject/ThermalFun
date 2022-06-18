package com.github.mmm1245.thermalfun.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ThermalFunStacks {
    private static final String BLAZE_HEAD_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjIwNjU3ZTI0YjU2ZTFiMmY4ZmMyMTlkYTFkZTc4OGMwYzI0ZjM2Mzg4YjFhNDA5ZDBjZDJkOGRiYTQ0YWEzYiJ9fX0=";

    public static final SlimefunItemStack INACTIVE_THERMAL_WAND = new SlimefunItemStack("INACTIVE_THERMAL_WAND", Material.STICK, "&9禁锢法杖", "&7在副手状态下通过杀死烈焰人激活");
    public static final SlimefunItemStack THERMAL_WAND = new SlimefunItemStack("THERMAL_WAND", Material.BLAZE_ROD, "&c热敏法杖", "&7通过在要塞宝箱中寻找升级来解锁技能", "&7Shift+右击 更改当前技能", "&7通过杀死烈焰人和岩浆怪来增加你的最大热量", "&7通过食用热汤和烤苹果来增加你的血量");
    public static final SlimefunItemStack KILL_BLAZE_HEAD = new SlimefunItemStack("BLAZE_HEAD", BLAZE_HEAD_TEXTURE, "&c在副手状态下杀死烈焰人");
    public static final SlimefunItemStack FORTRESS_LOOTTABLE_CHEST = new SlimefunItemStack("FORTRESS_LOOTTABLE", Material.CHEST, "&4在下界遗迹宝箱中寻找该物品");
}
