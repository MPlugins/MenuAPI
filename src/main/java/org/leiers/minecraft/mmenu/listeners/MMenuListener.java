package org.leiers.minecraft.mmenu.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.leiers.minecraft.mmenu.Menu;

public class MMenuListener implements Listener
{
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

        event.setCancelled(menu.hasStaticItems());
    }
}
