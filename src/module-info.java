module AgImmo {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires org.apache.poi.ooxml;
	requires javafx.graphics;
	
	opens controller to javafx.graphics, javafx.fxml;
}
