package org.leiers.minecraft.mmenu.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.leiers.minecraft.mmenu.MMenu;

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
        if (item == null)
            return null;

        ItemMeta meta = item.getItemMeta();

        if (meta == null)
            return item;

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
