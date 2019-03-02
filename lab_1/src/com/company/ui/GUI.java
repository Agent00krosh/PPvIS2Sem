package com.company.ui;

import javax.swing.*;


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
        task2 = (new Task2()).run();
        task3 = (new Task3()).run();
        task4 = (new Task4()).run();
        task5 = (new Task5()).run();

        task2.setVisible(false);
        task3.setVisible(false);
        task4.setVisible(false);
        task5.setVisible(false);
        currentTask = task1;

        main.add(task1);
        main.add(task2);
        main.add(task3);
        main.add(task4);
        main.add(task5);
    }

    private void onClickTask1() {
        currentTask.setVisible(false);
        task1.setVisible(true);
        currentTask = task1;
    }

    private void onClickTask2() {
        currentTask.setVisible(false);
        task2.setVisible(true);
        currentTask = task2;
    }

    private void onClickTask3() {
        currentTask.setVisible(false);
        task3.setVisible(true);
        currentTask = task3;
    }

    private void onClickTask4() {
        currentTask.setVisible(false);
        task4.setVisible(true);
        currentTask = task4;
    }

    private void onClickTask5() {
        currentTask.setVisible(false);
        task5.setVisible(true);
        currentTask = task5;
    }
}
