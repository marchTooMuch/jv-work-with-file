package core.basesyntax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        char[] array = new char[9999];
        int supply = 0;
        int buy = 0;
        try (FileReader input = new FileReader(fromFileName);) {
            input.read(array);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        String textFromFile = new String(array);
        String[] strings = textFromFile.split("\n");
        for (String line: strings) {
            String[] strArr = line.split(",");
            switch (strArr[0]) {
                case "supply": supply += Integer.parseInt(strArr[1].replaceAll("\r",""));
                    break;
                case "buy": buy += Integer.parseInt(strArr[1].replaceAll("\r",""));
                    break;
                default: ;
            }
        }
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cant create file" + fromFileName, e);
        }

        try (FileWriter in = new FileWriter(file)) {
            in.write("supply," + supply + System.lineSeparator());
            in.write("buy," + buy + System.lineSeparator());
            int result = supply - buy;
            in.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cant write from file " + fromFileName, e);
        }
    }
}
