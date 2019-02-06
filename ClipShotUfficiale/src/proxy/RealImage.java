package proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

public class RealImage implements Image {

  private Path iconPath;
  private File file;

  public RealImage(Path iconPath) {
    this.iconPath = iconPath;
    loadFromDisk(iconPath);
  }

  @Override
  public InputStream display() {
    InputStream in = null;
    try {
      in = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return in;
  }

  private File loadFromDisk(Path iconPath) {

    if (iconPath.toFile().exists()) {
      file = iconPath.toFile();
    }
    return file;

  }
}