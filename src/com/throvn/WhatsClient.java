package com.throvn;

import java.util.ArrayList;
import java.util.Arrays;


public class WhatsClient extends Client {
    private int myID = -1;
    private Window win;
    public ArrayList<Chat> msgs = new ArrayList<>();

    public WhatsClient(String ip, int port, Window win) {
        super(ip, port);
        this.win = win;
    }

    @Override
    public void processMessage(String pMessage) {
        if (pMessage.contains("CREATED,USER") || pMessage.contains("LOGGED_IN")) { // Denkfehler Created user does not send id yet.
            myID = Integer.parseInt(pMessage.split("LOGGED_IN ")[1]);
            send("SEND GROUPS");
            win.loggedIn();
        } else if (pMessage.contains("CREDENTIALS_WRONG")) {
            System.out.println("Wrong credentials");
        } else if (pMessage.indexOf("GROUPS") == 0) {
            ArrayList<Group> nG = new ArrayList<>();
            String [] m = pMessage.split("::::");
            m = Arrays.copyOfRange(m, 1, m.length-1);
            for (int i = 0; i < m.length-1; i = i+4) {
                nG.add(new Group(Integer.parseInt(m[i]), m[i+1], m[i+2]));
            }
            win.drawGroups(nG); // Bens methode
        } else if (pMessage.indexOf("GET CHAT") == 0) {
            msgs = new ArrayList<>();
            String [] m = pMessage.split("::::");
            for (int i = 1; i < m.length-1; i = i+3) {
                System.out.println(m[i] + "  " + m[i+1] + "  " + m[i+2]);
                msgs.add(new Chat(m[i], m[i+1], m[i+2]));
            }
            win.drawChats(msgs);
        } else if (pMessage.indexOf("RECIVE MESSAGE") == 0) {
            String[] m = pMessage.split("::::");
            String[] ms = Arrays.copyOfRange(m, 3, m.length);
            msgs.add(new Chat(m[1], String.join("::::", ms), m[2]));
            win.drawChats(msgs);
            System.out.println(msgs.toString());
        } else if (pMessage.indexOf("SUCCESS;CREATED,GROUP") == 0) {
            win.closeCreatedGroup();
            send("SEND GROUPS");
        }
    }

    public int getMyID() {
        return myID;
    }
}
