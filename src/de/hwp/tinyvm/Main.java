package de.hwp.tinyvm;

import java.io.IOException;
import java.util.Stack;

public class Main {

    public static final boolean DEBUG = false;

    public static final int CMD_MASK = 0b0000000000011111;
    public static final int LOAD_MASK = 0b1000000000000000;
    public static final int VALUE_MASK = 0b0111111111111111;
    public static final int IDX0_MASK = 0b0111110000000000;
    public static final int IDX1_MASK = 0b0000001111100000;
    public static final int JMP_MASK = 0b0111111111100000;

    public static int[] memory;
    public static int[] reg = new int[32];
    public static int pc = 0;
    public static Stack<Integer> stack = new Stack<>();
    public static int[] profiler = new int[1024];

    public static void main(String[] args) {

        Assembler myAssembler = new Assembler();
        try {
            memory = myAssembler.fileToOpcode("program.asm");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- TinyVM START ---");
        Main myVM = new Main();
        while (pc < memory.length) {
            myVM.doCommand();
        }
        System.out.println("--- TinyVM END ---");

        System.out.println("\n--- Profiler START ---");
        int count = 0;
        for (String line : myAssembler.getProgramList()) {
            System.out.print(line + " -- ");
            System.out.println(profiler[count]);
            count++;
        }
        System.out.println("--- Profiler END ---");
    }

    public void doCommand() {
        int opcode = memory[pc];

        if (opcode == 0) {
            pc++;
            return;
        }

        profiler[pc]++;

        int cmd = opcode & CMD_MASK;
        int idx1 = (opcode & IDX1_MASK) >> 5;
        int idx0 = (opcode & IDX0_MASK) >> 10;
        int load = (opcode & LOAD_MASK) >> 15;
        int jump = (opcode & JMP_MASK) >> 5;

        // System.out.println("BEFORE CMD: " + cmd + " | IDX0: " + reg[idx0]
        // + " | IDX1:" + reg[idx1]);

        if (load == 0) { // Commands.LOAD

            int value = (opcode & VALUE_MASK);
            reg[0] = value;
            pc++;

            if (DEBUG)
                System.out.println("LOAD " + value);

        } else {

            if (DEBUG)
                System.out.println(Commands.values()[cmd] + " " + idx0 + "," + idx1);

            switch (cmd) {
                case 0: // Commands.MOVE_FROM_MEM_TO_REG
                    reg[idx1] = memory[reg[idx0]];
                    pc++;
                    break;
                case 1: // Commands.MOVE_FROM_REG_TO_MEM
                    memory[reg[idx1]] = reg[idx0];
                    pc++;
                    break;
                case 2: // Commands.MOVE_FROM_REG_TO_REG
                    reg[idx1] = reg[idx0];
                    pc++;
                    break;
                case 3: // Commands.MOVE_FROM_MEM_TO_MEM
                    memory[reg[idx1]] = memory[reg[idx0]];
                    pc++;
                    break;
                case 4: // Commands.AND
                    reg[idx0] = reg[idx0] & reg[idx1];
                    pc++;
                    break;
                case 5: // Commands.OR
                    reg[idx0] = reg[idx0] | reg[idx1];
                    pc++;
                    break;
                case 6: // Commands.MUL
                    reg[idx0] = reg[idx0] * reg[idx1];
                    pc++;
                    break;
                case 7: // Commands.DIV
                    reg[idx0] = reg[idx0] / reg[idx1];
                    pc++;
                    break;
                case 8: // Commands.SUB
                    reg[idx0] = reg[idx0] - reg[idx1];
                    pc++;
                    break;
                case 9: // Commands.ADD
                    reg[idx0] = reg[idx0] + reg[idx1];
                    pc++;
                    break;
                case 10: // Commands.HIG
                    reg[idx0] = (reg[idx0] > reg[idx1]) ? 1 : 0;
                    pc++;
                    break;
                case 11: // Commands.EQU
                    reg[idx0] = (reg[idx0] == reg[idx1]) ? 1 : 0;
                    pc++;
                    break;
                case 12: // Commands.NOT
                    reg[idx0] = ~reg[idx1];
                    pc++;
                    break;
                case 13: // Commands.RET
                    pc = stack.pop() + 1;
                    break;
                case 14: // Commands.JIT
                    if (reg[0] != 0) {
                        pc = jump;
                    } else {
                        pc++;
                    }
                    break;
                case 15: // Commands.JSR
                    stack.push(pc);
                    pc = jump;
                    break;
                case 16: // Commands.JMP
                    pc = jump;
                    break;
                case 17: // Commands.PRINTREG
                    System.out.println("REGISTER:" + idx0 + " WERT:" + reg[idx0]);
                    pc++;
                    break;
                case 18: // Commands.PRINTMEM
                    System.out.println("MEMORY:" + reg[idx0] + " WERT:" + memory[reg[idx0]]);
                    pc++;
                    break;
                default: // Command not Found
                    pc++;
                    break;
            }

        }

        // System.out.println("AFTER CMD: " + cmd + " | IDX0: " + reg[idx0] +
        // " | IDX1:" + reg[idx1] + "\n");

    }
}
