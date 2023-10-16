package Exercici1;

public class ExercicisMultiproces1 {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "Exercici1.ParellSenar");
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
