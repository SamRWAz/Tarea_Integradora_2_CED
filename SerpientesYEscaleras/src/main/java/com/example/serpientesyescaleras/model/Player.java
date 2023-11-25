package com.example.serpientesyescaleras.model;

import com.example.serpientesyescaleras.control.GameController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Position position;
    private GameController gameController;
    private int playerNumber;
    private Image playerTexture;

    public Player(Canvas canvas, GameController gameController, int playerNumber, double x, double y){
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.playerNumber = playerNumber;
        this.gameController = gameController;
        this.playerTexture = playerTexture;

        this.position = new Position(x, y);
    }

    public void paint(GraphicsContext graphicsContext) {
        double playerWidth = 50;
        double playerHeight = 50;

        graphicsContext.drawImage(playerTexture, position.getX(), position.getY(), playerWidth, playerHeight);
    }

    public void newPosition(int diceResult, int[][] adjacencyMatrix) {
        int currentPosition = calculateCurrentPosition();
        int newPosition = currentPosition + diceResult;

        if (newPosition <= 50) {
            position.setX(calculateX(newPosition));
            position.setY(calculateY(newPosition));
        }

        if (isPlayerVictorious()) {
            gameController.victoria(playerNumber);
        }
    }

    public int getCurrentPosition() {
        return calculateCurrentPosition();
    }

    private int calculateCurrentPosition() {

        int row = (int) ((canvas.getHeight() - position.getY() - 52.0) / 52.0) + 1; // Ajuste según el tamaño del tablero
        int column;

        if (row % 2 == 0) {

            column = (int) (position.getX() / 90.0) + 1;
        } else {

            column = 10 - (int) (position.getX() / 90.0);
        }

        return (row - 1) * 10 + column;
    }

    private double calculateX(int vertex) {

        int row = (vertex - 1) / 10 + 1;

        double x;
        if (row % 2 == 0) {
            x = (vertex - 1) % 10 * 90.0;
        } else {

            x = (10 - (vertex - 1) % 10 - 1) * 90.0;
        }

        return x;
    }

    private double calculateY(int vertex) {
        int row = (vertex - 1) / 10 + 1;
        return (row - 1) * 52.0;
    }

    private boolean isPlayerVictorious() {
        return calculateCurrentPosition() >= 50;
    }


    public void setPlayerTexture(Image playerTexture) {
        this.playerTexture = playerTexture;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Image getPlayerTexture() {
        return playerTexture;
    }
}
