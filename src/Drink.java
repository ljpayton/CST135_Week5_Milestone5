public class Drink extends Product {
	private String productType = "drink";
	private boolean isDrink = true;

	public Drink() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	/*
	public static void main(String[] args) {
		Product p1 = new Product("Drink 1", "power-c", "Vitamin Water", 0.99, 12.32, 23, 34, 3);
		System.out.println(p1);
		Product p2 = new Product("Drink 2", "vital-t", "Vitamin water", 0.99, 12.32, 23, 34, 3);
		System.out.println(p2);
		System.out.println("p1.compareTo(p2) : " + p1.compareTo(p2));
		System.out.println("p2.compareTo(p2) : " + p2.compareTo(p2));
		Drink d1 = new Drink(p1);
		Drink d2 = new Drink(p2);
		System.out.println("d1.compareTo(d2) : " + d1.compareTo(d2));
		System.out.println("d2.compareTo(d2) : " + d2.compareTo(d2));
	}
	*/
	
	
	
	public String getProductType() {
		return productType;
	}

	public boolean isDrink() {
		return isDrink;
	}

	public Drink(String name, String brand, String productClass, double price, double sugar, double protein, double sodium,
			double calories) {
		super(name, brand, productClass, price, sugar, protein, sodium, calories);
	}

	// Copy Constructor
	public Drink(Product productToCopy) {
		super();
		this.setName(productToCopy.getName());
		this.setBrand(productToCopy.getBrand());
		this.setProductClass(productToCopy.getProductClass());
		this.setPrice(productToCopy.getPrice());
		this.setSugar(productToCopy.getSugar());
		this.setProtein(productToCopy.getProtein());
		this.setSodium(productToCopy.getSodium());
		this.setExpirationDate(productToCopy.getExpirationDate());
	}

	@Override
	public String toString() {
		StringBuilder resultStringBuilder = new StringBuilder();
		resultStringBuilder.append("Drink: " + " ");
		resultStringBuilder.append(this.getName() + " ");
		resultStringBuilder.append(this.getBrand() + " ");
		resultStringBuilder.append(this.getPrice() + " ");
		resultStringBuilder.append(this.getSugar() + " ");
		resultStringBuilder.append(this.getProtein() + " ");
		resultStringBuilder.append(this.getSodium() + " ");
		resultStringBuilder.append(this.getCalories() + " ");
		resultStringBuilder.append(this.getExpirationDate() + " ");
		String result = resultStringBuilder.toString();
		return result;
	}

	@Override
	public int compareTo(Product product) {
		if (product instanceof Drink) {
			int result = (this.getName()).compareTo(product.getName());
			if (result == 0) {
				if ((this.getPrice()) == (product.getPrice())) {
					return 0;
				} else if ((this.getPrice()) > (product.getPrice())) {
					return 1;
				} else {
					return -1;
				}
			} else {
				return result;
			}
		} else {
			return super.compareTo(product);
		}
	}
}
