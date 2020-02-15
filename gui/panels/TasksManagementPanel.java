package gui.panels;

import gui.SplitFrame;
import gui.rows.TasksManagementRow;
import logic.DivideTask;
import logic.Task;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * {@link JPanel} per la gestione dei task presenti in coda
 */
public class TasksManagementPanel extends JPanel {

    /**
     * La {@link gui.rows.Row} che permette di getire i task presenti nella coda
     */
    private TasksManagementRow tasksManagementRow;

    /**
     * Costruisce un TaskManagementPanel
     * @param startListener {@link ActionListener} collegato ai bottoni presenti in {@link TasksManagementRow}
     */
    public TasksManagementPanel(ActionListener startListener) {
        super();

        tasksManagementRow = new TasksManagementRow(startListener);

        this.add(Box.createVerticalStrut(20));
        this.add(tasksManagementRow);
    }

    /**
     * Ritorna la label del bottone per avviare i task
     * @return la label del {@link TasksManagementRow#startBtn}
     */
    public String getStartName() {
        return tasksManagementRow.getStartBtn().getText();
    }

    /**
     * Ritorna la label del bottone per modificare i task
     * @return la label del {@link TasksManagementRow#modifyBtn}
     */
    public String getModifyName() {
        return tasksManagementRow.getModifyBtn().getText();
    }

    /**
     * Ritorna la label del bottone per eliminare i task
     * @return la label del {@link TasksManagementRow#deleteBtn}
     */
    public String getDeleteName() {
        return tasksManagementRow.getDeleteBtn().getText();
    }

    /**
     * Avvia i task in coda.
     * <p>
     * Per ogni task in coda viene inizializzato un nuovo thread che a sua volta viene aggiunto ad una {@link ArrayList}
     * di thread, dopodichè ogni thread viene avviato.
     * @param tasks
     */
    public void startTasks(ArrayList<Task> tasks) {
        SplitFrame.performed = true;
        ArrayList<Thread> thread = new ArrayList<>();

        // Inizializzo ogni thread
        for (Task t : tasks) {
            thread.add(new Thread(t));
        }

        // Faccio partire ogni thread
        for (Thread t:thread) {
            t.start();
        }
    }

    /**
     * Modifica il {@link DivideTask} selezionato in coda.
     * <p>
     * Il task da modificare viene rimosso dalla coda in modo che l'utente possa modificarne le impostazioni
     * grazie al {@link DivideSettingPanel}.
     * @param tasks La coda dei task
     * @param index L'indice del task da modificare
     * @param divideSettingPanel riferimento a {@link DivideSettingPanel} per permettere all'utente di vedere quale
     *                           file si sta modificando
     */
    public void modifyElement(ArrayList<Task> tasks, int index, DivideSettingPanel divideSettingPanel) {
        Task t = tasks.get(index);
        if(t instanceof DivideTask) {
            this.deleteElement(tasks, index);
            String fileName = t.getFile().getAbsolutePath();
            divideSettingPanel.setFileTextBox(fileName);
        }
    }

    /**
     * Elimina il {@link Task} selezionato in coda
     * @param tasks La coda dei task
     * @param index L'indice del task da modificare
     */
    public void deleteElement(ArrayList<Task> tasks, int index) {
        tasks.remove(index);
    }
}
