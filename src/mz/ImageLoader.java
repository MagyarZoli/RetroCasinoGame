package mz;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Calling it by uploading it to an array by searching for the given values.
 * You can upload an array with the images in the folder. {@code loadImages(String)}
 * You can populate an array with the names of the images in the folder. {code loadImagesName(String)}
 * @see ImageLoader#loadImages(String)
 * @see ImageLoader#loadImagesName(String)
 * @since 1.0
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class ImageLoader {

    /**
     * Store imageIcons, file of type jpg jpeg or png.
     * @see ImageLoader#loadImages(String)
     */
    private static List<ImageIcon> imageIcons = new ArrayList<>();

    /**
     * Store imageIcons name, file of type jpg jpeg or png.
     * @see ImageLoader#loadImagesName(String)
     */
    private static List<String> imageIconsName = new ArrayList<>();

    /**
     * Storing files for operating methods.
     * @see ImageLoader#uploadImageIcons()
     * @see ImageLoader#uploadImageIconsName()
     */
    private static File[] files;

    /**
     * Before methods uploads the array it was called from, after specifying the location of the folder,
     * it checks which file image type it is,
     * then uploads these files to the specified array and passes it to the array of the called class.
     * @param folderPath browse to the location of the folder
     * @return If there were image type files in the specified folder, it returns the uploaded imageIcons array.
     */
    public static ImageIcon[] loadImages(String folderPath){
        files = new File(folderPath).listFiles();
        try {
            uploadImageIcons();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageIcons.toArray(new ImageIcon[0]);
    }

    /**
     * Methods, before uploading the array for which it was called,
     * after specifying the location of the folder,
     * it checks which file image type it is, then uploads the names of the files into
     * the specified array and passes this to the array of the called class.
     * @param folderPath browse to the location of the folder
     * @return If there were image type files in the specified folder,
     * the uploaded imageIconsName array returns the image file names.
     * @see ImageLoader#uploadImageIcons()
     */
    public static String[] loadImagesName(String folderPath){
        files = new File(folderPath).listFiles();
        try {
            uploadImageIconsName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageIconsName.toArray(new String[0]);
    }

    /**
     * Method in the specified way,
     * here the file image is then uploaded to the ImageIcon array.
     * @throws IOException
     */
    private static void uploadImageIcons()
            throws IOException {
        for (File file : files) {
            if (file.isFile() && isImageFile(file)) {
                ImageIcon icon = new ImageIcon(ImageIO.read(file));
                imageIcons.add(icon);
            }
        }
    }

    /**
     * Method in the specified way,
     * here the file image then uploads the name of the file into an array.
     * @throws IOException
     */
    private static void uploadImageIconsName()
            throws IOException {
        for (File file : files) {
            if (file.isFile() && isImageFile(file)) {
                imageIconsName.add(file.getName());
            }
        }
    }

    /**
     * Examines the file image type
     * @param file If the file to be analyzed is an image type, i.e. jpg, jpeg, png, it returns true.
     * @return If there is a match then it is {@code true},
     * if there is no match then it is {@code false}.
     */
    private static boolean isImageFile(File file) {
        String extension = getFileExtension(file);
        return (
                extension.equals("jpg") ||
                        extension.equals("jpeg")||
                        extension.equals("png")
        );
    }

    /**
     * File name split into . by extension.
     * returns the value between the thus extracted index and the length of the file name replaced by lowercase letters
     * @param file Whose extension it retrieves.
     * @return file extension, if it does not have a .extension, it is not handled and returns an empty value.
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
}

