package com.trendyol.shipment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.trendyol.shipment.ShipmentSize.*;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        validateProductCountAndThrowException();
        int threshold = 3;

        var productSizeMap = getProducts().stream().collect(Collectors.groupingBy(Product::getSize, Collectors.counting()));
        var productSizes = new ArrayList<>(productSizeMap.keySet());
        var highestShipmentSize = getHighestShipmentSize(productSizes);

        if (products.size() < threshold)
            return highestShipmentSize;

        return productSizeMap.entrySet().stream()
            .filter(shipmentSize -> shipmentSize.getValue() >= threshold)
            .filter(shipmentSizeLongEntry -> shipmentSizeLongEntry.getKey().isNotLast())
            .findFirst()
            .map(shipmentType -> shipmentType.getKey().nextSize())
            .orElse(highestShipmentSize);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private void validateProductCountAndThrowException() {
        if (products == null || products.isEmpty())
            throw new IllegalArgumentException("There is no item in basket.");
    }

    private ShipmentSize getHighestShipmentSize(List<ShipmentSize> shipmentSizeList) {
        return shipmentSizeList.stream().max(Comparator.comparing(Enum::ordinal)).orElse(SMALL);
    }
}
