package org.leiers.minecraft.mmenu;

import org.bukkit.plugin.java.JavaPlugin;
import org.leiers.minecraft.mmenu.listeners.MMenuListener;

public final class MMenu
{
    private static MMenu instance;

    public void register(JavaPlugin plugin)
    {
        plugin.getServer().getPluginManager().registerEvents(new MMenuListener(), plugin);
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
