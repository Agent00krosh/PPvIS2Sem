package com.company.ui;

import com.sun.org.apache.xpath.internal.functions.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class GUI {
    private JPanel main;
    private JPanel task1;
    private JPanel task2;
    private JPanel task3;
    private JPanel task4;
    private JPanel task5;
    private JPanel currentTask;

    public GUI() {
        JFrame window = new JFrame("Lab1");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(850, 550);

        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        initNavigation();
        initTasks();

        window.add(main);
        window.setVisible(true);
    }

    private void initNavigation() {
        JPanel navigation = new JPanel();
        navigation.setLayout(new BoxLayout(navigation, BoxLayout.LINE_AXIS));

        JButton task1Button = new JButton("task 1");
        task1Button.addActionListener(e -> onClickTask1());
        navigation.add(task1Button);

        JButton task2Button = new JButton("task 2");
        task2Button.addActionListener(e -> onClickTask2());
        navigation.add(task2Button);

        JButton task3Button = new JButton("task 3");
        task3Button.addActionListener(e -> onClickTask3());
        navigation.add(task3Button);

        JButton task4Button = new JButton("task 4");
        task4Button.addActionListener(e -> onClickTask4());
        navigation.add(task4Button);

        JButton task5Button = new JButton("task 5");
        task5Button.addActionListener(e -> onClickTask5());
        navigation.add(task5Button);

        main.add(navigation);
    }

    private void initTasks() {
        task1 = (new Task1()).run();
        currentTask = task1;
        main.add(task1);

    }

    private void onClickTask1() {
        main.remove(currentTask);
        currentTask = (new Task1()).run();
        main.add(currentTask);
        main.revalidate();
    }

    private void onClickTask2() {
        main.remove(currentTask);
        currentTask = (new Task2()).run();
        main.add(currentTask);
        main.revalidate();
    }

    private void onClickTask3() {
        main.remove(currentTask);
        currentTask = (new Task3()).run();
        main.add(currentTask);
        main.revalidate();
    }

    private void onClickTask4() {
        main.remove(currentTask);
        currentTask = (new Task4()).run();
        main.add(currentTask);
        main.revalidate();
    }

    private void onClickTask5() {
        main.remove(currentTask);
        currentTask = (new Task5()).run();
        main.add(currentTask);
        main.revalidate();
    }


}
