// Created on 20.11.2021, 22:22

package de.malik.survivalextension.commands;

import de.malik.survivalextension.SurvivalExtension;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Du musst ein Spieler sein!");
            return false;
        }
        Player player = (Player) sender;
        FileConfiguration cfg = SurvivalExtension.getCfg();
        if (args.length == 1) {
            final String KEY = player.getUniqueId().toString() + SurvivalExtension.CONFIG_SEPARATOR + args[0];
            if (cfg.contains(KEY)) {
                Location location = cfg.getLocation(KEY);
                player.teleport(location);
            }
            else player.sendMessage(ChatColor.BLUE + "Das Home " + ChatColor.WHITE + ChatColor.BOLD + args[0] + ChatColor.RESET + ChatColor.BLUE + " existiert nicht.");
        }
        return false;
    }
}
