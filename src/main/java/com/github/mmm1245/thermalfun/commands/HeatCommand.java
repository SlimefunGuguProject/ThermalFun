package com.github.mmm1245.thermalfun.commands;

import com.github.mmm1245.thermalfun.PlayerHeatStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.google.common.collect.Lists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HeatCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.isOp()) {
            Player player = (Player) sender;
            PlayerHeatStorage.HeatValues heatValues = ThermalFunMain.getHeatStorage().forPlayer(player);
            if (args.length != 2)
                return false;

            int value;
            try {
                value = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                return false;
            }

            if (args[0].equals("set")) {
                heatValues.setCurrent(value);
                player.sendMessage("Set heat to " + heatValues.getCurrent());
                return true;
            } else if (args[0].equals("add")) {
                heatValues.increaseCurrent(value);
                player.sendMessage("Set heat to " + heatValues.getCurrent());
                return true;
            } else if (args[0].equals("max")) {
                player.sendMessage("Set max heat to " + heatValues.getMax());
                return true;
            }
            return false;
        } else {
            sender.sendMessage("Must be ran by player with op permissions");
            return true;
        }
    }

    private static List<String> TABCOMPLETE_FIRST = Arrays.asList("set", "add", "max");
    private static List<String> TABCOMPLETE_SECOND = Arrays.asList("50", "100", "150","200");
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1){
            return TABCOMPLETE_FIRST;
        }
        if(args.length==2){
            return TABCOMPLETE_SECOND;
        }
        return null;
    }
}
