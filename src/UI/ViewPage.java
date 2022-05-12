package UI;

import Core.MUserData;
import Objects.Fields.Field;
import Objects.Fields.PassField;
import Objects.Password;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ViewPage extends MUserData {
    private int scrollHeight = 0;

    void create(Frame frame) {
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("View Passwords");
        JPanel viewPanel = new JPanel();
        JButton remove = new JButton("Remove");
        JButton back = new JButton("Back");

        JPanel scroll = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Buttons
        back.addActionListener(e -> {
            frame.setPage(PageType.MENU);
            saveToPasswords();
            scrollHeight = 0;
        });
        remove.addActionListener(e -> remove(scroll));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        viewPanel.setPreferredSize(new Dimension(550, 270));
        remove.setPreferredSize(new Dimension(365, 60));
        back.setPreferredSize(new Dimension(170, 60));
        scroll.setPreferredSize(new Dimension(520, 0));
        scrollPane.setPreferredSize(new Dimension(538, 258));

        // Fonts/text/color/border
        Font smallFont = new Font("Arial", Font.PLAIN, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        viewPanel.setBackground(Color.lightGray);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scroll.setOpaque(false);
        scroll.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 12));

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

        // Loading passwords
        for (Password password: loadFromPasswords()) {
            scroll.add(new PassField(password));
            addPassword(password);
            scrollHeight += 65;
            scroll.setPreferredSize(new Dimension(550, scrollHeight));
        }
    }

    private void remove(JPanel scroll) { // Removing password
        for (Component comp: scroll.getComponents()) {
            if (((Field) comp).getSelected()) {
                Field field = (Field)comp;

                if (field.getSelected()) {
                    scroll.remove(comp);
                    scroll.revalidate();
                    scroll.repaint();
                    scrollHeight -= 64;
                    scroll.setPreferredSize(new Dimension(550, scrollHeight));

                    removePassword(field.getText());
                }
            }
        }
    }
}