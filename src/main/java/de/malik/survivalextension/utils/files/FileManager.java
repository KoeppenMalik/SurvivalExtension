// Created on 20.11.2021, 19:22

package de.malik.survivalextension.utils.files;

import de.malik.survivalextension.utils.dataclasses.EntityMoney;
import de.malik.survivalextension.utils.dataclasses.MaterialMoney;
import de.malik.survivalextension.utils.dataclasses.ShopItemValue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    public static final String FILE_PATH_ENTITY_MONIES = "D:\\Minecraft\\Server\\test_server\\plugins\\SurvivalExtension\\entity_monies.csv";
    public static final String FILE_PATH_BLOCK_BREAK_MONIES = "D:\\Minecraft\\Server\\test_server\\plugins\\SurvivalExtension\\block_break_monies.csv";
    public static final String FILE_PATH_FOOD_PRICES = "D:\\Minecraft\\Server\\test_server\\plugins\\SurvivalExtension\\food_prices.csv";
    public static final String FILE_PATH_RESOURCES_PRICES = "D:\\Minecraft\\Server\\test_server\\plugins\\SurvivalExtension\\resources_prices.csv";
    public static final String FILE_PATH_ARMOR_PRICES = "D:\\Minecraft\\Server\\test_server\\plugins\\SurvivalExtension\\armor_prices.csv";
    public static final String FILE_PATH_SPAWN_EGG_PRICES = "D:\\Minecraft\\Server\\test_server\\plugins\\SurvivalExtension\\spawn_egg_prices.csv";
    public static final String FILE_PATH_TOOLS_PRICES = "D:\\Minecraft\\Server\\test_server\\plugins\\SurvivalExtension\\tools_prices.csv";

    public static ArrayList<EntityMoney> entityMonies;
    public static ArrayList<MaterialMoney> materialMonies;
    public static ArrayList<ShopItemValue> foodShopValues;
    public static ArrayList<ShopItemValue> resourcesShopValues;
    public static ArrayList<ShopItemValue> armorShopValues;
    public static ArrayList<ShopItemValue> spawnEggValues;
    public static ArrayList<ShopItemValue> toolsShopValues;

    public static void readMonies() {
        try {
            // entity monies
            File entityMoniesFile = new File(FILE_PATH_ENTITY_MONIES);
            ArrayList<String> lines = Reader.readLines(entityMoniesFile);
            entityMonies = Parser.parseEntityIds(lines);
            // block break monies
            File blockBreakMoniesFile = new File(FILE_PATH_BLOCK_BREAK_MONIES);
            lines = Reader.readLines(blockBreakMoniesFile);
            materialMonies = Parser.parseMaterialMonies(lines);
            // spawn egg prices
            File spawnEggPricesFile = new File(FILE_PATH_SPAWN_EGG_PRICES);
            lines = Reader.readLines(spawnEggPricesFile);
            spawnEggValues = Parser.parseShopItemValues(lines);
            // armor prices
            File armorPricesFile = new File(FILE_PATH_ARMOR_PRICES);
            lines = Reader.readLines(armorPricesFile);
            armorShopValues = Parser.parseShopItemValues(lines);
            // food prices
            File foodPricesFile = new File(FILE_PATH_FOOD_PRICES);
            lines = Reader.readLines(foodPricesFile);
            foodShopValues = Parser.parseShopItemValues(lines);
            // resources prices
            File resourcesPricesFile = new File(FILE_PATH_RESOURCES_PRICES);
            lines = Reader.readLines(resourcesPricesFile);
            resourcesShopValues = Parser.parseShopItemValues(lines);
            // tools prices
            File toolsPricesFile = new File(FILE_PATH_TOOLS_PRICES);
            lines = Reader.readLines(toolsPricesFile);
            toolsShopValues = Parser.parseShopItemValues(lines);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
