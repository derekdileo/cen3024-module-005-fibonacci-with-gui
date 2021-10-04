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

/** Application to compute and display graph of time complexities of recursive and iterative fibonacci computations.
 * @author Derek DiLeo
  */
public class FibonacciApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Set stage title
        stage.setTitle("Fibonacci Time Complexity"); // set stage title

        // Create and label x- and y-axis
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Fibonacci Position");
        yAxis.setLabel("Execution time in nanoSeconds");

        // Create LineChart from axes and set title
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Recursive vs. Iterative");

        // Create and name series for recursive and iterative coordinate inputs
        XYChart.Series recursiveSeries = new XYChart.Series();
        XYChart.Series iterativeSeries = new XYChart.Series();
        recursiveSeries.setName("Recursive Time Complexity");
        iterativeSeries.setName("Iterative Time Complexity");

        // Add all (x,y) coordinates (Key, Values) to each series from generated LinkedHashMaps in main() method
        int size = recursiveTimes.size();
        for(int i = 0; i < size; i++) {
            recursiveSeries.getData().add(new XYChart.Data(i, recursiveTimes.get(i)));
            iterativeSeries.getData().add(new XYChart.Data(i, iterativeTimes.get(i)));
        }
        // Create scene with lineChart, set stage with scene and show
        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().addAll(recursiveSeries, iterativeSeries);
        stage.setScene(scene);
        stage.show();
    }

    /** Map<Integer, Long> to store positions and execution times (nanoSec) for each method call
     */
    public static Map<Integer, Long> recursiveTimes = new LinkedHashMap<>();
    public static Map<Integer, Long> iterativeTimes = new LinkedHashMap<>();

    /** main() will
     * - compute fibonacci number using FibonacciNumbers class methods
     * - store execution times of each step to LinkedHashMaps
     * - add all as (x,y) coordinates of JavaFX LineChart
     * - display the chart to the user to view differences in time complexity between iterative and recursive methods
     * @param args mandatory String[] array for console line arguments of data type String
     * @author Derek DiLeo
     */
    public static void main(String[] args) {

        // Desired fibonacci number to be calculated (starting from 0)
        int position = 11;

        // Compute time results of fibRecursive(i) && fibIterative(i) for 0 <= i < position
        // Results are printed to console AND stored in LinkedHashMaps as Key/Value pairs of position / execution time
        // These data will be used to create and display a JavaFX line graph
        // which will visibly show differences in time complexity of both methods
        for (int i = 0; i < position; i++) {
            recursiveTimes.put(i, FibonacciNumbers.printRecursive(i));
            iterativeTimes.put(i, FibonacciNumbers.printIterative(i));
        }

        // Display line graph of results
        launch();
    }
}