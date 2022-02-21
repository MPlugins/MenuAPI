package org.leiers.minecraft.menu.test;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.leiers.minecraft.menu.menu.MIcon;
import org.leiers.minecraft.menu.menu.Menu;

public class TestMenu extends Menu
{
    @Override
    protected void setIcons()
    {
        setIcon(13, new MIcon(new ItemStack(Material.GREEN_CONCRETE), player -> {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("§aDu hast den GameMode gewechselt!");
            player.closeInventory();
        }));
    }
}
