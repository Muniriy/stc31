package task1.items.electronics.laptops;

public enum LaptopOS {
    MAC_OS11 ("macOS 11"),
    WINDOWS10 ("Windows 10"),
    LINUX ("CentOS 7");

    private final String operatingSystem;

    LaptopOS(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
