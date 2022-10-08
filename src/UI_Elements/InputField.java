package UI_Elements;

import Core.CommonAssets;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputField extends JTextField implements MouseListener {
    private static final Dimension inSize = new Dimension(550, 125);
    private static final Dimension outSize = new Dimension(405, 125);
    private static final Font inFont = new Font("Arial", Font.PLAIN, 45);
    private static final Font outFont = new Font("Arial", Font.ITALIC, 35);
    private static final Border border = BorderFactory.createLineBorder(Color.black, 6, true);

    public InputField(String type) {
        this.setName(type);
        this.setHorizontalAlignment(CENTER);
        this.setBorder(border);
        this.setBackground(Color.lightGray);
        this.addMouseListener(this);

        switch (type) {
            case "in":
                this.setPreferredSize(inSize);
                this.setFont(CommonAssets.inputItalic);
                this.setText("Input title...");
                this.setForeground(Color.gray);
                break;
            case "out":
                this.setPreferredSize(outSize);
                this.setFont(outFont);
                this.setText(".....");
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.getName().equals("in")) {
            if (this.getText().equals("Input title...") || this.getText().equals("Requires title!...") || this.getText().equals("Success!...") || this.getText().equals("16 character limit!...")) {
                this.setText("");
                this.setForeground(Color.darkGray);
                this.setFont(inFont);
            }
        }
        else {
            if (this.getText().equals("ASCII keys only!...") || this.getText().equals("Requires password!...") || this.getText().equals("16 character limit!...") || this.getText().equals(".....")) {
                this.setText("");
                this.setForeground(Color.darkGray);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
