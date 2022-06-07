package com.github.mmm1245.thermalfun.commands;

import com.github.mmm1245.thermalfun.EAbility;
import com.github.mmm1245.thermalfun.PlayerAbilityStorage;
import com.github.mmm1245.thermalfun.PlayerHeatStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThermalAbilitiesCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.isOp()) {
            Player player = (Player) sender;
            if (args.length != 2)
                return false;

            EAbility ability;
            try {
                ability = EAbility.valueOf(args[1]);
            } catch (IllegalArgumentException e) {
                return false;
            }

            PlayerAbilityStorage.AbilitiesList abilitiesList = ThermalFunMain.getAbilityStorage().forPlayer(player);

            if (args[0].equals("learn")) {
                if(abilitiesList.learn(ability)){
                    player.sendMessage("Learned ability " + args[1]);
                } else {
                    player.sendMessage("You already know ability " + args[1]);
                }
                return true;
            } else if (args[0].equals("revoke")) {
                if(abilitiesList.revoke(ability)) {
                    player.sendMessage("Revoked ability " + args[1]);
                } else {
                    player.sendMessage("Player doesnt have " + args[1]);
                }
                return true;
            }
            return false;
        } else {
            sender.sendMessage("Must be ran by player with op permissions");
            return true;
        }
    }

    private static final List<String> TABCOMPLETE_FIRST = Arrays.asList("learn", "revoke");
    private static final List<String> TABCOMPLETE_SECOND = Arrays.stream(EAbility.values()).map(ability -> ability.name()).collect(Collectors.toList());
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
