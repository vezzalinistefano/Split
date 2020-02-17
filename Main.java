import gui.SplitFrame;

/**
 * Classe Main del programma.
 */
public class Main {

    /**
     * Crea un nuovo {@link SplitFrame} e lo rende visibile
     */
    public static void main(String args[]) {
        SplitFrame sf = new SplitFrame();

        sf.setBounds(500, 200, 800, 600);
        sf.setVisible(true);
    }
}
