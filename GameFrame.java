    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;
import javax.swing.*;

public class GameFrame extends JFrame{
    private final MainMenu mainMenu;
    private SnakeGame snakeGame;
    
    public GameFrame(){
        
        setTitle("Snake Game");
        mainMenu = new MainMenu(this);
        setContentPane(mainMenu);
        pack();
        
    }
    public void starGame(String player1Name, String player2Name){
        snakeGame = new SnakeGame(player1Name, player2Name);
        setContentPane(snakeGame);
        snakeGame.requestFocusInWindow();
        pack();
    }
}
