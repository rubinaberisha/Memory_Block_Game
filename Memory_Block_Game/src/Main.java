import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Main extends JPanel implements ActionListener {
    Container cp;
    JFrame myFrame;
    MemoryGame memoryGame;
    private boolean playModeOn = false;

    public Main(Container c) {
        cp = c;
        myFrame = new JFrame();
        cp = myFrame.getContentPane();
        memoryGame = new MemoryGame();

        MenuBar mb = new MenuBar();
        myFrame.setMenuBar(mb);
        Menu menu = new Menu("MENU");
        mb.add(menu);
        MenuItem[] m = new MenuItem[]{new MenuItem("Restart"), new MenuItem("Exit")};
        menu.add(m[0]);
        m[0].addActionListener(this);
        menu.add(m[1]);
        m[1].addActionListener(this);

        JButton play = new JButton("PLAY");
        play.setLocation(135, 280);
        play.setSize(130, 30);
        play.setBackground(new Color(255, 255, 204));
        play.setBorder(new LineBorder(new Color(140, 7, 7, 113)));
        play.addActionListener(this);
        cp.add(play);
        cp.add(this);

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("Memory Block Game");
        myFrame.setSize(400, 400);
        myFrame.setVisible(true);
        myFrame.setLocationRelativeTo(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Shape s = new Rectangle2D.Double(0, 0, 400, 344);
        g2.setColor(new Color(150, 91, 91, 219));
        g2.fill(s);
        g2.setColor(new Color(162, 9, 9, 113));
        g2.drawRect(45, 50, 300, 200);
        g2.fillRect(45, 50, 300, 200);
        g2.setColor(new Color(63, 21, 21, 255));
        g2.fillRect(80, 80, 235, 145);
        g2.setColor(new Color(203, 106, 106, 229));
        g2.fillRect(115, 110, 170, 90);
        g2.setColor(new Color(215, 156, 156));
        g.fillRect(135, 130, 130, 50);
        g2.setColor(new Color(255, 255, 204));
        Font font = new Font("Times New Roman", Font.BOLD, 27);
        g2.setFont(font);
        g2.drawString("MEMORY BLOCK", 78, 38);
    }

    public void actionPerformed(ActionEvent ev) {
        String command = ev.getActionCommand();
        if (!playModeOn) {
            if (command.equals("PLAY")) {
                cp.removeAll();
                cp.add(memoryGame);
                playModeOn = true;
                myFrame.setSize(400, 400);
                myFrame.setLocationRelativeTo(null);
            }
        } else {
            if (command.equals("Restart")) {
                myFrame.setVisible(false);
                new Main(new JFrame().getContentPane());
            } else if (command.equals("Exit")) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main(new JFrame().getContentPane());
            }
        });
    }
}