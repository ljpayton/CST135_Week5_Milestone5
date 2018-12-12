import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Product implements Comparable<Product> {
	
	// Shared Properties
	private String name;
	private String brand;
	private String productClass;
	private double price;
	
	// Nutrition Facts
	private double sugar;
	private double protein;
	private double sodium;
	private double calories;
	
	// Expiration Date - format mm/dd/yyyy
	private String expirationDate;

	// Default Constructor
	public Product() {
		this.name = null;
		this.brand = null;
		this.productClass = null;
		this.price = 0.0;
		this.sugar = 0.0;
		this.protein = 0.0;
		this.sodium = 0.0;
		this.calories = 0.0;
		this.setExpirationDate(calculateExpirationDate());
	}

	// Parameterized Constructor
	public Product(String name, String brand, String productClass, double price, double sugar, double protein,
			double sodium, double calories) {
		this.name = name;
		this.brand = brand;
		this.productClass = productClass;
		this.price = price;
		this.sugar = sugar;
		this.protein = protein;
		this.sodium = sodium;
		this.calories = calories;
		this.setExpirationDate(calculateExpirationDate());
	}

	// Copy Constructor
	public Product(Product productToCopy) {
		this.name = productToCopy.getName();
		this.brand = productToCopy.getBrand();
		this.productClass = productToCopy.getProductClass();
		this.price = productToCopy.getPrice();
		this.sugar = productToCopy.getSugar();
		this.protein = productToCopy.getProtein();
		this.sodium = productToCopy.getSodium();
		this.calories = productToCopy.getCalories();
		this.setExpirationDate(calculateExpirationDate());
	}

	public String toString() {
		StringBuilder resultStringBuilder = new StringBuilder();
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

	// Temporary compares the name of the products, returns 1 if equal or -1 if
	// not. Will be needed later.
	@Override
	public int compareTo(Product other) {
		int result = 0;
		if ((this.name).equals(other.name)) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}

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

	public double getSugar() {
		return sugar;
	}

	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String calculateExpirationDate() {
		Date today = new Date();
		LocalDate localDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		// For Drinks, add 9 months
		if (this.getClass().getSimpleName().equals("Drink"))
			month += 9;
		// For Chips, add 3 months
		if (this.getClass().getSimpleName().equals("Chips"))
			month += 3;
		// For Candy, add 6 months
		if (this.getClass().getSimpleName().equals("Candy"))
			month += 6;
		// For Gum, add 1 year
		if (this.getClass().getSimpleName().equals("Drink"))
			year += 1;
		String expDate = month + "/" + day + "/" + year;
		return expDate;
	}
	
	public boolean isDrink() {
		if(productClass.equals("Drink")) {
			return true;
		}
		else {
			return false;

		}
	}
	public boolean isChips() {
		if(productClass.equals("Chips")) {
			return true;
		}
		else {
			return false;

		}
	}
	public boolean isCandy() {
		if(productClass.equals("Candy")) {
			return true;
		}
		else {
			return false;

		}
	}
	public boolean isGum() {
		if(productClass.equals("Gum")) {
			return true;
		}
		else {
			return false;

		}
	}
	
	
	
}
