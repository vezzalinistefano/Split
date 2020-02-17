package gui;

import gui.panels.*;
import gui.panels.QueueTablePanel;
import logic.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Il {@link JFrame} che contiene tutte le componenti del programma
 */
public class SplitFrame extends JFrame {

    /**
     * L'{@link ArrayList} che contiene i {@link Task}
     */
    private ArrayList<Task> tasksQueue = new ArrayList<>();

    /**
     * Contiene {@link DivideSettingPanel} e {@link MergeSettingPanel} dando la possibilitò
     * di switchare tra uno e l'altro
     */
    private JTabbedPane mainPanel;

    /**
     * Permette di settare un nuovo {@link logic.DivideTask}
     */
    private DivideSettingPanel divideSettingPanel;

    /**
     * Permette di settare un nuovo {@link logic.MergeTask}
     */
    private MergeSettingPanel mergeSettingPanel;

    /**
     * Il {@link JPanel} che mostra la tabella contenente i {@link Task} in coda.
     */
    private QueueTablePanel queueTablePanel;

    /**
     * Permette di far partire i task e/o eliminarli/modificarli.
     */
    private TasksManagementPanel tasksManagementPanel;

    public static final String DIVIDE_PANEL = "Dividi";
    public static final String MERGE_PANEL = "Unisci";

    /**
     * Permette al programma di capire se sono già stati eseguiti dei task o il programma
     * è appena stato aperto.
     */
    public static boolean performed = false;

    /**
     * Costruisce lo SplitFrame.
     * <p>
     * Aggiunge tutti i vari pannelli al frame e genera gli {@link ActionListener} che determinano
     * le azioni da compiere al click di ogni bottone.
     */
    public SplitFrame() {
        this.setTitle("Split");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JTabbedPane();

        ActionListener startTasksListener = actionEvent -> {
            String whichClicked = actionEvent.getActionCommand();
            if(whichClicked.equals(tasksManagementPanel.getStartName()))
                tasksManagementPanel.startTasks(tasksQueue);
            else if(whichClicked.equals(tasksManagementPanel.getModifyName())) {
                tasksManagementPanel.modifyElement(tasksQueue, queueTablePanel.getSelectedRow(),
                        divideSettingPanel);
                queueTablePanel.updateTableModel();
            }
            else if(whichClicked.equals(tasksManagementPanel.getDeleteName())) {
                tasksManagementPanel.deleteElement(tasksQueue, queueTablePanel.getSelectedRow());
                queueTablePanel.updateTableModel();
            }
        };
        tasksManagementPanel = new TasksManagementPanel(startTasksListener);

        queueTablePanel = new QueueTablePanel(tasksQueue);

        ActionListener divideTaskListener = actionEvent -> divideSettingPanel.AddDivideTask(tasksQueue);
        divideSettingPanel = new DivideSettingPanel(divideTaskListener, queueTablePanel);

        ActionListener mergeTaskListener = actionEvent -> mergeSettingPanel.AddMergeTask(tasksQueue);
        mergeSettingPanel = new MergeSettingPanel(mergeTaskListener, queueTablePanel);


        mainPanel.addTab(DIVIDE_PANEL, divideSettingPanel);
        mainPanel.addTab(MERGE_PANEL, mergeSettingPanel);


        this.add(mainPanel, BorderLayout.NORTH);

        this.add(queueTablePanel, BorderLayout.CENTER);

        this.add(tasksManagementPanel, BorderLayout.SOUTH);

        this.pack();
    }
}
