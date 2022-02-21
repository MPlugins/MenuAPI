package org.leiers.minecraft.menu;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.leiers.minecraft.menu.listeners.MMenuListener;
import org.leiers.minecraft.menu.menu.MenuRows;
import org.leiers.minecraft.menu.menu.MenuSettings;
import org.leiers.minecraft.menu.menu.MenuSettingsBuilder;
import org.leiers.minecraft.menu.test.TestMenu;

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

        TestMenu test = new TestMenu();
        MenuSettings settings = new MenuSettingsBuilder()
                .setTitle("My Menu")
                .setRows(MenuRows.FOUR)
                .setCanPlaceItems()
                .create();

        test.setSettings(settings);
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
