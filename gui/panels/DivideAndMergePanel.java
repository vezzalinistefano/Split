package gui.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class DivideAndMergePanel extends JPanel {

    public DivideAndMergePanel(String title) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorderTitle(title);
    }

    public void setBorderTitle(String newTitle) {
        TitledBorder border = BorderFactory.createTitledBorder(newTitle);
        this.setBorder(border);
    }
}
