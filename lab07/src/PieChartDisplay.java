import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.util.HashMap;

import java.util.Map;

import java.util.Scanner;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.geometry.Side;

import javafx.scene.Scene;

import javafx.scene.chart.PieChart;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;



public class PieChartDisplay extends Application {



    //map to store each item and its count



    private Map<String, Integer> map;



    //method to read the file and fill the map

    //make sure you have the file in the root directory of your project



    private void loadData(String filename) {

        try {

            //opening file

            Scanner scanner = new Scanner(new FileReader(new File(filename)));

            //looping through each line

            while (scanner.hasNext()) {

                //getting line, splitting line with comma

                String line = scanner.nextLine();

                String columns[] = line.split(",");

                //making sure the resultant array has atleast 6 length

                if (columns.length >= 6) {

                    //getting 6th column (index 5)

                    String field = columns[5].trim();

                    //checking if map contains the field

                    if (map.containsKey(field)) {

                        //getting current count

                        int count = map.get(field);

                        //adding one to count

                        count += 1;

                        //replacing old entry

                        map.put(field, count);

                    } else {

                        //adding as new value with count 1

                        map.put(field, 1);

                    }

                }

            }

        } catch (FileNotFoundException ex) {

            System.out.println(ex);

        }



    }



    //method to create an ObservableList of Pie chart data from the map to fill

    //the pie chart

    ObservableList<PieChart.Data> createChartData() {

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        //using each key-value pair, creating a PieChart data and adding to data list

        for (String key : map.keySet()) {

            data.add(new PieChart.Data(key, map.get(key)));

        }

        return data;

    }



    @Override

    public void start(Stage primaryStage) {

        //initializing map

        map = new HashMap<>();

        //reading data from file. make sure you have the file in root directory

        //of your project, if not detecting, please paste complete file path

        loadData("weatherwarnings-2015.csv");

        //creating a PieChart object using data from the map

        PieChart chart = new PieChart(createChartData());

        //aligning legend fields to left

        chart.setLegendSide(Side.LEFT);

        //creating a pane, adding chart and displaying it

        Pane root = new Pane();

        root.getChildren().add(chart);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Lab 07");

        primaryStage.show();

    }



    public static void main(String[] args) {

        launch(args);

    }



}