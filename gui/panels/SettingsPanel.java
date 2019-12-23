package gui.panels;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    public SettingsPanel() {
        super(new BorderLayout());
        JButton fileBtn = new JButton("Scegli file");

        this.add(fileBtn);
    }
}
