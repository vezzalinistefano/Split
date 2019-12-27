package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa le opzioni comuni ai due possibili compiti selezionabili
 * dall'utente
 */
public class MidPanel extends JPanel {

    private SettingsPanel settingsPanel;
    private JLabel currentAction;

    public MidPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createEtchedBorder());
        settingsPanel = new SettingsPanel();

        this.add(settingsPanel);
    }
}
