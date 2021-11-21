// Created on 20.11.2021, 21:03

package de.malik.survivalextension.utils.dataclasses;

import org.bukkit.Material;

public class MaterialMoney {

    private Material material;
    private int money;

    public MaterialMoney(Material material, int money) {
        this.material = material;
        this.money = money;
    }

    public Material getMaterial() {
        return material;
    }

    public int getMoney() {
        return money;
    }
}
