import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fileImportClass {

    static File file;
    public static File[] fileList;
    static List<File> list;

    public static FileNameExtensionFilter fileFilter;

    //numOfFileFound => Number of Files Found

    public static int numOfFilesFound;

    public static void setFolderPath(String path) {

        musicPlayerClass.currentFileIndex = 0;

        musicPlayerClass.clip.close();

        try{
            musicPlayerClass.inputStream.close();
        }
        catch(Exception e) {
            System.out.println("Error");
        }

        file = new File(path);

        fileList = file.listFiles();
        System.out.println("Searching For Files...");
        System.out.println("File With Unsupported File Formats Will Be Ignored");

        list = new ArrayList<>(Arrays.asList(fileList));


        for(int i = 0; i < fileList.length; i++) {
            if(!fileList[i].getName().contains(".wav") && !fileList[i].getName().contains(".aif")
                    && !fileList[i].getName().contains(".aiff") && !fileList[i].getName().contains(".au") && !fileList[i].getName().contains(".mp3")) {

                list = new ArrayList<>(Arrays.asList(fileList));
                list.remove(i);
            }
        }

        fileFilter = new FileNameExtensionFilter("music","wav","mp3");

        fileList = list.toArray(new File[0]);


        for(numOfFilesFound = 0; numOfFilesFound < fileList.length; numOfFilesFound++) {
            System.out.println(fileList[numOfFilesFound]);
        }

        System.out.println(numOfFilesFound+" Files Detected");
        System.out.println("File Listed Successfully");

        try{
            System.out.println("Starting Music Player...");

            //prints the name of the current file

            System.out.println("Now Playing: "+fileImportClass.fileList[musicPlayerClass.currentFileIndex]);
            WindowManager.currentMusic.setText("Now Playing: "+ fileImportClass.fileList[musicPlayerClass.currentFileIndex]);

            // inputStream gets the file path from the fileList array in the fileImportClass
            musicPlayerClass.inputStream = AudioSystem.getAudioInputStream(fileImportClass.fileList[musicPlayerClass.currentFileIndex]);

            musicPlayerClass.clip = AudioSystem.getClip();

            //clip object opens the inputStream and get the file to play

            musicPlayerClass.clip.open(musicPlayerClass.audioDecoder());

            //starts playing the music

            musicPlayerClass.clip.start();

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Starting Without Music Directory");
        }

    }

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

            list = new ArrayList<>(Arrays.asList(fileList));


            for(int i = 0; i < fileList.length; i++) {
                if(!fileList[i].getName().contains(".wav") && !fileList[i].getName().contains(".aif")
                      && !fileList[i].getName().contains(".aiff") && !fileList[i].getName().contains(".au") && !fileList[i].getName().contains(".mp3")) {

                    list = new ArrayList<>(Arrays.asList(fileList));
                    list.remove(i);
                }
            }

            fileFilter = new FileNameExtensionFilter("music","wav","mp3");

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

            JOptionPane.showMessageDialog(null,"Unable To Find A Music Directory\nLoad The File Manually Using 'File' In The ToolBar","Error",JOptionPane.WARNING_MESSAGE);
        }

    }
}
