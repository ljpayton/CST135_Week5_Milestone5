import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;





public class Main extends Application{

	
	
    Stage window;
    public TableView<Inventory> table;
    TextField quantityInput;
  
    BorderPane mainScreen;
	public Scene scene1, scene2;
    private GridPane mainGrid;
    private Button drinksButton, chipsButton, candyButton, gumButton, backButton, payButton, cancelOrderButton;
    private VBox drinksVB, chipsVB, candyVB, gumVB, leftAreaVB, rightAreaVB;
    private HBox topAreaHB, bottomAreaHB;
    private Label header, cartLabel, totalBill;
    private ImageView drinksIV, candyIV, chipsIV, gumIV;
    private Transaction order;
    private Dispenser vendingMachine;
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Creates the inventory 
		vendingMachine = new Dispenser();
		//vendingMachine.displayProducts();	 
		//locally references the stage
		window = primaryStage;
		//Starts the interface
		goToMain();
		//Starts a transaction, to track which items are being added to the cart. when Add to Cart is clicked
		startTransaction();
		//===================================Select Role===========================
		Label label1 = new Label ("Please Select Role"); 
		Label label2 = new Label ("Manage Inventory");
		
		//Buttons screen 1
		Button manager = new Button("Manager Role");
		Button customer = new Button("Customer Role");
		Button updateButton = new Button("Go Back");

		updateButton.setOnAction(e-> window.setScene(scene1));
		manager.setOnAction(e -> window.setScene(scene2));
		customer.setOnAction(e-> goToMain());
		
		VBox layout1 = new VBox(20); //vertical
		layout1.getChildren().addAll(label1, manager, customer);
		scene1 = new Scene(layout1, 200, 200);
		
		window.setScene(scene1);
		window.show();
		//===================================End Select Role===========================
		
