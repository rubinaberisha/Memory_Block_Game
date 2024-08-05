import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryGame extends JPanel {
    private JPanel gamePanel;
    private ArrayList<TileButton> tileButtons;
    private int pairsFound;
    private Timer timer;
    public MemoryGame() {
        setLayout(new BorderLayout());

        gamePanel = new JPanel(new GridLayout(4, 4));
        tileButtons = new ArrayList<>();
        pairsFound = 0;

        initializeTiles();
        addTilesToPanel();
        add(gamePanel, BorderLayout.CENTER);

        timer = new Timer(1000,new TimerListener());
        timer.start();
    }

    private void initializeTiles() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            values.add(i);
            values.add(i);
        }
        Collections.shuffle(values);

        for (int value : values) {
            Tile tile = new Tile(value);
            TileButton button = new TileButton(tile);
            button.setBackground(new Color(232, 195, 195, 219));
            button.addActionListener(new TileClickListener(button));
            tileButtons.add(button);
        }
    }

    private void addTilesToPanel() {
        for (TileButton button : tileButtons) {
            gamePanel.add(button);
        }
    }

    private void checkMatch(TileButton clickedButton) {
        clickedButton.showValue();

        TileButton selectedButton = getSelectedButton();

        if (selectedButton == null) {
            setSelectedButton(clickedButton);
        } else {
            if (selectedButton.getTile().getValue() == clickedButton.getTile().getValue()) {
                pairsFound++;
                selectedButton.setMatched(true);
                clickedButton.setMatched(true);

                setSelectedButton(null);

                if (pairsFound == 8) {
                    timer.stop();
                    JOptionPane.showMessageDialog(this, "Congratulations! You won!");
                    System.exit(0);
                }
            } else {
                Timer mismatchTimer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedButton.hideValue();
                        clickedButton.hideValue();

                        setSelectedButton(null);
                    }
                });
                mismatchTimer.setRepeats(false);
                mismatchTimer.start();
            }
        }
    }

    private TileButton getSelectedButton() {
        for (TileButton button : tileButtons) {
            if (button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    private void setSelectedButton(TileButton button) {
        for (TileButton tileButton : tileButtons) {
            if (tileButton.isSelected()) {
                tileButton.setSelected(false);
            }
            if (tileButton == button) {
                tileButton.setSelected(true);
            }
        }
    }
    private class TileClickListener implements ActionListener {
        private TileButton clickedButton;

        public TileClickListener(TileButton button) {

            clickedButton = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!clickedButton.isMatched()) {
                checkMatch(clickedButton);
            }
        }
    }
}