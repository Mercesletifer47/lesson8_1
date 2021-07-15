package ru.geekbrains.HomeWorkApp_lesson8_1;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
    private BattleMap battleMap;
    private Setting setting;

    public GameWindow() {
        this.setDefaultCloseOperation(3);
        this.setBounds(300, 300, 500, 500);
        this.setTitle("TicTacToe");
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton button = new JButton("Start new game");
        panel.add(button);
        JButton buttonExit = new JButton("Exit");
        panel.add(buttonExit);
        this.add(panel, "South");
        this.battleMap = new BattleMap(this);
        this.add(this.battleMap, "Center");
        this.setting = new Setting(this);
        button.addActionListener((e) -> {
            this.setting.setVisible(true);
        });
        buttonExit.addActionListener((e) -> {
            System.exit(0);
        });
        this.setVisible(true);
    }

    void startNewGame(int fieldSize, int winLength) {
        this.battleMap.startNewGame(fieldSize, winLength);
    }
}