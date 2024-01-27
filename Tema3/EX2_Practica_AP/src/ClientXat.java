import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientXat {
    public static void main(String[] args) {
        new ClientXat().iniciaClient();
    }

    public void iniciaClient() {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);

            System.out.println("Introdueix el teu DNI:");
            String dni = scanner.nextLine();
            outputStream.writeUTF(dni);

            new Thread(() -> {
                while (true) {
                    try {
                        String missatge = inputStream.readUTF();
                        System.out.println(missatge);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                String missatge = scanner.nextLine();
                outputStream.writeUTF(missatge);

                if (missatge.equals("adeu")) {
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
