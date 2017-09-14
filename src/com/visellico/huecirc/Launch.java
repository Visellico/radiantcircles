package com.visellico.huecirc;

import com.visellico.huecirc.graphicz.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Launch extends Canvas {

    public static Launch launch;
    private JFrame frame;

    private List<Circle> circles = new ArrayList<>();
    private boolean running;
//    private Runnable render;
//    private Runnable updater;
    private Action a;

    private static int dimScale = 70;
    private static final Dimension defaultSize = new Dimension(16 * dimScale, 9 * dimScale);

    private Launch() {
        circles.add(Circle.MakeDefaultCircle(3 * Math.PI/2, 0));

        setPreferredSize(defaultSize);

        frame = new JFrame("Circles");

//        render = () -> {for (Circle c : circles) c.render(g); };
//        render = () -> render();
//        updater = () -> update();

    }

    public void start() {
        running = true;
        mainLoop();
    }



    public void mainLoop() {

        int runThisManyTimes = 100;

        long oldTime = System.nanoTime();
        long curTime;

        double delta = 0;

        int fps = 1;
        int nanoPerSec = 1_000_000_000;
        double fpsRatio = nanoPerSec / fps;

        long timer = System.currentTimeMillis();
        int count = 0;

        System.out.println("frist This should be running at 1 update cycle per second but it aint" +
                "\n\tdelta: " + delta +
                "\n\tcount: " + count);

        while(/*running*/ runThisManyTimes > 0) {

            curTime = System.nanoTime();
            delta += (curTime - oldTime) / fpsRatio;
            oldTime = curTime;
            while (delta >= 1) {
                System.out.println("THIS MUST BE CALLED AT LEAST ONCE");
                delta--;
//                render();
//                update();
                count++;
                System.out.println("This should be running at 1 update cycle per second but it aint" +
                        "\n\tdelta: " + delta +
                        "\n\tcount: " + count);
                runThisManyTimes--;

            }

            if (System.currentTimeMillis() - timer >= 1000) {
//                frame.setTitle("Frames: " + count);
                count = 0;
            }


        }

        System.out.println("finished");

    }

    public void render() {
        return;
//        BufferStrategy bs = getBufferStrategy();
//
//        if (bs == null) {
//            createBufferStrategy(3);
//            return;
//        }
//
//        Graphics g = bs.getDrawGraphics();
//
//        g.setColor(Color.GREEN);
//        g.fillRect(0,0,defaultSize.width, defaultSize.height);
//
//        g.dispose();
//        bs.show();

    }

    public void update() {
    }

    public static void main(String... args) {

        launch = new Launch();

        launch.frame.setResizable(false);
        launch.frame.add(launch);
        launch.frame.pack();
        launch.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launch.frame.setLocationRelativeTo(null);
//        launch.frame.setVisible(true);

        launch.start();

    }

}
