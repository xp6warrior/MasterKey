package UI.Pages;

import Objects.TermField;
import UI.Frame;

import javax.swing.*;
import java.awt.*;

public class TermsPage implements IPages{
    private int scrollHeight;

    @Override
    public void create(Frame frame) {
        JPanel bottomPanel = new JPanel();
        scrollHeight = 0;

        // Components
        JLabel title = new JLabel("Modify Key Terms");
        JPanel viewPanel = new JPanel();
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        JButton back = new JButton("Back");

        JPanel scroll = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Buttons
        back.addActionListener(e -> frame.setPage(PagesEnum.MENU));
        add.addActionListener(e -> this.add(scroll));
        remove.addActionListener(e -> this.remove(scroll));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        viewPanel.setPreferredSize(new Dimension(550, 270));
        add.setPreferredSize(new Dimension(170, 60));
        remove.setPreferredSize(new Dimension(170, 60));
        back.setPreferredSize(new Dimension(170, 60));
        scroll.setPreferredSize(new Dimension(550, 0));
        scrollPane.setPreferredSize(new Dimension(540, 260));

        // Text/fonts/borders/colors
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
    }

    @Override
    public void add(JPanel panel) {
        for (Component comp: panel.getComponents()) { // Makes sure only one TextField at a time
            if (comp instanceof JTextField) {
                panel.remove(comp);
            }
        }
        TermField termField = new TermField(panel);

        scrollHeight+=65;
        panel.setPreferredSize(new Dimension(550, scrollHeight));
    }

    @Override
    public void remove(JPanel panel) {
        for (Component comp: panel.getComponents()) {
            if (comp instanceof TermField && ((TermField) comp).selected) {
                panel.remove(comp);
                panel.revalidate();
                panel.repaint();

                scrollHeight-=64;
                panel.setPreferredSize(new Dimension(550, scrollHeight));
            }
        }
    }

}