/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel {
    private JTextField playerNameField;
    private final JButton startButton;
    private final JComboBox<String> levelComboBox;
    private final GameFrame gameFrame;

    public MainMenu(GameFrame frame) {
        this.gameFrame = frame;

        setPreferredSize(new Dimension(600, 400));      
        //setBackground(Color.BLACK);
        //setBackground(new Color(34, 40, 49)); // Màu nền đậm
        setBackground(new Color(255, 182, 193));
        setLayout(new BorderLayout());

//        JLabel titleLabel = new JLabel("Snake Game", JLabel.CENTER);
//        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
//        titleLabel.setForeground(Color.WHITE);
 // Tiêu đề
        JLabel titleLabel = new JLabel("Snake Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 36));
        //titleLabel.setForeground(new Color(0, 173, 181));
        titleLabel.setForeground(new Color(255, 105, 180));
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));



//        JPanel inputPanel = new JPanel();
//        inputPanel.setBackground(Color.BLACK);
//        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
//
//        JLabel player1Label = new JLabel("Player 1 Name: ");
//        player1Label.setForeground(Color.WHITE);
//        JTextField player1NameField = new JTextField(15);
//
//        JLabel player2Label = new JLabel("Player 2 Name: ");
//        player2Label.setForeground(Color.WHITE);
//        JTextField player2NameField = new JTextField(15);
//        
//        startButton = new JButton("Start Game");
//
//        startButton.addActionListener(new ActionListenerImpl(player1NameField, player2NameField));
//
//        inputPanel.add(player1Label);
//        inputPanel.add(player1NameField);
//        inputPanel.add(player2Label);
//        inputPanel.add(player2NameField);
//        inputPanel.add(startButton);
//
//        add(titleLabel, BorderLayout.NORTH);
//        add(inputPanel, BorderLayout.CENTER);
//    }

        // Panel nhập liệu
        JPanel inputPanel = new JPanel();
        //inputPanel.setBackground(new Color(57, 62, 70)); // Màu nền của inputPanel
        inputPanel.setBackground(new Color(255, 192, 203));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 173, 181), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nhãn và ô nhập liệu cho người chơi 1
        JLabel player1Label = new JLabel("Player 1 Name: ");
        player1Label.setForeground(Color.BLACK);
        player1Label.setFont(new Font("Helvetica", Font.PLAIN, 18));
        JTextField player1NameField = new JTextField(15);
        player1NameField.setFont(new Font("Helvetica", Font.PLAIN, 18));
        //player1NameField.setBackground(new Color(238, 238, 238));
        //player1NameField.setForeground(new Color(34, 40, 49));
        player1NameField.setBackground(new Color(255, 240, 245)); // Hồng rất nhạt
        player1NameField.setForeground(new Color(255, 20, 147)); // Hồng tươi

        // Nhãn và ô nhập liệu cho người chơi 2
        JLabel player2Label = new JLabel("Player 2 Name: ");
        player2Label.setForeground(Color.BLACK);
        player2Label.setFont(new Font("Helvetica", Font.PLAIN, 18));
        JTextField player2NameField = new JTextField(15);
        player2NameField.setFont(new Font("Helvetica", Font.PLAIN, 18));
        //player2NameField.setBackground(new Color(238, 238, 238));
        //player2NameField.setForeground(new Color(34, 40, 49));
        player1NameField.setBackground(new Color(255, 240, 245)); // Hồng rất nhạt
        player1NameField.setForeground(new Color(255, 20, 147)); // Hồng tươi
             // Thêm mức độ chơi
        JLabel levelLabel = new JLabel("Select Level: ");
        levelLabel.setForeground(Color.BLACK);
        levelLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        String[] levels = {"Easy", "Medium", "Hard"};
        levelComboBox = new JComboBox<>(levels);
        levelComboBox.setFont(new Font("Helvetica", Font.PLAIN, 18));
//        levelComboBox.setBackground(new Color(238, 238, 238));
//        levelComboBox.setForeground(new Color(34, 40, 49));
        levelComboBox.setBackground(new Color(255, 240, 245)); // Hồng rất nhạt
        levelComboBox.setForeground(new Color(255, 20, 147)); // Hồng tươi

        // Nút bắt đầu trò chơi
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Helvetica", Font.BOLD, 18));
        //startButton.setBackground(new Color(0, 173, 181));
        startButton.setBackground(new Color(255, 105, 180));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);

        startButton.addActionListener(new ActionListenerImpl(player1NameField, player2NameField,levelComboBox));

        // Thêm các thành phần vào inputPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(player1Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(player1NameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(player2Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(player2NameField, gbc);
        
            gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(levelLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(levelComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inputPanel.add(startButton, gbc);
        // Panel chứa nút Start
        JPanel buttonPanel = new JPanel();
        //buttonPanel.setBackground(new Color(34, 40, 49));
        buttonPanel.setBackground(new Color(255, 182, 193));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        buttonPanel.add(startButton);
        // Panel cho mức độ chơi
        JPanel levelPanel = new JPanel();
        //levelPanel.setBackground(new Color(57, 62, 70));
        levelPanel.setBackground(new Color(255, 192, 203));
        levelPanel.setBorder(BorderFactory.createCompoundBorder(
                //BorderFactory.createLineBorder(new Color(0, 173, 181), 2),
                //BorderFactory.createEmptyBorder(20, 20, 20, 20)
                BorderFactory.createLineBorder(new Color(255, 105, 180), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        levelPanel.setLayout(new GridBagLayout());
        // Thêm tiêu đề và các panel con vào MainMenu               
        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(levelPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private class ActionListenerImpl implements ActionListener {

        private final JTextField player1NameField;
        private final JTextField player2NameField;
        private final JComboBox<String> levelComboBox;

        public ActionListenerImpl(JTextField player1NameField, JTextField player2NameField,JComboBox<String> levelComboBox) {
            this.player1NameField = player1NameField;
            this.player2NameField = player2NameField;
            this.levelComboBox = levelComboBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String player1Name = player1NameField.getText().trim();
            String player2Name = player2NameField.getText().trim();
            String selectedLevel = (String) levelComboBox.getSelectedItem();
            if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                gameFrame.starGame(player1Name, player2Name);
            } else {
                JOptionPane.showMessageDialog(MainMenu.this, "Please enter names for both players.");
            }
        }
    }

}