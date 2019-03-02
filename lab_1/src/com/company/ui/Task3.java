package com.company.ui;
import javax.swing.*;

class Task3 {
    private JTextField textField;
    private JPanel editTextPanel;
    private JPanel radioButtonPanel;

    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;

    private ButtonGroup buttonGroup;

    JPanel run() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initEditTextPanel();
        mainPanel.add(editTextPanel);
        initRadioButtonPanel();
        mainPanel.add(radioButtonPanel);
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
        radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));
        radioButton1 = new JRadioButton("radio button1");
        radioButton2 = new JRadioButton("radio button2");
        radioButton3 = new JRadioButton("radio button3");
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        radioButtonPanel.add(radioButton1);
        radioButtonPanel.add(radioButton2);
        radioButtonPanel.add(radioButton3);
    }
}