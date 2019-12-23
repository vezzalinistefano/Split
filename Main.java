import gui.SplitFrame;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        SplitFrame sf = new SplitFrame();
        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sf.setSize(500, 500);
        sf.setVisible(true);
    }
}
