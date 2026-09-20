package lw01.prelab;

public class ColourPrint extends PrintJob {
    private static final int FIRST_PAGES_LIMIT = 10;
    private static final int RATE_FIRST_PAGES = 1500;
    private static final int RATE_EXTRA_PAGES = 1000;
    private static final int SETUP_PER_COPY = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int charge;
        if (pages <= FIRST_PAGES_LIMIT) {
            charge = pages * RATE_FIRST_PAGES;
        } else {
            charge = FIRST_PAGES_LIMIT * RATE_FIRST_PAGES
                   + (pages - FIRST_PAGES_LIMIT) * RATE_EXTRA_PAGES;
        }
        return charge + SETUP_PER_COPY;
    }

    @Override
    public String label() {
        return "Colour";
    }
}