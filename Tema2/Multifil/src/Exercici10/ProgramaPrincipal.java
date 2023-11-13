package Exercici10;

import java.util.concurrent.Semaphore;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        Semaphore semTic = new Semaphore(1);
        Semaphore semTac = new Semaphore(0);
        Semaphore semToc = new Semaphore(0);

        TicTacTocThread ticThread = new TicTacTocThread("TIC", semTic, semTac);
        TicTacTocThread tacThread = new TicTacTocThread("TAC", semTac, semToc);
        TicTacTocThread tocThread = new TicTacTocThread("TOC", semToc, semTic);

        ticThread.start();
        tacThread.start();
        tocThread.start();

        try {
            ticThread.join();
            tacThread.join();
            tocThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa finalitzat.");
    }
}