		TableColumn<Inventory, String> nameCol = new TableColumn<> ("Name");
		nameCol.setMinWidth(75);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Inventory, String> brandCol = new TableColumn<> ("Brand");
		brandCol.setMinWidth(75);
		brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));

		TableColumn<Inventory, String> productCol = new TableColumn<> ("Product");
		productCol.setMinWidth(75);
		productCol.setCellValueFactory(new PropertyValueFactory<>("productClass"));

		TableColumn<Inventory, Double> priceCol = new TableColumn<> ("Price");
		priceCol.setMinWidth(75);
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<Inventory, Integer> quantityCol = new TableColumn<> ("Quantity");
		quantityCol.setMinWidth(75);
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
		quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));       

		quantityCol.setOnEditCommit(
			    (CellEditEvent<Inventory, Integer> t) -> {
			        ((Inventory) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setQuantity(t.getNewValue());
			});
		
		table = new TableView<>();
		table.setItems(getInventory());
		table.setEditable(true);
		table.getColumns().addAll(nameCol, brandCol, productCol, priceCol, quantityCol);
	
	HBox extra = new HBox();
	extra.setPadding(new Insets(10,10,10,10));
	extra.getChildren().add(updateButton);
	
	VBox layout2 = new VBox(60); //vertical
		layout2.getChildren().addAll(label2,table,extra);
		scene2 = new Scene(layout2, 700,700);
	
	}
	

	
	
	public void goToMain() {
		
		//Sets the title at the top of the stage
		window.setTitle("HuttoPayton Vending Machine");
		
		//Main Layout is a borderpane
		mainScreen = new BorderPane();
				
		//Setting up the Payment Buttons
		//During the first call, startTransaction hasn't been called yet, 
		//so order is null which sets the buttons to be invisible at first, which is what we want.
		if(order == null) {
			//Pay Button
				payButton = new Button("Pay");
				//We want the pay button to be invisible until there is at least one item in the cart to pay for
				payButton.setVisible(false);
				//When pay is clicked, calls the payBill method
				payButton.setOnAction(e -> payBill());
			
			//Cancel Button
				cancelOrderButton = new Button("Cancel");
				//We want the cancel button to also be invisible until there is at least one item in the cart, otherwise clicking it is meaningless
				cancelOrderButton.setVisible(false);
				//When cancel is clicked, it calls the cancelTransaction method
				cancelOrderButton.setOnAction(e -> cancelTransaction());
			
		} else {
			//If the cart isn't empty, meaning that we go to the main screen via the back button, we want the buttons to be visible
			if(!order.getItems().isEmpty()) {
				payButton = new Button("Pay");
				payButton.setVisible(true);
				payButton.setOnAction(e -> payBill());
				cancelOrderButton = new Button("Cancel");
				cancelOrderButton.setVisible(true);
				cancelOrderButton.setOnAction(e -> cancelTransaction());
			}
			//When back is clicked, we went the items in the cart to be shown, so we have to print them again
			for(Product cartItem : order.getItems()) {
				Label newCartItem = new Label(cartItem.getBrand() + " " + cartItem.getName() + " " + cartItem.getPrice());
				System.out.println(newCartItem.getText());
				rightAreaVB.getChildren().add(newCartItem);
			}
		}
		//BorderPane Center Area 
				//Setting up the main Screen Gridpane
				//Setting up the imageviews to go into the VBoxes
				drinksIV = new ImageView(new Image(getClass().getResourceAsStream("drinkImage.jpg")));
				drinksIV.setFitHeight(250);
				drinksIV.setFitWidth(300);
				
				chipsIV = new ImageView(new Image(getClass().getResourceAsStream("chipsImage.jpg")));
				chipsIV.setFitHeight(250);
				chipsIV.setFitWidth(300);
				
				candyIV = new ImageView(new Image(getClass().getResourceAsStream("candyImage.jpg")));
				candyIV.setFitHeight(250);
				candyIV.setFitWidth(300);
				
				gumIV = new ImageView(new Image(getClass().getResourceAsStream("gumImage.jpg")));
				gumIV.setFitHeight(250);
				gumIV.setFitWidth(300);
				
				
				//setting up the buttons to go with the corresponding images
				drinksButton = new Button("Drinks");
				drinksButton.setOnAction(e-> showDrinks());
				chipsButton = new Button("Chips");
				chipsButton.setOnAction(e-> showChips());
				candyButton = new Button("Candy");
				candyButton.setOnAction(e-> showCandy());
				gumButton = new Button("Gum");
				gumButton.setOnAction(e-> showGum());
				
				//Setting up the VBoxs to go into the gridpane
				//Drinks VBox
				drinksVB = new VBox();
				drinksVB.getChildren().addAll(drinksIV, drinksButton);
				drinksVB.setPrefWidth(300);
				drinksVB.setPrefHeight(300);
				drinksVB.setAlignment(Pos.CENTER);

				//Chips VBox
				chipsVB = new VBox();
				chipsVB.getChildren().addAll(chipsIV, chipsButton);
				chipsVB.setPrefWidth(300);
				chipsVB.setPrefHeight(300);
				chipsVB.setAlignment(Pos.CENTER);

				//Candy VBox
				candyVB = new VBox();
				candyVB.getChildren().addAll(candyIV, candyButton);
				candyVB.setPrefWidth(300);
				candyVB.setPrefHeight(300);
				candyVB.setAlignment(Pos.CENTER);

				//Gum VBox
				gumVB = new VBox();
				gumVB.getChildren().addAll(gumIV, gumButton);	
				gumVB.setPrefWidth(300);
				gumVB.setPrefHeight(300);
				gumVB.setAlignment(Pos.CENTER);
				
				Button bButton = new Button("Return To Main Menu");
				bButton.setOnAction(e -> window.setScene(scene1));
				VBox leftBar = new VBox(6);
				leftBar.setPrefHeight(50);
				leftBar.setPrefWidth(200);
				leftBar.getChildren().addAll(bButton);
				
				//The gridPane itself
				mainGrid = new GridPane();
				mainGrid.setPrefWidth(600);
				mainGrid.setPrefHeight(600);
				mainGrid.setAlignment(Pos.CENTER);
				mainGrid.setMinWidth(Control.USE_PREF_SIZE);
				mainGrid.setMinHeight(Control.USE_PREF_SIZE);
				mainGrid.add(drinksVB, 0, 0);
				mainGrid.add(chipsVB, 0, 1);
				mainGrid.add(candyVB, 1, 0);
				mainGrid.add(gumVB, 1, 1);
				mainGrid.getChildren().add(leftBar);
				
		//BorderPane Top Area
				topAreaHB = new HBox();
				header = new Label("HuttoPayton Vending Machine");
				header.setFont(new Font("Times New Roman", 32));
				topAreaHB.setAlignment(Pos.CENTER);
				topAreaHB.getChildren().addAll(header);
				topAreaHB.setPrefHeight(100);
				topAreaHB.setPrefWidth(1000);
				
				
		//BorderPane Right Area		
				rightAreaVB = new VBox();
				cartLabel = new Label("Your Cart:");
				rightAreaVB.getChildren().addAll(payButton, cancelOrderButton, cartLabel);
				if(order != null) {
					for(Product cartItem : order.getItems()) {
						Label newCartItem = new Label(cartItem.getBrand() + " " + cartItem.getName() + " " + cartItem.getPrice());
						System.out.println(newCartItem.getText());
						rightAreaVB.getChildren().add(newCartItem);
					}
				}
				rightAreaVB.setPrefHeight(600);
				rightAreaVB.setPrefWidth(200);
		
		
		//BorderPane Left Area
				leftAreaVB = new VBox();
				backButton = new Button("Back");
				backButton.setOnAction(e -> goToMain());
				backButton.setVisible(false);
				leftAreaVB.getChildren().addAll(backButton);
				leftAreaVB.setPrefHeight(600);
				leftAreaVB.setPrefWidth(200);
		
				
		
		//BorderPane Bottom Area
				bottomAreaHB = new HBox();
				if(order == null) {
					totalBill = new Label("Total: $0.00");
					totalBill.setFont(new Font("Times New Roman", 32));
					totalBill.setAlignment(Pos.CENTER);
				}
				else {
					totalBill = new Label("Total: $" + order.getTotal());
					totalBill.setFont(new Font("Times New Roman", 32));
					DecimalFormat df = new DecimalFormat("#.00"); 
					totalBill.setText("Total: " + df.format(order.getTotal()));
					totalBill.setAlignment(Pos.CENTER);
				}
				bottomAreaHB.getChildren().addAll(totalBill);
				//bottomAreaHB.setStyle("-fx-background-color: brown");
				bottomAreaHB.setPrefHeight(100);
				bottomAreaHB.setPrefWidth(1000);
				
		//================== End of GridPane ======================================
				

				
		//Attaches all of nodes to the borderpane
				
		mainScreen.setTop(topAreaHB);	
		mainScreen.setLeft(leftAreaVB);
		mainScreen.setCenter(mainGrid);
		mainScreen.setRight(rightAreaVB);
		mainScreen.setBottom(bottomAreaHB);
		
		//Sets the scene
		Scene mainScene = new Scene(mainScreen);
		window.setScene(mainScene);
		//Shows it on the stage
		window.show();
	}

	//Goes to the drink menu
	public void showDrinks() {
		//Now you can see the back button, since we are at a different menu
		backButton.setVisible(true);
		//Don't want to see the other options unless back is clicked
		mainGrid.getChildren().clear();
		//Creates a new list for drink items only
		ArrayList<DispenserQueue> drinksInMachine = vendingMachine.searchForDrinks();
		//Creates a new gridpane to show the drink items
		GridPane drinkGrid = new GridPane();
		//Counts how many drink items there are
		int numDrinkItems = 0;
		for(@SuppressWarnings("unused") DispenserQueue queue : drinksInMachine) {
			numDrinkItems++;
		}
		//System.out.println("numDrinkInMachine: " + numDrinkItems);
		//The attempt to even out the items, aka when to start a new row of items, that's not simple user declaration
		int gridRowLength = numDrinkItems / 2;
		//for tracking where in the row/column to put the item in the grid
		int row = 0;
		int column = 0;
		//For each different drink item
		for(DispenserQueue queue : drinksInMachine) {
			//Makes a VBox for all of the nodes
			VBox drink = new VBox();
			//Makes a label to tell you what the product is and how much
			Label productName = new Label(queue.getSlot().get(0).getName() + "  Price: " + queue.getSlot().get(0).getPrice());
			//Sets up the image
			ImageView productImage = queue.getProductImage();
			//Sets the size for the image
			productImage.setFitHeight(250);
			productImage.setFitWidth(200);
			//Sets up the Add to Cart Button
			Button addToCart = new Button("Add to Cart");
			addToCart.setOnAction(e -> addToCart(queue.getSlot().get(0)));
			//Attaches all of the children nodes to the vbox
			drink.getChildren().addAll(productName, productImage, addToCart);
			//Adds the vbox to the grid of drinks
			drinkGrid.add(drink, column, row);
			//Tracks the spot in grid
			column++;
			if(column == gridRowLength) {
				column = 0;
				row++;
			}
			numDrinkItems++;
		}
		//updates the gridpane to the drink grid
		mainScreen.setCenter(drinkGrid);		
	}
	//Goes to the chips menu
	public void showChips() {
		//Show the back button
		backButton.setVisible(true);
		//Clears the main screen grid
		mainGrid.getChildren().clear();
		//Gets the list of chip items
		ArrayList<DispenserQueue> chipsInMachine = vendingMachine.searchForChips();
		//Creates a new grid for chip items
		GridPane chipGrid = new GridPane();
		//Tracks the number of chip items
		int numChipItems = 0;
		for(@SuppressWarnings("unused") DispenserQueue queue : chipsInMachine) {
			numChipItems++;
		}
		//Grid placement variables 
		int gridRowLength = numChipItems / 2;
		int row = 0;
		int column = 0;
		//Creates grid of chips
		for(DispenserQueue queue : chipsInMachine) {
			//Vbox to put the image, description, prices, and addtoCart button
			VBox chip = new VBox();
			Label productName = new Label(queue.getSlot().get(0).getName() + "  Price: " + queue.getSlot().get(0).getPrice());
			//Sets up the image
			ImageView productImage = queue.getProductImage();
			productImage.setFitHeight(250);
			productImage.setFitWidth(200);
			//Creates the button
			Button addToCart = new Button("Add to Cart");
			addToCart.setOnAction(e -> addToCart(queue.getSlot().get(0)));
			// Adds the nodes to the VBox
			chip.getChildren().addAll(productName, productImage, addToCart);
			//Adds the vbox to the grid
			chipGrid.add(chip, column, row);
			column++;
			if(column == gridRowLength) {
				column = 0;
				row++;
			}
			numChipItems++;
		}
		//Puts the chip grid into the center area of the borderpane
		mainScreen.setCenter(chipGrid);
	}
	//Goes to the candy menu
	public void showCandy() {
		//Shows the back button
		backButton.setVisible(true);
		//Removes the main menu
		mainGrid.getChildren().clear();
		//Creates the list of candy items 
		ArrayList<DispenserQueue> candyInMachine = vendingMachine.searchForCandy();
		//Creates a grid for the chip items
		GridPane candyGrid = new GridPane();
		//Tracks the number of chip items
		int numCandyItems = 0;
		for(@SuppressWarnings("unused") DispenserQueue queue : candyInMachine) {
			numCandyItems++;
		}
		
		//Tracking grid position
		int gridRowLength = numCandyItems / 2;
		int row = 0;
		int column = 0;
		//Fills in the grid
		for(DispenserQueue queue : candyInMachine) {
			//Creates the VBox
			VBox chip = new VBox();
			//Creates the information label
			Label productName = new Label(queue.getSlot().get(0).getName() + "  Price: " + queue.getSlot().get(0).getPrice());
			//Creates the image
			ImageView productImage = queue.getProductImage();
			productImage.setFitHeight(250);
			productImage.setFitWidth(200);
			//Creates the button
			Button addToCart = new Button("Add to Cart");
			addToCart.setOnAction(e -> addToCart(queue.getSlot().get(0)));
			//adds the label, image, and button to the VBox
			chip.getChildren().addAll(productName, productImage, addToCart);
			//Adds the VBox to the grid
			candyGrid.add(chip, column, row);
			column++;
			if(column == gridRowLength) {
				column = 0;
				row++;
			}
			numCandyItems++;
		}
		//Puts the candy grid into the center area
		mainScreen.setCenter(candyGrid);
	}
	//Goes to the gum menu
	public void showGum() {
		//Shows the back button
		backButton.setVisible(true);
		//Removes the main menu
		mainGrid.getChildren().clear();
		//Creates a list of the gum items
		ArrayList<DispenserQueue> gumInMachine = vendingMachine.searchForGum();
		//Creates a new grid for gum items
		GridPane gumGrid = new GridPane();
		//Tracks the number of gum items
		int numGumItems = 0;
		for(@SuppressWarnings("unused") DispenserQueue queue : gumInMachine) {
			numGumItems++;
		}
		//Trakcs the grid position
		int gridRowLength = numGumItems / 2;
		int row = 0;
		int column = 0;
		//Fill the gum grid
		for(DispenserQueue queue : gumInMachine) {
			//Creates a VBox for the gum item
			VBox chip = new VBox();
			//Creates the label with name and price
			Label productName = new Label(queue.getSlot().get(0).getName() + "  Price: " + queue.getSlot().get(0).getPrice());
			//Creates the image and sets its size
			ImageView productImage = queue.getProductImage();
			productImage.setFitHeight(250);
			productImage.setFitWidth(200);
			//Creates the  Add to cart Button
			Button addToCart = new Button("Add to Cart");
			addToCart.setOnAction(e -> addToCart(queue.getSlot().get(0)));
			//Adds the label, image, and button 
			chip.getChildren().addAll(productName, productImage, addToCart);
			//Adds the VBox to the grid
			gumGrid.add(chip, column, row);
			column++;
			if(column == gridRowLength) {
				column = 0;
				row++;
			}
			numGumItems++;
		}
		//Sets the gridpane to the center area of the borderpane mainScreen
		mainScreen.setCenter(gumGrid);
	}
	
	//starts a transaction
	public void startTransaction() {
		//Creates a new empty cart
		order = new Transaction();
	}
	//When cancel is clicked
	public void cancelTransaction() {
		//If there isn't a cart stop
		if(order == null) {
			//System.out.println("Order was null");
			return;
		}
		//Overwrites the current transaction
		order = new Transaction();
		//Sets the total bill back to 0
		DecimalFormat df = new DecimalFormat("#.00"); 
		totalBill.setText("Total: " + df.format(order.getTotal()));
		
		//Clears the labels of the items in the cart
		rightAreaVB.getChildren().clear();
		rightAreaVB.getChildren().addAll(payButton, cancelOrderButton, cartLabel);

		//Since the cart is empty, hide the pay and cancel buttons
		payButton.setVisible(false);
		cancelOrderButton.setVisible(false);
	}
	//Pay was clicked
	public void payBill() {
		//calls transaction pay bill
		order.payBill();
		//After the bill is payed hide the pay and cancel buttons because the cart is empty again
		payButton.setVisible(false);
		cancelOrderButton.setVisible(false);
		//Clears the labels of the cart items since the cart is empty
		rightAreaVB.getChildren().clear();
		rightAreaVB.getChildren().addAll(payButton, cancelOrderButton, cartLabel);
		//Sets the total bill back to zero
		DecimalFormat df = new DecimalFormat("#.00"); 
		totalBill.setText("Total: " + df.format(order.getTotal()));
	}
	//Add the item to the cart
	public void addToCart(Product productToAddToCart) {
		//Adds the item to the transaction
		order.add(productToAddToCart);
		//Creates the label to post into the right area vb 
		Label newCartItem = new Label(productToAddToCart.getBrand() + " " + 
									  productToAddToCart.getName() + " " + 
									  productToAddToCart.getPrice());
		//Adds the new item to the list of cart items
		rightAreaVB.getChildren().add(newCartItem);
		
		//Formats and updates the total
		DecimalFormat df = new DecimalFormat("#.00"); 
		totalBill.setText("Total: " + df.format(order.getTotal()));
		//Since there is now an item in the cart, show the pay and cancel buttons
		payButton.setVisible(true);
		cancelOrderButton.setVisible(true);
	}
	

