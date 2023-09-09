package com.trendyol.shipment;

import java.util.HashMap;
import java.util.List;

import static com.trendyol.shipment.ShipmentSize.*;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        var sizeMap = new HashMap<ShipmentSize, Integer>();
        var maxSize = SMALL;
        for (var product: products ) {
            var productSize = product.getSize();
            var sizeCount = sizeMap.get(productSize);
            sizeCount = sizeCount == null ? 0 : sizeCount;

            if (sizeCount == 2) {
                return productSize.nextSize();
            }

            sizeMap.put(productSize, sizeCount + 1);
            if (productSize.isLargerThan(maxSize)) {
                maxSize = productSize;
            }
        }

        return maxSize;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
