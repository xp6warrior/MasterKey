package UI_Elements;

import Core.UserData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ScrollField extends JScrollPane {
    private static final Border border = BorderFactory.createLineBorder(Color.black, 5, true);
    private static final Dimension size = new Dimension(550, 260);
    private final int gap = 10;

    private int viewPanelHeight = gap;
    private final JPanel viewPanel;
    private final int viewPanelWidth;

    public ScrollField() {
        viewPanel = new JPanel();
        viewPanelWidth = size.width - 50; // Right side offset

        viewPanel.setPreferredSize(new Dimension(viewPanelWidth, viewPanelHeight));
        viewPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, gap));
        viewPanel.setBackground(Color.lightGray);

        this.setViewportView(viewPanel);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.setPreferredSize(new Dimension(size.width, size.height));
        this.setBorder(border);
        this.getVerticalScrollBar().setUnitIncrement(12);
    }

    public void add(JComponent comp) {
        for (Component c: viewPanel.getComponents()) { // One at a time creation
            if (c instanceof TermField && ((TermField) c).getComponents().length > 0) {
                return;
            }
        }

        viewPanel.add(comp);
        viewPanelHeight += comp.getPreferredSize().height + gap;
        viewPanel.setPreferredSize(new Dimension(viewPanelWidth, viewPanelHeight));

        viewPanel.revalidate();
        viewPanel.repaint();
    }

    public void delete(boolean pass) {
        for (Component comp: viewPanel.getComponents()) {
            Field field = (Field) comp;
            if (field.getSelected()) {
                viewPanel.remove(comp);
                viewPanelHeight -= comp.getPreferredSize().height + gap;
                viewPanel.setPreferredSize(new Dimension(viewPanelWidth, viewPanelHeight));

                if (pass) {
                    UserData.removePassword(field.getText().trim());
                } else  {
                    UserData.removeTerm(field.getText().trim());
                }
            }
        }
        viewPanel.revalidate();
        viewPanel.repaint();
    }
}
