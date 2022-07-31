package Fields;

import Core.ToolBox;
import Objects.Password;

import javax.swing.*;

public class PassField extends Field {
    public PassField(Password password) {
        super(password.getPass());
        this.setHorizontalAlignment(JButton.RIGHT);
        this.setLayout(ToolBox.passFieldLayout);
        this.setFont(ToolBox.italic_25);

        JLabel titleField = new JLabel(" "+password.getTitle());
        titleField.setPreferredSize(ToolBox.passSize);
        titleField.setFont(ToolBox.font_25);
        titleField.setBorder(ToolBox.blackBorder1);

        this.add(titleField);
    }
}