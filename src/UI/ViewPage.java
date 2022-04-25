package UI;

import Core.ModPass;
import Core.ModTerms;
import Objects.Fields.Field;
import Objects.Fields.PassField;
import Objects.Password;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ViewPage {
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
        back.addActionListener(e -> {frame.setPage(PageType.MENU);scrollHeight=0;ModPass.saveToPasswords();});
        remove.addActionListener(e -> this.remove(scroll));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        viewPanel.setPreferredSize(new Dimension(550, 270));
        remove.setPreferredSize(new Dimension(350, 60));
        back.setPreferredSize(new Dimension(180, 60));
        scroll.setPreferredSize(new Dimension(520, 258));
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

        load(scroll);
    }

    private void remove(JPanel panel) {
        for (Component comp: panel.getComponents()) {
            if (((Field) comp).getSelected()) {
                Field field = (Field)comp;

                panel.remove(comp);
                panel.revalidate();
                panel.repaint();

                scrollHeight-=64;
                panel.setPreferredSize(new Dimension(550, scrollHeight));

                ModPass.removePass(field.getText());
            }
        }
    }

    private void load(JPanel panel) {
        ArrayList<Password> passwords = ModPass.loadFromPasswords();

        if (!passwords.isEmpty()) {
            for (Password pass: passwords) {
                scrollHeight+=65;
                panel.add(new PassField(pass));
                ModPass.addPass(pass);
                panel.setPreferredSize(new Dimension(550, scrollHeight));
            }
        }
    }
}