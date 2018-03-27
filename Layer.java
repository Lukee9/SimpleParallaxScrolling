import java.awt.*;
import java.util.Comparator;

public class Layer {
    public Integer z;
    public Image image;
    private int x = 0;
    private int y = 0;
    private double rate;
    private double difference = 0.0;
    private int loopDifference;

    public Layer(int zIndex, Image layerImage, int xCoord, int yCoord, double scrollRate, int loopAfter) {
        z = zIndex;
        x = xCoord;
        y = yCoord;
        image = layerImage;
        rate = -1 * scrollRate; //Since most games go from left to right
        loopDifference = loopAfter;
    }

    //Return x coordinate after changing it based on scroll rate
    public int getDifferenceX() {
        difference += rate;
        shouldRestart();
        return (int) (x + difference);
    }
    public int getX(){
        return (int) (x + difference);
    }
    public int getY() {
        return y;
    }

    //Should repeat the image so it can loop forever
    private void shouldRestart() {
        if (Math.abs(difference) > loopDifference) {
            difference = 0;
        }
    }

    //Sort by z index
    public static Comparator sortByIndex() {
        return new Comparator<Layer>() {
            @Override
            public int compare(Layer o1, Layer o2) {
                return o1.z.compareTo(o2.z);
            }
        };
    }
}
