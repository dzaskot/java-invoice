package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private static int previousNumber;

    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private int number;
    
    public Invoice() {
        this.number = previousNumber + 1;
        previousNumber = this.number;
    }

    public void addProduct(Product product) {
        if (!products.containsKey(product)) {
            addProduct(product, 1);
        } else {
            products.put(product, products.get(product) + 1);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        } else if (!products.containsKey(product)) {
            products.put(product, quantity);
        } else {
            products.put(product, products.get(product) + quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }
    
    public int getNumber() {
        return this.number;
    }

    public String print() {

        StringBuilder invoicePrint = new StringBuilder();
        invoicePrint.append(this.number);
        invoicePrint.append(System.lineSeparator());
        List<String> productsList = products.entrySet().stream()
                .map(entry -> entry.getKey().getName() + " "
                        + entry.getValue() + " "
                        + entry.getKey().getPriceWithTax()
                        .multiply(new BigDecimal(entry.getValue()))
                        .setScale(2, RoundingMode.CEILING))
                .sorted()
                .collect(Collectors.toList());
        for (String p : productsList) {
            invoicePrint.append(p);
            invoicePrint.append(System.lineSeparator());
        }
        invoicePrint.append(products.size());

        return invoicePrint.toString();
    }

    public int getProductQuantity(Product product) {
        if (products.containsKey(product)) {
            return products.get(product);
        } else {
            return 0;
        }
    }
}