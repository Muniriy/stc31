package task1.items.electronics.laptops;

import task1.items.electronics.Electronics;

import java.math.BigDecimal;

public class Laptop extends Electronics {

    private final LaptopOS operatingSystem;
    private String cpu;
    private int ram;
    private float screenSize;

    public Laptop(int itemId, String itemTitle, BigDecimal price, String brand, String manufacturingCountry,
                  LaptopOS operatingSystem, String cpu, int ram, float screenSize) {
        super(itemId, itemTitle, price, brand, manufacturingCountry);
        this.operatingSystem = operatingSystem;
        this.cpu = cpu;
        this.ram = ram;
        this.screenSize = screenSize;
    }

    public LaptopOS getOperatingSystem() {
        return operatingSystem;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }
}
