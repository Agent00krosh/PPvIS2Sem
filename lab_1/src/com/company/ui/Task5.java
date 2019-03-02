package com.company.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Task5 {
    private JTextField textField;
    private JPanel editTextPanel;
    private JPanel buttonPanel;
    private JTable jTable;
    private DefaultTableModel tableModel;

    JPanel run() {
        JPanel mainPanel = new JPanel();

        initEditTextPanel();
        jTable = getJTable();
        tableModel = (DefaultTableModel) jTable.getModel();
        initButtonPanel();
        mainPanel.add(editTextPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(jTable);
        return mainPanel;
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        JButton button2 = new JButton("button 2");
        JButton button3 = new JButton("button 3");
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        button2.addActionListener(e -> onClickButton2());
        button3.addActionListener(e -> onClickButton3());
    }

    private void onClickButton2() {
        if (checkError()) {
            return;
        }
        String text = (String) tableModel.getValueAt(jTable.getSelectedRow(), 0);
        if (text.equals("")) {
            errorIsVisible("Error");
            return;
        }
        tableModel.setValueAt("", jTable.getSelectedRow(), 0);
        tableModel.setValueAt(text, jTable.getSelectedRow(), 1);

    }

    private void onClickButton3() {
        if (checkError()) {
            return;
        }

        String text = (String) tableModel.getValueAt(jTable.getSelectedRow(), 1);
        if (text.equals("")) {
            errorIsVisible("Error");
            return;
        }
        tableModel.setValueAt("", jTable.getSelectedRow(), 1);
        tableModel.setValueAt(text, jTable.getSelectedRow(), 0);
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

    private boolean checkError() {
        if (tableModel.getRowCount() == 0) {
            errorIsVisible("table is empty");
            return true;
        }

        if (jTable.getSelectedRow() == -1) {
            errorIsVisible("row is not selected");
            return true;
        }
        return false;
    }

    private void onClickSend() {
        addTableData();
        textField.setText("");
    }

    private JTable getJTable() {
        String[] colName = {"Column 1", "Column 2"};
        if (jTable == null) {
            jTable = new JTable() {
                public boolean isCellEditable(int nRow, int nCol) {
                    return false;
                }
            };
        }
        DefaultTableModel contactTableModel = (DefaultTableModel) jTable
                .getModel();
        contactTableModel.setColumnIdentifiers(colName);
        return jTable;
    }

    private void addTableData() {
        String[] data = new String[2];
        data[0] = textField.getText();
        data[1] = "";
        tableModel.addRow(data);
        jTable.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    private void errorIsVisible(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.PLAIN_MESSAGE);
    }

}