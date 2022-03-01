package org.leiers.minecraft.menu.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.leiers.minecraft.menu.menu.Menu;

public class MMenuListener implements Listener
{
    @EventHandler
    public void onClose(InventoryCloseEvent event)
    {
        Inventory inventory = event.getInventory();
        InventoryHolder inventoryHolder = inventory.getHolder();

        if (!(inventoryHolder instanceof Menu))
            return;

        Menu menu = (Menu) inventoryHolder;
        menu.onClose();
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event)
    {
        Inventory inventory = event.getInventory();
        InventoryHolder inventoryHolder = inventory.getHolder();

        event.setCancelled(inventoryHolder instanceof Menu);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Inventory inventory = event.getClickedInventory();

        if (inventory == null)
            return;

        InventoryHolder inventoryHolder = inventory.getHolder();

        if (!(inventoryHolder instanceof Menu))
            return;

        Menu menu = (Menu) inventoryHolder;
        menu.click(event.getSlot(), (Player) event.getWhoClicked());

        InventoryAction action = event.getAction();

        switch (action)
        {
            case PLACE_ALL:
            case PLACE_ONE:
            case PLACE_SOME:
                event.setCancelled(!menu.canPlaceItems());
                break;
            case PICKUP_ALL:
            case PICKUP_HALF:
            case PICKUP_ONE:
            case PICKUP_SOME:
                event.setCancelled(menu.cancelPickUp(event.getCurrentItem()));
                break;
            default:
                event.setCancelled(true);
                break;
        }
    }
}
