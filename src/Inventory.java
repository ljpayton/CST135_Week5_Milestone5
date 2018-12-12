
public class Inventory {
	
	private String name;
	private String brand;
	private String productClass;
	private double price;

	private int quantity;
	
	
	public Inventory(String name, String brand, String productClass, double price, int quantity) {
		this.name = name;
		this.brand = brand;
		this.productClass = productClass;
		this.price = price;
		this.quantity =  quantity;
	}
//	public void addItemToInvetory(String name) {
//		for(int i = 0; i < inventory.length; i++ ) {
//			if (inventory[i]== null) {
//				inventory[i] = name;
//			System.out.println("You have added:" + name);
//				return;
//			}
//	}
//	System.out.print("Inventory is empty");
//}
//	public void printInventory() {
//		for(String x:inventory) {
//			System.out.println(x);
//		}
//	}
//	private static String inventory [] = new String[15];
//


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProductClass() {
		return productClass;
	}
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;	}


}
