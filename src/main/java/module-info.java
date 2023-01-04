module com.damare.damare {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.damare to javafx.fxml;
    exports com.damare;
}