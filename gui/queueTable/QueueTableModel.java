package gui.queueTable;

import logic.DivideTask;
import logic.Task;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class QueueTableModel extends AbstractTableModel {

    private ArrayList<Task> tasks = null;
    private String[] colName = {"File", "Comprimi", "Cripta", "Parola chiave", "Avanzamento"};

    public QueueTableModel(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void update(ArrayList<Task> tasksQueue){
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
    public String getColumnName(int col) {return colName[col];}

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Task t = tasks.get(rowIndex);
        if (t != null) {

            if (t instanceof DivideTask) {
                switch (colIndex) {
                    case 0:
                        return ((DivideTask) t).getFileName();
                    case 1:
                        return ((DivideTask) t).isCompress();
                    case 2:
                        return ((DivideTask) t).isCrypt();
                    case 3:
                        return "" + ((DivideTask) t).getParts() + " da " + ((DivideTask) t).getSizeOfFiles();
                    case 4:
                        return ((DivideTask) t).getKeyword();
                    default:
                        return "";
                }
            } else {
                //TODO: getvalueat() per istanze di MergeTask
                return "";
            }
        }
        return "";
    }

}
