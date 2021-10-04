package com.derekdileo.cen3024module005fibonacciwithgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static Stage window;

    public static void display(String title, String message) {
        window = new Stage();

        // Block any input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(100);

        // Ask user for fibonacci position
        Label label = new Label();
        label.setText(message);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label, okButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

}
