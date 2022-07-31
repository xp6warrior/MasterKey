package UI;

import Core.MUserData;
import Core.RandomPassword;
import Core.ToolBox;
import Objects.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PassPage implements MouseListener {
    private JTextField inputTitle;
    private JLabel outputPass;

    void create(Frame frame) {
        // Components
        JLabel title = new JLabel("Create Password");
        inputTitle = new JTextField("Input title...");
        outputPass = new JLabel(".....");
        JButton refresh = new JButton("R");
        JButton confirm = new JButton("Confirm");
        JButton back = new JButton("Back");
        JPanel bottomPanel = new JPanel();

        // Removes blue highlight when clicking
        refresh.setFocusable(false);
        confirm.setFocusable(false);
        back.setFocusable(false);

        // Buttons
        back.addActionListener(e -> backButtonFunction(frame));
        refresh.addActionListener(e -> generate(outputPass));
        confirm.addActionListener(e -> confirm(inputTitle, outputPass));
        inputTitle.addMouseListener(this);

        // Sizes
        title.setPreferredSize(ToolBox.titleSize);
        inputTitle.setPreferredSize(ToolBox.menuButtonSize);
        outputPass.setPreferredSize(ToolBox.outputPassSize);
        refresh.setPreferredSize(ToolBox.refreshSize);
        confirm.setPreferredSize(ToolBox.confirmSize);
        back.setPreferredSize(ToolBox.backSize);

        // Alignment / Color / Border
        title.setHorizontalAlignment(SwingConstants.CENTER);
        inputTitle.setHorizontalAlignment(SwingConstants.CENTER);
        outputPass.setHorizontalAlignment(SwingConstants.CENTER);
        inputTitle.setBorder(ToolBox.blackBorder6);
        outputPass.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        inputTitle.setBackground(Color.lightGray);
        outputPass.setBackground(Color.lightGray);
        inputTitle.setForeground(Color.gray);
        outputPass.setOpaque(true);

        // Fonts
        title.setFont(ToolBox.font_50);
        inputTitle.setFont(ToolBox.italic_45);
        outputPass.setFont(ToolBox.italic_35);
        refresh.setFont(ToolBox.font_35);
        confirm.setFont(ToolBox.font_35);
        back.setFont(ToolBox.font_35);

        // BottomPanel components
        bottomPanel.setLayout(ToolBox.passPageBottomPanelLayout);
        bottomPanel.add(confirm);
        bottomPanel.add(back);

        // Frame components
        frame.getContentPane().setLayout(ToolBox.frameLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(inputTitle);
        frame.getContentPane().add(outputPass);
        frame.getContentPane().add(refresh);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        // Load passwords
        for (Password password: MUserData.loadFromPasswords()) {
            MUserData.addPassword(password);
        }
    }
    private void backButtonFunction(Frame frame) {
        frame.setPage(PageType.MENU);
        MUserData.saveToPasswords();
    }


    // Creates a random password
    private void generate(JLabel outputPass) {
        String randomPassword = RandomPassword.createRandomPassword();
        outputPass.setText(randomPassword);
        outputPass.setForeground(Color.darkGray);
    }

    // Confirms the password
    private void confirm(JTextField inputTitle, JLabel outputPass) {
        boolean conditionsMet = true;
        String input = inputTitle.getText();
        String output = outputPass.getText();

        if (output.equals("Requires password!...") || output.equals(".....")) {
            outputPass.setText("Requires password!...");
            outputPass.setForeground(Color.gray);
            conditionsMet = false;
        }

        if (input.equals("") || input.equals("Requires title!...") || input.equals("Input title...")) {
            inputTitle.setText("Requires title!...");
            inputTitle.setForeground(Color.gray);
            inputTitle.setFont(ToolBox.italic_45);
            conditionsMet = false;
        }

        if (conditionsMet) {
            MUserData.addPassword(new Password(input, output));
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (inputTitle.getText().equals("Input title...") || inputTitle.getText().equals("Requires title!...")) {
            inputTitle.setText("");
            inputTitle.setForeground(Color.darkGray);
            inputTitle.setFont(ToolBox.font_45);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}