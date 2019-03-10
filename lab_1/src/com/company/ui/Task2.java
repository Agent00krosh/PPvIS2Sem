package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;


class Task2 implements KeyListenerMethods {
    private JTextField textField;
    private JPanel editTextPanel;

    private JButton readButton;
    private JButton updateButton;
    private Thread thread;
    private JPanel mainPanel;

    JPanel run() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        initEditTextPanel();
        mainPanel.add(textField);
        initButtons();
        mainPanel.add(readButton);
        mainPanel.add(updateButton);
        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardFocusManager.addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_RELEASED && (e.getKeyCode() == KeyEvent.VK_R) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                start();
            }
            if (e.getID() == KeyEvent.KEY_RELEASED && (e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                stop();
            }
            return false;
        });

        return mainPanel;
    }

    private void initEditTextPanel() {
        editTextPanel = new JPanel();
        textField = new JTextField(20);
        editTextPanel.add(textField);
    }


    private void initButtons() {
        readButton = new JButton("read");
        readButton.addActionListener(e -> onClickRead());

        updateButton = new JButton("update");
        updateButton.addActionListener(e -> onClickUpdate());

    }

    private void onClickRead() {
        readButton.setText(textField.getText());
    }

    private void onClickUpdate() {
        String helpStr = readButton.getText();
        readButton.setText(updateButton.getText());
        updateButton.setText(helpStr);
    }

    @Override
    public void start() {
        if (thread != null) thread.stop();
        Runnable runnable = () -> {
            for (int i = 0; i < 1; i++) {
                i--;
                Component component = mainPanel.getComponent(mainPanel.getComponentCount()-1);
                mainPanel.remove(component);
                mainPanel.add(component,0);
                mainPanel.revalidate();
                try {
                    sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void stop() {
        if (thread != null) {
            thread.stop();
        }
    }
}
