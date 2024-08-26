package itstep.learning.oop;

import java.util.Locale;

@Warranty(2)
public class Pump extends Product {
    int productivity;

    public Pump(String manufacturer ,int productivity) {
        super.setManufacturer(manufacturer);
        this.setProductivity(productivity);
    }

    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }

    @Override
    public String getCard() {
        return String.format(Locale.ROOT,
                "Pump: '%s', Productivity: %d l/h%s",
                super.getManufacturer(), this.getProductivity(),super.getWarrantyInfo()
        );
    }
}
