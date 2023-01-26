module com.damare{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.json;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires google.api.services.calendar.v3.rev411;
    requires com.google.api.client.auth;
    requires google.api.client;
    requires com.google.api.client.extensions.jetty.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires java.desktop;
    requires org.testng;


    opens com.damare.main to javafx.fxml;
    exports com.damare.main;

}

