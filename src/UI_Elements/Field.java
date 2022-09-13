package UI_Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JButton implements MouseListener {
    protected static final Font font_30 = new Font("Arial", Font.PLAIN, 30);
    private boolean selected = false;

    public Field(int width, int height, boolean canSelect) {
        this.setPreferredSize(new Dimension(width, height));
        this.setHorizontalAlignment(LEFT);
        this.setOpaque(false);
        this.setFont(font_30);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

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
            this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
