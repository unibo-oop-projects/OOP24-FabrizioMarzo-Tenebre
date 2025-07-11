package view.scene.scene_swing;

import javax.swing.*;

import view.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingSceneGameOver implements Scene{
    private JFrame frame;
    private GameOverPanel panel;
    private int width;
    private int height;

    public SwingSceneGameOver(int width, int height) {
        this.width = width;
        this.height = height;

        frame = new JFrame("Game Over");
        frame.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);

        panel = new GameOverPanel();
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void render() {
        try {
            SwingUtilities.invokeAndWait(() -> frame.repaint());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GameOverPanel extends JPanel implements KeyListener {

        public GameOverPanel() {
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(this);
            setFocusTraversalKeysEnabled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Disegno testo centrato
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));

            String message = "GAME OVER";
            FontMetrics fm = g2d.getFontMetrics();
            int msgWidth = fm.stringWidth(message);
            int msgHeight = fm.getHeight();

            int x = (getWidth() - msgWidth) / 2;
            int y = (getHeight() / 2) + (msgHeight / 4);

            g2d.drawString(message, x, y);

            // Istruzione aggiuntiva per premere un tasto
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            String instruction = "Premi ESC per uscire";
            int instrWidth = g2d.getFontMetrics().stringWidth(instruction);
            g2d.drawString(instruction, (getWidth() - instrWidth) / 2, y + 50);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0); // chiude l'applicazione
            }
            // qui puoi aggiungere altre logiche tipo tornare al menu
        }

        @Override
        public void keyReleased(KeyEvent e) {
            /* nulla */ }

        @Override
        public void keyTyped(KeyEvent e) {
            /* nulla */ }
    }
}
