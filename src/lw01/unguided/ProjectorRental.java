package lw01.unguided;

public class ProjectorRental extends Rental {
    private static final int RATE_PER_DAY = 60000;
    private static final int RATE_BEYOND_3_DAYS = 45000;
    private int units;

    public ProjectorRental(String id, int days, int units) {
        super(id, days);
        this.units = units;
    }

    @Override
    public int calculateCharge() {
        if (getDays() > 3) {
            return ((3 * RATE_PER_DAY) + ((getDays() - 3) * RATE_BEYOND_3_DAYS) + 20000) * units;
        }
        return ((getDays() * RATE_PER_DAY) + 20000) * units;
    }

    @Override
    public String label() {
        return "Projector";
    }
    
}
