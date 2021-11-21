// Created on 21.11.2021, 14:09

package de.malik.survivalextension.listeners;

import de.malik.survivalextension.SurvivalExtension;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnInventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && item.getItemMeta() != null) {
                ItemMeta im = item.getItemMeta();
                if (im.getLocalizedName().equals(SurvivalExtension.LOCALIZED_NAME_SHOP_ITEM)) {
                    im.setLore(null);
                    im.setLocalizedName("");
                }
                item.setItemMeta(im);
            }
        }
    }
}
