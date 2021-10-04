package com.derekdileo.cen3024module005fibonacciwithgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();

        stackPane2.setId("confirm-box-pane");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));

        // Lock out primary window until alert is cleared
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        // Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        stackPane1.getChildren().addAll(stackPane2, layout);

        Scene scene = new Scene(stackPane1);
        window.setScene(scene);

        window.showAndWait();
        return answer;
    }
}
