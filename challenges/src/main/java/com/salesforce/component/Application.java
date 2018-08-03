package com.salesforce.component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Application {
  private static Application instance = null;
  private ComponentInstaller installer;

  private Application() {
    this.installer = new ComponentInstallerImpl();
  }

  public static Application getInstance() {
    if (instance == null) {
      instance = new Application();
    }
    return instance;
  }

  public void install(String fileName) {
    Scanner scanner = null;
    try {
      FileReader fileReader = new FileReader(fileName);
      scanner = new Scanner(fileReader);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.isEmpty() || line.equals("END")) {
          return;
        }
        processCommands(line);
      }
      scanner.close();
    } catch (FileNotFoundException fe) {
      System.out.println("Unable to read File: " + fileName);
    } finally {
      scanner.close();
    }
  }

  private void processCommands(String command) {
    if (command == null || command.isEmpty()) {
      return;
    }
    System.out.println(command);
    String[] temp = command.split("\\s+");
    if (command.startsWith("DEPEND")) {
      if (temp.length < 3) {
        System.out.println("Improper command");
        return;
      }

      for (int i = 2; i < temp.length; i++) {
        installer.makeDependency(temp[1], temp[i]);
      }
    } else if (command.startsWith("INSTALL")) {
      if (temp.length < 2) {
        System.out.println("Improper command");
        return;
      }
      installer.install(temp[1]);
    } else if (command.startsWith("LIST")) {
      installer.listInstalledComponents();
    } else if (command.startsWith("REMOVE")) {
      installer.remove(temp[1]);
    }
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return getInstance();
  }
}
