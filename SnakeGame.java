/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SnakeGame extends JPanel implements ActionListener {
    static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int SNAKE_SIZE = 10;
    private static final int ALL_DOTS = 1000;
    private static final int RAND_POS = 29;
    private static final int DELAY = 140;

    private final int xLeftPlayer[] = new int[ALL_DOTS];
    private final int yLeftPlayer[] = new int[ALL_DOTS];
    private final int xRightPlayer[] = new int[ALL_DOTS];
    private final int yRightPlayer[] = new int[ALL_DOTS];

    private int leftSnakeLength;
    private int rightSnakeLength;

    private int foodX;
    private int foodY;

    private final String player1Name;
    private final String player2Name;
    
    private boolean leftPlayerUp, leftPlayerDown, leftPlayerLeft, leftPlayerRight;
    private boolean rightPlayerUp, rightPlayerDown, rightPlayerLeft, rightPlayerRight;

    private boolean inGame = true;

    private Timer timer;
    private long startTime;
    private int headHits, bodyHits, tailHits;
    
    
    private int leftPlayerScore = 0;
    private int rightPlayerScore = 0;
    private int level = 1;
    private int leftHitsRight = 0;
    private int rightHitsLeft = 0;
    private int highScore = 0;
    private long pauseStartTime = 0;
    
    private JPanel infoPanel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel levelLabel;
    private JLabel timeLabel;
    private JLabel highScoreLabel;

    public SnakeGame(String player1Name,String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name; 
        initBoard();
        setupInfoPanel();
        
    }
    private void initBoard() {
        
        setLayout(null);
        
        addKeyListener(new TAdapter());
        setBackground(Color.darkGray);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initGame();
    }

    private void setupInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setBounds(0, 0, WIDTH, 50); // Đặt kích thước và vị trí của infoPanel
        infoPanel.setBackground(Color.white);
        infoPanel.setLayout(new GridLayout()); 
        
         // Thêm border và padding cho các label để tránh mất chữ
        player1Label = new JLabel("Player 1: " + player1Name + " - Score: " + leftPlayerScore);
        player1Label.setBorder(new LineBorder(Color.black));
        player1Label.setHorizontalAlignment(SwingConstants.CENTER);

        player2Label = new JLabel("Player 2: " + player2Name + " - Score: " + rightPlayerScore);
        player2Label.setBorder(new LineBorder(Color.black));
        player2Label.setHorizontalAlignment(SwingConstants.CENTER);

        levelLabel = new JLabel("Level: " + level);
        levelLabel.setBorder(new LineBorder(Color.black));
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timeLabel = new JLabel("Time: 0s");
        timeLabel.setBorder(new LineBorder(Color.black));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        highScoreLabel = new JLabel("High Score: " + highScore);
        highScoreLabel.setBorder(new LineBorder(Color.black));
        highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        infoPanel.add(player1Label);
        infoPanel.add(player2Label);
        infoPanel.add(levelLabel);
        infoPanel.add(timeLabel);
        infoPanel.add(highScoreLabel);
        
        add(infoPanel); // Thêm infoPanel vào JPanel chính (SnakeGame)
    }
     private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setOpaque(true);
        label.setBackground(Color.lightGray);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        return label;
    }

    private void updateInfoPanel() {
        player1Label.setText("Player 1: " + player1Name + " - Score: " + leftPlayerScore);
        player2Label.setText("Player 2: " + player2Name + " - Score: " + rightPlayerScore);
        levelLabel.setText("Level: " + level);

        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        timeLabel.setText("Time: " + elapsedTime + "s");
        highScoreLabel.setText("High Score: " + highScore);
    }
    private void initGame() {
        leftSnakeLength = 3;
        rightSnakeLength = 3;

        for (int i = 0; i < leftSnakeLength; i++) {
            xLeftPlayer[i] = 50 - i * SNAKE_SIZE;
            yLeftPlayer[i] = 50;
        }

        for (int i = 0; i < rightSnakeLength; i++) {
            xRightPlayer[i] = 100 - i * SNAKE_SIZE;
            yRightPlayer[i] = 100;
        }

        locateFood();

        leftPlayerUp = false;
        leftPlayerDown = false;
        leftPlayerLeft = false;
        leftPlayerRight = true;
        rightPlayerUp = false;
        rightPlayerDown = false;
        rightPlayerLeft = false;
        rightPlayerRight = true;

        timer = new Timer(DELAY, this);
        timer.start();
        startTime = System.currentTimeMillis();
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        updateInfoPanel();
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            drawFood(g);
            drawSnakes(g);
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(foodX, foodY, SNAKE_SIZE, SNAKE_SIZE);
    }

    private void drawSnakes(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < leftSnakeLength; i++) {
            g.fillRect(xLeftPlayer[i], yLeftPlayer[i], SNAKE_SIZE, SNAKE_SIZE);
        }

        g.setColor(Color.blue);
        for (int i = 0; i < rightSnakeLength; i++) {
            g.fillRect(xRightPlayer[i], yRightPlayer[i], SNAKE_SIZE, SNAKE_SIZE);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
    }
