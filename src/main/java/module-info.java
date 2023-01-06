module com.damare{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.json;



    opens com.damare.main to javafx.fxml;
    exports com.damare.main;

}

