package gui.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.util.Vector;

/**
 * Classe che estende JPanel e racchiude alcune impostazioni comuni
 * sia all'operazione di divisione che a quella di unione dei file
 */
public abstract class DivideAndMergePanel extends JPanel {

    /**
     * Setta il layout del pannello e il titolo
     *
     * @param title il titolo del pannello
     */
    public DivideAndMergePanel(String title) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorderTitle(title);
    }

    /**
     * Setta il titolo del pannello
     *
     * @param newTitle  il nuovo titolo del pannello
     */
    public void setBorderTitle(String newTitle) {
        TitledBorder border = BorderFactory.createTitledBorder(newTitle);
        this.setBorder(border);
    }
}
