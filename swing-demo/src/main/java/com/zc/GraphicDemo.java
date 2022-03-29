package com.zc;

import javax.swing.*;
import java.awt.*;

public class GraphicDemo {
    static MyPanel panel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("demo");
        Container contentPane = frame.getContentPane();
        panel = new MyPanel();
        contentPane.add(panel, "Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 200);
        frame.setVisible(true);
    }


    static class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            //在原点为(10,10)，半径为50画圆
//            g.drawOval(0, 0, 200, 100);
            //在原点为(100,10)，半径为50画填充效果的圆
//            g.fillOval(100, 10, 50, 50);
            //在原点为(190,10),长轴为75,短轴为50画的椭圆
//            g.drawOval(190, 10, 90, 30);
            //在原点为(70,90),长轴为140,短轴为100画填充效果的椭圆
//            g.fillOval(70, 90, 140, 100);


            g.drawArc(0,0,200,100,0,-45);
            


        }
    }
}
