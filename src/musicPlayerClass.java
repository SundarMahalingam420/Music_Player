import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class musicPlayerClass implements Runnable{

    static AudioInputStream inputStream;
    public static Clip clip;

    Scanner scanner = new Scanner(System.in);

    /*
      Points to the current playing file in the fileList Array.
      By default, the index is 0 , which points to the first file path in fileList array.
    */
    static int currentFileIndex = 0;

    @Override
    public void run() {
        try{
            System.out.println("Starting Music Player...");

            //prints the name of the current file

            System.out.println("Now Playing: "+fileImportClass.fileList[currentFileIndex]);

            // inputStream gets the file path from the fileList array in the fileImportClass
            inputStream = AudioSystem.getAudioInputStream(fileImportClass.fileList[currentFileIndex]);

            clip = AudioSystem.getClip();

            //clip object opens the inputStream and get the file to play

            clip.open(inputStream);

            //starts playing the music

            clip.start();

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        String res = "";

        //The music does not stops until res is equal to "Q".

        while(!res.equals("Q")) {


            res = scanner.next().toUpperCase();

            /*
            Below are the controls for the music player, which consists of Stop(S),
            Play(P), Next(>) & Previous(<), these inputs are taken by the String res
            which executes the codes given below.
            */


            switch(res) {

                case "S":
                    //Checks if the music is playing or not, if YES , the music is stopped. Else it is ignored.
                    if(clip.isRunning()) {
                        clip.stop();
                        System.out.println("Music Paused");
                    }
                    break;

                case "P":
                    //Checks if the music is stopped or not, if YES , the music is resumed. Else it is ignored.
                    if(!clip.isRunning()) {
                        clip.start();
                    }
                    break;

                case ">":
                    nextMusic();
                    break;

                case "<":
                    prevMusic();
                    break;

                default:
                    System.out.println("Provide Valid Input");
            }

        }
    }

    public static void nextMusic() {
        if(clip.isRunning()) {
            clip.stop();
        }
        try{

            //Clip and inputStream is closed to release the previous file data from the memory.

            clip.close();
            inputStream.close();

            /*
            Checks if the pointer is greater the size of fileList.
            the pointer resets to 0, if the condition is met.
            This is used to avoid ArrayOutOfBoundsException, which can break the program.

            DISCLAIMER:
            **Needs Proper Explanation**

            */
            if(currentFileIndex == fileImportClass.fileList.length - 1) {
                currentFileIndex = 0;
            }
            else {

                //increments the pointer

                currentFileIndex++;
            }

            //Prints the name of the current file

            System.out.println("Now Playing: "+fileImportClass.fileList[currentFileIndex]);

            /*
            Sets the path of the next file, Reinitialize the inputStream and clip
            with the modified pointer.

            Refer line no: 21 to 32 for more reference
            */

            inputStream = AudioSystem.getAudioInputStream(fileImportClass.fileList[currentFileIndex]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void prevMusic() {
        if(clip.isRunning()) {
            clip.stop();
        }
        try{

            //Clip and inputStream is closed to release the previous file data from the memory.

            clip.close();
            inputStream.close();

            /*
            Checks if the pointer is less than 0 (Ex: -1).
            the pointer resets to the last element in the fileList array, if the condition is met.
            This is used to avoid ArrayOutOfBoundsException, which can break the program.

            DISCLAIMER:
            **Needs Proper Explanation**

            */
            if(currentFileIndex == 0) {
                currentFileIndex = fileImportClass.fileList.length - 1;
            }
            else {

                //decrements the pointer

                currentFileIndex--;
            }

            //prints the name of the current file

            System.out.println("Now Playing: "+fileImportClass.fileList[currentFileIndex]);

            /*
            Sets the path of the next file, Reinitialize the inputStream and clip
            with the modified pointer.

            Refer line no: 21 to 32 for more reference
            */

            inputStream = AudioSystem.getAudioInputStream(fileImportClass.fileList[currentFileIndex]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();

        }
        catch(UnsupportedAudioFileException e) {
            System.out.println("File Format Not Supported. Please use .wav, .aif, .aiff, .au, SND file formats only");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(LineUnavailableException e) {
            System.out.println("Unable To Access Audio File");
        }
    }

    public static void customFile(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        clip.close();
        inputStream.close();

        inputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.start();

    }

    public static String getMusicDuration() {
        int a = (int) ((int) clip.getMicrosecondLength() * 0.000001);

        int secondH;
        int minuteH = 0;

        if(a < 60) {
            secondH = a;
            return minuteH+":"+secondH;
        }

        else{
            while(a > 60) {
                minuteH++;
                a = a - 60;
            }
            secondH = a;
            return minuteH+":"+secondH;
        }
    }
}

