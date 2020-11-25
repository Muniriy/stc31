package task1.items.electronics;

import task1.items.Item;

import java.math.BigDecimal;

public abstract class Electronics extends Item {

    private final String brand;
    private String manufacturingCountry;

    public Electronics(int itemId, String itemTitle, BigDecimal price,
                       String brand, String manufacturingCountry) {
        super(itemId, itemTitle, price);
        this.brand = brand;
        this.manufacturingCountry = manufacturingCountry;
    }

    public String getBrand() {
        return brand;
    }

    public String getManufacturingCountry() {
        return manufacturingCountry;
    }

    public void setManufacturingCountry(String manufacturingCountry) {
        this.manufacturingCountry = manufacturingCountry;
    }
}
