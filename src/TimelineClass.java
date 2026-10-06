import java.util.Timer;
import java.util.TimerTask;

public class TimelineClass {

    public static int secondHand = 0;
    public static int minuteHand = 0;

    TimelineClass() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondHand++;
                if(secondHand > 59) {
                    secondHand = 0;
                    minuteHand++;
                }
                System.out.println(minuteHand +":"+ secondHand);
                WindowManager.currentMusicTimeline.setText(musicPlayerClass.getMusicDuration()+"/"+minuteHand+":"+secondHand);
                WindowManager.currentMusicTimeline.repaint();

                if(musicPlayerClass.getMusicDuration().equals(minuteHand+":"+secondHand)) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    TimelineClass.secondHand = 0;
                    musicPlayerClass.nextMusic();
                }
            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);
    }
}
