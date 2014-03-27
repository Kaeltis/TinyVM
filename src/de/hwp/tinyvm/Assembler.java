package de.hwp.tinyvm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assembler {

    public int[] fileToOpcode(String file) throws IOException {
        int[] commandArray = new int[countLines(file)];
        int lineNr = 0;
        String line;

        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            String[] command = line.split(" ");
            String[] arguments = command[1].split(",");

            System.out.println(command[0] + " " + arguments[0] + " " + arguments[1]);

            switch (command[0]) {
                case "MUL":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 6;
                    break;
                default:
                    commandArray[lineNr] = 0b1000000000000000;
                    break;
            }

            lineNr++;
        }

        return commandArray;

    }

    private int countLines(String file) throws IOException {
        int lines = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

}
