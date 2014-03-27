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
                case "MOVE_FROM_MEM_TO_REG":
                    //TODO: MOVE_FROM_MEM_TO_REG
                    break;
                case "MOVE_FROM_REG_TO_MEM":
                    //TODO: MOVE_FROM_REG_TO_MEM
                    break;
                case "MOVE_FROM_REG_TO_REG":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 2;
                    break;
                case "MOVE_FROM_MEM_TO_MEM":
                    //TODO: MOVE_FROM_MEM_TO_MEM
                    break;
                case "AND":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 4;
                    break;
                case "OR":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 5;
                    break;
                case "MUL":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 6;
                    break;
                case "DIV":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 7;
                    break;
                case "SUB":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 8;
                    break;
                case "ADD":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 9;
                    break;
                case "HIG":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 10;
                    break;
                case "EQU":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 11;
                    break;
                case "NOT":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(arguments[0]) << 10) | (Integer.parseInt(arguments[1]) << 5) | 12;
                    break;
                case "RET":
                    //TODO: RET
                    break;
                case "JIT":
                    //TODO: JIT
                    break;
                case "JSR":
                    //TODO: JSR
                    break;
                case "JMP":
                    //TODO: JMP
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
