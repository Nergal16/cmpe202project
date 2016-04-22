import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundImage extends JPanel {

    /**
     *
     */
//	private static final long serialVersionUID = 1L;
    private Image backgroundImage;

    // Some code to initialize the background image.
    // Here, we use the constructor to load the image. This
    // can vary depending on the use case of the panel.
    public BackgroundImage(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File(fileName));
        //  backgroundImage = Toolkit.getDefaultToolkit().createImage(fileName);

    }

}
