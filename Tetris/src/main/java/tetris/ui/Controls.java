
package tetris.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;


public class Controls extends JFrame{

    public Controls() {
        initUI();
    }
    
    /**
     * Shows all the controls that player can use while playing.
     */
    private void initUI() {
        String controls = "<html>"
                + "Up - Rotates the block.<br>"
                + "Down - Drops block down one row.<br>"
                + "Left - Moves block to the left.<br>"
                + "Right - Moves block to the right.<br>"
                + "Spacebar - Drops block down.<br>"
                + "P - Pauses the game."
                + "</html>";

        JLabel label = new JLabel(controls);
        label.setFont(new Font("Serif", Font.BOLD, 14));
        label.setForeground(new Color(50, 50, 25));

        JPanel panel = new JPanel();
        panel.add(label);
        this.getContentPane().add(panel);

        setTitle("Controls");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
 
}
