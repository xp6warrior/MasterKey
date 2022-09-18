package UI_Elements;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JButton implements MouseListener {
    private static final Font font = new Font("Arial", Font.PLAIN, 30);
    private static final FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 5);

    private static final Border border1 = BorderFactory.createLineBorder(Color.black, 1);
    private static final Border border2 = BorderFactory.createLineBorder(Color.black, 2);
    private static final Border border3 = BorderFactory.createLineBorder(Color.black, 3);

    private boolean selected = false;

    public Field(int width, int height, boolean canSelect) {
        this.setPreferredSize(new Dimension(width, height));
        this.setHorizontalAlignment(LEFT);
        this.setOpaque(false);
        this.setFocusable(false);
        this.setBackground(new Color(0,0,0,0));
        this.setFont(font);
        this.setBorder(border1);
        this.setLayout(layout);
        this.setContentAreaFilled(false);

        if (canSelect) {
            this.addMouseListener(this);
        }
    }

    public boolean getSelected() {
        return selected;
    }

    @Override
    public void setText(String text) { // Side offsets
        if (this.getHorizontalAlignment() == LEFT) {
            super.setText(" " + text);
        } else {
            super.setText(text + " ");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selected = !selected;
        if (selected) {
            this.setBorder(border3);
        } else {
            this.setBorder(border2);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!selected) {
            this.setBorder(border2);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!selected) {
            this.setBorder(border1);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
