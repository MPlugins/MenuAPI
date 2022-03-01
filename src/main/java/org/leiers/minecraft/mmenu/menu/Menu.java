package org.leiers.minecraft.mmenu.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.leiers.minecraft.mmenu.MMenu;

import java.util.HashMap;

public abstract class Menu implements InventoryHolder
{
    static final boolean DEFAULT_CAN_PLACE_ITEMS = false;
    static final boolean DEFAULT_CAN_TAKE_ITEMS = false;
    static final String DEFAULT_MENU_TITLE = "Menu";
    static final int DEFAULT_MENU_ROWS = 3;

    private final HashMap<Integer, MIcon> icons = new HashMap<>();
    private Inventory inventory;
    private MenuSettings settings;

    protected Player player;

    public Menu()
    {
        this(MenuSettings.defaultSettings());
    }

    public Menu(MenuSettings settings)
    {
        setSettings(settings);
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

    public void addItem(ItemStack item)
    {
        getInventory().addItem(item);
    }

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
        this.inventory = Bukkit.createInventory(this, settings.getRows() * 9, settings.getTitle());
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
