package org.leiers.minecraft.menu.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.leiers.minecraft.menu.MMenu;

import java.util.function.Consumer;

public class MIcon
{
    private final ItemStack item;
    private final Consumer<Player> consumer;

    public MIcon(ItemStack item, Consumer<Player> consumer)
    {
        this.item = makeMenuItem(item);
        this.consumer = consumer;
    }

    private ItemStack makeMenuItem(ItemStack item)
    {
        ItemMeta meta = item.getItemMeta();

        if (meta == null)
            meta = Bukkit.getItemFactory().getItemMeta(item.getType());

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(MMenu.getInstance().getNamespacedKey(), PersistentDataType.SHORT, (short) 1);
        item.setItemMeta(meta);

        return item;
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
