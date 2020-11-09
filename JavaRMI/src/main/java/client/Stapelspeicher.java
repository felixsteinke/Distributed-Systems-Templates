package client;

/**
 * Stapelspeicher
 */
public class Stapelspeicher {

    private final String[] speicher = new String[8];
    private int stackPointer = speicher.length - 1;

    public static void main(String[] args) {
        String[] argsTemp = {"wo", "ist", "der", "anfang"};
        Stapelspeicher stapel = new Stapelspeicher();
        stapel.push("ENDE");
        for (String s : argsTemp) {
            stapel.push(s);
        }

        while (true) {
            String value = stapel.pop();
            if (value.equals("ENDE"))
                break;
            System.out.println(value);
        }
    }

    public void push(String value) {
        // TODO Füge Wert auf Stapel hinzu
        if (stackPointer >= 0) {
            speicher[stackPointer] = value;
            stackPointer -= 1;
        }
    }

    public String pop() {
        // TODO Entferne den letzten Wert vom Stack
        if (stackPointer <= (speicher.length - 2)) {
            stackPointer += 1;
            return speicher[stackPointer];
        }
        return null;
    }
}
