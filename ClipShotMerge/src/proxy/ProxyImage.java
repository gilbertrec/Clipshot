package proxy;
import java.io.InputStream;
import java.nio.file.Path;

public class ProxyImage implements Image {

  private RealImage realImage;
  private Path iconPath;

  public ProxyImage(Path iconPatch) {
    this.iconPath = iconPatch;
  }

  @Override
  public InputStream display() {
    if (realImage == null) {
      realImage = new RealImage(iconPath);
    }
    return realImage.display();
  }
  
}

