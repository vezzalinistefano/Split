package gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il JPanel della toolbar che permette
 * di scegliere quali operazioni eseguire
 */
public class JobsPanel extends JPanel {
    private JToolBar jobsBar;
    private JButton divideBtn, stitchBtn;

    /**
     * Metodo costruttore della classe JobsPanel, inizializza i due bottoni all'interno della
     * JToolbar che permettono all'utente di scegliere cosa fare
     */
    public JobsPanel() {
        super();
        this.setLayout(new BorderLayout());

        jobsBar = new JToolBar();
        jobsBar.setFloatable(false);

        divideBtn = new JButton("Dividi");
        stitchBtn = new JButton("Unisci");

        jobsBar.add(divideBtn);
        jobsBar.add(stitchBtn);

        this.add(jobsBar);

    }

}
