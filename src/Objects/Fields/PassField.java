package Objects.Fields;

import Objects.Password;

import javax.swing.*;
import java.awt.*;

public class PassField extends Field {
    public PassField(Password pass) {
        Field titleField = new Field(pass.getTitle());
        titleField.setPreferredSize(new Dimension(250, 40));
        this.setHorizontalAlignment(JButton.RIGHT);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
        this.setFont(new Font("Arial", Font.PLAIN, 18));
        this.setText(pass.getPass());

        this.add(titleField);
    }
}