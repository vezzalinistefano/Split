package logic;

import gui.panels.QueueTablePanel;

import java.io.File;

/**
 * Classe che implementa un task di divisione o ricomposizione dei file
 * da mettere in coda
 */
public abstract class Task implements Runnable {
    /**
     * La percentuale di avanzamento del task
     */
    private int progress;

    /**
     * Riferimento alla tabella dei task in coda utilizzato per l'aggiornamento della vista
     */
    private QueueTablePanel tablePanel;

    /**
     * La password utilizzata per criptare/decriptare un file
     */
    protected String keyword;

    /**
     * Il file da dividere o la prima parte dei file da riunire
     */
    protected File file;

    /**
     * Costruisce un task
     *
     * @param tablePanel riferimento alla tabella che mostra i task in coda
     */
    public Task(QueueTablePanel tablePanel) {
        this.progress = 0;
        this.tablePanel = tablePanel;
    }

    /**
     * Ritorna il progresso corrente
     *
     * @return il progresso corrente
     */
    public int getProgress() {
        return progress;
    }

    /**
     * Ritorna la password scelta dall'utente
     *
     * @return password immessa dall'utente
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Ritorna il file scelto dall'utente
     *
     * @return Il file scelto dall'utente
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Setta l'attuale progresso del task
     *
     * @param progress valore da aggiungere a {{@link #progress}}
     */
    public void setProgressValue(float progress) {
        this.progress += progress;
        tablePanel.updateTableModel();
    }
}
