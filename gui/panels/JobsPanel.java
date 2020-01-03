package gui.panels;

import gui.SplitFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che implementa il JPanel della toolbar che permette
 * di scegliere quali operazioni eseguire
 */
public class JobsPanel extends JPanel implements ActionListener {

    /**
     * Pannello principale che permette lo switch tra il pannello per
     * la divisione dei file e quello per l'unione dei file
     */
    private MainPanel currentJob;
    /**
     * Toolbar contenente bottoni per scegliere che operazione fare
     */
    private JToolBar jobsBar;
    /**
     * Bottone per switchare al pannello di divisione
     */
    private JButton divideBtn;
    /**
     * Bottone per switchere al pannello di unione
     */
    private JButton mergeBtn;
    /**
     * Layout che permette a due pannelli di intercambiarsi rimanendo entrambi
     * nella stessa posizione
     */
    private CardLayout cardLayout;

    /**
     * Aggiunge i bottoni e la toolbar al pannello
     *
     * @param currentJob    indica su quale pannello si trova l'utente
     * @param cardLayout    mantiene lo stesso cardLayout tra una classe e l'altra
     */
    public JobsPanel(MainPanel currentJob, CardLayout cardLayout) {
        super();
        this.setLayout(new BorderLayout());
        this.currentJob = currentJob;
        this.cardLayout = cardLayout;

        jobsBar = new JToolBar();
        jobsBar.setFloatable(false);

        divideBtn = new JButton(SplitFrame.DIVIDE_PANEL);
        mergeBtn = new JButton(SplitFrame.MERGE_PANEL);

        divideBtn.addActionListener(this);
        mergeBtn.addActionListener(this);

        jobsBar.add(divideBtn);
        jobsBar.add(mergeBtn);

        this.add(jobsBar);

    }

    /**
     * Gestisce lo switch tra il pannello di divisione e il pannello di
     * unione
     *
     * @param e quale bottone è stato premuto
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SplitFrame.DIVIDE_PANEL)
                && currentJob.getCurrentJob().equals(SplitFrame.MERGE_PANEL)) {
            cardLayout.show(currentJob, SplitFrame.DIVIDE_PANEL);
            currentJob.setCurrentJob(SplitFrame.DIVIDE_PANEL);
        } else if (e.getActionCommand().equals(SplitFrame.MERGE_PANEL)
                && currentJob.getCurrentJob().equals(SplitFrame.DIVIDE_PANEL)) {
            cardLayout.show(currentJob, SplitFrame.MERGE_PANEL);
            currentJob.setCurrentJob(SplitFrame.MERGE_PANEL);
        }
    }
}
