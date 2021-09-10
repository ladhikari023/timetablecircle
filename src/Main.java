/*
    Name: Laxman Adhikari
    CS 351
    Project 1
    In this project, I have made an animated circle whose working is taken from the instruction
    video. I have made a working formula to draw the points and connect it with the appropriate lines
    which would create a circle and in each cycle the points update and circle takes a different shape.
    When a complete loop ends, it again repeats the same cycle. I have generated random colors for the
    lines which would make circle different color in each loop. I have added controls like Run, Pause,
    Restart, and also we can jump to a specific timetable and specific points. Moreover, I have added
    two sliders where one is for increasing frames per second and other one is for increasing points in
    circle. I have added three favourite images which can be navigated by three buttons.
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import static java.lang.StrictMath.*;
import static java.lang.StrictMath.toRadians;

/* This is the main class of this program. This is the only class for project 1.*/
public class Main extends Application {
    int numOfPointsOnCircle = 360;
    int timeTable = 2;
    AnchorPane root = new AnchorPane();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(circleScene(), 900, 800);
        stage.setTitle("TimeTableCircle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    /* Created a Layout circleScene() which is added to the Scene.
    * Inside this method, I have also created VBox and HBox layouts to hold the buttons, texts and sliders
    * I have also added root layout in the return of this method. root method holds the circle display
    * I have called a method 'drawCircle' which has the logic of how circle is drawn
    * */
    public AnchorPane circleScene() {
        VBox vBox = new VBox(20);
        vBox.setLayoutX(10);
        vBox.setLayoutY(20);
        HBox row1 = new HBox(13);
        HBox row2 = new HBox(13);
        HBox row3 = new HBox(13);
        HBox row4 = new HBox(13);
        HBox row5 = new HBox(13);
        HBox row6 = new HBox(13);
        HBox row7 = new HBox(13);
        HBox row8 = new HBox(13);
        HBox row9 = new HBox(13);
        HBox row10 = new HBox(13);

        //Created Buttons for Controls
        Button runBtn = new Button("Run");
        Button pauseBtn = new Button("Pause");
        Button restartBtn = new Button("Restart");
        Button enterBtn = new Button("Enter");
        Button favImgBtn = new Button("Favourite Images");
        Button favImg1 = new Button("Favourite Image 1");
        Button favImg2 = new Button("Favourite Image 2");
        Button favImg3 = new Button("Favourite Image 3");

        favImg1.setVisible(false);
        favImg2.setVisible(false);
        favImg3.setVisible(false);

        //Created Labels to display text
        Label txt1 = new Label("Time Table Number:");
        Label txt2 = new Label("Number of Point:");
        Label txt3 = new Label("         Increase Time:");
        Label txt4 = new Label("        Increase Point:");

        //Created TextField to get input of time-table number and number of point
        TextField txtField1 = new TextField();
        TextField txtField2 = new TextField();

        //Created Slider to control time and points in circle
        Slider slider1 = new Slider(0, 100, 10);
        Slider slider2 = new Slider(0, 360, 8);

        //Added all buttons,text-fields,sliders and labels in HBox layout
        row1.getChildren().addAll(runBtn);
        row2.getChildren().addAll(pauseBtn, txt3, slider1);
        row3.getChildren().addAll(restartBtn, txt4, slider2);
        row4.getChildren().addAll(txt1, txtField1);
        row5.getChildren().addAll(txt2, txtField2);
        row6.getChildren().addAll(enterBtn);
        row7.getChildren().addAll(favImgBtn);
        row8.getChildren().addAll(favImg1);
        row9.getChildren().addAll(favImg2);
        row10.getChildren().addAll(favImg3);


        //Added all HBox layouts in VBox layout
        vBox.getChildren().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9, row10);
        root.setMouseTransparent(true);

        AnimationTimer timer = new AnimationTimer() {
            long initialTime = 0;
            @Override
            public void handle(long now) {
                if ((now - initialTime) + slider1.getValue() * 10_000_000 > 1_000_000_000) {
                    int val=8;
                    // Checking if we have any change in slider2 which controls number of points in circles
                    if(val!=(int)slider2.getValue()){
                        numOfPointsOnCircle=(int) slider2.getValue();
                    }
                    drawCircle();
                    initialTime = now;
                    timeTable++;
                }
            }
        };

        runBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.start();
            }
        });
        pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.stop();
            }
        });
        restartBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeTable = 2;
                numOfPointsOnCircle = 360;
                timer.start();
            }
        });
        enterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    timeTable = Integer.parseInt(txtField1.getText());
                    numOfPointsOnCircle = Integer.parseInt(txtField2.getText());
                    drawCircle();
                    timer.stop();
                }catch (Exception e){
                    System.out.println("Please enter the time table and points first!!");
                }
            }
        });
        favImgBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                favImg1.setVisible(true);
                favImg2.setVisible(true);
                favImg3.setVisible(true);
            }
        });
        favImg1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeTable = 15;
                numOfPointsOnCircle = 360;
                drawCircle();
                timer.stop();
            }
        });
        favImg2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeTable = 150;
                numOfPointsOnCircle = 360;
                drawCircle();
                timer.stop();
            }
        });
        favImg3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeTable = 240;
                numOfPointsOnCircle = 360;
                drawCircle();
                timer.stop();
            }
        });

        return new AnchorPane(vBox, root);
    }

    /*
     * In this method, I have written logic of circle.
     * Cleared the layout everytime animation starts
     * Generated random rgb values and assigned it to the stroke of line
     * Connected lines on two points obtained from the idea of video
     * Added line on root layout
     */
    private void drawCircle() {
        root.getChildren().clear();
        Color color = Color.color(Math.random(),Math.random(),Math.random());
        for (int i = 0; i < numOfPointsOnCircle; i++){
            int endPoints = (timeTable * i) % 360;
            Line line = new Line();
            line.setStartX(cos(toRadians(360 * i / numOfPointsOnCircle)) * 280 + 500f);
            line.setStartY(sin(toRadians(360 * i / numOfPointsOnCircle)) * 280 + 500f);
            line.setEndX(cos(toRadians(360 * endPoints / numOfPointsOnCircle)) * 280 + 500f);
            line.setEndY(sin(toRadians(360 * endPoints / numOfPointsOnCircle)) * 280 + 500f);
            line.setStroke(color);
            root.getChildren().addAll(line);
        }
    }
}


