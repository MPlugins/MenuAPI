package org.leiers.minecraft.menu.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.leiers.minecraft.menu.MMenu;

import java.util.HashMap;

public abstract class Menu implements InventoryHolder
{
    static final boolean DEFAULT_CAN_PLACE_ITEMS = false;
    static final boolean DEFAULT_CAN_TAKE_ITEMS = false;
    static final String DEFAULT_MENU_TITLE = "Menu";
    static final MenuRows DEFAULT_MENU_ROWS = MenuRows.THREE;

    private final HashMap<Integer, MIcon> icons = new HashMap<>();
    private final Inventory inventory;
    private MenuSettings settings;

    protected Player player;

    public Menu()
    {
        this(MenuSettings.defaultSettings());
    }

    public Menu(MenuSettings settings)
    {
        this.settings = settings;
        this.inventory = Bukkit.createInventory(this, settings.getRows().getAsInt() * 9, settings.getTitle());
    }

    public boolean canPlaceItems()
    {
        return settings.canPlaceItems();
    }

    public boolean canTakeItems()
    {
        return settings.canTakeItems();
    }

    protected abstract void setIcons();

    public void addIcon(MIcon icon)
    {
        int firstEmpty = getInventory().firstEmpty();

        getInventory().setItem(firstEmpty, icon.getItem());
        icons.put(firstEmpty, icon);
    }

    public void setIcon(int slot, MIcon icon)
    {
        getInventory().setItem(slot, icon.getItem());
        icons.put(slot, icon);
    }

    public void setSettings(MenuSettings settings)
    {
        this.settings = settings;
    }

    public boolean cancelPickUp(ItemStack item)
    {
        if (item == null)
            return false;

        return item.getItemMeta().getPersistentDataContainer().has(MMenu.getInstance().getNamespacedKey(),
                PersistentDataType.SHORT);
    }

    public void click(int slot, Player player)
    {
        if (getIcon(slot) != null)
            getIcon(slot).click(player);
    }

    public void open(Player player)
    {
        setIcons();

        this.player = player;
        player.openInventory(inventory);
    }

    public void onClose()
    {
    }

    public Player getPlayer()
    {
        return player;
    }

    public MIcon getIcon(int slot)
    {
        return icons.get(slot);
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }
}
