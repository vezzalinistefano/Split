package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Classe contenente il JPanel della toolbar che permette
 * di scegliere quali operazioni eseguire
 */
public class JobsPanel extends JPanel {
    public JobsPanel () {
        super(new BorderLayout());

        JToolBar jobsBar = new JToolBar();
        jobsBar.setFloatable(false);
        this.add(jobsBar, BorderLayout.PAGE_START);
        jobsBar.add(new JButton("Dividi"));
        jobsBar.add(new JButton("Ricomponi"));
    }

}
