import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameForm extends JFrame {
    private JPanel gameAreaPlaceholder;
    private GameArea gameArea;

    public GameForm() {
        initComponents();
        initControls();
        gameArea = new GameArea(gameAreaPlaceholder, 10);
        this.add(gameArea);
        startGame();
    }

    public void startGame() {
        new GameThread(gameArea).start();
    }

    private void initComponents() {
        gameAreaPlaceholder = new JPanel();
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();

        setLocationRelativeTo(null);
    }

    private void initControls() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockRight();
            }
        });

        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockLeft();
            }
        });

        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotateBlock();
            }
        });

        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.dropBlock();
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameForm().setVisible(true));
    }
}
