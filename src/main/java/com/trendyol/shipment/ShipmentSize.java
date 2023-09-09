package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    private static final ShipmentSize[] sizes = values();

    public ShipmentSize nextSize() {
        return this == X_LARGE ?
                X_LARGE :
                sizes[this.ordinal() + 1];
    }

    public boolean isLargerThan(ShipmentSize size) {
        return this.ordinal() > size.ordinal();
    }
}
