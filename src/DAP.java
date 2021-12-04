public class DAP extends Ativo{

    Double vna_ntnbFinalPrice;

    public DAP(
            String nome,
            Double startingPrice,
            Double finalPrice,
            Double vna_ntnbFinalPrice
    ) {
        super(nome, startingPrice, finalPrice);
        this.vna_ntnbFinalPrice = vna_ntnbFinalPrice;
    }

    @Override
    public double rentabilidade(Double amount) {
        return amount * (this.finalPrice - this.startingPrice) * vna_ntnbFinalPrice * 1.61462;
    }
}
