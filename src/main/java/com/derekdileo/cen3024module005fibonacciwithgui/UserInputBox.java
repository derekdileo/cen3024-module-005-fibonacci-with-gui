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

        // Handle close button request. Launch ConfirmBox to confirm if user wishes to exit
        window.setOnCloseRequest(e -> {
            // Consume the event to allow ConfirmBox to do its job
            e.consume();
            closeProgram();
        });



        // Ask user for fibonacci position
        Label label = new Label();
        label.setText(message);

        // TF for user input
        TextField textField = new TextField();

        Button okButton = new Button("Let's go!");

//        // Allow enter key to be used for button select
//        okButton.setOnKeyPressed(e -> {
//
//            boolean isNumeric = false;
//
//            // User input to String
//            strPosition = textField.getCharacters().toString();
//
//            // Check each character of user input to validate if numeric
//            for (int i = 0; i < strPosition.length(); i++) {
//                isNumeric = false; // Safety to ensure inputs like 4d are not allowed
//                char c = strPosition.charAt(i);
//                if(isDigit(c)) {
//                    isNumeric = true;
//                }
//            }
//
//            // Validate input
//            if (isNumeric && e.getCode().equals((KeyCode.ENTER))) {
//                intPosition = Integer.parseInt(strPosition);
//                okButton.fire();
//                window.close();
//            } else {
//                AlertBox.display("Invalid Input!", "Please type an integer that is >= 0.");
//            }
//        });


        okButton.setOnAction(e -> {
            boolean isNumeric = false;

            // User input to String
            strPosition = textField.getCharacters().toString();

            // Check each character of user input to validate if numeric
            for (int i = 0; i < strPosition.length(); i++) {
                isNumeric = false; // Safety to ensure inputs like 4d are not allowed
                char c = strPosition.charAt(i);
                if(isDigit(c)) {
                    isNumeric = true;
                }
            }

            // Validate input
            if (isNumeric) {
                intPosition = Integer.parseInt(strPosition);
                window.close();
            } else {
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

    private static void closeProgram() {
        Boolean answer = ConfirmBox.display("Exit?", "Are you sure you want to exit?");
        if(answer) {
            System.out.println("Window Closed!");
            window.close();
            System.exit(0);
        }
    }

     private static boolean isDigit(char c) {
        if(c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0') {
            return true;
        }
        return false;
    }

}




















