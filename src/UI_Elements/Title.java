package UI_Elements;

import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {
    private static final Font font = new Font("Arial", Font.BOLD, 50);

    public Title(String text, Dimension dim) {
        this.setText(text);
        this.setPreferredSize(dim);
        this.setHorizontalAlignment(CENTER);
        this.setFont(font);
    }
}
