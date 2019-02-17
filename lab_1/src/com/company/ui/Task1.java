package com.company.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Task1 {
    private JComboBox<String> comboBox;
    private JTextField textField;
    private JPanel editTextPanel;
    private JPanel comboBoxPanel;

    JPanel task1() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initEditTextPanel();
        initComboBox();
        mainPanel.add(editTextPanel);
        mainPanel.add(comboBoxPanel);
        return mainPanel;
    }

    private void initComboBox() {
        comboBox = new JComboBox<>();
        comboBoxPanel = new JPanel();
        comboBoxPanel.add(comboBox);
    }

    private void initEditTextPanel() {
        editTextPanel = new JPanel();
        JLabel label = new JLabel("Edit Text");
        editTextPanel.add(label);
        textField = new JTextField(10);
        editTextPanel.add(textField);
        JButton sendText = new JButton("Push");
        sendText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickSend();
            }
        });
        editTextPanel.add(sendText);
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
