package ru.geekbrains.HomeWorkApp_lesson8_1;
//1. Добработать рисование крестиков и ноликов в игровом поле.
//        2* . Показать победителя.
//        3***. Нарисовать выиграшную линию
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class BattleMap extends JPanel {
    private GameWindow gameWindow;
    private int fieldSize;
    private int winLength;
    private boolean isInit;
    private int cellWidth;
    private int cellHeight;

    public BattleMap(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int cellX = e.getX() / BattleMap.this.cellWidth;
                int cellY = e.getY() / BattleMap.this.cellHeight;
                if (BattleMap.this.isInit && !Logic.isGameFinished) {
                    Logic.humanTurn(cellY, cellX);
                }

                BattleMap.this.repaint();
            }
        });
    }

    void startNewGame(int fieldSize, int winLength) {
        this.fieldSize = fieldSize;
        this.winLength = winLength;
        this.isInit = true;
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.isInit) {
            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();
            this.cellWidth = panelWidth / this.fieldSize;
            this.cellHeight = panelHeight / this.fieldSize;
            g.setColor(Color.BLACK);
            ((Graphics2D) g).setStroke(new BasicStroke(2.0F));


            for (int i = 1; i < this.fieldSize; ++i) {
                int y = i * this.cellHeight;
                g.drawLine(0, y, panelWidth, y);
            }

            for (int i = 1; i < this.fieldSize; ++i) {
                int x = i * this.cellWidth;
                g.drawLine(x, 0, x, panelHeight);
            }

            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    if (Logic.map[i][j] == Logic.DOT_X) {
                        drawX(g, j, i);
                    } else if (Logic.map[i][j] == Logic.DOT_O) {
                        drawO(g, j, i);
                    }
                }
            }
        }
    }

    private void drawX(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(3.0F));
        g.drawLine(x * this.cellWidth, y * this.cellHeight, (x + 1) * this.cellWidth, (y + 1) * this.cellHeight);
        g.drawLine((x + 1) * this.cellWidth, y * this.cellHeight, x * this.cellWidth, (y + 1) * this.cellHeight);
    }

    private void drawO(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        ((Graphics2D) g).setStroke(new BasicStroke(3.0F));
        g.drawOval(x * this.cellWidth, y * this.cellHeight, x + 1 * this.cellWidth, y + 1 * this.cellHeight);
    }
}

