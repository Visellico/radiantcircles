package com.visellico.huecirc;

import com.visellico.huecirc.graphicz.GraphicsHelper;
import com.visellico.huecirc.graphicz.Renderable;

import java.awt.*;

public class Circle implements Renderable {

    private float huePercent;

    private final double heading;
    private final double rate;      //speed to adjust scale lol
    private double initX, initY;    //translation for each x,y to move it to the center
    private double x, y;            //position to draw on the screen
    private double endX, endY;
    private int radius;

    double slidePosition;
    int slideAmplitude;

    private Color color;
    private float sync;

    int counter;

    public static Circle MakeCircle(float sync) {

        return MakeCircle(sync, 0, 0, 100, 100, 0, 1);

    }

    public static Circle MakeCircle(float sync, int x, int y) {

        return MakeCircle(sync, x, y, 100, 100, 0, 1);

    }

    public static Circle MakeCircle(float sync, int x, int y, int radius, int slideAmplitude, double heading, double rate) {

        //inverted heading because java stupidlike
        Circle circle = new Circle(sync, -heading, rate);
        circle.x = circle.initX = x;
        circle.y = circle.initY = y;
        circle.radius = radius;
        circle.slideAmplitude = slideAmplitude;
        circle.endX = circle.slideAmplitude * Math.cos(circle.heading);
        circle.endY = circle.slideAmplitude * Math.sin(circle.heading);

        return circle;

    }

    private Circle(float sync, double heading, double rate) {
        this.sync = sync;
        this.heading = heading;
        this.rate = rate;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {

        counter = (counter + 1) % 360;

        huePercent = ((float) counter) / 360f;
        slidePosition = Math.sin(Math.toRadians(counter + sync));

        color = new Color(Color.HSBtoRGB(huePercent, .5f, 1));
        x = endX * slidePosition + initX;
        y = endY * slidePosition + initY;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        GraphicsHelper.fillCircle((int) x, (int) y, radius, graphics);
        //(x,y,radius,radius,0,360);
    }


}
