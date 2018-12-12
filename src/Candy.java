
public class Candy extends Snack{

	private String snackType = "candy";
	private boolean isCandy = true;
	
	
	public Candy() {
		super();
	}

	public Candy(String name, String brand, String productClass, double price, double sugar, double protein, double sodium, double calories) {
		super(name, brand, productClass, price, sugar, protein, sodium, calories);
	}
	
	//Copy Constructor
	public Candy(Product productToCopy) {
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
		resultStringBuilder.append("Candy: " + " ");
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

	public String getSnackType() {
		return snackType;
	}

	public boolean isCandy() {
		return isCandy;
	}

	@Override
	public int compareTo(Product product) {
		if (product instanceof Candy) {
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
