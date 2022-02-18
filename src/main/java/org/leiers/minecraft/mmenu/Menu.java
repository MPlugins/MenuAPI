package org.leiers.minecraft.mmenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public abstract class Menu implements InventoryHolder
{
    private final Inventory inventory;

    public Menu(String title)
    {
        this(title, 3);
    }

    public Menu(String title, int rows)
    {
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
    }

    private final HashMap<Integer, MIcon> icons = new HashMap<>();

    public boolean hasStaticItems()
    {
        return true;
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

    public void click(int slot, Player player)
    {
        getIcon(slot).click(player);
    }

    public void open(Player player)
    {
        setIcons();

        player.openInventory(inventory);
    }

    public MIcon getIcon(int slot)
    {
        return icons.getOrDefault(slot, MIcon.empty());
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }
}
