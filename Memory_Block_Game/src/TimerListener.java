import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    private int elapsedTime = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime++;
    }
}