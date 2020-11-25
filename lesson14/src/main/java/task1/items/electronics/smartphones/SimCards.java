package task1.items.electronics.smartphones;

public enum SimCards {
    NANOSIM("Nano sim"),
    MICROSIM("Micro sim"),
    MINISIM("Mini sim"),
    ESIM("eSim");

    private final String type;

    SimCards(String type) {
        this.type = type;
    }
}
