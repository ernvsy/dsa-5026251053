package lw01.unguided;

public class LaptopRental extends Rental {
    private static final int RATE_PER_DAY = 40000;
     private int units;

    public LaptopRental(String id, int days, int units) {
        super(id, days);
        this.units = units;
    }

    @Override
    public int calculateCharge() {
        return (getDays() * RATE_PER_DAY + 10000) * units;
    }

    @Override
    public String label() {
        return "Laptop";
    }
    
}
