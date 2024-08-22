package itstep.learning.oop;

import java.util.Locale;

public class Accumulator extends Product {
    int capacity;

    public Accumulator (String manufacturer ,int capacity) {
        super.setManufacturer(manufacturer);
        this.setCapacity(capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int productivity) {
        this.capacity = productivity;
    }

    @Override
    public String getCard() {
        return String.format(Locale.ROOT,
                "Accumulator: '%s', capacity: %d W",
                super.getManufacturer(), this.getCapacity()
        );
    }
}
