package com.github.mmm1245.thermalfun;

import com.github.mmm1245.thermalfun.api.Ability;
import com.github.mmm1245.thermalfun.abilities.PlayerAbilityStorage;
import com.github.mmm1245.thermalfun.items.HeatAddingItem;
import com.github.mmm1245.thermalfun.items.ThermalWand;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerUIUpdater implements Runnable {
    @Override
    public void run() {
        for (Player player : ThermalFunMain.getInstance().getServer().getOnlinePlayers()) {
            ItemStack hand = player.getInventory().getItemInMainHand();
            SlimefunItem handSfItem = SlimefunItem.getByItem(hand);
            PlayerHeatStorage.HeatValues heatValues = ThermalFunMain.getHeatStorage().forPlayer(player);

            boolean isThermalWand = handSfItem instanceof ThermalWand;
            boolean isHeadAddingItem = handSfItem instanceof HeatAddingItem;

            if (isHeadAddingItem) {
                heatValues.setFakeCurrentModifier(((HeatAddingItem) handSfItem).getHeat());
            } else if (isThermalWand) {
                PlayerAbilityStorage.AbilitiesList abilitiesList = ThermalFunMain.getAbilityStorage().forPlayer(player);
                heatValues.setFakeCurrentModifier(-(abilitiesList.getCurrent()==null?0:abilitiesList.getCurrent().cost()));
            } else {
                heatValues.setFakeCurrentModifier(0);
            }

            heatValues.setShown(isThermalWand || isHeadAddingItem);

            if (isThermalWand) {
                Ability selected = ThermalFunMain.getAbilityStorage().forPlayer(player).getCurrent();
                TextComponent text = new TextComponent(selected != null ? selected.userFriendlyName() : "No abilities");
                text.setColor(ChatColor.RED);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
            }
        }
    }
}
