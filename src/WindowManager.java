
import javax.swing.*;
import java.awt.*;

public class WindowManager {

    JFrame window;

    JPanel topContainer;
    JPanel playerContainer;

    JButton playButton;
    JButton pauseButton;
    JButton previousButton;
    JButton nextButton;

    JLabel currentMusic;

    JToolBar toolBar;
    JButton FileButton;
    JButton HelpButton;
    JButton AboutButton;

    void WindowComponents() {

        //===========================Containers======================================//
        topContainer = new JPanel();
        topContainer.setBackground(new Color(0,0,255,100));
        topContainer.setLayout(new BorderLayout());
        topContainer.setOpaque(true);

        playerContainer = new JPanel();
        playerContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        playerContainer.setBackground(new Color(0,0,255,200));
        playerContainer.setOpaque(true);
        playerContainer.setPreferredSize(new Dimension(200,50));

        //=============================Buttons========================================//

        previousButton = new JButton("<<");
        previousButton.setPreferredSize(new Dimension(50,35));
        previousButton.addActionListener(musicPlayerClassGUI.act);
        playerContainer.add(previousButton);

        playButton = new JButton("P");
        playButton.setPreferredSize(new Dimension(50,35));
        playButton.addActionListener(musicPlayerClassGUI.act);
        playerContainer.add(playButton);

        pauseButton = new JButton("X");
        pauseButton.setPreferredSize(new Dimension(50,35));
        pauseButton.addActionListener(musicPlayerClassGUI.act);
        playerContainer.add(pauseButton);

        nextButton = new JButton(">>");
        nextButton.setPreferredSize(new Dimension(50,35));
        nextButton.addActionListener(musicPlayerClassGUI.act);
        playerContainer.add(nextButton);

        currentMusic = new JLabel(new ImageIcon("/home/sundar/Downloads/pixil-frame-0.png"));
        topContainer.add(currentMusic,BorderLayout.SOUTH);

        FileButton = new JButton("File");
        FileButton.addActionListener(musicPlayerClassGUI.act);

        HelpButton = new JButton("Help");
        HelpButton.addActionListener(musicPlayerClassGUI.act);

        AboutButton = new JButton("About");
        AboutButton.addActionListener(musicPlayerClassGUI.act);

        toolBar = new JToolBar();
        toolBar.setPreferredSize(new Dimension(0,25));
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(0,0,255,100));
        toolBar.add(FileButton);
        toolBar.add(HelpButton);
        toolBar.add(AboutButton);
        topContainer.add(toolBar,BorderLayout.NORTH);
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
        window.add(topContainer, BorderLayout.CENTER);
        window.add(playerContainer, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);

        playButton.setBounds(topContainer.getWidth()/2 - 25, topContainer.getHeight()/2 - 25,50,50);
    }
}
