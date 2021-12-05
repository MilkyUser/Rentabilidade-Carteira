public class DDI extends Ativo{
    private final Double ptax800_VFinalPrice;
    private final Double USDBRLBMFFinalPrice;

    public DDI(String nome,
               Double startingPrice,
               Double finalPrice,
               Double ptax800_VFinalPrice,
               Double USDBRLBMFFinalPrice) {

        super(nome, startingPrice, finalPrice);
        this.ptax800_VFinalPrice = ptax800_VFinalPrice;
        this.USDBRLBMFFinalPrice = USDBRLBMFFinalPrice;
    }

    @Override
    public double rentabilidade(Double amount) {

        return (amount * (this.finalPrice - this.startingPrice) * this.ptax800_VFinalPrice)/
                this.USDBRLBMFFinalPrice;
    }

    @Override
    public String type() {
        return this.getClass().getSimpleName();
    }
}
