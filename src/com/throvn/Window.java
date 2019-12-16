package com.throvn;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Window {
    private ChatWindow cw;
    private WhatsClient wc = new WhatsClient("localhost", 3000, this);
    private Login l;
    private CreateGroup cg;
    private int activeGroupID = -1;

    public Window() {
        l = new Login(wc);
    }

    public void drawGroups(ArrayList<Group> groupList) {
        cw.clearGroups();
        groupList.forEach((n) -> {
            cw.addGroup(n.getName(), n.getId());
        });
    }

    public void drawChats(ArrayList<Chat> chatList) {
        String msg = "";
        for (int i = 0; i < chatList.size(); i++) {
            msg += chatList.get(i).getUser() + ":   " + chatList.get(i).getMsg() + "<br>";
        }
        cw.addChat(msg);
    }

    public void pressed(ActionEvent a) {
        JButton button = (JButton) a.getSource();
        System.out.println(button.getText() + " : "+ button.getClientProperty("id"));
    }

    public void loggedIn() {
        l.dispose();
        l = null;
        cw = new ChatWindow(this);
    }

    public void getChat(int groupID) {
        wc.send("SEND CHAT;"+groupID);
        activeGroupID = groupID;
    }

    public void openCreateGroup() {
        cg = new CreateGroup(wc);
    }

    public void sendMsg(String msg) {
        System.out.println("MSG: "+msg);
        if (activeGroupID != -1 && msg != "") {
            wc.send("SEND TO;"+activeGroupID+","+msg);
        }
    }

    public void closeCreatedGroup() {
        cg.dispose();
    }
}
