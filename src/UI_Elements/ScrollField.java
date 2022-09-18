package UI_Elements;

import Core.UserData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ScrollField extends JScrollPane {
    private static final Border border = BorderFactory.createLineBorder(Color.black, 5, true);

    private final JPanel viewPanel;
    private final int viewPanelWidth;
    private int viewPanelHeight;
    private final int gap;

    public static final int PASSWORD = 0;
    public static final int TERM = 1;

    public ScrollField(int width, int height, int vgap) {
        viewPanel = new JPanel();
        viewPanelWidth = width - 50; // Right side offset
        viewPanelHeight = vgap;
        this.gap = vgap;

        viewPanel.setPreferredSize(new Dimension(viewPanelWidth, viewPanelHeight));
        viewPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, vgap));
        viewPanel.setBackground(Color.lightGray);

        this.setViewportView(viewPanel);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(border);
    }

    public void add(JComponent comp) {
        viewPanel.add(comp);
        viewPanelHeight += comp.getPreferredSize().height + gap;
        viewPanel.setPreferredSize(new Dimension(viewPanelWidth, viewPanelHeight));

        viewPanel.revalidate();
        viewPanel.repaint();
    }

    public void remove(int type) {
        for (Component comp: viewPanel.getComponents()) {
            Field field = (Field) comp;
            if (field.getSelected()) {
                viewPanel.remove(comp);
                viewPanelHeight -= comp.getPreferredSize().height + gap;
                viewPanel.setPreferredSize(new Dimension(viewPanelWidth, viewPanelHeight));

                switch (type) {
                    case PASSWORD: UserData.removePassword(field.getText().trim());break;
                    case TERM: UserData.removeTerm(field.getText().trim()); break;
                }
            }
        }
        viewPanel.revalidate();
        viewPanel.repaint();
    }
}
