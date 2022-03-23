module com.zc.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;

    opens com.zc.javafxdemo to javafx.fxml;
    exports com.zc.javafxdemo;
}