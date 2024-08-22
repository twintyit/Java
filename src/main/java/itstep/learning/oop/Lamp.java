package itstep.learning.oop;

import java.util.Locale;

public class Lamp  extends Product{

    private double power;

    public Lamp( String manufacturer, double power ) {
        super.setManufacturer( manufacturer );
        this.setPower( power );
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        if(power < 0) {
            throw new RuntimeException("Negative power");
        }
        this.power = power;
    }

    @Override
    public String getCard() {
        return String.format(Locale.ROOT,
                "Lamp: '%s', Power: %.1f W",
                super.getManufacturer(), this.getPower()
        );
    }
}
