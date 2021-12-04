import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Carteira {

    public HashMap<Ativo, Double> carteira;
    public ArrayList<String> precos;

    public Carteira(File carteiraFile, ArrayList<String> precos) throws
            FileNotFoundException,
            AtivoNotFoundException
    {

        this.precos = precos;
        HashMap<Ativo, Double> carteira = new HashMap<>();
        Scanner scannerCarteira = new Scanner(carteiraFile);

        while (scannerCarteira.hasNextLine()) {

            String[] row = scannerCarteira.nextLine().split(";");
            Double[] valoresPair = getPrecos(row[1], precos);

            switch (row[0]) {
                case "DDI":

                    carteira.put(
                            new DDI(row[1],
                                    valoresPair[0],
                                    valoresPair[1],
                                    Carteira.getPrecos("PTAX800_V", precos)[1],
                                    Carteira.getPrecos("USDBRLBMF", precos)[1]
                                    ),
                            Double.parseDouble(row[2])
                    );

                case "DAP":
                    carteira.put(
                            new DAP(row[1],
                                    valoresPair[0],
                                    valoresPair[1],
                                    Carteira.getPrecos("VNA_NTNB", precos)[1]
                            ),
                            Double.parseDouble(row[2])
                    );

                case "Ação":
                    carteira.put(
                            new Acao(row[1], valoresPair[0], valoresPair[1]),
                            Double.parseDouble(row[2])
                    );
            }
            this.carteira = carteira;
        }
    }

    public static Double[] getPrecos(String name, Iterable<String> valores) throws AtivoNotFoundException {

        for (String row: valores){
            // Erases all non-ascii characters from String
            row = row.replaceAll("[^\\x00-\\x7F]", "");

            String[] splitted = row.split(";");

            if(splitted[0].equals(name)){
                return new Double[]{Double.parseDouble(splitted[1]), Double.parseDouble(splitted[2])};
            }
        }
        throw new AtivoNotFoundException(name);
    }

    public Double rentabilidadeTotal(){

        Double sum = 0.;
        for(Ativo ativo: carteira.keySet()){
            sum += carteira.get(ativo);
        }
        return sum;
    }

    public static ArrayList<String> getPrecosList(File precosFile) throws FileNotFoundException {

        Scanner scannerPrecos = new Scanner(precosFile);
        ArrayList<String> precos = new ArrayList<>();

        while (scannerPrecos.hasNextLine()) {
            precos.add(scannerPrecos.nextLine());
        }
        scannerPrecos.close();
        return precos;
    }

}
