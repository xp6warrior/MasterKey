package UIv1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {

    Button(String name, String actionCommand) {
        this.setText(name);
        this.addActionListener(this);
        this.setActionCommand(actionCommand);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "pAdd" -> System.out.println("Hello");
        }
    }
}