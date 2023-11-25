package com.example.serpientesyescaleras.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public Button btnJugar;

    public void onJugar(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/serpientesyescaleras/game.fxml"));

            Parent root = loader.load();

            GameController gameController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> gameController.closeWindows());
            gameController.init(1);

            Stage myStage = (Stage) this.btnJugar.getScene().getWindow();
            myStage.close();

        } catch (IOException ex){

            System.out.println(ex.getMessage());

        }

    }

    public void closeWindows() {
    }
}