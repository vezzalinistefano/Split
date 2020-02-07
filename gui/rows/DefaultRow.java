package gui.rows;

import javax.swing.*;
import java.awt.*;

public class DefaultRow extends Row {

    private JCheckBox defaultChkBox;

    public JCheckBox getDefaultChkBox() {
        return defaultChkBox;
    }

    public DefaultRow() {
        super();

        this.defaultChkBox = new JCheckBox("Default");

        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.add(defaultChkBox);
        this.defaultChkBox.setSelected(true);
    }
}
