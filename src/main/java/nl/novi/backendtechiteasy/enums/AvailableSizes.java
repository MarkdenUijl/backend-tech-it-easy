package nl.novi.backendtechiteasy.enums;

public enum AvailableSizes {
    SIZE_42(42L),
    SIZE_44(44L),
    SIZE_55(55L);

    private final Long screenSize;

    AvailableSizes(Long screenSize) {
        this.screenSize = screenSize;
    }

    public Long getScreenSize() {
        return screenSize;
    }
}
