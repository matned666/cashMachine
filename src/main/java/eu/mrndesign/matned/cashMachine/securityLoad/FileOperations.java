package eu.mrndesign.matned.cashMachine.securityLoad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperations {

    private File file;

    public FileOperations(String path) {
        file = new File(path);
    }

    public String readDataFromFile() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        return reader.nextLine();
    }

    public void writeDataToFile(String data) throws IOException {
        PrintWriter writer;
        writer = new PrintWriter(file);
        writer.println(data);
        writer.close();
    }

}