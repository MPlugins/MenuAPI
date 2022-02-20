package org.leiers.minecraft.mmenu;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.leiers.minecraft.mmenu.listeners.MMenuListener;

public final class MMenu
{
    private NamespacedKey namespacedKey;
    private static MMenu instance;

    private JavaPlugin plugin;

    public void register(JavaPlugin plugin)
    {
        this.plugin = plugin;
        namespacedKey = new NamespacedKey(MMenu.getInstance().getPlugin(), "mmenu_item");
        plugin.getServer().getPluginManager().registerEvents(new MMenuListener(), plugin);
    }

    public NamespacedKey getNamespacedKey()
    {
        return namespacedKey;
    }

    public JavaPlugin getPlugin()
    {
        return plugin;
    }

    private MMenu()
    {
    }

    public static MMenu getInstance()
    {
        if (instance == null)
            instance = new MMenu();

        return instance;
    }
}
