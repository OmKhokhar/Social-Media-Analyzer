package exceptions;

public class InvalidMenuOption extends Exception {
    public InvalidMenuOption(String message) {
        super(message);
    }
}
