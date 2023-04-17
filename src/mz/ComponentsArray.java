package mz;

import java.awt.Component;
import java.lang.reflect.Array;

/**
 * A class for operations with Components.
 * @since 1.0
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class ComponentsArray {

    /**
     * Creating an array of the specified class.
     * The contents of the array are filled with the type of the specified class.
     * @param componentDotClass correctly specifying (Component name).class
     * @param index array index
     * @return given the given class, it creates an array from it and fills it with elements of that class.
     * @param <Thing> any class that the Component class inherits
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @see Component
     * @see Array#newInstance(Class, int)
     */
    public static<Thing extends Component> Thing[] newComponents(Class<Thing> componentDotClass, int index) {
        Thing[] newComponents = (Thing[]) Array.newInstance(componentDotClass, index);
        for(int i=0; i<index; i++){
            try {
                newComponents[i] = componentDotClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e){
                throw new RuntimeException(e);
            }
        }
        return newComponents;
    }
}
