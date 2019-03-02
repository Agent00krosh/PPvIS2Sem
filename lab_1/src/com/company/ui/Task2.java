package com.company.ui;

import javax.swing.*;
import java.awt.*;

class Task2 {
    private JTextField textField;
    private JPanel editTextPanel;
    private JPanel buttonsPanel;
    private JButton readButton;
    private JButton updateButton;

    JPanel run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initEditTextPanel();
        mainPanel.add(editTextPanel);
        initButtons();
        mainPanel.add(buttonsPanel);
        return mainPanel;
    }

    private void initEditTextPanel() {
        editTextPanel = new JPanel();
        JLabel label = new JLabel("Edit Text");
        editTextPanel.add(label);
        textField = new JTextField(20);
        editTextPanel.add(textField);
    }


    private void initButtons() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        readButton = new JButton("read");
        readButton.addActionListener(e -> onClickRead());


        updateButton = new JButton("update");
        updateButton.addActionListener(e -> onClickUpdate());

        buttonsPanel.add(readButton);
        buttonsPanel.add(updateButton);
    }

    private void onClickRead() {
        readButton.setText(textField.getText());
    }

    private void onClickUpdate() {
        String helpStr = readButton.getText();
        readButton.setText(updateButton.getText());
        updateButton.setText(helpStr);
    }
}
