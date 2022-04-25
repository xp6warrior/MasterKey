package UI;

import javax.swing.*;
import java.awt.*;

class MenuPage {
    void create(Frame frame) {
        // Components
        JLabel title = new JLabel("Password Generator");
        JButton newButton = new JButton("Create new password");
        JButton termsButton = new JButton("Modify key terms");
        JButton viewButton = new JButton("View passwords");

        // Buttons
        newButton.addActionListener(e -> frame.setPage(PageType.PASS));
        termsButton.addActionListener(e -> frame.setPage(PageType.TERMS));
        viewButton.addActionListener(e -> frame.setPage(PageType.VIEW));

        // Text/font
        title.setHorizontalAlignment(SwingConstants.CENTER);
        Font smallFont = new Font("Arial", Font.PLAIN, 35);

        // Fonts
        title.setFont(new Font("Arial", Font.BOLD, 50));
        newButton.setFont(smallFont);
        termsButton.setFont(smallFont);
        viewButton.setFont(smallFont);

        // Size
        title.setPreferredSize(new Dimension(600, 150));
        newButton.setPreferredSize(new Dimension(550, 110));
        termsButton.setPreferredSize(new Dimension(550, 110));
        viewButton.setPreferredSize(new Dimension(550, 110));

        // Frame components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(newButton);
        frame.getContentPane().add(termsButton);
        frame.getContentPane().add(viewButton);
    }
}