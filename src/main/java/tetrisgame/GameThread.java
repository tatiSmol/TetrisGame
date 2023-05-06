package tetrisgame;

public class GameThread extends Thread{
    private GameArea gameArea;
    private GameForm gameForm;
    private boolean paused;
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;
    private int pause = 1000;
    private int speedupPerLevel = 100;

    public GameThread(GameArea gameArea, GameForm gameForm, boolean paused) {
        this.gameArea = gameArea;
        this.gameForm = gameForm;
        this.paused = paused;

        gameForm.updateScore(score);
        gameForm.updateLevel(level);
    }

    @Override
    public void run() {
        while (true) {
            gameArea.spawnBlock();

            while (gameArea.moveBlockDown()) {
                try {
                    synchronized (this) {
                        while (paused) {
                            wait();
                        }
                    }

                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    return;
                }
            }

            if (gameArea.isBlockOutOfBounds()) {
                TetrisGame.gameOver(score);
                break;
            }

            gameArea.moveBlockToBackground();

            synchronized (this) {
                while (paused) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }

            score += gameArea.clearLines();
            gameForm.updateScore(score);

            int lvl = score / scorePerLevel + 1;
            if (lvl > level) {
                level = lvl;
                scorePerLevel++;
                gameForm.updateLevel(level);
                pause -= speedupPerLevel;
            }
        }
    }

    public void pauseGame() {
        paused = true;
    }

    public synchronized void resumeGame() {
        paused = false;
        notifyAll();
    }
}
