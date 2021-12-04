public abstract class Ativo {

    public final String nome;
    public double startingPrice;
    public double finalPrice;

    public Ativo(String nome, double startingPrice, double finalPrice){
        this.nome = nome;
        this.startingPrice = startingPrice;
        this.finalPrice = finalPrice;
    }

    public abstract double rentabilidade(Double amount);

}
