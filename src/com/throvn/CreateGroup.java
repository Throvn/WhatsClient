package com.throvn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGroup implements ActionListener {
    private WhatsClient client;
    private JFrame win = new JFrame();
    private JPanel panel = new JPanel();
    private JTextField groupName = new JTextField();
    private JTextField desc = new JTextField();

    public CreateGroup(WhatsClient client) {
        this.client = client;

        win.setTitle("WhatsChat");
        win.setResizable(false);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setContentPane(panel);
        win.setLayout(null);
        win.pack();
        win.setSize(200, 200);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - win.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - win.getHeight()) / 2);
        win.setLocation(x, y);

        JLabel login1 = new JLabel("Create/Join Group");
        login1.setBounds(20, 20, 160, 30);
        login1.setFont(new Font("Dialog", Font.BOLD, 12));
        login1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(login1);
        groupName.setBounds(20, 60, 160, 30);
        panel.add(groupName);
        desc.setBounds(20, 100, 160, 30);
        panel.add(desc);

        JButton create = new JButton("Create");
        create.putClientProperty("statement", "CREATE GROUP;");
        create.addActionListener(this);
        create.setBounds(20, 140, 80, 30);

        JButton join = new JButton("Join");
        join.putClientProperty("statement", "JOIN;");
        join.addActionListener(this);
        join.setBounds(100, 140, 80, 30);

        panel.add(create);
        panel.add(join);

        win.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        String msg = b.getClientProperty("statement") + groupName.getText() + "," + desc.getText();
        if (b.getClientProperty("statement").equals("JOIN;")) {
            msg = b.getClientProperty("statement") + groupName.getText() + "," + client.getMyID();
        }
        client.send(msg);
    }

    public void dispose () {
        win.dispose();
    }
}
