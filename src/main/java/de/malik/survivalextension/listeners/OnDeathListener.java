// Created on 20.11.2021, 23:00

package de.malik.survivalextension.listeners;

import de.malik.survivalextension.SurvivalExtension;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeathListener implements Listener {

    @EventHandler
    public void onDeathListener(PlayerDeathEvent e) {
        FileConfiguration cfg = SurvivalExtension.getCfg();
        Player player = e.getEntity();
        final String PLAYER_UUID = player.getUniqueId().toString();
        cfg.set(PLAYER_UUID, (cfg.getInt(PLAYER_UUID) / 2));
        player.sendMessage(ChatColor.BLUE + "Du hast die Hälfte deines Geldes verloren. Dein Kontostand beträgt nun:" + ChatColor.BOLD + ChatColor.WHITE + cfg.getInt(PLAYER_UUID));
        SurvivalExtension.saveCfg();
    }
}
