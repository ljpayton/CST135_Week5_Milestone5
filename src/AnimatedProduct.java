import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimatedProduct {
	
	
	ImageView imageToAnimate;
	
	public AnimatedProduct(ImageView imageToAnimate){
		Image sourceImage = imageToAnimate.getImage();
		ImageView copyImageViewToAnimate = new ImageView();
		copyImageViewToAnimate.setImage(sourceImage);
	}
	
	
	/*
	 * 	Image sourceImage = new Image("http://goo.gl/kYEQl");
		ImageView imageView = new ImageView();
		imageView.setImage(sourceImage);
		ImageView destImageView = new ImageView();

		// copying sourceImage
		destImageView.setImage(SwingFXUtils.toFXImage(SwingFXUtils.fromFXImage(sourceImage, null), null));

		VBox vBox = new VBox();
		vBox.getChildren().addAll(imageView, destImageView);
		StackPane root = new StackPane();
		root.getChildren().add(vBox);
		Scene scene = new Scene(root, 300, 300);
		primaryStage.setTitle("java-buddy.blogspot.com");
		primaryStage.setScene(scene);
		primaryStage.show();
	 */
	
	
	
	public void dropInCart(ImageView productImage) {
		productImage.toFront();
		TranslateTransition dropToCart = new TranslateTransition(Duration.millis(10000), productImage);
		dropToCart.setFromY(0);
		dropToCart.setToY(10000);
		dropToCart.setCycleCount(1);
		dropToCart.play();
	}
	
	public void putBack(ImageView productImage) {
		TranslateTransition dropToCart = new TranslateTransition(Duration.millis(10), productImage);
		dropToCart.setFromY(10000);
		dropToCart.setToY(0);
		dropToCart.setCycleCount(1);
		dropToCart.play();
	}
	
	
	
	
	
	
	
}