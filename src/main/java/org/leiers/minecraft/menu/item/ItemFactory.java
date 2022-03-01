package org.leiers.minecraft.menu.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemFactory
{
    private final ItemStack item;
    private final ItemMeta meta;
    private final List<String> lore;

    public ItemFactory(JavaPlugin plugin)
    {
        this(Material.BARRIER);
    }

    public ItemFactory(Material material)
    {
        this(new ItemStack(material, 1));
    }

    public ItemFactory(ItemStack item)
    {
        this.item = item;
        this.meta = item.getItemMeta();
        this.lore = new ArrayList<>();
    }

    public ItemFactory setMaterial(Material material)
    {
        item.setType(material);

        return this;
    }

    public ItemFactory setDisplayName(String name)
    {
        this.meta.setDisplayName(name.replace("&", "§"));

        return this;
    }

    public ItemFactory addLore(String... lore)
    {
        List<String> list = new ArrayList<>();

        for (String str : lore)
            list.add(str.replace("&", "§"));

        this.lore.addAll(list);

        return this;
    }

    public ItemFactory addEnchantment(Enchantment enchantment, int level)
    {
        meta.addEnchant(enchantment, level, true);

        return this;
    }

    public ItemFactory addEnchantments(Map<Enchantment, Integer> enchantments)
    {
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet())
            addEnchantment(entry.getKey(), entry.getValue());

        return this;
    }

    public ItemFactory setDamage(int damage)
    {
        if (meta instanceof Damageable)
            ((Damageable) meta).setDamage(damage);

        return this;
    }

    public ItemFactory addItemFlag(ItemFlag... flags)
    {
        this.meta.addItemFlags(flags);

        return this;
    }

    public ItemStack create()
    {
        this.meta.setLore(lore);
        this.item.setItemMeta(this.meta);

        return item;
    }
}