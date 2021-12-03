package node23.View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

import node23.Model.Game;

public class MainView extends JFrame implements FocusListener, ActionListener, WindowListener {
    private JButton resumeButton = new JButton("Resume Games");
    private JButton rankingButton = new JButton("Ranking Games");
    ArrayList<Game> games;
    boolean exit = true;

    public MainView(ArrayList<Game> games) {
        this.games = games;
        
        this.setTitle("Quaker Log Parser");
        this.setResizable(false);
        this.setSize(300,235);
        this.setVisible(true);
        this.setLayout(null);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(this);

        resumeButton.setBounds(0, 0, 300, 100);
        resumeButton.setForeground(Color.white);
        resumeButton.setBackground(new Color(50,205,50));
        resumeButton.addFocusListener(this);
        resumeButton.addActionListener(this);
        this.add(resumeButton);

        rankingButton.setBounds(0, 100, 300, 100);
        rankingButton.setForeground(Color.white);
        rankingButton.setBackground(new Color(255,215,0));
        rankingButton.addFocusListener(this);
        rankingButton.addActionListener(this);
        this.add(rankingButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resumeButton) {
            try {
                new ResumeView(games);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
            exit = false;
            this.dispose();
        }
        
        if (e.getSource() == rankingButton) {
            
        }
    }

    @Override
    public void focusGained(FocusEvent f) {
        if (f.getSource() == resumeButton) {
            UIManager.put("Button.select", new Color(0x2d572c));
        }
        if (f.getSource() == rankingButton) {
            
        }
    }

    @Override
    public void focusLost(FocusEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

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
}
