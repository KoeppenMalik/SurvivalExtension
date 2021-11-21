// Created on 20.11.2021, 14:31

package de.malik.survivalextension.listeners;

import de.malik.survivalextension.SurvivalExtension;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        FileConfiguration cfg = SurvivalExtension.getCfg();
        if (!cfg.contains(e.getPlayer().getUniqueId().toString()))
            cfg.set(e.getPlayer().getUniqueId().toString(), 0);
        SurvivalExtension.saveCfg();
    }
}
