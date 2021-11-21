// Created on 20.11.2021, 13:41

package de.malik.survivalextension.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeShortsCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Du musst ein Spieler sein!");
            return false;
        }
        Player player = (Player) sender;
        if (player.isOp()) {
            switch (args[0]) {
                case "0": player.setGameMode(GameMode.SURVIVAL); break;
                case "1": player.setGameMode(GameMode.CREATIVE); break;
                case "2": player.setGameMode(GameMode.SPECTATOR); break;
                default: break;
            }
        }
        else player.sendMessage(ChatColor.BLUE + "Du musst Operator sein!");
        return false;
    }
}
