package com.visellico.huecirc;

import com.visellico.huecirc.graphicz.Renderable;

import java.awt.*;

public class Circle implements Renderable {

    private float color_hue;
    private float position;
    private double angle;

    private int x, y;
    private int radius;

    private Color color;
    private float sync;

    int counter;

    public static Circle MakeDefaultCircle(double theta, float sync) {

        Circle circle = new Circle(theta, sync);
        circle.x = circle.y = 0;
        circle.radius = 100;

        return circle;

    }


    private Circle(double theta, float sync) {
        this.angle = theta;
        this.sync = sync;
    }

    public void update() {

        counter = (counter + 1) % 360;

        color = new Color(Color.HSBtoRGB(color_hue, 1, 1));


    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawArc(x,y,radius,radius,0,360);
    }


}
