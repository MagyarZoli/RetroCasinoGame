package retroCasinoGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * It inherits the JButton class and contains other modifications,
 * the MouseListener interface is also implemented.
 */
public class Button
extends JButton
implements MouseListener{

    /**
     * Storing button background color.
     */
    private Color bg;

    /**
     * Store button foreground color.
     */
    private Color fg;

    /**
     * Button construct specified arguments are passed to the JButton class.
     * other changes in the {@code allButton()} method.
     * Default settings:
     * <ul>
     *     <li>add Mouse Listener</li>
     *     <li>Empty Border</li>
     *     <li>not Focusable</li>
     *     <li>font setting "Bernard MT Condensed" bold font size 20</li>
     * </ul>
     * @see JButton
     * @see MouseListener
     */
    public Button() {
        super();
        allButton();
    }

    /**
     * Default settings:
     * <ul>
     *     <li>add Mouse Listener</li>
     *     <li>Empty Border</li>
     *     <li>not Focusable</li>
     *     <li>font setting "Bernard MT Condensed" bold font size 20</li>
     * </ul>
     */
    private void allButton(){
        this.addMouseListener(this);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setFocusable(false);
        this.setFont(new Font("Bernard MT Condensed", Font.BOLD, 20));
    }

    /**
     * The foreground color of the button can be specified.
     * @param fg  the desired foreground <code>Color</code>
     */
    @Override
    public void setForeground(Color fg){
        super.setForeground(fg);
        this.fg = fg;
    }

    /**
     * The background color of the button can be specified.
     * @param bg the desired background <code>Color</code>
     */
    @Override
    public void setBackground(Color bg){
        super.setBackground(bg);
        this.bg = bg;
    }

    /**
     * The foreground color of the button can be retrieved.
     * @return Button Foreground Color
     */
    @Override
    public Color getForeground(){
        return this.fg;
    }

    /**
     * The background color of the button can be retrieved.
     * @return Button Background Color
     */
    @Override
    public Color getBackground(){
        return this.bg;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //no overwriting no modification!
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //no overwriting no modification!
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //no overwriting no modification!
    }

    /**
     * If the mouse pointer hovers over the button,
     * the colors of the button will change and become brighter.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(fg.brighter());
        this.setBackground(bg.brighter());
    }

    /**
     * If the mouse pointer is not over the button,
     * the colors of the button will change, it will become darker.
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(fg.darker());
        this.setBackground(bg.darker());
    }
}
