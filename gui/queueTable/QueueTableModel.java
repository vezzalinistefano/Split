package gui.queueTable;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class QueueTableModel extends AbstractTableModel {

    private Vector jobs = null;
    private String[] colName = {"File", "Comprimi", "Cripta", "Parola chiave", "Avanzamento"};

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return this.colName.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return null;
    }
}