//    private void paintInfo(Graphics g){
//        g.setColor(Color.white);
//        g.drawString("Player 1: " + player1Name + " - Score: " + leftPlayerScore, 10, 20);
//        g.drawString("Player 2: " + player2Name + " - Score: " + rightPlayerScore, 200, 20);
//        g.drawString("Level: " + level, 400, 20);
//
//        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
//        g.drawString("Time: " + elapsedTime + "s", 10, 40);
//        g.drawString("High Score: " + highScore, 200, 40);
//        
//    }

    
    private void checkFoodCollision() {
        if ((xLeftPlayer[0] == foodX) && (yLeftPlayer[0] == foodY)) {
            leftSnakeLength++;
            leftPlayerScore++; 
            locateFood();
        }

        if ((xRightPlayer[0] == foodX) && (yRightPlayer[0] == foodY)) {
            rightSnakeLength++;
            rightPlayerScore++;
            locateFood();
        }
    }

    private void move() {
        for (int i = leftSnakeLength; i > 0; i--) {
            xLeftPlayer[i] = xLeftPlayer[i - 1];
            yLeftPlayer[i] = yLeftPlayer[i - 1];
        }

        for (int i = rightSnakeLength; i > 0; i--) {
            xRightPlayer[i] = xRightPlayer[i - 1];
            yRightPlayer[i] = yRightPlayer[i - 1];
        }

        if (leftPlayerUp) {
            yLeftPlayer[0] -= SNAKE_SIZE;
        } else if (leftPlayerDown) {
            yLeftPlayer[0] += SNAKE_SIZE;
        } else if (leftPlayerLeft) {
            xLeftPlayer[0] -= SNAKE_SIZE;
        } else if (leftPlayerRight) {
            xLeftPlayer[0] += SNAKE_SIZE;
        }

        if (rightPlayerUp) {
            yRightPlayer[0] -= SNAKE_SIZE;
        } else if (rightPlayerDown) {
            yRightPlayer[0] += SNAKE_SIZE;
        } else if (rightPlayerLeft) {
            xRightPlayer[0] -= SNAKE_SIZE;
        } else if (rightPlayerRight) {
            xRightPlayer[0] += SNAKE_SIZE;
        }

        checkCollision();
        checkFoodCollision();
    }

    private void checkCollision() {
        // Check wall collision
        if (xLeftPlayer[0] >= WIDTH || xLeftPlayer[0] < 0 || yLeftPlayer[0] >= HEIGHT || yLeftPlayer[0] < 0 ||
            xRightPlayer[0] >= WIDTH || xRightPlayer[0] < 0 || yRightPlayer[0] >= HEIGHT || yRightPlayer[0] < 0) {
            inGame = false;
        }

        // Check self collision
        for (int i = leftSnakeLength; i > 0; i--) {
            if (i > 1 && xLeftPlayer[0] == xLeftPlayer[i] && yLeftPlayer[0] == yLeftPlayer[i]) {
                inGame = false;
            }
        }

        for (int i = rightSnakeLength; i > 0; i--) {
            if (i > 1 && xRightPlayer[0] == xRightPlayer[i] && yRightPlayer[0] == yRightPlayer[i]) {
                inGame = false;
            }
        }

        // Check snake collision
        for (int i = 0; i < leftSnakeLength; i++) {
            if (xLeftPlayer[0] == xRightPlayer[i] && yLeftPlayer[0] == yRightPlayer[i]) {
                headHits++;
                if (headHits >= 2) {
                    inGame = false;
                }
            } else if (xLeftPlayer[i] == xRightPlayer[0] && yLeftPlayer[i] == yRightPlayer[0]) {
                headHits++;
                if (headHits >= 2) {
                    inGame = false;
                }
            } else if (xLeftPlayer[0] == xRightPlayer[i] && yLeftPlayer[0] == yRightPlayer[i]) {
                bodyHits++;
                if (bodyHits >= 3) {
                    inGame = false;
                }
            } else if (xLeftPlayer[i] == xRightPlayer[0] && yLeftPlayer[i] == yRightPlayer[0]) {
                bodyHits++;
                if (bodyHits >= 3) {
                    inGame = false;
                }
            } else if (xLeftPlayer[0] == xRightPlayer[i] && yLeftPlayer[0] == yRightPlayer[i]) {
                tailHits++;
                if (tailHits >= 5) {
                    inGame = false;
                }
            } else if (xLeftPlayer[i] == xRightPlayer[0] && yLeftPlayer[i] == yRightPlayer[0]) {
                tailHits++;
                if (tailHits >= 5) {
                    inGame = false;
                }
            }
        }

        // Check head collision with each other
        if (xLeftPlayer[0] == xRightPlayer[0] && yLeftPlayer[0] == yRightPlayer[0]) {
            inGame = false;
        }
    }

    private void locateFood() {
        int r = (int) (Math.random() * RAND_POS);
        foodX = ((r * SNAKE_SIZE));

        r = (int) (Math.random() * RAND_POS);
        foodY = ((r * SNAKE_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_W && !leftPlayerDown) {
                leftPlayerUp = true;
                leftPlayerDown = false;
                leftPlayerLeft = false;
                leftPlayerRight = false;
            }

            if (key == KeyEvent.VK_S && !leftPlayerUp) {
                leftPlayerUp = false;
                leftPlayerDown = true;
                leftPlayerLeft = false;
                leftPlayerRight = false;
            }

            if (key == KeyEvent.VK_A && !leftPlayerRight) {
                leftPlayerUp = false;
                leftPlayerDown = false;
                leftPlayerLeft = true;
                leftPlayerRight = false;
            }

            if (key == KeyEvent.VK_D && !leftPlayerLeft) {
                leftPlayerUp = false;
                leftPlayerDown = false;
                leftPlayerLeft = false;
                leftPlayerRight = true;
            }

            if (key == KeyEvent.VK_UP && !rightPlayerDown) {
                rightPlayerUp = true;
                rightPlayerDown = false;
                rightPlayerLeft = false;
                rightPlayerRight = false;
            }

            if (key == KeyEvent.VK_DOWN && !rightPlayerUp) {
                rightPlayerUp = false;
                rightPlayerDown = true;
                rightPlayerLeft = false;
                rightPlayerRight = false;
            }

            if (key == KeyEvent.VK_LEFT && !rightPlayerRight) {
                rightPlayerUp = false;
                rightPlayerDown = false;
                rightPlayerLeft = true;
                rightPlayerRight = false;
            }

            if (key == KeyEvent.VK_RIGHT && !rightPlayerLeft) {
                rightPlayerUp = false;
                rightPlayerDown = false;
                rightPlayerLeft = false;
                rightPlayerRight = true;
            }
        }
    }                                                                                                     
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
