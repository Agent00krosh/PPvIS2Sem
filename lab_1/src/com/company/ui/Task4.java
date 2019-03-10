package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

class Task4 implements KeyListenerMethods{
    private JTextField textField;
    private List<JCheckBox> checkBoxes;
    private JPanel mainPanel;
    private Thread thread;
    private JButton pushButton;

    JPanel run() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initEditTextPanel();
        mainPanel.add(textField);
        mainPanel.add(pushButton);
        initCheckBoxPanel();
        for (JCheckBox checkBox : checkBoxes) {
            mainPanel.add(checkBox);
        }

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
        textField = new JTextField(20);
        pushButton = new JButton("Push");
        pushButton.addActionListener(e -> onClickPushButton());
    }

    private void onClickPushButton() {
//        clearCheckBoxes();
        if (!isNotError()) {
            errorIsVisible();
            return;
        }
        fillCheckBoxes();
    }

    private void clearCheckBoxes() {
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }
    }

    private boolean isNotError() {
        List<String> names = getNamesOfBoxes();
        for (String name : names) {
            if (getCheckBoxPosition(name) == -1) {
                return false;
            }
        }
        return true;
    }

    private int getCheckBoxPosition(String text) {
        int position = -1;
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (text.equals(checkBoxes.get(i).getText())) {
                position = i;
            }
        }
        return position;
    }

    private void fillCheckBoxes() {
        List<String> names = getNamesOfBoxes();
        for (String name : names) {
            checkBoxes.get(getCheckBoxPosition(name)).setSelected(!checkBoxes.get(getCheckBoxPosition(name)).isSelected());
        }
    }

    private void errorIsVisible() {
        clearCheckBoxes();
        JOptionPane.showMessageDialog(null, "Exist error name", "Error", JOptionPane.PLAIN_MESSAGE);
    }

    private void initCheckBoxPanel() {
        checkBoxes = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            checkBoxes.add(new JCheckBox("box " + i));
        }
    }

    private List<String> getNamesOfBoxes() {
        String text = textField.getText();
        return Arrays.asList(text.split(", "));
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