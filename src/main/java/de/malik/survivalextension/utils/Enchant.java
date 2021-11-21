// Created on 19.11.2021, 23:37

package de.malik.survivalextension.utils;

import org.bukkit.enchantments.Enchantment;

public class Enchant {

    private Enchantment enchant;
    private int level;

    public Enchant(Enchantment enchant, int level) {
        this.enchant = enchant;
        this.level = level;
    }

    public Enchantment getEnchant() {
        return enchant;
    }

    public int getLevel() {
        return level;
    }
}
