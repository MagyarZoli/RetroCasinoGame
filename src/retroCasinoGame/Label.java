package retroCasinoGame;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * It inherits the JLabel class, plus other modifications.
 */
public class Label 
extends JLabel{

    /**
     * stores the color of the letter
     */
    private Color fg;

    /**
     * Setting label active, if it is active, its color changes with the {@code setActive()} method.
     * By default, Label is not active.
     * @see Label#setActiv(boolean)
     */
    private boolean activ = false;

    /**
     * set font "Bernard MT Condensed" bold and font size 20
     */
    private Font font = new Font("Bernard MT Condensed", Font.BOLD, 20);

    /**
     * Passing a Label argument to the JLabel class.
     * default setting:
     * <ul>
     *     <li>not focusable</li>
     *     <li>not transparent</li>
     *     <li>set font "Bernard MT Condensed" bold and font size 20</li>
     * </ul>
     */
    public Label(){
        super();
        allLabel();
    }

    /**
     * Passing a Label argument to the JLabel class.
     * default setting:
     * <ul>
     *     <li>not focusable</li>
     *     <li>not transparent</li>
     *     <li>set font "Bernard MT Condensed" bold and font size 20</li>
     * </ul>
     * @param text text to display
     */
    public Label(String text){
        super(text);
        allLabel();
    }

    /**
     * default setting:
     * <ul>
     *     <li>not focusable</li>
     *     <li>not transparent</li>
     *     <li>set font "Bernard MT Condensed" bold and font size 20</li>
     * </ul>
     */
    private void allLabel(){
        this.setFocusable(false);
        this.setOpaque(true);
        this.setFont(font);
    }

    /**
     *The color of the label letter can be specified.
     * @param fg  the desired foreground <code>Color</code>
     */
    @Override
    public void setForeground(Color fg){
        super.setForeground(fg);
        this.fg = fg;
    }

    /**
     * Placement of the text contained in the label.
     * @param alignment One of the following constants
     * @see JLabel#setHorizontalAlignment(int)
     *
     */
    @Override
    public void setHorizontalAlignment(int alignment){
        super.setHorizontalAlignment(alignment);
    }

    /**
     * By calling this method, you can activate the label,
     * whose letter color will change to be brighter,
     * if it is not active, its color will be darker.
     * It does not allow you to activate it more than once if it is activated.
     * @param activ true/false
     */
    public void setActiv(boolean activ){
        this.activ = activ;
        if(activ){
            this.setForeground(this.fg.brighter().brighter());
        }
        else{
            this.setForeground(this.fg.darker().darker());
        }
    }

    /**
     * It is possible to request whether the label is activated.
     * @return is activated
     */
    public boolean isActiv(){
        return this.activ;
    }
}
