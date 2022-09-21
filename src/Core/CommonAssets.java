package Core;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class CommonAssets {
    public static final ImageIcon padlock = new ImageIcon(Objects.requireNonNull(CommonAssets.class.getResource("/padlock.png")));

    public static final Dimension fieldSize = new Dimension(500, 50);
    public static final Dimension titleSize = new Dimension(600, 150);

    public static final Font inputItalic = new Font("Arial", Font.ITALIC, 45);
    public static final Font fieldFont =  new Font("Arial", Font.PLAIN, 30);
}
