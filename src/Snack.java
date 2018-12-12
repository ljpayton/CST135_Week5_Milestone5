public abstract class Snack extends Product {

	private String productType = "snack";
	private boolean isSnack = true;
	
	public Snack() {
		super();
	}
	
	
	public String getProductType() {
		return productType;
	}


	public boolean isSnack() {
		return isSnack;
	}


	public Snack(String name, String brand, String productClass, double price, double sugar, double protein, double sodium, double calories) {
		super(name, brand, productClass, price, sugar, protein, sodium, calories);
	}
	
	//Copy Constructor
	public Snack(Product productToCopy) {
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

}
