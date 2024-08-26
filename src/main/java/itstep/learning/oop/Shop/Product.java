package itstep.learning.oop.Shop;

import java.util.Locale;

public abstract class Product {
    private String manufacturer;

    public abstract String getCard();

    public String getManufacturer(){
        return manufacturer;
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public String getWarrantyInfo() {
        if (this.getClass().isAnnotationPresent(Warranty.class)) {
            Warranty warranty = this.getClass().getAnnotation(Warranty.class);
            return String.format(Locale.ROOT, ", Warranty: %d years", warranty.value());
        } else {
            return ", No warranty";
        }
    }
}
