import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Carteira {

    public LinkedHashMap<Ativo, Double> carteira;
    public ArrayList<String> precos;

    public Carteira(File carteiraFile, ArrayList<String> precos) throws
            FileNotFoundException,
            AtivoNotFoundException
    {

        this.precos = precos;
        LinkedHashMap<Ativo, Double> carteira = new LinkedHashMap<>();
        Scanner scannerCarteira = new Scanner(carteiraFile);


        do {
            String[] row = scannerCarteira.nextLine().split(";");
            // Erases all non-printable characters from String
            row[0] = row[0].replaceAll("\\p{C}", "");
            Double[] valoresPair = getPrecos(row[1], precos);

            switch (row[0]) {
                case "DDI" :
                    carteira.put(new DDI(
                            row[1],
                            valoresPair[0],
                            valoresPair[1],
                            Carteira.getPrecos("PTAX800_V", precos)[1],
                            Carteira.getPrecos("USDBRLBMF", precos)[1]
                    ), Double.parseDouble(row[2]));
                    break;

                case "DAP":
                    carteira.put(
                            new DAP(
                                    row[1],
                                    valoresPair[0],
                                    valoresPair[1],
                                    Carteira.getPrecos("VNA_NTNB", precos)[1]
                            ),
                            Double.parseDouble(row[2])
                    );
                    break;

                case "Ação":
                    carteira.put(
                            new Acao(
                                    row[1],
                                    valoresPair[0],
                                    valoresPair[1]
                            ),
                            Double.parseDouble(row[2])
                    );
                    break;
            }
        } while (scannerCarteira.hasNextLine());
        this.carteira = carteira;
    }

    public static Double[] getPrecos(String name, Iterable<String> valores) throws
            AtivoNotFoundException
    {
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
