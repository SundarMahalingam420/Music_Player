import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                }

                if(actionEvent.getActionCommand().equals("X")) {
                    if(musicPlayerClass.clip.isRunning()) {
                        musicPlayerClass.clip.stop();
                    }
                }

                if(actionEvent.getActionCommand().equals(">>")) {
                    musicPlayerClass.nextMusic();
                }

                if(actionEvent.getActionCommand().equals("<<")) {
                    musicPlayerClass.prevMusic();
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