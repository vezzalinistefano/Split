package gui.panels;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private JButton fileBtn;
    private JTextField txtFileSelected;

    public SettingsPanel() {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        fileBtn = new JButton("Scegli file");
        txtFileSelected = new JTextField();
        txtFileSelected.setEditable(false);
        txtFileSelected.setText("placeholder txtFileSelected");


        this.add(fileBtn);
        this.add(txtFileSelected);
    }
}
