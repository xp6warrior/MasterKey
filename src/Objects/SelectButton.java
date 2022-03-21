package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectButton extends JButton implements MouseListener {
    public boolean selected;

    public SelectButton(String text) {
        this.setText(text);
        this.setPreferredSize(new Dimension(500, 50));
        this.setContentAreaFilled(false);
        this.setHorizontalAlignment(JButton.LEFT);
        this.setFont(new Font("Arial", Font.PLAIN, 30));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
        this.addMouseListener(this);
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
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
}
