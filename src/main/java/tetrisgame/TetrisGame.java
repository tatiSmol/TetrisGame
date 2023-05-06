package tetrisgame;

import javax.swing.*;
import java.awt.EventQueue;

public class TetrisGame {
    private static GameForm gameForm;
    private static StartupForm startupForm;
    private static LeaderboardForm leaderboardForm;

    public static void start() {
        gameForm.setVisible(true);
        gameForm.startGame();
    }

    public static void showStartup() {
        startupForm.setVisible(true);
    }

    public static void showLeaderboard() {
        leaderboardForm.setVisible(true);
    }

    public static void gameOver(int score) {
        String playerName = JOptionPane.showInputDialog("Game Over!\nPlease enter your name.");
        gameForm.setVisible(false);
        leaderboardForm.addPlayer(playerName, score);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            gameForm = new GameForm();
            startupForm = new StartupForm();
            leaderboardForm = new LeaderboardForm();

            startupForm.setVisible(true);
        });
    }
}
