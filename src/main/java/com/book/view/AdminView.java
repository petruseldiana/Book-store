package com.book.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {

    private static final String TITLE = "Admin view";

    private static final int WIDTH = 1050;
    private static final int HEIGHT = 700;

    private JLabel label = new JLabel("ADMINISTRATOR");
    private JLabel userInfo = new JLabel("~USER INFORMATION~");
    private JLabel firstNameLabel = new JLabel("First name:");
    private JLabel lastNameLabel = new JLabel("Last name:");
    private JLabel addressLabel = new JLabel("Address:");
    private JLabel emailLabel = new JLabel("E-mail:");
    private JLabel phoneLabel = new JLabel("Phone:");
    private JLabel salaryLabel = new JLabel("Salary: ");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JLabel roleLabel = new JLabel("Role:");

    private JLabel bookInfo = new JLabel("~BOOK INFORMATION~");
    private JLabel titleLabel = new JLabel("Title: ");
    private JLabel authorLabel = new JLabel("Author: ");
    private JLabel genreLabel = new JLabel("Genre: ");
    private JLabel quantityLabel = new JLabel("Quantity: ");
    private JLabel priceLabel = new JLabel("Price: ");
    private JLabel bookOutOfStore = new JLabel("Generate book report:");

    private JTextField titleTextField = new JTextField();
    private JTextField authorTextField = new JTextField();
    private JTextField genreTextField = new JTextField();
    private JTextField quantityTextField = new JTextField();
    private JTextField priceTextField = new JTextField();

    private JTextField firstNameTextField = new JTextField();
    private JTextField lastNameTextField = new JTextField();
    private JTextField addressTextField = new JTextField();
    private JTextField emailTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JTextField salaryTextField = new JTextField();
    private JTextField usernameTextField = new JTextField();
    private JTextField passwordTextField = new JTextField();
    private JTextField roleTextField = new JTextField();

    private JSeparator separator = new JSeparator();
    private JSeparator separator1 = new JSeparator();
    private JSeparator separator2 = new JSeparator();

    private JButton logoutButton = new JButton("Logout");
    private JButton addUserButton = new JButton("ADD");
    private JButton updateUserButton = new JButton("UPDATE");
    private JButton deleteUserButton = new JButton("DELETE");
    private JButton listUserButton = new JButton("LIST");
    private JButton addBookButton = new JButton("ADD");
    private JButton updateBookButton = new JButton("UPDATE");
    private JButton deleteBookButton = new JButton("DELETE");
    private JButton listBookButton = new JButton("LIST");
    private JButton getReportButton = new JButton("GET REPORT");

    private JCheckBox checkboxPDF = new JCheckBox("PDF",false);
    private JCheckBox checkBoxTXT = new JCheckBox("TXT",false);

    Image img1= new ImageIcon("img/admin1.jpg").getImage();
    private JLabel imgLabel = new JLabel("");

    public AdminView() throws HeadlessException {
        super(TITLE);
        initializeView();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeView() {
        setLayout(null);

        imgLabel.setIcon(new ImageIcon(img1));
        imgLabel.setBounds(0, 0, 1100, 700);

        separator.setBounds(30, 65, 1000, 8);

        separator1.setBounds(420,65,8,500);
        separator1.setOrientation(SwingConstants.VERTICAL);

        separator2.setBounds(800,65,8,500);
        separator2.setOrientation(SwingConstants.VERTICAL);

        label.setBounds(450,10,300,50);
        label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 30));

        userInfo.setBounds(75,75,400,50);
        userInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 25));

        firstNameLabel.setBounds(10, 130, 130, 25);
        firstNameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        lastNameLabel.setBounds(13, 165, 130, 25);
        lastNameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        addressLabel.setBounds(30, 200, 85, 25);
        addressLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        emailLabel.setBounds(42, 235, 85, 25);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        phoneLabel.setBounds(43, 270, 100, 25);
        phoneLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        salaryLabel.setBounds(43, 305, 100, 25);
        salaryLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        usernameLabel.setBounds(15, 340, 100, 25);
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        passwordLabel.setBounds(15, 375, 100, 25);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        roleLabel.setBounds(56, 410, 85, 25);
        roleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        bookInfo.setBounds(480,75,400,50);
        bookInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 25));
        titleLabel.setBounds(450, 195, 130, 25);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        authorLabel.setBounds(450, 230, 130, 25);
        authorLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        genreLabel.setBounds(450, 265, 85, 25);
        genreLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        quantityLabel.setBounds(450, 300, 85, 25);
        quantityLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        priceLabel.setBounds(450, 335, 100, 25);
        priceLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        bookOutOfStore.setBounds(810,180,450,50);
        bookOutOfStore.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 19));

        firstNameTextField.setBounds(120, 130, 200, 25);
        firstNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lastNameTextField.setBounds(120, 165, 200, 25);
        lastNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        addressTextField.setBounds(120, 200, 200, 25);
        addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailTextField.setBounds(120, 235, 200, 25);
        emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        phoneTextField.setBounds(120, 270, 200, 25);
        phoneTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        salaryTextField.setBounds(120, 305, 200, 25);
        salaryTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        usernameTextField.setBounds(120, 340, 200, 25);
        usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordTextField.setBounds(120, 375, 200, 25);
        passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        roleTextField.setBounds(120, 410, 200, 25);
        roleTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));

        titleTextField.setBounds(550, 195, 200, 25);
        titleTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        authorTextField.setBounds(550, 230, 200, 25);
        authorTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        genreTextField.setBounds(550, 265, 200, 25);
        genreTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        quantityTextField.setBounds(550, 300, 200, 25);
        quantityTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        priceTextField.setBounds(550, 335, 200, 25);
        priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));

        logoutButton.setBounds(910,600,100,40);
        logoutButton.setBackground(new Color(0, 0, 0));
        logoutButton.setForeground(new Color(255, 255, 255));
        logoutButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        addUserButton.setBounds(30,460,150,50);
        addUserButton.setBackground(new Color(0, 0, 0));
        addUserButton.setForeground(new Color(255, 255, 255));
        addUserButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        deleteUserButton.setBounds(200,460,150,50);
        deleteUserButton.setBackground(new Color(0, 0, 0));
        deleteUserButton.setForeground(new Color(255, 255, 255));
        deleteUserButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        updateUserButton.setBounds(30,530,150,50);
        updateUserButton.setBackground(new Color(0, 0, 0));
        updateUserButton.setForeground(new Color(255, 255, 255));
        updateUserButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        listUserButton.setBounds(200,530,150,50);
        listUserButton.setBackground(new Color(0, 0, 0));
        listUserButton.setForeground(new Color(255, 255, 255));
        listUserButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        addBookButton.setBounds(470,440,150,50);
        addBookButton.setBackground(new Color(0, 0, 0));
        addBookButton.setForeground(new Color(255, 255, 255));
        addBookButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        deleteBookButton.setBounds(640,440,150,50);
        deleteBookButton.setBackground(new Color(0, 0, 0));
        deleteBookButton.setForeground(new Color(255, 255, 255));
        deleteBookButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        updateBookButton.setBounds(470,510,150,50);
        updateBookButton.setBackground(new Color(0, 0, 0));
        updateBookButton.setForeground(new Color(255, 255, 255));
        updateBookButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        listBookButton.setBounds(640,510,150,50);
        listBookButton.setBackground(new Color(0, 0, 0));
        listBookButton.setForeground(new Color(255, 255, 255));
        listBookButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        getReportButton.setBounds(820,330,180,50);
        getReportButton.setBackground(new Color(0, 0, 0));
        getReportButton.setForeground(new Color(255, 255, 255));
        getReportButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        checkboxPDF.setBounds(860,250,50,50);
        checkboxPDF.setBackground(new Color(255,0,0));
        checkBoxTXT.setBounds(940,250,50,50);
        checkBoxTXT.setBackground(new Color(255,0,0));

        add(checkboxPDF);
        add(checkBoxTXT);

        add(label);
        add(separator1);
        add(separator2);
        add(separator);
        //employee info labels
        add(userInfo);
        add(firstNameLabel);
        add(lastNameLabel);
        add(addressLabel);
        add(emailLabel);
        add(phoneLabel);
        add(salaryLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(roleLabel);
        add(bookInfo);
        add(titleLabel);
        add(authorLabel);
        add(genreLabel);
        add(quantityLabel);
        add(priceLabel);
        add(bookOutOfStore);
        //employee info text fields
        add(firstNameTextField);
        add(lastNameTextField);
        add(addressTextField);
        add(emailTextField);
        add(phoneTextField);
        add(salaryTextField);
        add(usernameTextField);
        add(passwordTextField);
        add(roleTextField);
        add(titleTextField);
        add(authorTextField);
        add(genreTextField);
        add(quantityTextField);
        add(priceTextField);
        //buttons
        add(logoutButton);
        add(addUserButton);
        add(deleteUserButton);
        add(updateUserButton);
        add(listUserButton);
        add(addBookButton);
        add(deleteBookButton);
        add(updateBookButton);
        add(listBookButton);
        add(getReportButton);
        add(imgLabel);
    }

