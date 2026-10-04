import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class musicPlayerClassGUI implements  Runnable{

    public static ActionListener act;

    @Override
    public void run() {
        System.out.println("Thread Baby!");

        act = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(actionEvent.getActionCommand().equals("P")) {
                    if(!musicPlayerClass.clip.isRunning()) {
                        musicPlayerClass.clip.start();
                    }
                    else{
                        musicPlayerClass.clip.stop();
                    }

                }

                if(actionEvent.getActionCommand().equals(">>")) {
                    musicPlayerClass.nextMusic();
                }

                if(actionEvent.getActionCommand().equals("<<")) {
                    musicPlayerClass.prevMusic();
                }
                if(actionEvent.getActionCommand().equals("File")) {
                    int returnVal = WindowManager.musicSelector.showOpenDialog(WindowManager.musicSelector);

                    if(returnVal == JFileChooser.APPROVE_OPTION) {

                        try {
                            musicPlayerClass.customFile(WindowManager.musicSelector.getSelectedFile());
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                            JOptionPane.showMessageDialog(null,e,"Error",JOptionPane.INFORMATION_MESSAGE);
                            musicPlayerClass.nextMusic();
                        }
                    }
                }
                if(actionEvent.getActionCommand().equals("About")) {
                    JOptionPane.showMessageDialog(null,Main.AppInfo,"About",JOptionPane.INFORMATION_MESSAGE);
                }
                if(actionEvent.getActionCommand().equals("Help")) {
                    JOptionPane.showMessageDialog(null,Main.HelpInfo,"Help",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

    }
}