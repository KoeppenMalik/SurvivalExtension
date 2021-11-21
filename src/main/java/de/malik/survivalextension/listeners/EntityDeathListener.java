// Created on 20.11.2021, 18:35

package de.malik.survivalextension.listeners;

import de.malik.survivalextension.SurvivalExtension;
import de.malik.survivalextension.utils.dataclasses.EntityMoney;
import de.malik.survivalextension.utils.files.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent e) {
        FileConfiguration cfg = SurvivalExtension.getCfg();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            final String PLAYER_UUID = killer.getUniqueId().toString();
            for (EntityMoney entityMoney : FileManager.entityMonies) {
                if (e.getEntity().getName().equals(entityMoney.getName())) {
                    cfg.set(PLAYER_UUID, (cfg.getInt(PLAYER_UUID) + entityMoney.getMoney()));
                }
            }
        }
        SurvivalExtension.saveCfg();
    }
}
