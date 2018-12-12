import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class DispenserQueue {

	String brand;
	String productName;
	String productClass;
	ArrayList<Product> slot;
	ImageView productImage;
	
	//Empty Queue Constructor
	public DispenserQueue() {
		slot = new ArrayList<Product>();
		productName = "empty";
		brand = "none";
	}
	//Initialized Queue
	public DispenserQueue(Product productType) {
		slot = new ArrayList<Product>();
		this.addProductToQueue(productType);
		productName = productType.getName();
		brand = productType.getBrand();
		productClass = slot.get(0).getProductClass();
		String imageName = productName + ".jpg";
		productImage = new ImageView(getClass().getResource(imageName).toExternalForm());
		//System.out.println( imageName + " Succeeded");
	}

	
	//Adds a single product to the queue
	public void addProductToQueue(Product productToAdd) {
		slot.add(productToAdd);
		productName = productToAdd.getName();
	}
	//Removes the specified product from the queue, ex: expiration date is passed, remove it.
	public void removeProductFromQueue(Product productToRemove) {
		slot.remove(productToRemove);
	}
	
	public String getProductType() {
		return brand;	
	}
	
	
	

	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ArrayList<Product> getSlot() {
		return slot;
	}
	public void setSlot(ArrayList<Product> slot) {
		this.slot = slot;
	}
	
	public boolean isDrinkItem() {
		if(productClass.equals("Drink")) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isChipsItem() {
		if(productClass.equals("Chips")) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isCandyItem() {
		if(productClass.equals("Candy")) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isGumItem() {
		if(productClass.equals("Gum")) {
			return true;
		}
		else {
			return false;
		}
	}
	public ImageView getProductImage() {
		return productImage;
	}

	
	
	
	

	
	
	
	
}
