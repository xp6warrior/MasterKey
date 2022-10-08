package UI_Elements;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private static final Font font = new Font("Arial", Font.PLAIN, 35);

    public Button(String text, Dimension dim) {
        this.setText(text);
        this.setFont(font);
        this.setFocusable(false);
        this.setPreferredSize(dim);
    }

    public Button(ImageIcon img, Dimension dim) {
        this.setIcon(img);
        this.setFocusable(false);
        this.setPreferredSize(dim);
    }
}
