package com.company.ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Task4 {
    private JTextField textField;
    private JPanel editTextPanel;
    private JPanel checkBoxPanel;
    private List<JCheckBox> checkBoxes;

    JPanel run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initEditTextPanel();
        mainPanel.add(editTextPanel);
        initCheckBoxPanel();
        mainPanel.add(checkBoxPanel);
        return mainPanel;
    }

    private void initEditTextPanel() {
        editTextPanel = new JPanel();
        JLabel label = new JLabel("Edit Text ");
        editTextPanel.add(label);
        textField = new JTextField(20);
        editTextPanel.add(textField);
        JButton pushButton = new JButton("Push");
        pushButton.addActionListener(e -> onClickPushButton());
        editTextPanel.add(pushButton);
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
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        for (int i = 1; i <= 3; i++) {
            checkBoxes.add(new JCheckBox("box " + i));
            checkBoxPanel.add(checkBoxes.get(i - 1));
        }
    }

    private List<String> getNamesOfBoxes() {
        String text = textField.getText();
        return Arrays.asList(text.split(", "));
    }

}