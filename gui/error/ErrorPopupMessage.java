package gui.error;

import javax.swing.*;

/**
 * Permette di mostrare un messaggio di errore a scelta
 */
public class ErrorPopupMessage {

    /**
     * Mostra il messaggio di errore in una finestra popup
     * @param infoMessage Il messaggio di errore
     * @param titleBar Il titolo della finestra popup
     */
    public static void show(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "ERRORE: " + titleBar,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
