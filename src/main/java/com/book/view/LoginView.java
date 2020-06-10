package com.book.view;

import com.book.model.service.ContextHolder;
import com.book.model.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{

    private static final String TITLE = "Login";

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    Image img1= new ImageIcon("img/login.jpg").getImage();

    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JLabel imgLabel = new JLabel("");
    private JLabel login = new JLabel("WELCOME");

    private JTextField usernameTextField = new JTextField();
    private JPasswordField passwordTextField = new JPasswordField();

    private JButton loginButton = new JButton("Login");

    private JSeparator separator = new JSeparator();


    public LoginView() throws HeadlessException {
        super(TITLE);
        initializeView();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void initializeView() {
        setLayout(null);

        login.setBounds(150, 85, 300, 50);
        login.setForeground(new Color(0,0,0));
        login.setFont(new Font("Tahoma", Font.BOLD , 50));

        usernameLabel.setBounds(70, 182, 130, 25);
        usernameLabel.setForeground(new Color(0, 0, 0));
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        passwordLabel.setBounds(70, 242, 130, 25);
        passwordLabel.setForeground(new Color(0, 0, 0));
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));

        imgLabel.setIcon(new ImageIcon(img1));
        imgLabel.setBounds(0, 0, 600, 400);

        usernameTextField.setBounds(190, 180, 250, 35);
        usernameTextField.setFont(new Font("Tahoma",Font.PLAIN, 15));

        passwordTextField.setBounds(190, 240, 250, 35);
        passwordTextField.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 15));

        loginButton.setBounds(290, 290, 150, 35);
        loginButton.setBackground(new Color(0, 0, 0));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        separator.setBounds(70, 140, 460, 8);
        separator.setBackground(new Color(0,0,0));

        add(login);
        add(usernameLabel);
        add(passwordLabel);

        add(usernameTextField);
        add(passwordTextField);

        add(loginButton);

        add(separator);
        add(imgLabel);

    }

    public void addLoginActionListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public String getUserName() {
        return this.usernameTextField.getText();
    }

    public String getPassword() {
        return this.passwordTextField.getText();
    }

    public void cleatInputs() {
        this.usernameTextField.setText("");
        this.passwordTextField.setText("");
    }

    public LoginView getOuter() {

        return LoginView.this;
    }
}
