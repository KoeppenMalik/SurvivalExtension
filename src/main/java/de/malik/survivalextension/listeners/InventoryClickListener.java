// Created on 19.11.2021, 23:27

package de.malik.survivalextension.listeners;

import de.malik.survivalextension.SurvivalExtension;
import de.malik.survivalextension.utils.ItemBuilder;
import de.malik.survivalextension.utils.dataclasses.ShopFragment;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(SurvivalExtension.SHOP_TITLE)) {
            for (ShopFragment shopFragment : SurvivalExtension.shopFragments) {
                if (e.getCurrentItem() != null) {
                    e.setCancelled(true);
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(shopFragment.getLabelItem().getItemMeta().getDisplayName())) {
                        player.openInventory(shopFragment.getInvToOpen());
                        break;
                    }
                }
            }
        }
        for (ShopFragment shopFragment : SurvivalExtension.shopFragments) {
            if (e.getView().getTitle().equals(shopFragment.getLabelItem().getItemMeta().getDisplayName())) {
                if (e.getCurrentItem() != null) {
                    ItemStack currentItem = e.getCurrentItem();
                    FileConfiguration cfg = SurvivalExtension.getCfg();
                    final String PLAYER_UUID = player.getUniqueId().toString();
                    if (currentItem.getItemMeta().getLore() != null) {
                        e.setCancelled(true);
                        int price = Integer.parseInt(currentItem.getItemMeta().getLore().get(0).split("; ")[0].split("€")[0]);
                        int playerMoney = cfg.getInt(PLAYER_UUID);
                        if (playerMoney >= price) {
                            cfg.set(PLAYER_UUID, playerMoney - price);
                            currentItem.setAmount(Integer.parseInt(currentItem.getItemMeta().getLore().get(0).split("; ")[1]));
                            player.getInventory().addItem(currentItem);
                        } else
                            player.sendMessage(ChatColor.DARK_RED + "Du hast nicht genügend Geld. Dir fehlen " + ChatColor.WHITE + ChatColor.BOLD + (price - playerMoney) + "€" + ChatColor.RESET + ChatColor.DARK_RED + ".");
                        SurvivalExtension.saveCfg();
                        break;
                    }
                }
            }
        }
    }
}
