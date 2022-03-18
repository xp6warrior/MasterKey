package UIv2.Pages;

import UIv2.Frame;

import javax.swing.*;
import java.awt.*;

public class PassPage {
    public static void create(Frame frame) {
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("Create Password");
        JTextField inputTitle = new JTextField("Input title");
        JLabel outputPass = new JLabel("Output");
        JButton refresh = new JButton("R");
        JButton confirm = new JButton("Confirm");
        JButton back = new JButton("Back");

        // Buttons
        back.addActionListener(e -> frame.setPage(PagesEnum.MENU));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        inputTitle.setPreferredSize(new Dimension(550, 110));
        outputPass.setPreferredSize(new Dimension(420, 110));
        refresh.setPreferredSize(new Dimension(110, 110));
        confirm.setPreferredSize(new Dimension(350, 70));
        back.setPreferredSize(new Dimension(180, 70));

        // Text/fonts/borders/colors
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

        // BottomPanel components
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        bottomPanel.add(confirm);
        bottomPanel.add(back);

        // Frame components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(inputTitle);
        frame.getContentPane().add(outputPass);
        frame.getContentPane().add(refresh);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
    }
}