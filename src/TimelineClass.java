import java.util.Timer;
import java.util.TimerTask;

public class TimelineClass {

    public static int secondHand = 0;
    public static int minuteHand = 0;

    public static int timeLineProgress = 0;

    public static boolean isPaused;

    String getCurrentMusicDuration() {

        if(secondHand < 10) {
            return minuteHand+":"+"0"+secondHand;
        }

        return minuteHand+":"+secondHand;
    }

    void calculateCurrentMusicDuration() {

        secondHand++;
        timeLineProgress++;
        if(secondHand > 59) {
            secondHand = 0;
            minuteHand++;
        }
    }

    TimelineClass() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(!isPaused) {

                    calculateCurrentMusicDuration();

                    WindowManager.timeLine.setValue(timeLineProgress);

                    System.out.println(minuteHand +":"+ secondHand);

                    WindowManager.currentMusicTimeline.setText(musicPlayerClass.getMusicDuration()+"/"+getCurrentMusicDuration());
                    WindowManager.currentMusicTimeline.repaint();

                    if(musicPlayerClass.getMusicDuration().equals(getCurrentMusicDuration())) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        TimelineClass.secondHand = 0;
                        TimelineClass.minuteHand = 0;
                        timeLineProgress = 0;
                        musicPlayerClass.nextMusic();
                    }
                }

            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);
    }
}
