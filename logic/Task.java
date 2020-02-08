package logic;

import javax.swing.*;

/**
 * Classe che implementa un task di divisione o ricomposizione dei file
 * da mettere in coda
 */
public abstract class Task implements Runnable{
    private JProgressBar progress;
}
