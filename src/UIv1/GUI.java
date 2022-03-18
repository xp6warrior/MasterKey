package UIv1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class GUI implements ActionListener {

    // Commands
    public static void Run() {
        JFrame frame = CreateFrame();
        MenuScreen(frame);
    }

    static JFrame CreateFrame() {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setTitle("Password Generator");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    static void FinaliseFrame(JFrame frame) {
        frame.revalidate();
        frame.repaint();
    }

    static void ClearFrame(JFrame frame) {frame.getContentPane().removeAll();}


    // Screens
    static void MenuScreen(JFrame frame) {
        ClearFrame(frame);

        // Components
        JLabel title = new JLabel("Password Generator");
        JButton newButton = new JButton("Create new password");
        JButton modButton = new JButton("Modify key terms");
        JButton viewButton = new JButton("View passwords");

        // Button functionality
        newButton.addActionListener(e -> NewPasswordScreen(frame));
        modButton.addActionListener(e -> ModifyKeyTermsScreen(frame));
        viewButton.addActionListener(e -> ViewPasswordsScreen(frame));

        // Text alignment/fonts
        title.setHorizontalAlignment(SwingConstants.CENTER);
        Font smallFont = new Font("Arial", Font.PLAIN, 35);

        // Fonts
        title.setFont(new Font("Arial", Font.BOLD, 50));
        newButton.setFont(smallFont);
        modButton.setFont(smallFont);
        viewButton.setFont(smallFont);

        // Size
        title.setPreferredSize(new Dimension(600, 150));
        newButton.setPreferredSize(new Dimension(550, 110));
        modButton.setPreferredSize(new Dimension(550, 110));
        viewButton.setPreferredSize(new Dimension(550, 110));

        // Frame components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(newButton);
        frame.getContentPane().add(modButton);
        frame.getContentPane().add(viewButton);

        FinaliseFrame(frame);
    }

    static void NewPasswordScreen(JFrame frame) {
        ClearFrame(frame);
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("Create Password");
        JTextField inputTitle = new JTextField("Input title");
        JLabel outputPass = new JLabel("Output");
        Button refresh = new Button("R", "pR");
        Button confirm = new Button("Confirm", "pAdd");
        JButton back = new JButton("Back");

        // Button Functionality
        back.addActionListener(e -> MenuScreen(frame));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        inputTitle.setPreferredSize(new Dimension(550, 110));
        outputPass.setPreferredSize(new Dimension(420, 110));
        refresh.setPreferredSize(new Dimension(110, 110));
        confirm.setPreferredSize(new Dimension(350, 70));
        back.setPreferredSize(new Dimension(180, 70));

        // Text alignment/borders/fonts/color
        Font smallFont = new Font("Arial", Font.PLAIN, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        inputTitle.setHorizontalAlignment(SwingConstants.CENTER);
        outputPass.setHorizontalAlignment(SwingConstants.CENTER);
        inputTitle.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        outputPass.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        inputTitle.setBackground(Color.lightGray);
        outputPass.setBackground(Color.lightGray);
        inputTitle.setOpaque(true);
        outputPass.setOpaque(true);

        // Fonts
        title.setFont(new Font("Arial", Font.BOLD, 60));
        inputTitle.setFont(new Font("Arial", Font.PLAIN, 40));
        outputPass.setFont(smallFont);
        refresh.setFont(smallFont);
        confirm.setFont(smallFont);
        back.setFont(smallFont);

        // BottomPanel layout/components
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        bottomPanel.add(confirm);
        bottomPanel.add(back);

        // Frame layout/components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(inputTitle);
        frame.getContentPane().add(outputPass);
        frame.getContentPane().add(refresh);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        FinaliseFrame(frame);
    }

    static void ModifyKeyTermsScreen(JFrame frame) {
        ClearFrame(frame);
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("Modify Key Terms");
        JPanel viewPanel = new JPanel();
        Button add = new Button("Add", "mAdd");
        Button remove = new Button("Remove", "mRemove");
        JButton back = new JButton("Back");

        JPanel scroll = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Button Functionality
        back.addActionListener(e -> MenuScreen(frame));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        viewPanel.setPreferredSize(new Dimension(550, 270));
        add.setPreferredSize(new Dimension(170, 60));
        remove.setPreferredSize(new Dimension(170, 60));
        back.setPreferredSize(new Dimension(170, 60));
        scroll.setPreferredSize(new Dimension(520, 258));
        scrollPane.setPreferredSize(new Dimension(538, 258));

        // Fonts/text alignment/color/borders/layouts
        Font smallFont = new Font("Arial", Font.PLAIN, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        viewPanel.setBackground(Color.lightGray);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scroll.setOpaque(false);
        scroll.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));

        // Fonts
        title.setFont(new Font("Arial", Font.BOLD, 50));
        add.setFont(smallFont);
        remove.setFont(smallFont);
        back.setFont(smallFont);

        // BottomPanel components
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.add(add);
        bottomPanel.add(remove);
        bottomPanel.add(back);

        // ViewPanel components
        viewPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        viewPanel.add(scrollPane);

        // Frame components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(viewPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        FinaliseFrame(frame);
    }

    static void ViewPasswordsScreen(JFrame frame) {
        ClearFrame(frame);
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("View Passwords");
        JPanel viewPanel = new JPanel();
        Button remove = new Button("Remove", "vRemove");
        JButton back = new JButton("Back");

        JPanel scroll = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Button Functionality
        back.addActionListener(e -> MenuScreen(frame));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        viewPanel.setPreferredSize(new Dimension(550, 270));
        remove.setPreferredSize(new Dimension(350, 60));
        back.setPreferredSize(new Dimension(180, 60));
        scroll.setPreferredSize(new Dimension(520, 258));
        scrollPane.setPreferredSize(new Dimension(538, 258));

        // Fonts/text alignment/color
        Font smallFont = new Font("Arial", Font.PLAIN, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        viewPanel.setBackground(Color.lightGray);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scroll.setOpaque(false);
        scroll.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));

        // Fonts
        title.setFont(new Font("Arial", Font.BOLD, 50));
        remove.setFont(smallFont);
        back.setFont(smallFont);

        // BottomPanel components
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.add(remove);
        bottomPanel.add(back);

        // ViewPanel components
        viewPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        viewPanel.add(scrollPane);

        // Frame components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(viewPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        FinaliseFrame(frame);
    }
}