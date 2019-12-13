package com.throvn;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Window {
    private ChatWindow cw;
    private WhatsClient wc = new WhatsClient("localhost", 3000, this);

    public Window() {
        cw = new ChatWindow(this);

        JFrame frame = new JFrame();
        frame.setTitle("Welcome to WhatsChat.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(cw.panel);
        frame.setSize(300, 500);
        frame.pack();
        frame.setVisible(true);

        wc.send("LOG IN;Louis1,Stanko");
    }

    public void drawGroups(ArrayList<Group> groupList) {
        groupList.forEach((n) -> {
            cw.addGroup(n.getName(), n.getId());
        });
    }

    public void drawChats(ArrayList<Chat> chatList) {
        chatList.forEach((n) -> {
            cw.addChat(n.getUser(), n.getMsg());
        });
    }

    public void pressed(ActionEvent a) {
        JButton button = (JButton) a.getSource();
        System.out.println(button.getText() + " : "+ button.getClientProperty("id"));
    }
}
