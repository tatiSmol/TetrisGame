package tetrisgame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaderboardForm extends JFrame {
    private final String LBFileName = "leaderboard";
    private JButton menuBtn;
    private JScrollPane jScrollPane1;
    private JTable leaderboard;
    private DefaultTableModel defaultTableModel;
    private TableRowSorter<TableModel> sorter;

    public LeaderboardForm() {
        initComponents();
        initTableData();
        initTableSorter();
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
            Class[] types = new Class[] {Object.class, Integer.class};
            boolean[] canEdit = new boolean[] {false, false};

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

    @SuppressWarnings("unchecked")
    private void initTableData() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Player");
        columnNames.add("Score");

        defaultTableModel = (DefaultTableModel) leaderboard.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(columnNames);

        File file = new File("src/main/resources/" + LBFileName);
        if (file.length() > 0) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                Vector<Vector<Object>> dataVector = (Vector<Vector<Object>>) objectInputStream.readObject();
                for (Vector<Object> row : dataVector) {
                    defaultTableModel.addRow(row);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void initTableSorter() {
        sorter = new TableRowSorter<>(leaderboard.getModel());
        sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(1, SortOrder.DESCENDING)));
        leaderboard.setRowSorter(sorter);
    }

    private void saveLeaderboard() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("src/main/resources/" + LBFileName))) {
            objectOutputStream.writeObject(defaultTableModel.getDataVector());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void menuBtnActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        TetrisGame.showStartup();
    }

    public void addPlayer(String name, int score) {
        defaultTableModel.addRow(new Object[] {name, score});
        sorter.sort();
        saveLeaderboard();
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
