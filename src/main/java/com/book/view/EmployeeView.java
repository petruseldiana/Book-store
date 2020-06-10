package com.book.view;

import com.book.model.presentation.ShoppingBasket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {

    private static final String TITLE = "Admin view";

    private static final int WIDTH = 1390;
    private static final int HEIGHT = 650;

    private Long shoppingBasketId;

    private JLabel label = new JLabel("EMPLOYEE");
    private JLabel titleLabel = new JLabel("Title:");
    private JLabel authorLabel = new JLabel("Author:");
    private JLabel genreLabel = new JLabel("Genre:");
    private JLabel shoppingBasketLabel = new JLabel("~ Shopping Basket ~");

    private JLabel bookInfo = new JLabel("~ Books information ~");
    private final JLabel totalPriceLabel = new JLabel("Total price: ");
    private final JTextField shoppingBasketTotalPrice = new JTextField();
    private final JLabel currentUserLabel;
    private JLabel insertQuantity = new JLabel("Quantity: ");

    private JSeparator separator = new JSeparator();
    private JSeparator separator1 = new JSeparator();
    private JSeparator separator2 = new JSeparator();

    private JButton logoutButton = new JButton("Logout");

    private JButton searchButton = new JButton("SEARCH");
    private JButton sellButton = new JButton("SELL");
    private JButton showButton = new JButton("SHOW");
    private JButton addToBasketButton = new JButton("ADD");

    private JTable bookTable = new JTable();
    private JScrollPane scrollPane = new JScrollPane();

    private JTable shoppingBasketTable = new JTable();
    private JScrollPane scrollPaneBasket = new JScrollPane();

    private JTextField titleTextField  = new JTextField();
    private JTextField authorTextField  = new JTextField();
    private JTextField genreTextField  = new JTextField();

    private SpinnerNumberModel quantity;
    private JSpinner spinner;

    Image img1= new ImageIcon("img/employee6.jpg").getImage();
    private JLabel imgLabel = new JLabel("");

    public EmployeeView() throws HeadlessException {
        super(TITLE);
        initializeView();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        currentUserLabel = new JLabel("-empty-");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeView() {
        setLayout(null);

        imgLabel.setIcon(new ImageIcon(img1));
        imgLabel.setBounds(0, 0, 1390, 650);

        separator.setBounds(30, 65, 1370, 8);

        separator1.setBounds(380,65,8,500);
        separator1.setOrientation(SwingConstants.VERTICAL);

        separator2.setBounds(900,65,8,500);
        separator2.setOrientation(SwingConstants.VERTICAL);

        label.setBounds(600,10,300,50);
        label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 30));

        titleLabel.setBounds(50, 180, 85, 25);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        authorLabel.setBounds(30, 225, 85, 25);
        authorLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        genreLabel.setBounds(42, 270, 85, 25);
        genreLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        titleTextField .setBounds(120, 180, 200, 25);
        titleTextField .setFont(new Font("Tahoma", Font.PLAIN, 15));
        authorTextField .setBounds(120, 225, 200, 25);
        authorTextField .setFont(new Font("Tahoma", Font.PLAIN, 15));
        genreTextField .setBounds(120, 270, 200, 25);
        genreTextField .setFont(new Font("Tahoma", Font.PLAIN, 15));

        logoutButton.setBounds(1250,550,100,40);
        logoutButton.setBackground(new Color(0, 0, 0));
        logoutButton.setForeground(new Color(255, 255, 255));
        logoutButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        searchButton.setBounds(140,400,150,50);
        searchButton.setBackground(new Color(0, 0, 0));
        searchButton.setForeground(new Color(255, 255, 255));
        searchButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        sellButton.setBounds(1210,410,150,50);
        sellButton.setBackground(new Color(0, 0, 0));
        sellButton.setForeground(new Color(255, 255, 255));
        sellButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        showButton.setBounds(670,460,150,50);
        showButton.setBackground(new Color(0, 0, 0));
        showButton.setForeground(new Color(255, 255, 255));
        showButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        addToBasketButton.setBounds(440,460,150,50);
        addToBasketButton.setBackground(new Color(0, 0, 0));
        addToBasketButton.setForeground(new Color(255, 255, 255));
        addToBasketButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));

        bookInfo.setBounds(500, 80,300,100);
        bookInfo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));

        shoppingBasketLabel.setBounds(1000, 80,300,100);
        shoppingBasketLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));

        scrollPane.setEnabled(false);
        scrollPane.setBounds(405,200,475,250);
        scrollPane.setViewportView(bookTable);

        scrollPaneBasket.setEnabled(false);
        scrollPaneBasket.setBounds(910,200,450,200);
        scrollPaneBasket.setViewportView(shoppingBasketTable);

        shoppingBasketTotalPrice.setBounds(1150, 480,150,30);
        shoppingBasketTotalPrice.setFont(new Font("Tahoma", Font.ITALIC, 20));

        totalPriceLabel.setBounds(950, 480,300,30);
        totalPriceLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));

        insertQuantity.setBounds(420,550,150,30);
        insertQuantity.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC | Font.CENTER_BASELINE, 20));

        quantity = new SpinnerNumberModel();
        quantity.setValue(0);
        spinner = new JSpinner(quantity);
        spinner.setBounds(550, 550,70,40);

        add(totalPriceLabel);
        add(insertQuantity);
        add(spinner);
        add(shoppingBasketTotalPrice);
        add(shoppingBasketLabel);
        add(label);
        add(logoutButton);
        add(titleLabel);
        add(separator);
        //employee info labels
        add(authorLabel);
        add(genreLabel);
        add(bookInfo);
        //employee info text fields
        add(titleTextField );
        add(authorTextField );
        add(genreTextField );
        //buttons
        add(addToBasketButton);
        add(searchButton);
        add(sellButton);
        add(showButton);
        add(separator1);
        add(separator2);
        add(scrollPane);
        add(scrollPaneBasket);
        add(imgLabel);
    }

    public void addLogoutActionListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addAddButtonActionListener(ActionListener listener){
        addToBasketButton.addActionListener(listener);
    }

    public void addShowActionListener(ActionListener listener) {
        showButton.addActionListener(listener);
    }

    public void addSellButtonActionListener(ActionListener listener){
        sellButton.addActionListener(listener);
    }

    public void addSearchActionListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public Long getSelectedBookId(){
        return (Long) bookTable.getValueAt(bookTable.getSelectedRow(),0);
    }

    public String getSelectedBookTitle(){
        return (String) bookTable.getValueAt(bookTable.getSelectedRow(),1);
    }

    public String getSelectedBookAuthor(){
        return (String) bookTable.getValueAt(bookTable.getSelectedRow(),2);
    }

    public String getSelectedBookGenre(){
        return (String) bookTable.getValueAt(bookTable.getSelectedRow(),3);
    }

    public int getSelectedBookQuantity(){
        return (int) bookTable.getValueAt(bookTable.getSelectedRow(),4);
    }

    public double getSelectedBookPrice(){
        return (double) bookTable.getValueAt(bookTable.getSelectedRow(),5);
    }

    public String getTitleTextField(){
        return titleTextField.getText();
    }

    public String getAuthorTextField(){
        return authorTextField.getText();
    }

    public String getGenreTextField(){
        return genreTextField.getText();
    }

    public void setLoggedInUser(String userName) {
        this.currentUserLabel.setText("Logged in as: " + userName);
    }

    public void setShoppingBasketId(Long shoppingBasketId) {
        this.shoppingBasketId = shoppingBasketId;
    }

    public Long getShoppingBasketId() {
        return this.shoppingBasketId;
    }

    public int getQuantity() {
        return quantity.getNumber().intValue();
    }

    public void setTotalPriceLabel(String price){
        shoppingBasketTotalPrice.setText(price);
    }

    public void refreshBookTable(Object[][] data, String[] columnNames) {

        DefaultTableModel tableModel = (DefaultTableModel) bookTable.getModel();

        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void refreshShoppingBasketTable(Object[][] data, String[] columnNames) {

        DefaultTableModel tableModel = (DefaultTableModel) shoppingBasketTable.getModel();

        tableModel.setDataVector(data, columnNames);
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.fireTableDataChanged();
    }

    public void refreshSelectedShoppingBasket(ShoppingBasket shoppingBasket, Object[][] data, String[] columnNames) {

        refreshShoppingBasketTable(data, columnNames);
        double sum = shoppingBasket.getShoppingBasketBooks().stream().mapToDouble(o -> o.getBook().getPrice() * o.getQuantity()).sum();
        shoppingBasketTotalPrice.setText(String.valueOf(sum) + " RON");
    }
}
