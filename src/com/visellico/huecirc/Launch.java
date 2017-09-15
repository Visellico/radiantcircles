package com.visellico.huecirc;

import com.visellico.huecirc.graphicz.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Launch extends Canvas {

    public static Launch launch;
    private JFrame frame;

    private List<Circle> circles = new ArrayList<>();
    private boolean running;
    private Runnable render;
    private Runnable updater;
    private Action a;

    private static int dimScale = 100;
//    private static final Dimension defaultSize = new Dimension(16 * dimScale, 9 * dimScale);    //good bye sweet 16x9
    private static final Dimension defaultSize = new Dimension(9 * dimScale, 9 * dimScale);

    private int circRadius = 50;

    private Launch() {
        int angleNumerator = 72;
        int sync = 0;
        int arithmeticDiff = 5;

//        for (int i = 0; i < 36; i++)
//            circles.add(Circle.MakeCircle(sync++ * arithmeticDiff, defaultSize.width / 2, defaultSize.height / 2, circRadius, defaultSize.height / 2 - circRadius, angleNumerator-- * Math.PI/36, 1));

        angleNumerator = 180;
        arithmeticDiff = 1;
        for (int i = 0; i < 180; i++)
            circles.add(Circle.MakeCircle(sync++ * arithmeticDiff, defaultSize.width / 2, defaultSize.height / 2, circRadius, defaultSize.height / 2 - circRadius, angleNumerator-- * Math.PI/180, 1));


        setPreferredSize(defaultSize);

        frame = new JFrame("Circles");

//        render = () -> {for (Circle c : circles) c.render(g); };
        render = () -> render();
        updater = () -> update();

    }

    public void start() {
        running = true;
        mainLoop();
    }

    public void mainLoop() {

        long oldTime = System.nanoTime();
        long curTime;

        double delta = 0;

        int fps = 60;
        int nanoPerSec = 1_000_000_000;
        double fpsRatio = nanoPerSec / fps;

        long timer = System.currentTimeMillis();
        int count = 0;

        while(running) {

            curTime = System.nanoTime();
            delta += (curTime - oldTime) / fpsRatio;
            oldTime = curTime;
            while (delta >= 1) {
                delta--;
//                render();
//                update();
                render.run();
                updater.run();
                count++;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle("Frames: " + count);
                count = 0;
            }

        }

        System.out.println("finished");

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

//        g.setColor(Color.WHITE);
//        g.fillRect(0,0,defaultSize.width, defaultSize.height);

        for (Circle c : circles)
            c.render(g);

        g.dispose();
        bs.show();

    }

    public void update() {

        for (Circle c : circles)
            c.update();

    }

    public static void main(String... args) {

        launch = new Launch();

        launch.frame.setResizable(false);
        launch.frame.add(launch);
        launch.frame.pack();
        launch.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launch.frame.setLocationRelativeTo(null);
        launch.frame.setVisible(true);

        launch.start();

    }

}
