/*
    Name: Laxman Adhikari
    CS 351
    Project 1
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
import javafx.stage.Stage;


public class Main extends Application {
    int numOfPointsOnCircle = 360;
    int timeTable = 2;
    AnchorPane root = new AnchorPane();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(InitialScene(), 800, 800);
        stage.setTitle("Project 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public AnchorPane InitialScene() {
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
        Button startBtn = new Button("Run");
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
        Slider slider2 = new Slider(0, 360, 5);

        //Added all buttons,text-fields,sliders and labels in HBox layout
        row1.getChildren().addAll(startBtn);
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
                    if(val!=(int)slider2.getValue()){
                        numOfPointsOnCircle=(int) slider2.getValue();
                    }
                    drawCircle();
                    initialTime = now;
                    timeTable++;
                }
            }
        };

        favImgBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                favImg1.setVisible(true);
                favImg2.setVisible(true);
                favImg3.setVisible(true);
            }
        });

        return new AnchorPane(vBox, root);
    }

    private void drawCircle() {

    }
}


