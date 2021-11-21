// Created on 20.11.2021, 14:41

package de.malik.survivalextension.commands;

import de.malik.survivalextension.SurvivalExtension;
import de.malik.survivalextension.utils.CmdHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Du musst ein Spieler sein!");
            return false;
        }
        Player player = (Player) sender;
        final String SENDER_UUID = player.getUniqueId().toString();
        FileConfiguration cfg = SurvivalExtension.getCfg();
        if (args.length == 2) {
            if (CmdHelper.isOnlinePlayer(args[0]) && CmdHelper.isInteger(args[1])) {
                final int VALUE = Integer.parseInt(args[1]);
                if (cfg.getInt(SENDER_UUID) >= VALUE) {
                    Player target = Bukkit.getPlayer(args[0]);
                    final String TARGET_UUID = target.getUniqueId().toString();
                    cfg.set(SENDER_UUID, (cfg.getInt(SENDER_UUID) - VALUE));
                    cfg.set(TARGET_UUID, (cfg.getInt(TARGET_UUID) + VALUE));
                    player.sendMessage(ChatColor.BLUE + "Du hast " + ChatColor.WHITE + ChatColor.BOLD + VALUE + "€" + ChatColor.RESET + ChatColor.BLUE + " an " + ChatColor.WHITE + target.getName() + ChatColor.BLUE + " gegeben!");
                    target.sendMessage(ChatColor.WHITE + player.getName() + ChatColor.BLUE + " hat dir " + ChatColor.WHITE+  ChatColor.BOLD + VALUE + ChatColor.RESET + ChatColor.BLUE + " gegeben.");
                    SurvivalExtension.saveCfg();
                }
                else player.sendMessage(ChatColor.DARK_RED + "Du hast nicht genügend Geld!");
            }
        }
        return false;
    }
}
