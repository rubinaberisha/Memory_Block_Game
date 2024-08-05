import javax.swing.*;

public class TileButton extends JButton {
    private Tile tile;
    private boolean matched;
    private boolean selected;

    public TileButton(Tile tile) {
        this.tile = tile;
        matched = false;
        selected = false;
    }

    public Tile getTile() {
        return tile;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void showValue() {
        setText(String.valueOf(tile.getValue()));
        setEnabled(false);
    }

    public void hideValue() {
        setText("");
        setEnabled(true);
    }
}
