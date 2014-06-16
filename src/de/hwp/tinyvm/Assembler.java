package de.hwp.tinyvm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Assembler {
    List<String> programList = new LinkedList<>();

    public int[] fileToOpcode(String file) throws IOException {
        int[] commandArray = new int[1024];

        int lineNr = 0;
        String line;
        String idx0;
        String idx1;

        BufferedReader reader = new BufferedReader(new FileReader(file));

        System.out.println("--- Assembler START ---");

        while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
                continue;
            }

            if (line.contains(" #")) {
                line = line.split(" #")[0];
            }

            String[] command = line.split(" ");
            if (line.contains(",")) {
                idx0 = command[1].split(",")[0];
                idx1 = command[1].split(",")[1];
                System.out.println(command[0] + " " + idx0 + " " + idx1);
                programList.add(command[0] + " " + idx0 + " " + idx1);
            } else {
                idx0 = command[1];
                idx1 = null;
                System.out.println(command[0] + " " + idx0);
                programList.add(command[0] + " " + idx0);
            }

            switch (command[0]) {
                case "MOVE_FROM_MEM_TO_REG":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5);
                    break;
                case "MOVE_FROM_REG_TO_MEM":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 1;
                    break;
                case "MOVE_FROM_REG_TO_REG":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 2;
                    break;
                case "MOVE_FROM_MEM_TO_MEM":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 3;
                    break;
                case "AND":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 4;
                    break;
                case "OR":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 5;
                    break;
                case "MUL":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 6;
                    break;
                case "DIV":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 7;
                    break;
                case "SUB":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 8;
                    break;
                case "ADD":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 9;
                    break;
                case "HIG":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 10;
                    break;
                case "EQU":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 11;
                    break;
                case "NOT":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | (Integer.parseInt(idx1) << 5) | 12;
                    break;
                case "RET":
                    commandArray[lineNr] = 0b1000000000000000 | 13;
                    break;
                case "JIT":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 5) | 14;
                    break;
                case "JSR":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 5) | 15;
                    break;
                case "JMP":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 5) | 16;
                    break;
                case "LOAD":
                    commandArray[lineNr] = Integer.parseInt(idx0);
                    break;
                case "PRINTREG":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | 17;
                    break;
                case "PRINTMEM":
                    commandArray[lineNr] = 0b1000000000000000 | (Integer.parseInt(idx0) << 10) | 18;
                    break;
                default:
                    commandArray[lineNr] = 0b1000000000000000;
                    break;
            }

            lineNr++;
        }

        System.out.println("--- Assembler END ---");

        return commandArray;

    }

    public List<String> getProgramList() {
        return programList;
    }
}
