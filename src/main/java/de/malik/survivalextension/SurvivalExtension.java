package de.malik.survivalextension;

import de.malik.survivalextension.commands.*;
import de.malik.survivalextension.listeners.*;
import de.malik.survivalextension.utils.ItemBuilder;
import de.malik.survivalextension.utils.dataclasses.ShopFragment;
import de.malik.survivalextension.utils.dataclasses.ShopItemValue;
import de.malik.survivalextension.utils.files.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SurvivalExtension extends JavaPlugin {

    public static final String CONFIG_SEPARATOR = "_";
    public static final String LOCALIZED_NAME_SHOP_ITEM = "§6ShopItem";
    public static final String FOOD_SHOP_TITLE = "§9Essens Shop";
    public static final String RESOURCES_SHOP_TITLE = "§9Ressourcen Shop";
    public static final String ARMOR_SHOP_TITLE = "§9Rüstungs Shop";
    public static final String SPAWN_EGG_SHOP_TITLE = "§9Spawn Egg Shop";
    public static final String TOOLS_SHOP_TITLE = "§9Tools Shop";
    public static final String SHOP_TITLE = "§4Shop";

    public static Inventory shopInventory;

    public static ArrayList<ShopFragment> shopFragments = new ArrayList<>();

    @Override
    public void onEnable() {
        FileManager.readMonies();
        shopFragments.add(createFoodShopItem());
        shopFragments.add(createResourcesShopItem());
        shopFragments.add(createArmorShopItem());
        shopFragments.add(createSpawnEggShopItem());
        shopFragments.add(createToolsShopItem());
        createShopInv();
        // commands
        getCommand("money").setExecutor(new MoneyCommands());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("gm").setExecutor(new GameModeShortsCommands());
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        // events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new OnJoinListener(), this);
        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new OnBlockBreakListener(), this);
        pm.registerEvents(new OnDeathListener(), this);
        pm.registerEvents(new OnInventoryCloseListener(), this);
    }

    private ShopFragment createShopFragment(String name, String description, String title, Material labelMaterial, ItemStack[] itemsToBuy, int pos) {
        ItemStack labelItem = new ItemBuilder(labelMaterial).setName(name).setLore(description).build();
        Inventory invToOpen = Bukkit.createInventory(null, 5*9, title);
        for (int i = 0; i < itemsToBuy.length; i++) {
            invToOpen.setItem(2*i+1, itemsToBuy[i]);
        }
        return new ShopFragment(labelItem, invToOpen, pos);
    }

    private ShopFragment createResourcesShopItem() {
        ItemStack[] itemsToBuy = createItemStackFromShopItemValues(FileManager.resourcesShopValues);
        return createShopFragment(RESOURCES_SHOP_TITLE, "§4Hier kannst du Ressourcen kaufen!", RESOURCES_SHOP_TITLE, Material.IRON_INGOT, itemsToBuy, 0);
    }

    private ShopFragment createFoodShopItem() {
        ItemStack[] itemsToBuy = createItemStackFromShopItemValues(FileManager.foodShopValues);
        return createShopFragment(FOOD_SHOP_TITLE, "§2Hier kannst du Essen kaufen!", FOOD_SHOP_TITLE, Material.COOKED_MUTTON, itemsToBuy, 2);
    }

    private ShopFragment createArmorShopItem() {
        ItemStack[] itemsToBuy = createItemStackFromShopItemValues(FileManager.armorShopValues);
        return createShopFragment(ARMOR_SHOP_TITLE, "§3Hier kannst du Rüstung kaufen!", ARMOR_SHOP_TITLE, Material.IRON_HELMET, itemsToBuy, 4);
    }

    private ShopFragment createSpawnEggShopItem() {
        ItemStack[] itemsToBuy = createItemStackFromShopItemValues(FileManager.spawnEggValues);
        return createShopFragment(SPAWN_EGG_SHOP_TITLE, "§7Hier kannst du spawn eggs kaufen!", SPAWN_EGG_SHOP_TITLE, Material.AXOLOTL_SPAWN_EGG, itemsToBuy, 6);
    }

    private ShopFragment createToolsShopItem() {
        ItemStack[] itemsToBuy = createItemStackFromShopItemValues(FileManager.toolsShopValues);
        return createShopFragment(TOOLS_SHOP_TITLE, "§7Hier kannst du tools kaufen!", TOOLS_SHOP_TITLE, Material.WOODEN_PICKAXE, itemsToBuy, 8);
    }

    private void createShopInv() {
        shopInventory = Bukkit.createInventory(null, 3*9, SHOP_TITLE);
        for (ShopFragment shopFragment : shopFragments) {
            shopInventory.setItem(shopFragment.getPos(), shopFragment.getLabelItem());
        }
    }

    private ItemStack[] createItemStackFromShopItemValues(ArrayList<ShopItemValue> list) {
        ItemStack[] itemStacks = new ItemStack[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ItemStack itemStack = new ItemBuilder(list.get(i).getMaterial()).setLore(list.get(i).getPrice() + "€; " + list.get(i).getAmount()).setAmount(list.get(i).getAmount()).setLocalizedName(LOCALIZED_NAME_SHOP_ITEM).build();
            itemStacks[i] = itemStack;
        }
        return itemStacks;
    }

    public static void saveCfg() {
        getPlugin(SurvivalExtension.class).saveConfig();
    }

    public static FileConfiguration getCfg() {
        return getPlugin(SurvivalExtension.class).getConfig();
    }
}
