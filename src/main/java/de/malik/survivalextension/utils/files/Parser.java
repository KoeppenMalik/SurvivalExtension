// Created on 20.11.2021, 19:11

package de.malik.survivalextension.utils.files;

import de.malik.survivalextension.utils.dataclasses.EntityMoney;
import de.malik.survivalextension.utils.dataclasses.MaterialMoney;
import de.malik.survivalextension.utils.dataclasses.ShopItemValue;
import org.bukkit.Material;

import java.util.ArrayList;

public class Parser {

    public static final String COMMENT_PREFIX = "#";
    public static final String SPLIT_REGEX = ";";

    public static ArrayList<EntityMoney> parseEntityIds(ArrayList<String> lines) {
        ArrayList<EntityMoney> entityMonies = new ArrayList<>();
        if (lines.size() == 0) {
            return new ArrayList<>();
        }
        for (String line : lines) {
            String[] cols = line.split(SPLIT_REGEX);
            entityMonies.add(new EntityMoney(cols[0], Integer.parseInt(cols[1])));
        }
        return entityMonies;
    }

    public static ArrayList<ShopItemValue> parseShopItemValues(ArrayList<String> lines) {
        ArrayList<ShopItemValue> shopItemValues = new ArrayList<>();
        if (lines.size() == 0) {
            return new ArrayList<>();
        }
        for (String line : lines) {
            String[] cols = line.split(SPLIT_REGEX);
            shopItemValues.add(new ShopItemValue(Material.getMaterial(cols[0]), Integer.parseInt(cols[1]), Integer.parseInt(cols[2])));
        }
        return shopItemValues;
    }

    public static ArrayList<MaterialMoney> parseMaterialMonies(ArrayList<String> lines) {
        ArrayList<MaterialMoney> materialMonies = new ArrayList<>();
        if (lines.size() == 0) {
            return new ArrayList<>();
        }
        for (String line : lines) {
            String[] cols = line.split(SPLIT_REGEX);
            materialMonies.add(new MaterialMoney(Material.getMaterial(cols[0]), Integer.parseInt(cols[1])));
        }
        return materialMonies;
    }
}