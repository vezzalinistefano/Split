package gui.queueTable;

import logic.DivideTask;
import logic.MergeTask;
import logic.Task;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class QueueTableModel extends AbstractTableModel {

    private ArrayList<Task> tasks;
    private String[] colName = {"Modalità", "File", "Comprimi", "Cripta", "Parola chiave", "Avanzamento"};

    public QueueTableModel(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void update() {
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return this.colName.length;
    }

    @Override
    public String getColumnName(int col) {
        return colName[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Task t = tasks.get(rowIndex);
        if (t != null) {

            if (t instanceof DivideTask) {
                switch (colIndex) {
                    case 0:
                        return "Divisione";
                    case 1:
                        return ((DivideTask) t).getFileName();
                    case 2:
                        return ((DivideTask) t).isCompress();
                    case 3:
                        return ((DivideTask) t).isCrypt();
                    case 4:
                        return "" + ((DivideTask) t).getKeyword();
                    case 5:
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
                    case 4:
                        return "" + ((MergeTask) t).getKeyword();
                    case 5:
                        return "" + t.getProgress() + "%";
                    default:
                        return "";
                }
            }
        }
        return "";
    }
}
