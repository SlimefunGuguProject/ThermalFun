package com.github.mmm1245.thermalfun.commands;

import com.github.mmm1245.thermalfun.abilities.PlayerAbilityStorage;
import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.api.Ability;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ThermalAbilitiesCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.isOp()) {
            Player player = (Player) sender;
            if (args.length != 2)
                return false;

            Ability ability = ThermalFunMain.getAbilityRegistery().get(args[1]);
            if(ability == null)
                return false;

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
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==1){
            return TABCOMPLETE_FIRST;
        }
        if(args.length==2){
            return ThermalFunMain.getAbilityRegistery().getKeys();
        }
        return null;
    }
}
