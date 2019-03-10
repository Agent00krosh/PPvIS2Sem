package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

class Task1 implements KeyListenerMethods {
    private JComboBox<String> comboBox;
    private JTextField textField;
    private JPanel editTextPanel;
    private JPanel comboBoxPanel;
    private JButton sendText;
    private JPanel mainPanel;
    private Thread thread;


    JPanel run() {
        mainPanel = new JPanel();
        initEditTextPanel();
        initComboBox();
        mainPanel.add(editTextPanel);
        mainPanel.add(sendText);
        mainPanel.add(comboBoxPanel);

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


    private void initComboBox() {
        comboBox = new JComboBox<>();
        comboBoxPanel = new JPanel();
        comboBoxPanel.add(comboBox);
    }

    private void initEditTextPanel() {
        editTextPanel = new JPanel();
        textField = new JTextField(10);
        editTextPanel.add(textField);
        sendText = new JButton("Push");
        sendText.addActionListener(e -> onClickSend());
    }

    private void onClickSend() {
        if (!checkAlready(textField.getText())) {
            comboBox.addItem(textField.getText());
            textField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "This text already in Combo Box", "Error", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean checkAlready(String text) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).equals(text)) return true;
        }
        return false;
    }

}