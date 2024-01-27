/**La classe Message defineix un missatge de xat amb informació essencial: DNI de l'emissor (dni1),
 * DNI del destinatari (dni2), i el text del missatge (text).
 * Aquesta estructura simplifica la comunicació entre clients i servidor en el sistema de xat.*/

public class Message {
    private String dni1;
    private String dni2;
    private String text;

    public Message(String dni1, String dni2, String text) {
        this.dni1 = dni1;
        this.dni2 = dni2;
        this.text = text;
    }

    public String getDni1() {
        return dni1;
    }

    public String getDni2() {
        return dni2;
    }

    public String getText() {
        return text;
    }
}
