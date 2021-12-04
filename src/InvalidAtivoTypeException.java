public class InvalidAtivoTypeException extends Exception{

    public InvalidAtivoTypeException(String s) {
        super(String.format("%s is not a valid subclass of Ativo", s));
    }
}
