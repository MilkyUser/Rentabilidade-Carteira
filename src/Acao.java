public class Acao extends Ativo {

    public Acao(String nome, double startingPrice, double finalPrice) {
        super(nome, startingPrice, finalPrice);
    }

    @Override
    public double rentabilidade(Double amount) {
        return amount*(this.finalPrice - this.startingPrice);
    }

}
