package com.company.ui;

import javax.swing.*;

class Task1 {
    private JComboBox<String> comboBox;
    private JTextField textField;
    private JPanel editTextPanel;
    private JPanel comboBoxPanel;

    JPanel run() {
        JPanel mainPanel = new JPanel();

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
        sendText.addActionListener(e -> onClickSend());
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
