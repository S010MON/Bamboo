package Bamboo.view.resources;

import java.awt.*;

public abstract class Colour
{
    public static Color BACKGROUND()
    {
        return new Color(158, 208, 239);
    }

    public static Color BACKGROUND2()
    {
        return new Color(249, 253, 240);
    }

    public static Color CIRCLE_OUTLINE()
    {
        return new Color(56, 154, 51);
    }
    
    public static Color ACTIVE_TEXT()
    {
        return Color.BLACK;
    }
    
    public static Color INACTIVE_TEXT()
    {
        return Color.GRAY;
    }
}