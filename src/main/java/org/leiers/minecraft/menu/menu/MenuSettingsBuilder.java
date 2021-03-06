package org.leiers.minecraft.menu.menu;

import static org.leiers.minecraft.menu.menu.Menu.DEFAULT_CAN_PLACE_ITEMS;
import static org.leiers.minecraft.menu.menu.Menu.DEFAULT_CAN_TAKE_ITEMS;
import static org.leiers.minecraft.menu.menu.Menu.DEFAULT_MENU_TITLE;
import static org.leiers.minecraft.menu.menu.Menu.DEFAULT_MENU_ROWS;

public class MenuSettingsBuilder
{
    private boolean canPlaceItems = DEFAULT_CAN_PLACE_ITEMS;
    private boolean canTakeItems = DEFAULT_CAN_TAKE_ITEMS;
    private String title = DEFAULT_MENU_TITLE;
    private MenuRows rows = DEFAULT_MENU_ROWS;

    public MenuSettingsBuilder setTitle(String title)
    {
        this.title = title;

        return this;
    }

    public MenuSettingsBuilder setRows(MenuRows rows)
    {
        this.rows = rows;

        return this;
    }

    public MenuSettingsBuilder setCanPlaceItems()
    {
        canPlaceItems = true;

        return this;
    }

    public MenuSettingsBuilder setCanTakeItems()
    {
        canTakeItems = true;

        return this;
    }

    public MenuSettings create()
    {
        return new MenuSettings(title, rows, canPlaceItems, canTakeItems);
    }
}
