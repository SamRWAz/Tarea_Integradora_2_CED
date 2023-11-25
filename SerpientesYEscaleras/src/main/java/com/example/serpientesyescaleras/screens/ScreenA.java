package com.example.serpientesyescaleras.screens;

import com.example.serpientesyescaleras.control.GameController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import com.example.serpientesyescaleras.model.Player;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ScreenA {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Player> players;
    private Image groundTexture;
    private Image playerTexture;
    private Image playerTexture2;
    private GameController gameController;
    private boolean isPlayer1Turn = true;
    private int[][] matrizTablero;

    public ScreenA(Canvas canvas, GameController gameController){
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.players = new ArrayList<>();

        for (int i = 0; i < 2; i++){
           Player player = new Player(this.canvas, gameController, i + 1, 22.5, 438.5);
           players.add(player);

        }

        this.gameController = gameController;

        playerTexture = new Image(getClass().getResourceAsStream("/player/Player1.png"));
        playerTexture2 = new Image(getClass().getResourceAsStream("/player/Player2.png"));
        groundTexture = new Image(getClass().getResourceAsStream("/floor/Tablero.png"));

        players.get(0).setPlayerTexture(playerTexture);
        players.get(1).setPlayerTexture(playerTexture2);

        initializeGrafo();
        
    }

    private void initializeGrafo() {
        int vertices = 50;
        matrizTablero = new int[vertices + 1][vertices + 1];


        for (int i = 1; i <= vertices; i++) {
            for (int j = 1; j <= vertices; j++) {
                matrizTablero[i][j] = 0;
            }
        }

        connectSnakesAndLadders();
    }

    private void connectSnakesAndLadders() {

        connectVertices(11, 29);
        connectVertices(16, 37);
        connectVertices(33, 49);

        connectVertices(43, 22);
        connectVertices(34, 12);
        connectVertices(23, 4);
    }

    private void connectVertices(int from, int to) {
        matrizTablero[from][to] = 1;
    }

    public void paint() {

        graphicsContext.setFill(Color.rgb(0, 0, 0, 0));
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.drawImage(groundTexture, 0, 0, canvas.getWidth(), canvas.getHeight());

        for(Player player: players){
            for (int i = 0; i < 2; i++){
                if(i == 0){
                    player.paint(graphicsContext);
                }else{
                    player.paint(graphicsContext);
                }
            }

        }

    }

    public void move(int diceResult) {
        Player currentPlayer = isPlayer1Turn ? players.get(0) : players.get(1);
        currentPlayer.newPosition(diceResult, matrizTablero);

        if (currentPlayer.getCurrentPosition() >= 50) {
            gameController.victoria(currentPlayer.getPlayerNumber());
        } else {
            isPlayer1Turn = !isPlayer1Turn;
        }
    }



}
