// Created on 19.11.2021, 23:29

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

public class MoneyCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§9Du musst ein Spieler sein!");
            return false;
        }
        Player player = (Player) sender;
        FileConfiguration cfg = SurvivalExtension.getCfg();
        final String SENDER_UUID = player.getUniqueId().toString();
        switch (args.length) {
            case 0:
                if (player.hasPermission("survivalex.seemoney")) {
                    player.sendMessage(ChatColor.BLUE + "Dein aktuelles Guthaben beträgt: " + ChatColor.WHITE + ChatColor.BOLD + cfg.getInt(SENDER_UUID) + ChatColor.RESET + ChatColor.BLUE + "€");
                    break;
                }
            case 3:
                if (player.hasPermission("survivalex.moneyadmin")) {
                    if (args[0].equalsIgnoreCase("add") && CmdHelper.isInteger(args[1]) && CmdHelper.isOnlinePlayer(args[2])) {
                        final int VALUE = Integer.parseInt(args[1]);
                        Player target = Bukkit.getPlayer(args[2]);
                        final String TARGET_UUID = target.getUniqueId().toString();
                        cfg.set(TARGET_UUID, (cfg.getInt(TARGET_UUID) + VALUE));
                        SurvivalExtension.saveCfg();
                        player.sendMessage(ChatColor.BLUE + "Das Guthaben von " + ChatColor.WHITE + target.getName() + ChatColor.BLUE + " beträgt nun " + ChatColor.WHITE + ChatColor.BOLD + cfg.getInt(TARGET_UUID) + "€");
                    } else if (args[0].equalsIgnoreCase("set") && CmdHelper.isInteger(args[2]) && CmdHelper.isOnlinePlayer(args[1])) {
                        final int VALUE = Integer.parseInt(args[2]);
                        Player target = Bukkit.getPlayer(args[1]);
                        final String TARGET_UUID = target.getUniqueId().toString();
                        cfg.set(TARGET_UUID, VALUE);
                        SurvivalExtension.saveCfg();
                        player.sendMessage(ChatColor.BLUE + "Das Guthaben von " + ChatColor.WHITE + target.getName() + ChatColor.BLUE + " beträgt nun " + ChatColor.WHITE + ChatColor.BOLD + cfg.getInt(TARGET_UUID) + "€");
                    } else if (args[0].equalsIgnoreCase("remove") && CmdHelper.isOnlinePlayer(args[2])) {
                        Player target = Bukkit.getPlayer(args[2]);
                        final String TARGET_UUID = target.getUniqueId().toString();
                        int value = 0;
                        if (args[1].equals("*"))
                            value = cfg.getInt(TARGET_UUID);
                        else {
                            if (CmdHelper.isInteger(args[1]))
                                value = Integer.parseInt(args[1]);
                            else break;
                        }
                        cfg.set(TARGET_UUID, (cfg.getInt(TARGET_UUID) - value));
                        SurvivalExtension.saveCfg();
                        player.sendMessage(ChatColor.BLUE + "Das Guthaben von " + ChatColor.WHITE + target.getName() + ChatColor.BLUE + " beträgt nun " + ChatColor.WHITE + ChatColor.BOLD + cfg.getInt(TARGET_UUID) + "€");
                    }
                    break;
                }
            default: break;
        }
        return false;
    }
}
