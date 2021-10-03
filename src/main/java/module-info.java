module com.derekdileo.cen3024module005fibonacciwithgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.derekdileo.cen3024module005fibonacciwithgui to javafx.fxml;
    exports com.derekdileo.cen3024module005fibonacciwithgui;
}