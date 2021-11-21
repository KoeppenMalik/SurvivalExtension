// Created on 19.11.2021, 23:29

package de.malik.survivalextension.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta im;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        im = itemStack.getItemMeta();
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        im.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(String... lores) {
        im.setLore(Arrays.asList(lores));
        return this;
    }

    public ItemBuilder setLocalizedName(String localizedName) {
        im.setLocalizedName(localizedName);
        return this;
    }

    public ItemBuilder addEnchants(Enchant... enchants) {
        for (Enchant enchant : enchants) {
            im.addEnchant(enchant.getEnchant(), enchant.getLevel(), true);
        }
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        im.setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(im);
        return itemStack;
    }
}
