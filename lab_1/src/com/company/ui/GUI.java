package com.company.ui;

import javax.swing.*;
import java.awt.*;


public class GUI {
    private JFrame window;
    public GUI(){
        window = new JFrame("Lab1");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(850,550);
        initNavigation();
        window.setVisible(true);
    }

    private void initNavigation(){
        JPanel navigation = new JPanel();
        window.add(navigation);
        navigation.setLayout(new CardLayout());
        navigation.add((new Task2()).task2());
    }
}
