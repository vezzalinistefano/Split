package gui.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * {@link JPanel}
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
