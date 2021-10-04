package com.derekdileo.cen3024module005fibonacciwithgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/** Application to compute and display graph of time complexities of recursive and iterative fibonacci executions.
 * @author Derek DiLeo
  */
public class FibonacciApplication extends Application {

    /** Map<Integer, Long> to store positions and execution times (nanoSec) for each method call
     */
    public static Map<Integer, Long> recursiveTimes = new LinkedHashMap<>();
    public static Map<Integer, Long> iterativeTimes = new LinkedHashMap<>();

    /** main() calls launch() which calls start(Stage stage) to run program
     * @param args mandatory String[] array for console line arguments of data type String
     */
    public static void main(String[] args) {
        launch();
    }

    // Declare new stage (window) outside of start() method to make accessible to closeProgram() method
    Stage window;

    /** start() method will:
     * - launch UserInputBox to ask user for fibonacci position
     * - use returned integer to compute fibonacci number using FibonacciNumbers class methods
     * - store execution times of each step to LinkedHashMaps
     * - add all as (x,y) coordinates of JavaFX LineChart
     * - display the chart to the user to view differences in time complexity between iterative and recursive methods
     * @param stage
     * @throws IOException
     */

    @Override
    public void start(Stage stage) throws IOException {

        // Rename stage to window for simplicity
        window = stage;

        // Set stage title
        window.setTitle("Fibonacci Time Complexity");

        // Handle close button request. Launch ConfirmBox to confirm if user wishes to exit
        window.setOnCloseRequest(e -> {
            // Consume the event to allow ConfirmBox to do its job
            e.consume();
            closeProgram();
        });

        // Gather desired fibonacci position from user (with integer validation)
        final int position = UserInputBox.display("Fibonacci Number Calculator", "Which Fibonacci position would you like to calculate?");

        // Compute time results of fibRecursive(i) && fibIterative(i) for 0 <= i < position
        // Results are printed to console AND stored in LinkedHashMaps as Key/Value pairs of position / execution time
        // These data will be used to create and display a JavaFX line graph
        // which will visibly show differences in time complexity of both methods
        for (int i = 0; i < position; i++) {
            recursiveTimes.put(i, FibonacciNumbers.printRecursive(i));
            iterativeTimes.put(i, FibonacciNumbers.printIterative(i));
        }

        // Create and label x- and y-axis
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Fibonacci Position");
        yAxis.setLabel("Execution time in nanoSeconds");

        // Create LineChart from axes and set title
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Recursive vs. Iterative");

        // Create and name series for recursive and iterative coordinate inputs
        XYChart.Series recursiveSeries = new XYChart.Series();
        XYChart.Series iterativeSeries = new XYChart.Series();
        recursiveSeries.setName("Recursive Time Complexity");
        iterativeSeries.setName("Iterative Time Complexity");

        // Add all (x,y) coordinates (Key, Values) to each series from generated LinkedHashMaps in main() method
        for(int i = 0; i < recursiveTimes.size(); i++) {
            recursiveSeries.getData().add(new XYChart.Data(i, recursiveTimes.get(i)));
            iterativeSeries.getData().add(new XYChart.Data(i, iterativeTimes.get(i)));
        }
        // Create scene with lineChart, set stage with scene and show
        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().addAll(recursiveSeries, iterativeSeries);
        window.setScene(scene);

        window.show();
    }

    /** Method uses ConfirmBox class to confirm if user wants to quit
     */
    private void closeProgram() {
        // Ask if user wants to exit
        Boolean answer = ConfirmBox.display("", "Are you sure you want to quit?");
        if (answer) {
            // Run any necessary code before window closes:
            // Save / transfer files, etc...
            System.out.println("Window Closed!");
            window.close();
        }
    }

}