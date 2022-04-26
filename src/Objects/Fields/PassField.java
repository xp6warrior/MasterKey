package Objects.Fields;

import Objects.Password;

import javax.swing.*;
import java.awt.*;

public class PassField extends Field {
    public PassField(Password password) {
        Field titleField = new Field(password.getTitle());
        titleField.setPreferredSize(new Dimension(250, 40));
        titleField.setFont(new Font("Arial", Font.PLAIN, 25));
        this.setHorizontalAlignment(JButton.RIGHT);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
        this.setFont(new Font("Arial", Font.PLAIN, 25));
        this.setText(password.getPass());

        this.add(titleField);
    }
}