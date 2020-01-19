package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;



import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	
	private HashMap<Product, Integer> products = new HashMap<>();
	

	public void addProduct(Product product) {
		products.put(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if(products.containsKey(product)) {
			Integer updatedQuntity = products.get(product);
		}
	}

	public BigDecimal getSubtotal() {
		return null;
	}

	public BigDecimal getTax() {
		return null;
	}

	public BigDecimal getTotal() {
		return null;
	}
}
