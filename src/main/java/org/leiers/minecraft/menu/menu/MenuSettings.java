package org.leiers.minecraft.menu.menu;

public class MenuSettings
{
    private final String title;
    private final MenuRows rows;
    private final boolean canPlaceItems;
    private final boolean canTakeItems;

    public MenuSettings(String title, MenuRows rows, boolean canPlaceItems, boolean canTakeItems)
    {
        this.title = title;
        this.rows = rows;
        this.canPlaceItems = canPlaceItems;
        this.canTakeItems = canTakeItems;
    }

    public String getTitle()
    {
        return title;
    }

    public MenuRows getRows()
    {
        return rows;
    }

    public boolean canPlaceItems()
    {
        return canPlaceItems;
    }

    public boolean canTakeItems()
    {
        return canTakeItems;
    }

    public static MenuSettings defaultSettings()
    {
        return new MenuSettingsBuilder().create();
    }
}
