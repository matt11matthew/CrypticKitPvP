package com.cryptickits.kitpvp.rank.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class CreateRankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("cryptickits.createkit")) {
                return perform(sender, args);
            }
        }
        if (sender instanceof ConsoleCommandSender) {
            return perform(sender, args);
        }
        return false;
    }

    private boolean perform(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

        }
        return false;
    }
}
