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

    // Convert audio format to PCM audio format for the speakers (adds supports for MP3 files)
    public static AudioInputStream audioDecoder() {
        AudioFormat baseFormat = inputStream.getFormat();

        AudioFormat decodedFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false
        );

        AudioInputStream decodedInput = AudioSystem.getAudioInputStream(decodedFormat, inputStream);
        return decodedInput;
    }


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

            clip.open(audioDecoder());

            //starts playing the music

            clip.start();

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

        try{

            if(clip.isRunning()) {
                clip.close();
                inputStream.close();

                TimelineClass.resetTimeline();
            }

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
            WindowManager.currentMusic.setText("Now Playing: "+ fileImportClass.fileList[currentFileIndex]);

            /*
            Sets the path of the next file, Reinitialize the inputStream and clip
            with the modified pointer.

            Refer line no: 21 to 32 for more reference
            */

            inputStream = AudioSystem.getAudioInputStream(fileImportClass.fileList[currentFileIndex]);
            clip = AudioSystem.getClip();
            clip.open(musicPlayerClass.audioDecoder());
            clip.start();
        }
        catch(Exception e) {
            if(e.getClass().getName().equals("java.lang.ArrayIndexOutOfBoundsException")) {
                System.out.println("No Music Directory Exists");
            }
            else{
                e.printStackTrace();
            }
        }
    }

    public static void prevMusic() {

        try{

            if(clip.isRunning()) {
                clip.close();
                inputStream.close();

                TimelineClass.resetTimeline();
            }

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
            WindowManager.currentMusic.setText("Now Playing: "+ fileImportClass.fileList[currentFileIndex]);

            /*
            Sets the path of the next file, Reinitialize the inputStream and clip
            with the modified pointer.

            Refer line no: 21 to 32 for more reference
            */

            inputStream = AudioSystem.getAudioInputStream(fileImportClass.fileList[currentFileIndex]);
            clip = AudioSystem.getClip();
            clip.open(musicPlayerClass.audioDecoder());
            clip.start();

        }
        catch(UnsupportedAudioFileException e) {
            System.out.println("File Format Not Supported. Please use .wav, .mp3, .aif, .aiff, .au, SND file formats only");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(LineUnavailableException e) {
            System.out.println("Unable To Access Audio File");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("No Music Directory Exists");
        }
    }

    public static void customFile(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        WindowManager.currentMusic.setText("Now Playing: "+ fileImportClass.fileList[currentFileIndex]);
        WindowManager.currentMusic.repaint();

        TimelineClass.isPaused = false;
        TimelineClass.resetTimeline();

        if(clip == null) {
            inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();

            TimelineClass tm = new TimelineClass();
        }

        else{
            clip.close();
            inputStream.close();

            inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(musicPlayerClass.audioDecoder());
            clip.start();
        }

    }

    public static String getMusicDuration() {
        int a = (int) ((int) clip.getMicrosecondLength() * 0.000001);

        WindowManager.timeLine.setMaximum((int) ((int) clip.getMicrosecondLength() * 0.000001));

        int secondH;
        int minuteH = 0;

        if(a < 60) {
            secondH = a;
            if(a < 10) {
                return minuteH+":"+"0"+secondH;
            }
            return minuteH+":"+secondH;
        }

        else{
            while(a > 60) {
                minuteH++;
                a = a - 60;
            }
            secondH = a;

            if(secondH < 10) {
                return minuteH+":"+"0"+secondH;
            }
            return minuteH+":"+secondH;
        }
    }
}

