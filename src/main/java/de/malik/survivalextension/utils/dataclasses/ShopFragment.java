// Created on 20.11.2021, 14:57

package de.malik.survivalextension.utils.dataclasses;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopFragment {

    private ItemStack labelItem;
    private Inventory invToOpen;
    private int pos;

    public ShopFragment(ItemStack labelItem, Inventory invToOpen, int pos) {
        this.labelItem = labelItem;
        this.invToOpen = invToOpen;
        this.pos = pos;
    }

    public ItemStack getLabelItem() {
        return labelItem;
    }

    public Inventory getInvToOpen() {
        return invToOpen;
    }

    public int getPos() {
        return pos;
    }
}