//    public void addCheckBoxPDF(ActionListener listener){
//        checkboxPDF.addActionListener(listener);
//    }
//
//    public void addCheckBoxTXT(ActionListener listener){
//        checkBoxTXT.addActionListener(listener);
//    }

    public boolean getPDF(){
        if(checkboxPDF.isSelected() == true){
            return true;
        }else{
            return false;
        }
    }

    public boolean getTXT(){
        if(checkBoxTXT.isSelected() == true){
            return true;
        }else{
            return false;
        }
    }

    public void addLogoutActionListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addAddUserButtonActionListener(ActionListener listener){
        addUserButton.addActionListener(listener);
    }

    public void addDelUserButtonActionListener(ActionListener listener){
        deleteUserButton.addActionListener(listener);
    }

    public void addUpdateUserButtonActionListener(ActionListener listener){
        updateUserButton.addActionListener(listener);
    }

    public void addListUserButtonActionListener(ActionListener listener){
        listUserButton.addActionListener(listener);
    }

    public void addAddBookButtonActionListener(ActionListener listener){
        addBookButton.addActionListener(listener);
    }

    public void addDelBookButtonActionListener(ActionListener listener){
        deleteBookButton.addActionListener(listener);
    }

    public void addUpdateBookButtonActionListener(ActionListener listener){
        updateBookButton.addActionListener(listener);
    }

    public void addListBookButtonActionListener(ActionListener listener){
        listBookButton.addActionListener(listener);
    }

    public void addReportButtonActionListener(ActionListener listener){
        getReportButton.addActionListener(listener);
    }

    public String getFirstName(){
        return firstNameTextField.getText();
    }

    public String getLastName(){
        return lastNameTextField.getText();
    }

    public String getAddress(){
        return addressTextField.getText();
    }

    public String getEmail(){
        return emailTextField.getText();
    }

    public String getPhone(){
        return phoneTextField.getText();
    }

    public String getSalary(){
        return salaryTextField.getText();
    }

    public String getUsername(){
        return usernameTextField.getText();
    }

    public String getPassword(){
        return passwordTextField.getText();
    }

    public String getRole(){
        return roleTextField.getText();
    }

    public String getTitle(){
        return titleTextField.getText();
    }

    public String getAuthor(){
        return authorTextField.getText();
    }

    public String getGenre(){
        return genreTextField.getText();
    }

    public String getQuantity(){
        return quantityTextField.getText();
    }

    public String getPrice(){
        return priceTextField.getText();
    }

    public void setAddress(String address){
        addressTextField.setText(address);
    }

    public void setPhone(String phone){
        phoneTextField.setText(phone);
    }

    public void setEmail(String email){
        emailTextField.setText(email);
    }

    public void setSalary(String salary){
        salaryTextField.setText(salary);
    }

    public void setFirstName(String firstName){
        firstNameTextField.setText(firstName);
    }

    public void setLastName(String lastName){
        lastNameTextField.setText(lastName);
    }

    public void setUsername(String username){
        usernameTextField.setText(username);
    }

    public void setPassword(String password){
        passwordTextField.setText(password);
    }

    public void setRole(String role){
        roleTextField.setText(role);
    }

    public void setTitle(String title){
        titleTextField.setText(title);
    }

    public void setAuthor(String author){
        authorTextField.setText(author);
    }

    public void setGenreTextField(String genre){
        genreTextField.setText(genre);
    }

    public void setQuantity(String quantity){
        quantityTextField.setText(quantity);
    }

    public void setPrice(String price){
        priceTextField.setText(price);
    }
}
