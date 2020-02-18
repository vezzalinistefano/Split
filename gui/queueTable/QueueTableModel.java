package gui.queueTable;

import logic.DivideTask;
import logic.MergeTask;
import logic.Task;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * L'{@link AbstractTableModel} che definisce la vista e il comportamento di
 * {@link gui.panels.QueueTablePanel#queueTable}.
 */
public class QueueTableModel extends AbstractTableModel {

    /**
     * La coda dei {@link Task}
     */
    private ArrayList<Task> tasks;

    /**
     * Contiene le intestazioni della tabella
     */
    private String[] colName = {"Modalità", "File", "Parti", "Dimensioni", "Comprimi",
            "Cripta", "Parola chiave", "Avanzamento"};

    /**
     * Inizializza la tabella
     *
     * @param tasks La coda dei {@link Task}
     */
    public QueueTableModel(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Aggiorna la vista della tabella
     */
    public void update() {
        this.fireTableDataChanged();
    }

    /**
     * Permette di ottenere il numero di righe presenti nella tabella
     *
     * @return Il numero di righe nella tabella
     */
    @Override
    public int getRowCount() {
        return tasks.size();
    }

    /**
     * Permette di ottenere il numero di colonne presenti nella tabella
     *
     * @return Il numero di colonne nella tabella
     */
    @Override
    public int getColumnCount() {
        return this.colName.length;
    }

    /**
     * Permette di ottenere il nome della colonna selezionata
     *
     * @param col L'indice della colonna interessata
     * @return L'intestazione della colonna interessata
     */
    @Override
    public String getColumnName(int col) {
        return colName[col];
    }

    /**
     * Definisce le informazioni da metterei in ogni casella della tabella
     *
     * @param rowIndex Indice della riga della da riempire
     * @param colIndex Indice della colonna da riempire
     * @return L'informazione da mettere nella specifica casella
     */
    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Task t = tasks.get(rowIndex);
        if (t != null) {

            if (t instanceof DivideTask) {
                switch (colIndex) {
                    case 0:
                        return "Divisione";
                    case 1:
                        return t.getFile().getName();
                    case 2:
                        return ((DivideTask) t).getParts();
                    case 3:
                        return ((DivideTask) t).getSizeOfFiles() + " B";
                    case 4:
                        return ((DivideTask) t).isCompress();
                    case 5:
                        return ((DivideTask) t).isCrypt();
                    case 6:
                        return t.getKeyword();
                    case 7:
                        return "" + t.getProgress() + "%";
                    default:
                        return "";
                }
            } else {
                switch (colIndex) {
                    case 0:
                        return "Unione";
                    case 1:
                        return ((MergeTask) t).getFileName();
                    case 2:
                        return ((MergeTask) t).getFilesLength();
                    case 6:
                        return "" + t.getKeyword();
                    case 7:
                        return "" + t.getProgress() + "%";
                    default:
                        return "";
                }
            }
        }
        return "";
    }
}
