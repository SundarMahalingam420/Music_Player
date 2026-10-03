
public class Main {

    public static String AppInfo = "Music Player\n" +
            "Version: 0.0.1\n" +
            "Programmed By: Sundar Mahalingam";

    public static String HelpInfo = "Controls\n" +
            "P -> Play\n" +
            "X -> Pause\n" +
            "<< -> Previous\n" +
            ">> -> Next";

    public static void main(String[] args) {
        System.out.println("My Music Player!");

        musicPlayerClassGUI bb = new musicPlayerClassGUI();
        Thread thread = new Thread(bb);
        thread.start();

        WindowManager win = new WindowManager();
        fileImportClass ff = new fileImportClass();
        musicPlayerClass music = new musicPlayerClass();
    }
}