package task1.items.electronics.smartphones;

import task1.items.electronics.Electronics;

import java.math.BigDecimal;

public class Smartphone extends Electronics {

    private final MobileOS operatingSystem;
    private float screenSize;
    private int memory;
    private SimCards[] simCard;

    public Smartphone(int itemId, String itemTitle, BigDecimal price, String brand, String manufacturingCountry,
                      MobileOS operatingSystem, float screenSize, int memory, SimCards[] simCard) {
        super(itemId, itemTitle, price, brand, manufacturingCountry);
        this.operatingSystem = operatingSystem;
        this.screenSize = screenSize;
        this.memory = memory;
        this.simCard = simCard;
    }

    public MobileOS getOperatingSystem() {
        return operatingSystem;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public SimCards[] getSimCard() {
        return simCard;
    }

    public void setSimCard(SimCards[] simCard) {
        this.simCard = simCard;
    }
}
