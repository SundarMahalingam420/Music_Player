
import javax.swing.*;
import java.awt.*;

public class WindowManager {

    JFrame window;

    JMenuBar OptionBar;
    JPanel topContainer;
    JPanel playerContainer;
    public static JPanel timelineContainer;

    //Test
    public static JProgressBar timeLine;

    JMenu OpenOption;
    JMenuItem FileOpen;
    JMenuItem DirectoryOpen;

    JMenu HelpOption;
    JMenuItem Controls;
    JMenuItem About;

    JButton playButton;
    JButton previousButton;
    JButton nextButton;

    static JLabel currentMusic;
    public static JLabel currentMusicTimeline;


    public static JFileChooser musicSelector;

    void WindowComponents() {

        OptionBar = new JMenuBar();

        FileOpen = new JMenuItem("File");
        FileOpen.addActionListener(musicPlayerClassGUI.act);

        DirectoryOpen = new JMenuItem("Folder");
        DirectoryOpen.addActionListener(musicPlayerClassGUI.act);

        OpenOption = new JMenu("Open");
        OpenOption.add(FileOpen);
        OpenOption.add(DirectoryOpen);

        OptionBar.add(OpenOption);

        HelpOption = new JMenu("Help");

        Controls = new JMenuItem("Controls");
        Controls.addActionListener(musicPlayerClassGUI.act);
        HelpOption.add(Controls);

        About = new JMenuItem("About");
        About.addActionListener(musicPlayerClassGUI.act);
        HelpOption.add(About);

        OptionBar.add(HelpOption);

        //===========================Containers======================================//

        topContainer = new JPanel();
        topContainer.setBackground(new Color(0,0,255,100));
        topContainer.setLayout(new BorderLayout());
        topContainer.setOpaque(true);


        playerContainer = new JPanel();
        playerContainer.setLayout(new FlowLayout());
        playerContainer.setBackground(new Color(0,0,255,200));
        playerContainer.setOpaque(true);
        playerContainer.setPreferredSize(new Dimension(200,50));

        timelineContainer = new JPanel();
        timelineContainer.setLayout(new FlowLayout());
        timelineContainer.setBackground(Color.LIGHT_GRAY);
        timelineContainer.setOpaque(true);
        timelineContainer.setPreferredSize(new Dimension(300,25));

        playerContainer.add(timelineContainer);

        //--------Test=================================================================//
        timeLine = new JProgressBar();
        timeLine.setMinimum(0);
        timelineContainer.add(timeLine);

        //=============================Buttons========================================//

        previousButton = new JButton("<<");
        previousButton.setPreferredSize(new Dimension(50,35));
        previousButton.addActionListener(musicPlayerClassGUI.act);
        playerContainer.add(previousButton);

        playButton = new JButton("P");
        playButton.setPreferredSize(new Dimension(50,35));
        playButton.addActionListener(musicPlayerClassGUI.act);
        playerContainer.add(playButton);

        nextButton = new JButton(">>");
        nextButton.setPreferredSize(new Dimension(50,35));
        nextButton.addActionListener(musicPlayerClassGUI.act);
        playerContainer.add(nextButton);

        currentMusic = new JLabel();
        currentMusic.setOpaque(true);
        WindowManager.currentMusic.setText("Now Playing: "+ fileImportClass.fileList[musicPlayerClass.currentFileIndex]);
        topContainer.add(new JLabel(new ImageIcon("/home/sundar/Downloads/pixil-frame-0.png")),BorderLayout.CENTER);
        topContainer.add(currentMusic,BorderLayout.SOUTH);

        musicSelector = new JFileChooser();
        musicSelector.setFileFilter(fileImportClass.fileFilter);

        currentMusicTimeline = new JLabel();
        timelineContainer.add(currentMusicTimeline);

    }

    WindowManager() {

        WindowComponents();

        window = new JFrame("Music Player");
        window.setSize(400,400);
        window.setLocationRelativeTo(null);
        window.setFocusable(false);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setJMenuBar(OptionBar);
        window.add(topContainer, BorderLayout.CENTER);
        window.add(playerContainer, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);

        playButton.setBounds(topContainer.getWidth()/2 - 25, topContainer.getHeight()/2 - 25,50,50);
    }

}
