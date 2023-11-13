package Exercici9;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class VocalThread extends Thread {
    private String text;
    private char vowel;
    private String filePath;
    private Semaphore semaphore;

    public VocalThread(String text, char vowel, String filePath, Semaphore semaphore) {
        this.text = text;
        this.vowel = vowel;
        this.filePath = filePath;
        this.semaphore = semaphore;
    }

    private int contarVocals() {
        int comptador = 0;
        for (char c : text.toCharArray()) {
            if (Character.toLowerCase(c) == Character.toLowerCase(vowel)) {
                comptador++;
            }
        }
        return comptador;
    }

    public void run() {
        try {
            semaphore.acquire();
            int comptador = contarVocals();
            semaphore.release();

            escriureResultatEnFitxer(vowel, comptador, filePath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void escriureResultatEnFitxer(char vocal, int comptador, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            String resultat = "vocal " + vocal + ": " + comptador + "\n";
            fileWriter.write(resultat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

