package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class ExciseProduct extends Product {
    protected static BigDecimal EXCISE = new BigDecimal("5.56");

    public ExciseProduct(String name, BigDecimal price, BigDecimal tax) {
        super(name, price, tax);
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return price.multiply(taxPercent).add(price).add(EXCISE);
    }
}
