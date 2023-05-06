package tetrisgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameForm extends JFrame {
    private JPanel gameAreaPlaceholder;
    private JLabel levelDisplay;
    private JLabel scoreDisplay;
    private JButton menuBtn;
    private JButton pauseBtn;
    private GameArea gameArea;
    private GameThread gameThread;
    private boolean paused = false;

    public GameForm() {
        initComponents();
        initControls();
        gameArea = new GameArea(gameAreaPlaceholder, 10);
        this.add(gameArea);
    }

    public void startGame() {
        gameArea.initBackgroundArray();
        gameThread = new GameThread(gameArea, this, false);
        gameThread.start();
    }

    private void initComponents() {
        gameAreaPlaceholder = new JPanel();
        scoreDisplay = new JLabel();
        levelDisplay = new JLabel();
        menuBtn = new JButton();
        pauseBtn = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        gameAreaPlaceholder.setBackground(new Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        gameAreaPlaceholder.setPreferredSize(new Dimension(200, 300));

        GroupLayout gameAreaPlaceholderLayout = new GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
                gameAreaPlaceholderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
                gameAreaPlaceholderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 298, Short.MAX_VALUE)
        );

        scoreDisplay.setFont(new Font("Segoe UI", 0, 18));
        scoreDisplay.setText("Score: 0");

        levelDisplay.setFont(new Font("Segoe UI", 0, 18));
        levelDisplay.setText("Level: 1");

        menuBtn.setText("Main Menu");
        menuBtn.setFocusable(false);
        menuBtn.setPreferredSize(new Dimension(123, 23));
        menuBtn.addActionListener(this::menuBtnActionPerformed);

        pauseBtn.setText("PAUSE");
        pauseBtn.addActionListener(this::pauseBtnActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                        .addComponent(menuBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup().addGap(31, 31, 31)
                                        .addComponent(pauseBtn)))
                        .addGap(34, 34, 34)
                        .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(levelDisplay, GroupLayout.PREFERRED_SIZE, 90,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(scoreDisplay, GroupLayout.PREFERRED_SIZE, 90,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(menuBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
                                        .addComponent(pauseBtn, GroupLayout.PREFERRED_SIZE, 31,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(scoreDisplay)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(levelDisplay))
                                .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void menuBtnActionPerformed(ActionEvent evt) {
        gameThread.interrupt();
        this.setVisible(false);
        TetrisGame.showStartup();
    }

    private void pauseBtnActionPerformed(ActionEvent evt) {
        Action pauseAction = getRootPane().getActionMap().get("pause");
        pauseAction.actionPerformed(new ActionEvent(evt.getSource(), ActionEvent.ACTION_PERFORMED, null));
    }

    private void initControls() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");

        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    gameArea.moveBlockRight();
                }
            }
        });

        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    gameArea.moveBlockLeft();
                }
            }
        });

        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    gameArea.rotateBlock();
                }
            }
        });

        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    gameArea.dropBlock();
                }
            }
        });

        actionMap.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paused = !paused;
                if (paused) {
                    gameThread.pauseGame();
                    pauseBtn.setText("RESUME");
                } else {
                    gameThread.resumeGame();
                    pauseBtn.setText("PAUSE");
                }
            }
        });
    }

    public void updateScore(int score) {
        scoreDisplay.setText("Score: " + score);
    }

    public void updateLevel(int level) {
        levelDisplay.setText("Level: " + level);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameForm().setVisible(true));
    }
}
