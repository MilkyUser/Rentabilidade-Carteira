import java.io.FileNotFoundException;
import java.io.File;

public class Main {

    public static void main(String[] args) throws
            FileNotFoundException,
            AtivoNotFoundException
    {
        File carteiraFile = new File(args[0]);
        File precosFile = new File(args[1]);

        if(!(carteiraFile.exists() || precosFile.exists())){
            throw new FileNotFoundException();
        }

        Carteira carteira = new Carteira(carteiraFile, Carteira.getPrecosList(precosFile));
        System.out.printf("A rentabilidade da carteira é de: R$%,.2f%n", carteira.rentabilidadeTotal());
    }
}
