package com.company.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;

class Task5 implements  KeyListenerMethods{
    private JTextField textField;
    private JTable jTable;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private Thread thread;
    private JButton button2;
    private JButton button3;
    private JButton sendText;

    JPanel run() {
        mainPanel = new JPanel();

        initEditTextPanel();
        jTable = getJTable();
        tableModel = (DefaultTableModel) jTable.getModel();
        initButtonPanel();
        mainPanel.add(textField);
        mainPanel.add(sendText);


        mainPanel.add(button2);
        mainPanel.add(button3);
        mainPanel.add(jTable);

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

    private void initButtonPanel() {

        button2 = new JButton("button 2");
        button3 = new JButton("button 3");

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
        textField = new JTextField(10);
        sendText = new JButton("Push");
        sendText.addActionListener(e -> onClickSend());
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