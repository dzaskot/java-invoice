package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BottleOfWine extends Product {
    private BigDecimal price;
    private final BigDecimal taxPercent = new BigDecimal("0.23");

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
        this.price = price;
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return price.multiply(taxPercent).add(price)
                .add((new BigDecimal("5.56")).setScale(2, RoundingMode.CEILING));
    }

}
