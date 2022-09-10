package UI;

import Core.MUserData;
import Core.ToolBox;

import javax.swing.*;

public class MenuPage extends MUserData {
    void create(Frame frame) {
        // Components
        JLabel title = new JLabel("Password Generator");
        JButton newButton = new JButton("Create New Password");
        JButton termsButton = new JButton("Add / Remove Terms");
        JButton viewButton = new JButton("View Passwords");

        // Removes blue highlight when clicking
        newButton.setFocusable(false);
        termsButton.setFocusable(false);
        viewButton.setFocusable(false);

        // Buttons
        newButton.addActionListener(e -> frame.setPage(PageType.PASS));
        termsButton.addActionListener(e -> frame.setPage(PageType.TERMS));
        viewButton.addActionListener(e -> frame.setPage(PageType.VIEW));

        // Sizes
        title.setPreferredSize(ToolBox.titleSize);
        newButton.setPreferredSize(ToolBox.menuButtonSize);
        termsButton.setPreferredSize(ToolBox.menuButtonSize);
        viewButton.setPreferredSize(ToolBox.menuButtonSize);

        // Alignment
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Fonts
        title.setFont(ToolBox.font_50);
        newButton.setFont(ToolBox.font_35);
        termsButton.setFont(ToolBox.font_35);
        viewButton.setFont(ToolBox.font_35);

        // Frame components
        frame.getContentPane().setLayout(ToolBox.menuPageLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(newButton);
        frame.getContentPane().add(termsButton);
        frame.getContentPane().add(viewButton);
    }
}