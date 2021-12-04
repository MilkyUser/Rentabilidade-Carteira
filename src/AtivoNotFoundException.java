public class AtivoNotFoundException extends Exception{

    AtivoNotFoundException(String name){
        super(String.format("Ativo %s was not found in parsed file", name));
    }
}
