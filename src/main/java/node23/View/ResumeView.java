package node23.View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import node23.Model.Game;

public class ResumeView extends JFrame implements ActionListener, WindowListener, KeyListener {
    private JButton backButton = new JButton("<");
    private JButton fowardButton = new JButton(">");
    JTextPane jtp = new JTextPane();
    ArrayList<Game> games;
    int index = 0;

    public ResumeView(ArrayList<Game> games) throws BadLocationException {
        this.games = games;    
        this.setTitle("Quaker Log Parser");
        this.setResizable(false);
        this.setSize(500,300);
        this.setLayout(null);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(this);
        jtp.addKeyListener(this);
        
        backButton.setBounds(-15, 135, 50, 50);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.addActionListener(this);
        this.add(backButton);
        
        fowardButton.setBounds(450, 135, 50, 50);
        fowardButton.setForeground(Color.white);
        fowardButton.setBackground(Color.LIGHT_GRAY);
        fowardButton.addActionListener(this);
        this.add(fowardButton);
        
        
        jtp.setEditable(false);
        jtp.setContentType("text/");
        jtp.setBackground(Color.DARK_GRAY);
        jtp.setText(games.get(index).toString()); 
        jtp.setForeground(Color.WHITE);
        jtp.setBounds(40, 0, 400, 300);
        this.add(jtp);

        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent f) {
        if (f.getSource() == backButton) {
            if(index > 0){
                index--;
                jtp.setText(games.get(index).toString());
            }
        }
        if (f.getSource() == fowardButton) {
            if(index < games.size()-1){
                index++;
                jtp.setText(games.get(index).toString());
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if(index > 0){
                index--;
                jtp.setText(games.get(index).toString());
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(index < games.size()-1){
                index++;
                jtp.setText(games.get(index).toString());
            }
        }
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        new MainView(games);
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
