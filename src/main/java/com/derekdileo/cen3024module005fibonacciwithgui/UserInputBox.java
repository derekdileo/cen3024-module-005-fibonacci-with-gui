package com.derekdileo.cen3024module005fibonacciwithgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 */
public class UserInputBox {

    static int intPosition;
    static String strPosition;
    public static Stage window;

    public static int display(String title, String message) {
        window = new Stage();

        // Block any input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        window.setTitle(title);

        // Ask user for fibonacci position
        Label label = new Label();
        label.setText(message);

        // TF for user input
        TextField textField = new TextField();

        Button okButton = new Button("Let's go!");

        okButton.setOnAction(e -> {
            // User input to String
            strPosition = null;
            strPosition = textField.getCharacters().toString();

            // Validate input
            if (strPosition != null) {
                intPosition = Integer.parseInt(strPosition);
                window.close();
            }
            else {
                AlertBox.display("Invalid Input!", "Please type an integer that is >= 0.");
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label, textField, okButton);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return intPosition;
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Exit?", "Are you sure you want to exit?");
        if(answer) {
            System.out.println("Window Closed!");
            window.close();
        }
    }

}




















