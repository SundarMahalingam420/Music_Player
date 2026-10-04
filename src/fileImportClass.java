import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fileImportClass {

    File file;
    public static File[] fileList;
    List<File> list;

    public static FileNameExtensionFilter fileFilter;

    //numOfFileFound => Number of Files Found

    public static int numOfFilesFound;

    fileImportClass() {
        try{

            //Checks for the operating system of the user

            String osName = System.getProperty("os.name").toLowerCase();
            System.out.println("Operating System Detected: "+osName);

            //the file object gets the music directory in the computer

            if(osName.contains("win")) {
                file = new File("C:/Users/<YourUsername>/Music");
            }
            else if(osName.contains("linux")) {
                file = new File("/home/sundar/Music");
            }
            else if(osName.contains("mac")) {
                file = new File("/Music");
            }

            // the fileList object gets all the files path from the music directory

            fileList = file.listFiles();
            System.out.println("Searching For Files...");
            System.out.println("File With Unsupported File Formats Will Be Ignored");

            /*
             The loop below remove files with unsupported file formats like mp3.
             The Array is converted into a ArrayList and the file which does not
             contains the .wav format is remove from the list. The ArrayList is
             converted back to a Array.
            */

            for(int i = 0; i < fileList.length; i++) {
                if(!fileList[i].getName().contains(".wav") && !fileList[i].getName().contains(".aif")
                      && !fileList[i].getName().contains(".aiff") && !fileList[i].getName().contains(".au")) {

                    list = new ArrayList<>(Arrays.asList(fileList));
                    list.remove(i);
                }
            }

            fileFilter = new FileNameExtensionFilter("music","wav");

            fileList = list.toArray(new File[0]);

            // prints the fileList array which contains all the files paths

            for(numOfFilesFound = 0; numOfFilesFound < fileList.length; numOfFilesFound++) {
                System.out.println(fileList[numOfFilesFound]);
            }

            System.out.println(numOfFilesFound+" Files Detected");
            System.out.println("File Listed Successfully");
        }
        catch(NullPointerException e) {

            //if the music directory is empty

            System.out.println("File Not Found");
        }


    }
}
