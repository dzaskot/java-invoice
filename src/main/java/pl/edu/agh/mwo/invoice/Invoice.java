package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;



import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	
	private HashMap<Product, Integer> products = new HashMap<>();
	

	public void addProduct(Product product) {
		this.addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if(!(quantity <= 0)) {
		products.put(product, quantity);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public BigDecimal getNetPrice() {
		BigDecimal totalNetPrice = BigDecimal.ZERO;
		for(Product product: products.keySet()) {
			Integer quantity = products.get(product);
			totalNetPrice = totalNetPrice.add(product.getPrice().multiply(new BigDecimal(quantity)));
		}
		return totalNetPrice;
	}

	public BigDecimal getTax() {
		return this.getGrossPrice().subtract(getNetPrice());	
	}

	public BigDecimal getGrossPrice() {
		BigDecimal totalGrossPrice = BigDecimal.ZERO;
		for(Product product: products.keySet()) {
			Integer quantity = products.get(product);
			totalGrossPrice = totalGrossPrice.add(product.getPriceWithTax().multiply(new BigDecimal(quantity)));
		}
		return totalGrossPrice;
	}
}
