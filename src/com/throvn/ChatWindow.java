package com.throvn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChatWindow implements ActionListener {
    private JButton button1;
    private ArrayList<JButton> buttons = new ArrayList<>();
    public JPanel panel = new JPanel();
    private Window win;

    public ChatWindow(Window win) {
        this.win = win;
    }

    public void makeButtons (JPanel panel) {

        for(int i = 0; i < 10; i++) {
            JButton b = new JButton("button"+i);
            b.addActionListener(this);
            buttons.add(b);
            panel.add(b);
        }
    }

    public void addGroup(String name, int id) {
        JButton b = new JButton(name);
        b.putClientProperty("id", id);
        b.addActionListener(this);
        buttons.add(b);
        panel.add(b);
    }

    public void addChat(String name, String msg) {
        JLabel l = new JLabel(name+":   "+msg);
        panel.add(l);
    }

    public void actionPerformed(ActionEvent a) {
        win.pressed(a);

    }
}