package com.example.serpientesyescaleras.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class VictoryController {
    public Button btnSalir;
    public Label lblPlayer;

    public void onSalir(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/serpientesyescaleras/Menu.fxml"));

            Parent root = loader.load();

            MenuController menuController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> menuController.closeWindows());

            Stage myStage = (Stage) this.btnSalir.getScene().getWindow();
            myStage.close();

        } catch (IOException ex){

            System.out.println(ex.getMessage());

        }
    }

    public void closeWindows() {
    }

    public void init(int playerNumber) {

        lblPlayer.setText(String.valueOf(playerNumber));
    }
}
