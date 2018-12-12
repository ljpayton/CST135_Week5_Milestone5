import java.util.ArrayList;

import javafx.util.Callback;

public class Transaction {
	
	private ArrayList<Product> cart;
	private double total; 
	
	
	public Transaction() {
		cart = new ArrayList<Product>();
		total = 0.00;
	}
	
	public void add(Product itemToBuy) {
		System.out.print(itemToBuy.getName() + " was added to the cart ");
		cart.add(itemToBuy);
		total = total + itemToBuy.getPrice();
		System.out.println("the new total is:" + total);
	}
	

	public void payBill() {
		System.out.println("payBill() was called");
		for(Product cartItem : cart) {
			System.out.println("you purchased a " + cartItem.getName() + " for " + cartItem.getPrice());
		}
		cart = new ArrayList<Product>();
		total = 0.0;
		for(Product cartItem : cart) {
			System.out.println("you have a " + cartItem.getName() + " for " + cartItem.getPrice() + "in the cart still.");
		}
		if(cart != null) {
			System.out.println("cart is not null");
		}
	}

	public double getTotal() {
		return total;
	}

	public ArrayList<Product> getItems() {
		
		if(cart == null) {
			ArrayList<Product> emptyCart = new ArrayList<Product>();
			return emptyCart;
		}
		else {
			return cart;
		}
		
	}
	
}
