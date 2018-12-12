
public class Gum extends Snack{
	
	private String snackType = "gum";
	private boolean isGum = true;

	public Gum() {
		// TODO Auto-generated constructor stub
		super();
	}

	
	public Gum(String name, String brand, String productClass, double price, double sugar, double protein, double sodium, double calories) {
		super(name, brand, productClass, price, sugar, protein, sodium, calories);
	}
	
	//Copy Constructor
	public Gum(Product productToCopy) {
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
	
	
	public String getSnackType() {
		return snackType;
	}


	public boolean isGum() {
		return isGum;
	}


	@Override
	public String toString() {
		StringBuilder resultStringBuilder = new StringBuilder();
		resultStringBuilder.append("Gum: " + " ");
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
		if (product instanceof Gum) {
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
