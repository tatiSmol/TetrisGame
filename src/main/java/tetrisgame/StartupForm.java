package tetrisgame;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartupForm extends JFrame {
    private JButton startBtn;
    private JButton leaderBtn;
    private JButton quitBtn;

    public StartupForm() {
        initComponents();
    }

    private void initComponents() {
        startBtn = new JButton();
        leaderBtn = new JButton();
        quitBtn = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        startBtn.setText("Start Game");
        startBtn.addActionListener(this::startBtnActionPerformed);
        leaderBtn.setText("Leaderboard");
        leaderBtn.addActionListener(this::leaderBtnActionPerformed);
        quitBtn.setText("Quit");
        quitBtn.addActionListener(this::quitBtnActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(99, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(startBtn, GroupLayout.PREFERRED_SIZE, 218,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(quitBtn, GroupLayout.PREFERRED_SIZE, 218,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(leaderBtn, GroupLayout.PREFERRED_SIZE, 218,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(140, Short.MAX_VALUE)
                                .addComponent(startBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leaderBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quitBtn)
                                .addGap(79, 79, 79))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void startBtnActionPerformed(ActionEvent ev) {
        this.setVisible(false);
        TetrisGame.start();
    }

    private void leaderBtnActionPerformed(ActionEvent ev) {
        this.setVisible(false);
        TetrisGame.showLeaderboard();
    }

    private void quitBtnActionPerformed(ActionEvent ev) {
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StartupForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new StartupForm().setVisible(true));
    }
}
