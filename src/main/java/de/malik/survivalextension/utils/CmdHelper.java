// Created on 20.11.2021, 14:34

package de.malik.survivalextension.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CmdHelper {

    public static boolean isOnlinePlayer(String name) {
        Player player = Bukkit.getPlayer(name);
        if (player == null) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
