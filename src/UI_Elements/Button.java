package UI_Elements;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private static final Font font = new Font("Arial", Font.PLAIN, 35);

    public Button(String text, Dimension dim) {
        this.setText(text);
        this.setFont(font);
        general(dim);
    }

    public Button(ImageIcon img, Dimension dim) {
        this.setIcon(img);
        general(dim);
    }

    private void general(Dimension dim) {
        this.setFocusable(false);
        this.setPreferredSize(dim);
    }
}
