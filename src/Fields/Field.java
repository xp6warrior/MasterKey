package Fields;

import Core.ToolBox;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Field extends JButton implements MouseListener {
    public boolean selected = false;
    public String displayText;

    public Field(String name) {
        this.displayText = name;
        this.setText(" "+name+" ");
        createField();
    }

    private void createField() {
        this.setPreferredSize(ToolBox.fieldSize);
        this.setBorder(ToolBox.blackBorder1);
        this.setFont(ToolBox.font_30);
        this.setHorizontalAlignment(JButton.LEFT);
        this.setContentAreaFilled(false);
        this.addMouseListener(this);
        this.setFocusable(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selected = !selected;
        if (selected) {
            this.setBorder(ToolBox.blackBorder3);
        } else {
            this.setBorder(ToolBox.blackBorder2);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!selected) {
            this.setBorder(ToolBox.blackBorder2);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!selected) {
            this.setBorder(ToolBox.blackBorder1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
