
public class Main {

    public static String AppInfo = "Music Player\n" +
            "Version: 0.0.1\n" +
            "Programmed By: Sundar Mahalingam";

    public static String HelpInfo = "Controls\n" +
            "P -> Play/Pause\n" +
            "<< -> Previous\n" +
            ">> -> Next";

    public static void main(String[] args) {
        System.out.println("My Music Player!");

        musicPlayerClassGUI bb = new musicPlayerClassGUI();
        Thread threadGUI = new Thread(bb);
        threadGUI.start();

        fileImportClass ff = new fileImportClass();

        musicPlayerClass music = new musicPlayerClass();
        Thread thread2 = new Thread(music);
        thread2.start();


        WindowManager win = new WindowManager();
        TimelineClass tc = new TimelineClass();


    }
}