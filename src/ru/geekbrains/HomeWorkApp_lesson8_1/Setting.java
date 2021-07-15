package ru.geekbrains.HomeWorkApp_lesson8_1;
//1. Добработать рисование крестиков и ноликов в игровом поле.
//        2* . Показать победителя.
//        3***. Нарисовать выиграшную линию
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class Setting extends JFrame {
    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;
    private GameWindow gameWindow;
    private JSlider slFieldSize;
    private JSlider slDotsToWin;

    public Setting(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.setBounds(550, 550, 450, 450);
        this.setTitle("TicTacToe setting");
        this.slFieldSize = new JSlider(3, 10, 3);
        this.slFieldSize.setMajorTickSpacing(1);
        this.slFieldSize.setPaintTicks(true);
        this.slFieldSize.setPaintLabels(true);
        this.slDotsToWin = new JSlider(3, 3, 3);
        this.slDotsToWin.setMajorTickSpacing(1);
        this.slDotsToWin.setPaintTicks(true);
        this.slDotsToWin.setPaintLabels(true);
        this.slFieldSize.addChangeListener((e) -> {
            this.slDotsToWin.setMaximum(this.slFieldSize.getValue());
        });
        this.setLayout(new GridLayout(5, 1));
        this.add(new Label("Field size:"));
        this.add(this.slFieldSize);
        this.add(new Label("Winning line:"));
        this.add(this.slDotsToWin);
        JButton button = new JButton("Start a game");
        this.add(button);
        button.addActionListener((e) -> {
            int fieldSize = this.slFieldSize.getValue();
            int winLength = this.slDotsToWin.getValue();
            Logic.SIZE = fieldSize;
            Logic.DOTS_TO_WIN = winLength;
            Logic.initMap();
            Logic.printMap();
            Logic.isGameFinished = false;
            gameWindow.startNewGame(fieldSize, winLength);
            this.setVisible(false);
        });
        this.setVisible(false);
    }
}
