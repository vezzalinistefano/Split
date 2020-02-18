package gui.panels;

import javax.swing.*;

/**
 * Un {@link JPanel} settato per utilizzare il {@link BoxLayout}
 */
public abstract class DivideAndMergePanel extends JPanel {

    /**
     * Costruisce un nuovo DivideAndMergePanel
     */
    public DivideAndMergePanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

}
