package tetrisgame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaderboardForm extends JFrame {
    private JButton menuBtn;
    private JScrollPane jScrollPane1;
    private JTable leaderboard;
    private DefaultTableModel defaultTableModel;

    public LeaderboardForm() {
        initComponents();
        initTableData();
    }

    private void initComponents() {
        menuBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        leaderboard = new JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        menuBtn.setText("Main Menu");
        menuBtn.setPreferredSize(new Dimension(123, 23));
        menuBtn.addActionListener(this::menuBtnActionPerformed);

        leaderboard.setModel(
                new DefaultTableModel(new Object[][] {},
                        new String[] {"Player", "Score"}) {
            boolean[] canEdit = new boolean[] {false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jScrollPane1.setViewportView(leaderboard);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                        .addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 375,
                                                GroupLayout.PREFERRED_SIZE))
                                .addComponent(menuBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
                        .addContainerGap().addComponent(menuBtn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);

    }

    private void initTableData() {
        defaultTableModel = (DefaultTableModel) leaderboard.getModel();
    }

    private void menuBtnActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        TetrisGame.showStartup();
    }

    public void addPlayer(String name, int score) {
        defaultTableModel.addRow(new Object[] {name, score});
        this.setVisible(true);
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
            Logger.getLogger(LeaderboardForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new LeaderboardForm().setVisible(true));
    }

}
