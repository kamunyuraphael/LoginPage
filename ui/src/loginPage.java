package ui.src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import server.DatabaseHelper;

public class loginPage extends JFrame implements ActionListener {
    // Component objects
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel lblStatus;

    // Modern Design Color objects
    private final Color COLOR_BG = new Color(245, 247, 250);       // light grey
    private final Color COLOR_CARD = new Color(255, 255, 255);    // Clean white
    private final Color COLOR_PRIMARY = new Color(67, 97, 238);   // Blue
    private final Color COLOR_ACCENT = new Color(76, 201, 240);    // Tech cyan
    private final Color COLOR_TEXT = new Color(33, 37, 41);       // Dark text

    public loginPage() {
        // Step 1: Window properties, no need to use 'f.property' since we are extending JFrame
        setTitle("Secure Authentication Window");
        setSize(420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main content background
        JPanel mainPanel = new JPanel(new GridBagLayout()); // Centering layout: GridBagLayout is used for centering the card panel
        mainPanel.setBackground(COLOR_BG);
        add(mainPanel);

        // Step 2: The Card Layout Container
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS)); // Vertical stacking of elements
        cardPanel.setBackground(COLOR_CARD);
        cardPanel.setBorder(BorderFactory.createCompoundBorder( // Outer border for shadow effect
            BorderFactory.createLineBorder(new Color(222, 226, 230), 1),
            new EmptyBorder(30, 40, 30, 40) // Inside padding
        ));

        // Step 3: Elements & Fonts
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Header Title
        JLabel lblHeader = new JLabel("Welcome Back");
        lblHeader.setFont(titleFont);
        lblHeader.setForeground(COLOR_PRIMARY);
        lblHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubHeader = new JLabel("Sign in or create your account");
        lblSubHeader.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubHeader.setForeground(Color.GRAY);
        lblSubHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Fields and Component setup
        // Username Label and Text Field
        JLabel lblUser = new JLabel("USERNAME");
        lblUser.setFont(labelFont);
        lblUser.setForeground(COLOR_TEXT);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtUsername = new JTextField(15);
        txtUsername.setFont(inputFont);
        txtUsername.setMaximumSize(new Dimension(320, 35)); // Set maximum size to maintain consistent width

        // Password Label and Password Field
        JLabel lblPass = new JLabel("PASSWORD");
        lblPass.setFont(labelFont);
        lblPass.setForeground(COLOR_TEXT);
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPassword = new JPasswordField(15);
        txtPassword.setFont(inputFont);
        txtPassword.setMaximumSize(new Dimension(320, 35));

        // Styled Action Buttons
        btnLogin = createStyledButton("Log In", COLOR_PRIMARY, Color.WHITE);
        btnRegister = createStyledButton("Create Account", COLOR_CARD, COLOR_PRIMARY);
        btnRegister.setBorder(BorderFactory.createLineBorder(COLOR_PRIMARY, 1));

        // Dynamic Status Message text line
        lblStatus = new JLabel(" ");
        lblStatus.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Step 4: Pack elements into the card layout with spacers
        // createVerticalStrut is used to add vertical spacing between components for better visual separation
        cardPanel.add(lblHeader);
        cardPanel.add(Box.createVerticalStrut(5)); // Spacer between header and subheader
        cardPanel.add(lblSubHeader);
        cardPanel.add(Box.createVerticalStrut(25)); // Spacer before username input
        
        cardPanel.add(lblUser);
        cardPanel.add(Box.createVerticalStrut(5));
        cardPanel.add(txtUsername);
        cardPanel.add(Box.createVerticalStrut(15));
        
        cardPanel.add(lblPass);
        cardPanel.add(Box.createVerticalStrut(5));
        cardPanel.add(txtPassword);
        cardPanel.add(Box.createVerticalStrut(25));
        
        cardPanel.add(btnLogin);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(btnRegister);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(lblStatus);

        // Center card inside window layout grid
        mainPanel.add(cardPanel);

        // Bind core action tracking components
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
    }

    // Helper constructor method for styling UI buttons cleanly
    private JButton createStyledButton(String text, Color bg, Color fg) { // Helper method to create styled buttons
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false); // Removes the default focus border for a cleaner look
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Changes cursor to hand pointer on hover for better UX
        btn.setMaximumSize(new Dimension(320, 38)); // Set maximum size to maintain consistent width
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        // Input validation: Ensure that both fields are filled before proceeding
        if (username.isEmpty() || password.isEmpty()) {
            lblStatus.setForeground(Color.RED);
            lblStatus.setText("Fields cannot be empty.");
            return;
        }

        // Determine which button was clicked and handle accordingly
        if (e.getSource() == btnRegister) {
            handleRegistration(username, password);
        } else if (e.getSource() == btnLogin) {
            handleLogin(username, password);
        }
    }

    private void handleRegistration(String username, String password) {
        boolean success = DatabaseHelper.registerUser(username, password);
        if (success) {
            lblStatus.setForeground(new Color(40, 167, 69)); // Forest Green
            lblStatus.setText("Registration Successful!");
            clearFields();
        } else {
            lblStatus.setForeground(Color.RED);
            lblStatus.setText("Username already exists.");
        }
    }

    private void handleLogin(String username, String password) {
        boolean valid = DatabaseHelper.validateUser(username, password);
        if (valid) {
            lblStatus.setForeground(new Color(40, 167, 69));
            lblStatus.setText("Welcome back!");
            JOptionPane.showMessageDialog(this, "Login successful, welcome " + username + "!", "Dashboard Access Granted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            lblStatus.setForeground(Color.RED);
            lblStatus.setText("Invalid user credentials.");
        }
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public static void main(String[] args) {
        DatabaseHelper.initializeDatabase();

        // Native Nimbus Look & Feel invocation sequence
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Reverts silently to system defaults if Nimbus theme is missing
        }

        SwingUtilities.invokeLater(() -> { // Ensures that the UI is created on the Event Dispatch Thread for thread safety
            new loginPage().setVisible(true);
        });
    }
}
