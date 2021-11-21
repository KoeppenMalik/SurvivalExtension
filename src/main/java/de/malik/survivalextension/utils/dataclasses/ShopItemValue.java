// Created on 20.11.2021, 19:38

package de.malik.survivalextension.utils.dataclasses;

import org.bukkit.Material;

public class ShopItemValue {

    private Material material;
    private int price, amount;

    public ShopItemValue(Material material, int amount, int price) {
        this.material = material;
        this.amount = amount;
        this.price = price;
    }

    public Material getMaterial() {
        return material;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
