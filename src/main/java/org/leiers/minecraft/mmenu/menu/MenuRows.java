package org.leiers.minecraft.mmenu.menu;

public enum MenuRows
{
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private final int size;

    MenuRows(int rows)
    {
        this.size = rows;
    }

    public int getAsInt()
    {
        return size;
    }
}
