package UI_Elements;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    private static final Dimension size = new Dimension(600, 150);
    private static final Font font = new Font("Arial", Font.BOLD, 50);

    public Label(String text) {
        this.setText(text);
        this.setPreferredSize(size);
        this.setHorizontalAlignment(CENTER);
        this.setFont(font);
    }
}
