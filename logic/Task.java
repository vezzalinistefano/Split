package logic;

import gui.queueTable.QueueTablePanel;

/**
 * Classe che implementa un task di divisione o ricomposizione dei file
 * da mettere in coda
 */
public abstract class Task implements Runnable{
    private int progress;
    private QueueTablePanel tablePanel;

    public Task(QueueTablePanel tablePanel) {
        this.progress = 0;
        this.tablePanel = tablePanel;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgressValue(float progress) {
        this.progress += progress;
        tablePanel.updateTableModel();
    }
}
