package com.throvn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    private JFrame win = new JFrame();
    private JPanel panel = new JPanel();
    private JTextField name = new JTextField();
    private JPasswordField pswd = new JPasswordField();
    private WhatsClient client;

    public Login(WhatsClient client) {
        super();
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

        JLabel login1 = new JLabel("Welcome");
        login1.setBounds(20, 20, 160, 30);
        login1.setFont(new Font("Dialog", Font.BOLD, 12));
        login1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(login1);
        name.setBounds(20, 60, 160, 30);
        panel.add(name);
        pswd.setBounds(20, 100, 160, 30);
        panel.add(pswd);

        // Test
        name.setText("abc");
        pswd.setText("123");

        JButton login = new JButton("Login");
        login.putClientProperty("statement", "LOG IN;");
        login.addActionListener(this);
        login.setBounds(20, 140, 80, 30);

        JButton signup = new JButton("Signup");
        signup.putClientProperty("statement", "CREATE USER;");
        signup.addActionListener(this);
        signup.setBounds(100, 140, 80, 30);

        panel.add(login);
        panel.add(signup);

        win.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        String msg = b.getClientProperty("statement") + name.getText() + "," + pswd.getText();
        client.send(msg);
    }

    public void dispose () {
        win.dispose();
    }
}
