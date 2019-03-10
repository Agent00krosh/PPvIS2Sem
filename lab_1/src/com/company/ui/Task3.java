package com.company.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

class Task3 implements KeyListenerMethods {
    private JTextField textField;
    private JButton pushButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JPanel mainPanel;
    private Thread thread;

    private ButtonGroup buttonGroup;

    JPanel run() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initEditText();
        mainPanel.add(textField);
        mainPanel.add(pushButton);
        initRadioButtonPanel();
        mainPanel.add(radioButton1);
        mainPanel.add(radioButton2);
        mainPanel.add(radioButton3);


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

    private void initEditText() {
        textField = new JTextField(10);
        pushButton = new JButton("Push");
        pushButton.addActionListener(e -> onClickPushButton());
    }

    private void onClickPushButton() {
        if (radioButton1.getText().equals(textField.getText())) {
            if (!radioButton1.isSelected()) {
                buttonGroup.clearSelection();
                radioButton1.setSelected(true);
            }
            return;
        }
        if (radioButton2.getText().equals(textField.getText())) {
            if (!radioButton2.isSelected()) {
                buttonGroup.clearSelection();
                radioButton2.setSelected(true);
            }
            return;
        }
        if (radioButton3.getText().equals(textField.getText())) {
            if (!radioButton3.isSelected()) {
                buttonGroup.clearSelection();
                radioButton3.setSelected(true);
            }
            return;
        }
        JOptionPane.showMessageDialog(null, "Error name", "Error", JOptionPane.PLAIN_MESSAGE);
    }


    private void initRadioButtonPanel() {

        buttonGroup = new ButtonGroup();
        radioButton1 = new JRadioButton("radio button1");
        radioButton2 = new JRadioButton("radio button2");
        radioButton3 = new JRadioButton("radio button3");
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);


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