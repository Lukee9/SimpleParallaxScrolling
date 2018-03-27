import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
    }
}

class GameWindow extends JFrame {
    public GameWindow() {
        setSize(1600, 900);
        setTitle("Parallax Example");
        setLayout(new CardLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        add(new GamePanel());
        repaint();
    }
}

//Images provided by https://opengameart.org/content/3-parallax-backgrounds
class GamePanel extends JPanel {
    ArrayList<Layer> layers;

    public GamePanel() {
        setSize(1600, 900);
        int width = getWidth();
        Layer next;
        layers = new ArrayList<>();
        next = new Layer(1, new ImageIcon("assets/layer_01.png").getImage(), 0, 0, 0, width * 2);
        layers.add(next);
        next = new Layer(2, new ImageIcon("assets/layer_02.png").getImage(), 0, 0, 0.025, width * 2);
        layers.add(next);
        next = new Layer(3, new ImageIcon("assets/layer_03.png").getImage(), 0, 0, 0.05, width * 2);
        layers.add(next);
        next = new Layer(4, new ImageIcon("assets/layer_04.png").getImage(), 0, 0, 0.1, width * 2);
        layers.add(next);
        next = new Layer(5, new ImageIcon("assets/layer_05.png").getImage(), 0, 0, 0.2, width * 2);
        layers.add(next);
        layers.sort(Layer.sortByIndex());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Layer layer : layers) {
            //Draw the image
            g2d.drawImage(layer.image, layer.getDifferenceX(), layer.getY(), this);
            //Draw the flipped image and then the original image afterwards so the animation can seamlessly loop
            g2d.drawImage(layer.image, layer.getX() + this.getWidth() * 2, layer.getY(), -1600, 900, this); //Multiplied by 2 instead of 1 here to fix negative width
            g2d.drawImage(layer.image, layer.getX() + this.getWidth() * 2, layer.getY(), 1600, 900, this);
        }
        repaint();
    }
}