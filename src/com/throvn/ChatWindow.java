package com.throvn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ChatWindow implements ActionListener {
    private ArrayList<String[]> groupData = new ArrayList<>();
    private DefaultListModel<String> list = new DefaultListModel<>();
    private JList groups = new JList(list);
    private JTextPane chatDisplay = new JTextPane();
    private Window win;
    private JFrame f = new JFrame();

    public ChatWindow(Window win) {
        super();
        this.win = win;


        f.setSize(400, 600);
        chatDisplay.setText("Click on a group and start chatting.");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
        f.setLocation(x, y);

        f.setLayout(new BorderLayout());

        groups.setPreferredSize(new Dimension(f.getWidth() / 3, f.getHeight() / 2));
        groups.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.lightGray));

        chatDisplay.setEditable(false);
        chatDisplay.setContentType("text/html");

        f.add(groups, BorderLayout.LINE_START);
        f.add(chatDisplay, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        controls.setLayout(new BorderLayout());

        JButton createGroup = new JButton("Create Group");
        createGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(((JButton)e.getSource()).getText());
                win.openCreateGroup();
            }
        });

        controls.add(createGroup, BorderLayout.WEST);

        JTextField messageField = new JTextField();
        messageField.setText("Message");
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    JTextField msg = (JTextField)e.getSource();
                    win.sendMsg(msg.getText().replace("\n", ""));
                }
            }
        });

        controls.add(messageField, BorderLayout.CENTER);

        f.add(controls, BorderLayout.PAGE_END);

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList list = (JList)e.getSource();
                int index = list.locationToIndex(e.getPoint());
                if (index > -1) {
                    String name = (String)list.getModel().getElementAt(index);
                    groupData.forEach((element) -> {
                        if (name.equals(element[0])) {
                            win.getChat(Integer.parseInt(element[1]));
                        }
                    });
                }
            }
        };
        groups.addMouseListener(ml);
    }

    public void addGroup(String name, int id) {
        System.out.println(name + " " + id);
        groupData.add(new String[] {name, String.valueOf(id)});
        list.addElement(name);

        f.setVisible(true);
    }

    public void clearGroups() {
        groupData.clear();
    }

    public void addChat(String msg) {
        chatDisplay.setText(msg);
    }

    public void actionPerformed(ActionEvent a) {
        win.pressed(a);
    }
}