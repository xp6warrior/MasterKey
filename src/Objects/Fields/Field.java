package Objects.Fields;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JButton implements MouseListener {
    private boolean selected = false;

    public Field() {
        createField();
    }

    public Field(String name) {
        this.setText(name);
        createField();
    }

    private void createField() {
        this.setPreferredSize(new Dimension(500, 50));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
        this.setFont(new Font("Arial", Font.PLAIN, 30));
        this.setHorizontalAlignment(JButton.LEFT);
        this.setContentAreaFilled(false);
        this.addMouseListener(this);
    }

    public boolean getSelected() {
        return selected;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selected = !selected;
        if (selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 2, false));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 2, false));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
