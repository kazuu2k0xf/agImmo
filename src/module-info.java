module AgImmo {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires org.apache.poi.ooxml;
	requires javafx.graphics;
	requires bcrypt;
	
	opens controller to javafx.graphics, javafx.fxml;
}
