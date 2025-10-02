import java.io.*;
import java.awt.*;
import javax.swing.*;

public class App {
    private static Lib myLibrary = new Lib("Lucy's Library");
    private static JFrame mainFrame;
    private static JScrollPane scrollPane;
    private static JPanel mainPanel;

    public static void main(String[] args) throws Exception {
        final JFrame logInFrame = new JFrame();
        logInFrame.setSize(new Dimension(300, 300));
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JLabel mLabel = new JLabel("Log In");
        mLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        mLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel namePanel = new JPanel();
        namePanel.setMaximumSize(new Dimension(200, 150));
        namePanel.setMinimumSize(new Dimension(200, 150));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter username:");
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField name = new JTextField(20);
        Dimension fixedsize = new Dimension(name.getPreferredSize().width, 30);
        name.setMaximumSize(fixedsize);
        name.setMinimumSize(fixedsize);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel statusLabel = new JLabel("Enter Status:");
        statusLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] options = {"Admin", "Student"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setMaximumSize(fixedsize);
        comboBox.setMinimumSize(fixedsize);
        comboBox.setFont(new Font("Cambria", Font.PLAIN, 16));
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        namePanel.add(nameLabel);
        namePanel.add(name);
        namePanel.add(Box.createVerticalStrut(15));
        namePanel.add(statusLabel);
        namePanel.add(comboBox);
        
        RoundedButton loginBtn = new RoundedButton("Log in", 15);
        loginBtn.setBackground(Color.CYAN);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.addActionListener(e -> {
            String username = name.getText().trim();

            if (username.isEmpty()) {
                nameLabel.setText("Username is Required!!");
                name.setBackground(Color.red);
                name.setForeground(Color.white);
            } else {
                nameLabel.setText("Enter Username:");
                name.setBackground(Color.white);
                name.setForeground(Color.black);

                String identity = (String) comboBox.getSelectedItem();
                if (identity.equalsIgnoreCase("student")) {
                    Student s = myLibrary.getStudent(username);
                    if (s != null) {
                        go(s);
                        logInFrame.dispose();
                    } else {
                        name.setText("");
                        JOptionPane.showMessageDialog(null, "Sorry Not a registered student!!");
                        name.requestFocusInWindow();
                    }
                }  else {
                    Admin a = myLibrary.getAdmin(username);
                    if (a != null) {
                        go(a);
                        logInFrame.dispose();
                    } else {
                        name.setText("");
                        JOptionPane.showMessageDialog(null, "Sorry Not a registered Admin!!");
                        name.requestFocusInWindow();
                    }
                }
            }   
        });

        loginPanel.add(mLabel);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(namePanel);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(loginBtn);

        logInFrame.getContentPane().add(loginPanel);
        logInFrame.setVisible(true);
        logInFrame.setResizable(false);
        logInFrame.setLocationRelativeTo(null);
    }

