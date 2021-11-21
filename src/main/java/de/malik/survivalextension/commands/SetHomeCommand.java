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

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Du musst ein Spieler sein!");
            return false;
        }
        Player player = (Player) sender;
        FileConfiguration cfg = SurvivalExtension.getCfg();
        if (args.length == 1) {
            Location pos = player.getLocation();
            final String KEY = player.getUniqueId().toString() + SurvivalExtension.CONFIG_SEPARATOR + args[0];
            cfg.set(KEY, pos);
            player.sendMessage(ChatColor.BLUE + "Home " + ChatColor.BOLD + ChatColor.WHITE + args[0] + ChatColor.RESET + ChatColor.BLUE + " wurde gesetzt.");
            SurvivalExtension.saveCfg();
        }
        return false;
    }
}
