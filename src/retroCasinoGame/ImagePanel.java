package retroCasinoGame;

import java.awt.Graphics;
import javax.swing.JPanel;

import list.ImageList;

/**
 * {@code list.ImageList} displays images in a panel. repeats each image 3x.
 */
public class ImagePanel
extends JPanel{

    /**
     * Store the overridden method argument of {@code paintComponent} as an external method.
     * @see ImagePanel#paintComponent(Graphics)
     */
    private Graphics graphics;

    /**
     * Panel default width is 1000.
     */
    private int width = 1000;

    /**
     * Panel default height is 1000.
     */
    private int height = 1000;

    /**
     * dividing the width of the panel into 3 equal parts so that all 3 repeated images can fit.
     */
    private int widthImage = (int)(width/3);

    /**
     * panel height divided into 6 equal parts to fit all 6 images.
     */
    private int heightImage = (int)(height/6);

    /**
     * Arguments specified in its constructor are passed to the JPanel class.
     * default setting:
     * <ul>
     *     <li>transparent</li>
     * </ul>
     */
    public ImagePanel(){
        super();
        this.setOpaque(false);
    }

    /**
     * {@code JPanel} passes the values to {@code setBounds},
     * then stores the values for other methods.
     * @param x the new <i>x</i>-coordinate of this component
     * @param y the new <i>y</i>-coordinate of this component
     * @param width the new {@code width} of this component
     * @param height the new {@code height} of this component
     */
    public void setBounds(int x, int y, int width, int height){
        super.setBounds(x, y, width, height);
        this.width = width;
        this.height = height;
        this.widthImage = (int)(width/3);
        this.heightImage = (int)(height/6);
    }

    /**
     * Images imported from the {@code list.ImageList} class are drawn in the panel.
     * @param g the <code>Graphics</code> object to protect
     * @see list.ImageList
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.graphics = g;
        imagesDraw();
        repaint();
    }

    /**
     * drawing images on the panel. Each image repeated 3x.
     */
    private void imagesDraw(){
        for(int i = 0; i< ImageList.IMAGES.length; i++){
            for(int j=0; j<3; j++){
                graphics.drawImage(ImageList.IMAGES[i], (this.widthImage*j), (this.heightImage*(5-i)), this.widthImage, this.heightImage, getFocusCycleRootAncestor());
            }
        }
    }
}
