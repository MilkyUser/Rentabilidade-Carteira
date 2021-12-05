import java.io.FileNotFoundException;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws
            FileNotFoundException,
            AtivoNotFoundException
    {
        // Ensures that real numbers are printed with a '.' as a decimal separator
        NumberFormat nf_out = NumberFormat.getNumberInstance(Locale.US);
        nf_out.setMaximumFractionDigits(2);

        // Open pointed files
        File carteiraFile = new File(args[0]);
        File precosFile = new File(args[1]);

        if(!(carteiraFile.exists() || precosFile.exists())){
            throw new FileNotFoundException();
        }
        Carteira carteira = new Carteira(carteiraFile, Carteira.getPrecosList(precosFile));
        double sum = 0;
        for(Map.Entry<Ativo, Double> entry: carteira.carteira.entrySet()){
            double rentabilidade = entry.getKey().rentabilidade(entry.getValue());
            System.out.printf(
                    "%s;%s;%s\n",
                    entry.getKey().type(),
                    entry.getKey().nome,
                    nf_out.format(rentabilidade).replace(",", "")
            );
            sum += rentabilidade;
        }
        System.out.printf(";TOTAL;%s", nf_out.format(sum).replace(",", ""));
    }
}
