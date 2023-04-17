package mz;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Responsive Frame follows the size changes of the window,
 * and the added Components also follow the size changes,
 * but it can also be specified in the traditional version.
 * @see java.awt.event.ComponentAdapter
 * @see javax.swing.JFrame
 * @since 1.1
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class ResponsiveFrame
extends JFrame{

    /**
     * Place the following components on this panel.
     */
    private JPanel responsivePanel = new JPanel(null);

    /**
     * Upper left {@code x} point depicted in the components coordinate system.
     * By default, it can receive 1 free component, as soon as the addition takes place,
     * there will be 1 more free space after calling the {@code arraySize()} method.
     * @see ResponsiveFrame#arraySize(int) 
     */
    private int[] x = new int[1];

    /**
     * Upper left {@code x} point represented in the components coordinate system.
     * By default, it can receive 1 free component, as soon as the addition takes place,
     * there will be 1 more free space after calling the {@code arraySize()} method.
     * @see ResponsiveFrame#arraySize(int)
     */
    private int[] y = new int[1];

    /**
     * Storing the {@code width} of the component.
     * By default, it can receive 1 component, as soon as the addition takes place,
     * after calling the {@code arraySize()} method, it can receive a new 1 component.
     * @see ResponsiveFrame#arraySize(int)
     */
    private int[] width = new int[1];

    /**
     * Storing the {@cose height} of the component.
     * By default, it can receive 1 component, as soon as the addition takes place,
     * after calling the {@code arraySize()} method, it can receive a new 1 component.
     * @see ResponsiveFrame#arraySize(int)
     */
    private int[] height = new int[1];

    /**
     * Storing components in an array.
     * By default, it can receive 1 component, as soon as the addition takes place,
     * after calling the {@code arraySize()} method, it can receive a new 1 component.
     * @see ResponsiveFrame#arraySize(int)
     */
    private Component[] components = new Component[1];

    /**
     * Store exception components in an array.
     * These are the components that should not be responsive.
     * @see ResponsiveFrame#arraySize(int)
     */
    private int[] exception = new int[1];

    /**
     * Frame width can be specified with the {@code getWidth()} method.
     */
    private int frameWidth;

    /**
     * Frame width can be specified with the {@code getHeight()} method.
     */
    private int frameHeight;

    /**
     * Storage of the number of components added to the frame.
     */
    private int componentCounter = 0;

    /**
     * Initialization, {@code JFrame} is passed to {@code super()} 
     * saving frame width and height dimensions. 
     * Additional components added to responsivePanel.
     * {@code ComponentListener} monitors the changes.
     */
    public ResponsiveFrame(){
        super();
        this.frameWidth = this.getWidth();
        this.frameHeight = this.getHeight();
        responsivePanel.setOpaque(false);
        super.add(responsivePanel);
        componentList();
        this.getContentPane().add(responsivePanel);
    }

    /**
     * window size can be specified with it
     */
    @Override
    public void setSize(int width, int height){
        this.frameWidth = width;
        this.frameHeight = height;
        super.setSize(width, height);
    }

    /**
     * Adds the selected components to a responsive frame to make it responsive.
     * By default, yes.
     * @param component that you added to ResponsiveFrame
     * @param responsive you can make your component responsive
     */
    public void setResponsive(Component component, boolean responsive){
        for(int i=0; i<components.length; i++){
            if(components[i].getBounds().equals(component.getBounds()) && !responsive){
                exception[i] = i;
            }
        }
    }

    /**
     * Returns that the specified components are responsive
     * @param component that you added to ResponsiveFrame
     */
    public boolean getResponsive(Component component){
        if(exception[getComponenetIndex(component)]==-1){
            return true;
        }
        return false;
    }

    /**
     * Returns the index of the given component based on which it was added to the ResponsiveFrame
     * @param component
     * @return if NullPointerException("ComponentIndex(Component component) return is null.
     * how to fix add(component)!"
     * @see ResponsiveFrame#add
     */
    public int getComponenetIndex(Component component){
        for(int i=0; i<components.length; i++){
            if(components[i].getBounds().equals(component.getBounds())){
                return i;
            }
        }
        throw new NullPointerException("ComponentIndex(Component component) return is null. how to fix add(component)!");
    }

    /**
     * Returns how many components have been added to the ResponsiveFrame.
     */
    public int getComponenetCounter(){
        return this.componentCounter;
    }

    /**
     * This method can be used to add the component to the ResponsiveFrame
     * @see ResponsiveFrame#arraySize
     * @see ResponsiveFrame#arrayUpload
     * @return responsivePanel.add(component)
     */
    public Component add(Component component){
        arraySize(componentCounter);
        arrayUpload(component);
        return responsivePanel.add(component);
    }

    /**
     * Call componentListener and ComponentAdapter() abstract class to monitor window size changes.
     * Component resizing.
     * @see java.awt.Component#addComponentListener
     * @see java.awt.event.ComponentAdapter#componentResized
     * @see ResponsiveFrame#newBounds
     */
    private void componentList(){
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                newBounds();
            }
        });
    }

    /**
     * new value when adding a new component.
     * on the basis of which it re-creates the data arrays of the sizes and components and then replaces them with the basic arrays,
     * so the data remains and the size of the arrays has become 1 larger.
     * @param counter 
     */
    private void arraySize(int counter){
        if(components.length<=counter){
            int[] newX= new int[counter+1];
            int[] newY = new int[counter+1];
            int[] newWidth = new int[counter+1];
            int[] newHeight = new int[counter+1];
            Component[] newComponents = new Component[counter+1];
            int[] newException = new int[counter+1];
            for(int i=0; i<components.length; i++){
                newX[i] = x[i];
                newY[i] = y[i];
                newWidth[i] = width[i];
                newHeight[i] = height[i];
                newComponents[i] = components[i];
                newException[i] = exception[i];
            }
            this.x=newX;
            this.y=newY;
            this.width=newWidth;
            this.height=newHeight;
            this.components = newComponents;
            this.exception = newException;
        }
    }

    /**
     * Saves the dimensions of the specified component in the arrays created for it.
     * Plus the component is also saved and contains a counter that returns how many components are added to the ResponsivFrame.
     * @param component
     */
    private void arrayUpload(Component component){
        this.x[componentCounter] = component.getX();
        this.y[componentCounter] = component.getY();
        this.width[componentCounter] = component.getWidth();
        this.height[componentCounter] = component.getHeight();
        this.components[componentCounter] = component;
        this.exception[componentCounter] = -1;
        componentCounter++;
    }

    /**
     * specifying new Bounds for components
     * @see ResponsiveFrame#rate
     */
    private void newBounds(){
        for(int i=0; i<components.length; i++){
            if(components[i]!=null && exception[i]!=i){
                components[i].setBounds(
                    (int)(getWidth()/rate(frameWidth, x[i])),
                    (int)(getHeight()/rate(frameHeight, y[i])),
                    (int)(getWidth()/rate(frameWidth, width[i])),
                    (int)(getHeight()/rate(frameHeight, height[i]))
                );
            }
        }
    }

    /**
     * Arithmetic exception to prevent division by 0
     * @param a to be shared
     * @param b dealer b!=0
     * @return if b=0, result 1
     */
    private double rate(double a, double b){
        try{
            return (a/b);
        }
        catch(ArithmeticException e){
            return 1;
        }
    }
}