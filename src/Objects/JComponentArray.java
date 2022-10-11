package Objects;

import javax.swing.*;
import java.awt.*;

public class JComponentArray {
    // JComponentArray
    // An object that stores the GUI components of a menu
    // Used to transfer GUI information of a menu to a frame for rendering

    private final JComponent[] components; // Normal components
    private final JComponent[] bottomPanelComponents; // Components at the bottom of the screen (Add, Remove, etc.)

    public JComponentArray(JComponent[] components, JComponent[] bottomPanel) {
        this.components = components;
        this.bottomPanelComponents = bottomPanel;
    }

    public Component[] getComponents() {
        return components;
    }

    public Component[] getBottomPanelComponents() {
        return bottomPanelComponents;
    }

}
