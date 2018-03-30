package lt.andrius_statkevicius.java_mokymai.coffeemachine.exceptions;

public class TooMuchSomethingException extends Exception {
    public TooMuchSomethingException (String message) {
        super(message); //iškviečiamas tėvinės klasės konstruktorius
    }
}
