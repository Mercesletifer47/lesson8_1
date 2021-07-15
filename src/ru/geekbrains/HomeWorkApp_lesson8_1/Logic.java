package ru.geekbrains.HomeWorkApp_lesson8_1;
//1. Добработать рисование крестиков и ноликов в игровом поле.
//        2* . Показать победителя.
//        3***. Нарисовать выиграшную линию
import javax.swing.*;
import java.util.Random;

public class Logic {
    static int SIZE = 3;
    static int DOTS_TO_WIN = 3;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';
    static char[][] map;
    static final Random random = new Random();
    static boolean isGameFinished;

    public Logic() {
    }

    public static void go() {
        isGameFinished = true;
        printMap();
        if (checkWinLines('X', DOTS_TO_WIN)) {
            System.out.println("Победил человек");
            JOptionPane.showMessageDialog(new JFrame(), "Победил человек");
        } else if (isMapFull()) {
            System.out.println("Ничья");
            JOptionPane.showMessageDialog(new JFrame(), "Ничья");
        } else {
            aiTurn();
            printMap();
            if (checkWinLines('O', DOTS_TO_WIN)) {
                System.out.println("Победил Искуственный Интеллект");
                JOptionPane.showMessageDialog(new JFrame(), "Победил Искуственный Интеллект");
            } else if (isMapFull()) {
                System.out.println("Ничья");
                JOptionPane.showMessageDialog(new JFrame(), "Ничья");
            } else {
                isGameFinished = false;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                map[i][j] = '.';
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        int i;
        for (i = 0; i < SIZE; ++i) {
            System.out.print(i + 1 + " ");
        }

        System.out.println();

        for (i = 0; i < SIZE; ++i) {
            System.out.print(i + 1 + " ");

            for (int j = 0; j < SIZE; ++j) {
                System.out.printf("%c ", map[i][j]);
            }

            System.out.println();
        }
    }

    public static void humanTurn(int y, int x) {
        if (isCellValid(y, x)) {
            map[y][x] = 'X';
            go();
        }
    }

    public static void aiTurn() {
        int i;
        int j;
        for (i = 0; i < SIZE; ++i) {
            for (j = 0; j < SIZE; ++j) {
                if (isCellValid(i, j)) {
                    map[i][j] = 'O';
                    if (checkWinLines('O', DOTS_TO_WIN)) {
                        return;
                    }

                    map[i][j] = '.';
                }
            }
        }

        for (i = 0; i < SIZE; ++i) {
            for (j = 0; j < SIZE; ++j) {
                if (isCellValid(i, j)) {
                    map[i][j] = 'X';
                    if (checkWinLines('X', DOTS_TO_WIN)) {
                        map[i][j] = 'O';
                        return;
                    }
                    map[i][j] = '.';
                }
            }
        }

        for (i = 0; i < SIZE; ++i) {
            for (j = 0; j < SIZE; ++j) {
                if (isCellValid(i, j)) {
                    map[i][j] = 'O';
                    if (checkWinLines('O', DOTS_TO_WIN - 1) && Math.random() < 0.5D) {
                        return;
                    }
                    map[i][j] = '.';
                }
            }
        }

        for (i = 0; i < SIZE; ++i) {
            for (j = 0; j < SIZE; ++j) {
                if (isCellValid(i, j)) {
                    map[i][j] = 'X';
                    if (checkWinLines('X', DOTS_TO_WIN - 1) && Math.random() < 0.5D) {
                        map[i][j] = 'O';
                        return;
                    }
                    map[i][j] = '.';
                }
            }
        }
        int x;
        int y;
        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = 'O';
    }

    public static boolean isCellValid(int y, int x) {
        if (x >= 0 && y >= 0 && x < SIZE && y < SIZE) {
            return map[y][x] == '.';
        } else {
            return false;
        }
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (map[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkLine(int cy, int cx, int vy, int vx, char dot, int dotsToWin) {
        if (cx + vx * (dotsToWin - 1) <= SIZE - 1 && cy + vy * (dotsToWin - 1) <= SIZE - 1 && cy + vy * (dotsToWin - 1) >= 0) {
            for (int i = 0; i < dotsToWin; ++i) {
                if (map[cy + i * vy][cx + i * vx] != dot) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    static boolean checkWinLines(char dot, int dotsToWin) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (checkLine(i, j, 0, 1, dot, dotsToWin) || checkLine(i, j, 1, 0, dot, dotsToWin) || checkLine(i, j, 1, 1, dot, dotsToWin) || checkLine(i, j, -1, 1, dot, dotsToWin)) {
                    return true;
                }
            }
        }
        return false;

    }
}
