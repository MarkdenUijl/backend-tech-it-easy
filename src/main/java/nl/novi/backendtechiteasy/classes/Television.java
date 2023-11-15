package nl.novi.backendtechiteasy.classes;

public class Television {
    private String name;
    private String type;
    private int serialNumber;

    public Television(String name, String type, int serialNumber) {
        this.name = name;
        this.type = type;
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
