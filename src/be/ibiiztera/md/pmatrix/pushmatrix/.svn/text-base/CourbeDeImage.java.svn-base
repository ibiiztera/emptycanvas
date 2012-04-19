package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Set;

public class CourbeDeImage {

    private BufferedImage image;
    private Hashtable<Point, Color> points;

    public CourbeDeImage(BufferedImage image) {
        super();
        this.image = image;
        this.points = new Hashtable<Point, Color>();

        anayliserImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Hashtable<Point, Color> getPoints() {
        return points;
    }

    public void setPoints(Hashtable<Point, Color> points) {
        this.points = points;
    }

    public Set<Point> getPointsList() {
        return points.keySet();
    }

    public void anayliserImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            int x0 = i;
            int y0 = -1;
            int y1 = -1;
            for (int j = 0; j < image.getHeight(); j++) {
                if (!new Color(image.getRGB(i, j)).equals(Color.white)) {
                    y0 = y1;
                    y1 = j;
                    if (y0 == -1 || (y1 > y0 + 1)) {
                        points.put(new Point(i, j), new Color(image.getRGB(i, j)));
                        break;
                    }
                }

            }
        }
    }

    public void classerPoints() {
    }
}
