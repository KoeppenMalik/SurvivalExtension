// Created on 20.11.2021, 17:12

package de.malik.survivalextension.commands;

import de.malik.survivalextension.SurvivalExtension;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Du musst ein Spieler sein!");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.openInventory(SurvivalExtension.shopInventory);
        } else player.sendMessage(ChatColor.BLUE + "Versuch es mit " + ChatColor.WHITE + "/shop" + ChatColor.BLUE + ".");
        return false;
    }
}