public ObservableList<Inventory> getInventory(){
ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
inventoryList.add(new Inventory("Original", "Coca-Cola", "Drink", .99,10));
inventoryList.add(new Inventory("Vanilla", "Coca-Cola", "Drink", .99,10));
inventoryList.add(new Inventory("Cherry", "Coca-Cola", "Drink", .99,10));
inventoryList.add(new Inventory("power-c", "Vitamin Water", "Drink", .99,10));
inventoryList.add(new Inventory("vital-t", "Vitamin Water", "Drink", .99,10));
inventoryList.add(new Inventory("XXX", "Vitamin Water", "Drink", .99,10));
inventoryList.add(new Inventory("Polar Ice", "Extra", "Gum", .75,10));
inventoryList.add(new Inventory("Spearmint", "Extra", "Gum", .75,10));
inventoryList.add(new Inventory("Cinnamon", "Extra", "Gum", .75,10));
inventoryList.add(new Inventory("Polar Ice", "Extra", "Gum", .75,10));
inventoryList.add(new Inventory("Spearmint", "Extra", "Gum", .75,10));
inventoryList.add(new Inventory("Cinnamon", "Extra", "Gum", .75,10));
inventoryList.add(new Inventory("Watermelon", "Extra", "Gum", .75,10));
inventoryList.add(new Inventory("Corn", "Doritos", "Chips", .98,10));
inventoryList.add(new Inventory("Ranch", "Doritos", "Chips", .98,10));
inventoryList.add(new Inventory("Chocolate", "Hershey's", "Candy", .99,10));
inventoryList.add(new Inventory("Krackel", "Hershey's", "Candy", .99 ,10));
inventoryList.add(new Inventory("White Chocolate", "Hershey's", "Candy", .99,10));
inventoryList.add(new Inventory("Almond", "Hershey's", "Candy", .99,10));

return inventoryList;

}

}

