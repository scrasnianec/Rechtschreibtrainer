package View;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame() {
        setTitle("Rechtschreibtrainer");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void changeTitle(String title) {
        setTitle(title);
    }
}
