package Core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Assets {
    public static final Image lock = new ImageIcon("res/padlock.png").getImage();
    public static final ImageIcon refresh = new ImageIcon("res/refresh.png");

    public static final Dimension mediumButtonSize = new Dimension(265, 60);

    public static final Dimension menuButtonSize = new Dimension(550, 110);
    public static final Dimension fieldSize = new Dimension(500, 50);
    // Specific
    public static final Dimension outputPassSize = new Dimension(420, 110);

    public static final Border blackBorder6 = BorderFactory.createLineBorder(Color.black, 6, true);

    public static final Font font_50 = new Font("Arial", Font.BOLD, 50);
    public static final Font font_45 = new Font("Arial", Font.PLAIN, 45);
    public static final Font italic_45 = new Font("Arial", Font.ITALIC, 45);
    public static final Font font_35 = new Font("Arial", Font.PLAIN, 35);
    public static final Font italic_35 = new Font("Arial", Font.ITALIC, 35);

    public static final FlowLayout bottomPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 10);
    public static final FlowLayout frameLayout = new FlowLayout(FlowLayout.CENTER, 20, 15);
    public static final FlowLayout passPageBottomPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 30);
    public static final FlowLayout menuPageLayout = new FlowLayout(FlowLayout.CENTER, 10, 15);
}
