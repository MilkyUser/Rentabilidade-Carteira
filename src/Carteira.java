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
                    DDI ddi = new DDI(row[1],
                            valoresPair[0],
                            valoresPair[1],
                            Carteira.getPrecos("PTAX800_V", precos)[1],
                            Carteira.getPrecos("USDBRLBMF", precos)[1]
                    );
                    carteira.put(ddi, Double.parseDouble(row[2]));
                    System.out.printf(
                            "%s, Quantidade: %f, Inicio: %f, Fim: %f, Rendimento: %f\n",
                            ddi.nome,
                            carteira.get(ddi),
                            ddi.startingPrice,
                            ddi.finalPrice,
                            ddi.rentabilidade(carteira.get(ddi))
                    );
                    break;

                case "DAP":
                    DAP dap = new DAP(row[1],
                            valoresPair[0],
                            valoresPair[1],
                            Carteira.getPrecos("VNA_NTNB", precos)[1]
                    );
                    carteira.put(dap, Double.parseDouble(row[2]));
                    System.out.printf(
                            "%s, Quantidade: %f, Inicio: %f, Fim: %f, Rendimento: %f\n",
                            dap.nome,
                            carteira.get(dap),
                            dap.startingPrice,
                            dap.finalPrice,
                            dap.rentabilidade(carteira.get(dap))
                    );
                    break;

                case "Ação":
                    Acao acao = new Acao(row[1], valoresPair[0], valoresPair[1]);
                    carteira.put(
                            acao,
                            Double.parseDouble(row[2])
                    );
                    System.out.printf(
                            "%s, Quantidade: %f, Inicio: %f, Fim: %f, Rendimento: %f\n",
                            acao.nome,
                            carteira.get(acao),
                            acao.startingPrice,
                            acao.finalPrice,
                            acao.rentabilidade(carteira.get(acao))
                    );
                    break;
            }
        }
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

    public Double rentabilidadeTotal(){

        double sum = 0.;
        for(Ativo ativo: this.carteira.keySet()){
            //System.out.printf("%s;%f\n", ativo.nome, ativo.rentabilidade(this.carteira.get(ativo)));
            sum += ativo.rentabilidade(this.carteira.get(ativo));
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
