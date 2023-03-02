module com.example.parckinglot_c2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.parckinglot_c2 to javafx.fxml;
    exports com.example.parckinglot_c2.Controllers;
    opens com.example.parckinglot_c2.Controllers to javafx.fxml;
    exports com.example.parckinglot_c2;
}