    public static void go(Person p){
        mainFrame = new JFrame();
        mainFrame.setSize(new Dimension(1000, 800));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.X_AXIS));
        navPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        navPanel.setMinimumSize(new Dimension(0, 50));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        navPanel.setBackground(Color.white);

        JLabel navLabel = new JLabel("Library Management System");
        navLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        navLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(navPanel.getBackground());
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        RoundedButton addBtn = new RoundedButton("Add Book", 15);
        addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addBtn.setFont(new Font("Calibri", Font.PLAIN, 18));
        addBtn.setBackground(Color.CYAN);
        addBtn.addActionListener(e -> {
            addBookFrame();
        });

        RoundedButton returnBtn = new RoundedButton("Return Book", 15);
        returnBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnBtn.setFont(new Font("Calibri", Font.PLAIN, 18));
        returnBtn.setBackground(Color.CYAN);
        returnBtn.addActionListener(e -> {
            rBookFrame(p);
        });

        RoundedButton registerBtn = new RoundedButton("Register Student", 15);
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerBtn.setFont(new Font("Calibri", Font.PLAIN, 18));
        registerBtn.setBackground(Color.CYAN);
        registerBtn.addActionListener(e -> {
            registerFrame();
        });

        navPanel.add(navLabel);
        navPanel.add(Box.createHorizontalGlue());  
        
        if (p instanceof Admin) {
            btnPanel.add(registerBtn);
            btnPanel.add(Box.createHorizontalStrut(15));
            btnPanel.add(addBtn);
            btnPanel.add(Box.createHorizontalStrut(15));
        }

        btnPanel.add(returnBtn);
        navPanel.add(btnPanel);

        refreshBooks(p);

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);        

        mainFrame.getContentPane().add(BorderLayout.NORTH, navPanel);
        mainFrame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        mainFrame.setVisible(true);
    }

    public static JPanel bookPanel(String imgPath, String title, String author){
        JPanel cardPanel = new JPanel();
        cardPanel.setMaximumSize(new Dimension(300, 300));
        cardPanel.setMinimumSize(new Dimension(300, 300));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        
        JLabel imgLabel = new JLabel();
        imgLabel.setBackground(Color.BLUE);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(imgPath);
        Image img = icon.getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(img));

        JLabel tLabel = new JLabel("Title: " + title);
        tLabel.setFont(new Font("Constantia", Font.PLAIN, 18));
        tLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel aLabel = new JLabel("Author: " + author);
        aLabel.setFont(new Font("Constantia", Font.PLAIN, 18));
        aLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(imgLabel);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(tLabel);
        cardPanel.add(Box.createVerticalStrut(8));
        cardPanel.add(aLabel);

        return cardPanel;
    }

    public static void addBookFrame(){
        JFrame addFrame = new JFrame();
        addFrame.setSize(new Dimension(400, 500));
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));

        JPanel bPanel = new JPanel();
        bPanel.setMaximumSize(new Dimension(300, 150));
        bPanel.setMinimumSize(new Dimension(300, 50));
        bPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.Y_AXIS));
        bPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter book name: ");
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField btitle = new JTextField(30);
        Dimension titleSize = new Dimension(btitle.getPreferredSize().width, 30);
        btitle.setMaximumSize(titleSize);
        btitle.setMinimumSize(titleSize);
        btitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel authorLabel = new JLabel("Enter book author: ");
        authorLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField bauthor = new JTextField(30);
        Dimension authorSize = new Dimension(bauthor.getPreferredSize().width, 30);
        bauthor.setMaximumSize(authorSize);
        bauthor.setMinimumSize(authorSize);
        bauthor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        bauthor.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton pickImgBtn = new RoundedButton("Upload Image", 10);
        pickImgBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pickImgBtn.setFont(new Font("Calibri", Font.PLAIN, 18));
        pickImgBtn.setBackground(Color.CYAN);
        pickImgBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        final String[] imagePath = {null};
        pickImgBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(addFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectFile = fileChooser.getSelectedFile();
                imagePath[0] = selectFile.getAbsolutePath();
                ImageIcon icon = new ImageIcon(imagePath[0]);
                Image img = icon.getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            }
        });

        RoundedButton submitBtn = new RoundedButton("Submit", 15);
        submitBtn.setBackground(Color.CYAN);
        submitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.addActionListener(e -> {
            myLibrary.addBook(btitle.getText(), bauthor.getText(), imagePath[0]);
            JOptionPane.showMessageDialog(null, "Book saved Successfully!!");
            btitle.setText("");
            bauthor.setText("");
            imageLabel.setIcon(null);
        });

        bPanel.add(nameLabel);
        bPanel.add(btitle);
        bPanel.add(Box.createVerticalStrut(10));
        bPanel.add(authorLabel);
        bPanel.add(bauthor);
        bPanel.add(Box.createVerticalStrut(10));

        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(bPanel);
        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(pickImgBtn);
        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(imageLabel);
        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(submitBtn);

        addFrame.getContentPane().add(bookPanel);
        addFrame.setVisible(true);
        addFrame.setResizable(false);
        addFrame.setLocationRelativeTo(null);
    }

    public static void registerFrame(){
        JFrame regFrame = new JFrame();
        regFrame.setSize(new Dimension(400, 400));
        regFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));

        JLabel mLabel = new JLabel("Register Student");
        mLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        mLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel rPanel = new JPanel();
        rPanel.setMaximumSize(new Dimension(300, 150));
        rPanel.setMinimumSize(new Dimension(300, 150));
        rPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rPanel.setLayout(new BoxLayout(rPanel, BoxLayout.Y_AXIS));

        JLabel sLabel = new JLabel("Enter Student Name:");
        sLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        sLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField sName = new JTextField(30);
        Dimension fieldSize = new Dimension(sName.getPreferredSize().width, 30);
        sName.setMaximumSize(fieldSize);
        sName.setMinimumSize(fieldSize);
        sName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sIdLabel = new JLabel("Enter Student ID:");
        sIdLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        sIdLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField sId = new JTextField(30);
        sId.setMaximumSize(fieldSize);
        sId.setMinimumSize(fieldSize);
        sId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sId.setAlignmentX(Component.LEFT_ALIGNMENT);

        RoundedButton regBtn = new RoundedButton("Register", 10);
        regBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regBtn.setBackground(Color.CYAN);
        regBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
        regBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        regBtn.addActionListener(e -> {
            Student s = new Student(Integer.parseInt(sId.getText()), sName.getText());
            myLibrary.registerStudent(s);
            sName.setText(null);
            sId.setText(null);
        });

        rPanel.add(sLabel);
        rPanel.add(sName);
        rPanel.add(Box.createVerticalStrut(10));
        rPanel.add(sIdLabel);
        rPanel.add(sId);

        mPanel.add(Box.createVerticalStrut(10));
        mPanel.add(mLabel);
        mPanel.add(Box.createVerticalStrut(10));
        mPanel.add(rPanel);
        mPanel.add(Box.createVerticalStrut(10));
        mPanel.add(regBtn);

        regFrame.getContentPane().add(mPanel);
        regFrame.setVisible(true);
        regFrame.setLocationRelativeTo(null);
        regFrame.setResizable(false);
    }

    public static void rBookFrame(Person p){
        JFrame rFrame = new JFrame();
        rFrame.setSize(new Dimension(400, 500));
        rFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));

        JPanel bPanel = new JPanel();
        bPanel.setMaximumSize(new Dimension(300, 150));
        bPanel.setMinimumSize(new Dimension(300, 50));
        bPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.Y_AXIS));
        bPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter book name: ");
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField btitle = new JTextField(30);
        Dimension titleSize = new Dimension(btitle.getPreferredSize().width, 30);
        btitle.setMaximumSize(titleSize);
        btitle.setMinimumSize(titleSize);
        btitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel authorLabel = new JLabel("Enter book author: ");
        authorLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField bauthor = new JTextField(30);
        bauthor.setMaximumSize(titleSize);
        bauthor.setMinimumSize(titleSize);
        bauthor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        bauthor.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton pickImgBtn = new RoundedButton("Upload Image", 10);
        pickImgBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pickImgBtn.setFont(new Font("Calibri", Font.PLAIN, 18));
        pickImgBtn.setBackground(Color.CYAN);
        pickImgBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        final String[] imagePath = {null};
        pickImgBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));
            int result = fileChooser.showOpenDialog(rFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectFile = fileChooser.getSelectedFile();
                imagePath[0] = selectFile.getAbsolutePath();
                ImageIcon icon = new ImageIcon(imagePath[0]);
                Image img = icon.getImage().getScaledInstance(130, 200, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            }
        });

        RoundedButton submitBtn = new RoundedButton("Submit", 15);
        submitBtn.setBackground(Color.CYAN);
        submitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.addActionListener(e -> {
            myLibrary.returnBook(p, btitle.getText(), bauthor.getText());
            btitle.setText("");
            bauthor.setText("");
            imageLabel.setIcon(null);

            refreshBooks(p);
            scrollPane.setViewportView(mainPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        bPanel.add(nameLabel);
        bPanel.add(btitle);
        bPanel.add(Box.createVerticalStrut(10));
        bPanel.add(authorLabel);
        bPanel.add(bauthor);
        bPanel.add(Box.createVerticalStrut(10));

        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(bPanel);
        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(pickImgBtn);
        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(imageLabel);
        bookPanel.add(Box.createVerticalStrut(10));
        bookPanel.add(submitBtn);

        rFrame.getContentPane().add(bookPanel);
        rFrame.setVisible(true);
        rFrame.setResizable(false);
        rFrame.setLocationRelativeTo(null);
    }

    public static void refreshBooks(Person p){
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridLayout(0, 3, 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Book b : myLibrary.getBooks()) {
            JPanel bPanel = new JPanel();
            bPanel.setSize(new Dimension(300, 300));
            bPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.Y_AXIS));

            JPanel detailsPanel = bookPanel(b.getUrl(), b.getTitle(), b.getAuthor());

            RoundedButton borrowBtn = new RoundedButton("Borrow Book", 10);
            borrowBtn.setBackground(Color.CYAN);
            borrowBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            borrowBtn.setFont(new Font("Calibri", Font.PLAIN, 18));
            borrowBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            borrowBtn.addActionListener(e -> {
                myLibrary.borrowBook(p, b.getTitle(), b.getAuthor(), b.getUrl());
                mainPanel.remove(bPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            });

            bPanel.add(detailsPanel);
            bPanel.add(Box.createVerticalStrut(10));
            bPanel.add(borrowBtn);

            mainPanel.add(bPanel);
        }
    }
}