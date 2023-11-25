package com.example.serpientesyescaleras.control;

import com.example.serpientesyescaleras.screens.ScreenA;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    public Label lblPlayer;
    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ScreenA screenA;
    public Button btnLanzarD;
    private int state;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.screenA = new ScreenA(this.canvas, this);


        new Thread(
                () -> {
                    while (true){
                        Platform.runLater(() -> {
                            screenA.paint();
                        });

                        try{
                            Thread.sleep(50);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

    }


    public void closeWindows() {
    }

    public void onLanzarD(ActionEvent actionEvent) {

        int diceResult = (int) (Math.random() * 6) + 1;
        screenA.move(diceResult);

        if (state == 1) {
            state = 2;
        } else {
            state = 1;
        }

        updatePlayer(state);

    }

    public void victoria(int playerNumber){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/serpientesyescaleras/victory.fxml"));

            Parent root = loader.load();

            VictoryController victoryController = loader.getController();

            Stage currentStage = (Stage) lblPlayer.getScene().getWindow();
            currentStage.close();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e ->
                    victoryController.closeWindows()

            );
            victoryController.init(playerNumber);


        }catch (IOException ex){

            System.out.println(ex.getMessage());

        }

    }

    public void init(int jugador) {

        lblPlayer.setText(jugador + "");
        state = 1;

    }

    public void updatePlayer(int jugadorTurn){
        Platform.runLater(() -> {
            lblPlayer.setText(String.valueOf(jugadorTurn));
        });
    }



}
