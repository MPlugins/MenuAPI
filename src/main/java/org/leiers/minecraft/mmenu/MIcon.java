package org.leiers.minecraft.mmenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MIcon
{
    private final ItemStack item;
    private final Consumer<Player> consumer;

    public MIcon(ItemStack item, Consumer<Player> consumer)
    {
        this.item = item;
        this.consumer = consumer;
    }

    public void click(Player player)
    {
        consumer.accept(player);
    }

    public ItemStack getItem()
    {
        return item;
    }

    public static MIcon empty()
    {
        return new MIcon(new ItemStack(Material.AIR), player -> {});
    }
}
