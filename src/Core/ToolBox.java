package Core;

import Fields.Field;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public abstract class ToolBox {
    public static final Dimension titleSize = new Dimension(600, 150);
    public static final Dimension menuButtonSize = new Dimension(550, 110);
    public static final Dimension termButtonSize = new Dimension(170, 60);
    public static final Dimension viewPanelSize = new Dimension(550, 270);
    public static final Dimension scrollPanelSize = new Dimension(550, 0);
    public static final Dimension scrollPaneSize = new Dimension(540, 260);
    public static final Dimension fieldSize = new Dimension(500, 50);
    // Specific
    public static final Dimension outputPassSize = new Dimension(420, 110);
    public static final Dimension refreshSize = new Dimension(110, 110);
    public static final Dimension confirmSize = new Dimension(350, 70);
    public static final Dimension backSize = new Dimension(180, 70);
    public static final Dimension removeSize = new Dimension(365, 60);
    public static final Dimension passSize = new Dimension(250, 40);

    public static final Border blackBorder6 = BorderFactory.createLineBorder(Color.black, 6, true);
    public static final Border blackBorder3 = BorderFactory.createLineBorder(Color.black, 3, true);
    public static final Border blackBorder2 = BorderFactory.createLineBorder(Color.black, 2, true);
    public static final Border blackBorder1 = BorderFactory.createLineBorder(Color.black, 1, false);

    public static final Font font_50 = new Font("Arial", Font.BOLD, 50);
    public static final Font font_45 = new Font("Arial", Font.PLAIN, 45);
    public static final Font italic_45 = new Font("Arial", Font.ITALIC, 45);
    public static final Font font_35 = new Font("Arial", Font.PLAIN, 35);
    public static final Font italic_35 = new Font("Arial", Font.ITALIC, 35);
    public static final Font font_30 = new Font("Arial", Font.PLAIN, 30);
    public static final Font italic_30 = new Font("Arial", Font.ITALIC, 30);
    public static final Font font_25 = new Font("Arial", Font.PLAIN, 25);
    public static final Font italic_25 = new Font("Arial", Font.ITALIC, 25);

    public static final FlowLayout bottomPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 10);
    public static final FlowLayout viewPanelLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
    public static final FlowLayout frameLayout = new FlowLayout(FlowLayout.CENTER, 20, 15);
    // Specific
    public static final FlowLayout passFieldLayout = new FlowLayout(FlowLayout.LEFT, 4, 4);
    public static final FlowLayout passPageBottomPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 25);
    public static final FlowLayout menuPageLayout = new FlowLayout(FlowLayout.CENTER, 10, 15);


    public static Object[] removeFieldFromList(JPanel scrollPanel, int scrollPanelHeight) {
        ArrayList<String> names = new ArrayList<>();
        int scrollHeight = scrollPanelHeight;

        for (Component comp: scrollPanel.getComponents()) {
            if (((Field) comp).getSelected()) {
                Field field = (Field)comp;

                updateUI(scrollPanel, field, false);
                scrollHeight -= 64;
                scrollPanel.setPreferredSize(new Dimension(550, scrollHeight));

                names.add(field.getText());
            }
        }

        return new Object[] {names, scrollHeight};
    }

    public static void updateUI(JPanel panel, JComponent comp, boolean add) {
        if (add) {
            panel.add(comp);
        } else {
            panel.remove(comp);
        }
        panel.revalidate();
        panel.repaint();
    }
}
