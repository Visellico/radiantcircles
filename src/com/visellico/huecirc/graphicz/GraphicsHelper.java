package com.visellico.huecirc.graphicz;

import java.awt.*;

public class GraphicsHelper {

    /**
     * Draws a filled in circle to the graphics object centered at x, y
     * @param x Center X coordinate
     * @param y Center Y coordinate
     * @param radius
     * @param graphics Graphics instance to draw to
     */
    public static void fillCircle(int x, int y, int radius, Graphics graphics) {

        graphics.fillOval(x - radius /  2, y - radius / 2, radius, radius);
    }

}
