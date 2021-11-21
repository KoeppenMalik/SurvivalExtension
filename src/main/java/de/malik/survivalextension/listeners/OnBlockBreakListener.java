// Created on 20.11.2021, 18:19

package de.malik.survivalextension.listeners;

import de.malik.survivalextension.SurvivalExtension;
import de.malik.survivalextension.utils.dataclasses.MaterialMoney;
import de.malik.survivalextension.utils.files.FileManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        FileConfiguration cfg = SurvivalExtension.getCfg();
        final String PLAYER_UUID = e.getPlayer().getUniqueId().toString();
        final Material MATERIAL = e.getBlock().getBlockData().getMaterial();
        for (MaterialMoney materialMoney : FileManager.materialMonies) {
            if (materialMoney.getMaterial() == MATERIAL) {
                cfg.set(PLAYER_UUID, (cfg.getInt(PLAYER_UUID) + materialMoney.getMoney()));
            }
        }
        SurvivalExtension.saveCfg();
    }
}
