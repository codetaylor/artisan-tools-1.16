package com.codetaylor.mc.artisantools.lib;

import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FolderCompressor {

  private final Path pathToZip;
  private final Path pathToOutput;
  private final Logger logger;

  public FolderCompressor(Path pathToZip, Path pathToOutput, Logger logger) {

    this.pathToZip = pathToZip;
    this.pathToOutput = pathToOutput;
    this.logger = logger;
  }

  public boolean compress() {

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(this.pathToOutput.toFile());
      ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
      List<Path> pathList = Files.list(pathToZip).collect(Collectors.toList());

      for (Path path : pathList) {
        this.compress(path, path.getFileName().toString(), zipOutputStream);
      }

      zipOutputStream.close();
      fileOutputStream.close();
      return true;

    } catch (IOException e) {
      this.logger.error("Error compressing path: " + this.pathToZip, e);
      return false;
    }
  }

  private void compress(Path pathToZip, String fileName, ZipOutputStream zipOutputStream) throws IOException {

    if (Files.isHidden(pathToZip)) {
      return;
    }

    if (Files.isDirectory(pathToZip)) {

      if (fileName.endsWith("/")) {
        zipOutputStream.putNextEntry(new ZipEntry(fileName));

      } else {
        zipOutputStream.putNextEntry(new ZipEntry(fileName + "/"));
      }

      zipOutputStream.closeEntry();

      List<Path> pathList = Files.list(pathToZip).collect(Collectors.toList());

      for (Path path : pathList) {
        this.compress(path, fileName + "/" + path.getFileName(), zipOutputStream);
      }

      return;
    }

    FileInputStream fileInputStream = new FileInputStream(pathToZip.toFile());
    ZipEntry zipEntry = new ZipEntry(fileName);
    zipOutputStream.putNextEntry(zipEntry);
    byte[] bytes = new byte[1024];
    int length;

    while ((length = fileInputStream.read(bytes)) >= 0) {
      zipOutputStream.write(bytes, 0, length);
    }

    fileInputStream.close();
  }
}
