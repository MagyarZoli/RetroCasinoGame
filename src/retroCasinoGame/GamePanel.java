package retroCasinoGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import list.ColorList;
import mz.ImageLoader;

/**
 * In the GamePanel class, drawing random pictures on a 3-column is redrawn as a result of external events.
 */
public class GamePanel
extends JPanel
implements ActionListener{

    /**
     * Calling a random number that will randomly distribute images to the {@code randomImage} stack,
     * this upload is done by calling the {@code randomImage()} method.
     * @see GamePanel#randomImage()
     */
    private final Random random = new Random();

    /**
     * Store the overridden method argument of {@code paintComponent} as an external method.
     * @see GamePanel#paintComponent(Graphics)
     */
    private Graphics graphics;

    /**
     * panel width, default value set to 200
     */
    private int width = 200;

    /**
     * panel height, default value set to 200
     */
    private int height = 200;

    /**
     * dividing the width of the panel into 3 equal parts,
     * saving this value as a round integer
     */
    private int paintWidth = (int)(width/3);

    /**
     * dividing the height of the panel into 3 equal parts,
     * saving this value as a round integer
     */
    private int paintHeight = (int)(height/3);

    /**
     * 3 speeds with which the images stored in the array on the panel will be moved.
     * @see GamePanel#speedRotating()
     */
    private double[] speeds = new double[3];

    /**
     * 3 spin counters that continuously decrease with different intensities
     * pass these values to the speeds array in the {@code speedRotating()} method,
     * which is used in the inherited {@code actionPerformed()} method.
     * @see GamePanel#speedRotating()
     * @see GamePanel#paintComponent(Graphics)
     */
    private double[] spins = new double[3];

    /**
     * Array elements were filled with random images.
     * Uploading is done by the {@code randomImage()} method.
     * Images are provided by the {@code list.ImageList} import class.
     * @see GamePanel#randomImage()
     * @see mz.ImageLoader
     */
    private int[] randomImage = randomImage();

    /**
     * These arrays contain the values that {@code randomImage} stores values in 5 named lines.
     * The default loading of the array is done by the {@code winArray()} method.
     * The controlled upload is made by the {@code winQuestion()} method.
     * @see GamePanel#winArray()
     * @see GamePanel#winQuestion()
     */
    private int[][] winArray = winArray();

    /**
     * it only allows the {@code winQuestion()} method to be called at the right moment
     * for the checked upload to run properly.
     * @see GamePanel#speedRotating()
     * @see GamePanel#winQuestion()
     */
    private boolean winquestion = false;

    /**
     * Location to browse the image's folder.
     */
    private String folderPath = "./image";

    /**
     * Upload ImageIcon array with images in the image folder.
     */
    private ImageIcon[] imageIcon = ImageLoader.loadImages(folderPath);

    /**
     * Arguments specified in the GamePanel constructor are passed to JPanel,
     * plus additional default settings:
     * <ul>
     *     <li>default dimensions are 200x200</li>
     *     <li>not transparent</li>
     *     <li>set background color from {@code list.ColorList} class</li>
     * </ul>
     */
    public GamePanel(){
        super();
        gamePanel();
    }

    /**
     * Default settings:
     * <ul>
     *     <li>default dimensions are 200x200</li>
     *     <li>not transparent</li>
     *     <li>set background color from {@code list.ColorList} class</li>
     * </ul>
     */
    private void gamePanel(){
        this.setSize(width+1,height+1);
        this.setOpaque(true);
        this.setBackground(ColorList.GAME_BACKGROUND);
        timer(10);
    }

    /**
     * {@code JPanel} passes the values to {@code setBounds},
     * then stores the values for other methods.
     * @param x the new <i>x</i>-coordinate of this component
     * @param y the new <i>y</i>-coordinate of this component
     * @param width the new {@code width} of this component
     * @param height the new {@code height} of this component
     */
    @Override
    public void setBounds(int x, int y, int width, int height){
        super.setBounds(x, y, width, height);
        this.width = width-1;
        this.height = height-1;
        this.paintWidth = (int)(width/3);
        this.paintHeight = (int)(height/3);
    }

    /**
     * The value that causes the images on the panel to move and alternate can be set,
     * this value continuously decreases until it reaches 0, and when this value is reached, the images stop moving.
     * @param spin specified value must be greater than 0!
     */
    public void setSpin(double spin){
        if(spin>=0){
            for(int i=0; i<3; i++){
                this.spins[i] = spin;
            }
        }
    }

    /**
     * Callable rotate returns the value of the last rotated panel.
     * @return last spins
     */
    public double getSpin(){
        return this.spins[2];
    }

    /**
     * By setting the default value, which is not won, can be specified.
     * @see GamePanel#winArray()
     */
    public void defaultWins(){
        this.winArray = winArray();
    }

    /**
     * It includes the values that the 5 possible cases won, then a case won if not 0,
     * its size determines the size of the prize,
     * more than one can be valid at the same time.
     * @return list 5 possible winning lines
     * @see GamePanel#win(int[])
     */
    public int[] getWins(){
        return new int[]{win(winArray[0]), win(winArray[1]), win(winArray[2]), win(winArray[3]), win(winArray[4])};
    }

    /**
     * The inherited method of ActionListener, in which the {@code speedRotating()} and {@code repaint()} methods are called,
     * moves the image, loads the arrays, and checks this winning row.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        speedRotating();
        repaint();
    }

    /**
     * Moving random images on 3 vertical columns with different decreasing speeds.
     * To draw pictures, the {@code imageDraw()} method is called,
     * if there is a winning line, a {@code lineDraw()} method is used to draw a red line above it.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        this.graphics = g;
        super.paintComponent(g);
        imageDraw(randomImage[0], 0, (int)(speeds[0]-(paintHeight)));
        imageDraw(randomImage[1], 0, (int)(speeds[0]));
        imageDraw(randomImage[2], 0, (int)(speeds[0]+(paintHeight)));
        imageDraw(randomImage[3], 0, (int)(speeds[0]+(2*paintHeight)));
        imageDraw(randomImage[4], paintWidth, (int)(speeds[1]-(paintHeight)));
        imageDraw(randomImage[5], paintWidth, (int)(speeds[1]));
        imageDraw(randomImage[6], paintWidth, (int)(speeds[1]+(paintHeight)));
        imageDraw(randomImage[7], paintWidth, (int)(speeds[1]+(2*paintHeight)));
        imageDraw(randomImage[8], 2*paintWidth, (int)(speeds[2]-(paintHeight)));
        imageDraw(randomImage[9], 2*paintWidth, (int)(speeds[2]));
        imageDraw(randomImage[10], 2*paintWidth, (int)(speeds[2]+(paintHeight)));
        imageDraw(randomImage[11], 2*paintWidth, (int)(speeds[2]+(2*paintHeight)));
        lineDraw();
    }

    /**
     * Start timer
     * @param delay given in milliseconds!
     */
    private void timer(int delay){
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    /**
     * Uploads random images to the array, reads the images from the {@code list.ImageList} class.
     * @return array filled with random images.
     * @see mz.ImageLoader
     */
    private int[] randomImage(){
        int[] newRandomImage = new int[12];
        for(int i=0; i<newRandomImage.length; i++){
            newRandomImage[i] = random.nextInt(6);
        }
        return newRandomImage;
    }

    /**
     * It uploads additional arrays into the array, in which it uploads default values,
     * based on which the {@code win()} method will not be true
     * @see GamePanel#win(int[])
     * @return default values loaded array
     */
    private int[][] winArray(){
        int[][] winArray = new int[5][3];
        int[] defaultWins = new int[]{1,2,3};
        for(int i=0; i<winArray.length; i++){
            winArray[i] = defaultWins;
        }
        return winArray;
    }

    /**
     * When the spinning starts, it draws new images, the previous ones inherit the previous images,
     * when the spin is over, it is uploaded to the array for inspection.
     * @see GamePanel#winQuestion()
     */
    private void speedRotating(){
        for(int i=0; i<3; i++){
            if(speeds[i]>=paintHeight){
                speeds[i]=0;
                spins[i]-=12-(i*(i+1));
                for(int j=3; j>0; j--){
                    randomImage[j+(i*4)] = randomImage[(j-1)+(i*4)];
                }
                randomImage[(i*4)] = random.nextInt(imageIcon.length);
                winquestion =true;
            }
            else{
                speeds[i]+=spins[i];
            }
        }
        if(winquestion && spins[2]==0){
            winQuestion();
            winquestion = false;
        }
    }

    /**
     * When the array is uploaded in the checked mode, these lines are checked by the {@code win()} method
     * to see if there is a match in them.
     */
    private void winQuestion(){
        winArray[0] = new int[]{randomImage[1], randomImage[6], randomImage[11]};
        winArray[1] = new int[]{randomImage[1], randomImage[5], randomImage[9]};
        winArray[2] = new int[]{randomImage[2], randomImage[6], randomImage[10]};
        winArray[3] = new int[]{randomImage[3], randomImage[7], randomImage[11]};
        winArray[4] = new int[]{randomImage[3], randomImage[6], randomImage[9]};
        winquestion = false;
    }

    /**
     * Array values comparison, if all 3 values are the same, it returns the size of the prize,
     * if there is no match, the return value is 0.
     * @param array
     * @return value if there is a match, no match 0.
     * @see GamePanel#winQuestion()
     */
    private int win(int[] array){
        if(array[0]==array[1] && array[0]==array[2]){
            return array[0]+1;
        }
        return 0;
    }

    /**
     * width and height of the image to 1/3 of the size of the previously demarcated panel.
     * method draws the image in the selected location.
     * @param index calls the random image based on index
     * @param x upper left x point in the coordinate system
     * @param y upper left y point in the coordinate system
     */
    private void imageDraw(int index, int x, int y){
        graphics.drawImage(imageIcon[index].getImage(), x, y, this.paintWidth, this.paintHeight, getFocusCycleRootAncestor());
    }

    /**
     * observes which event row won and draws a red line on this row, there can be several at the same time.
     */
    private void lineDraw(){
        graphics.setColor(Color.RED);
        if(win(winArray[0])!=0){
            graphics.drawLine(0, 0, width, height);
        }
        if(win(winArray[1])!=0){
            graphics.drawLine(0, (int)(paintHeight/2), width, (int)(paintHeight/2));
        }
        if(win(winArray[2])!=0){
            graphics.drawLine(0, (int)(height/2), width, (int)(height/2));
        }
        if(win(winArray[3])!=0){
            graphics.drawLine(0, (int)(height-(paintHeight/2)), width, (int)(height-(paintHeight/2)));
        }    
        if(win(winArray[4])!=0){
            graphics.drawLine(0, height, width, 0);
        }    
    }
}