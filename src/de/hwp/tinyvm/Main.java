package de.hwp.tinyvm;

public class Main {

    public static final int CMD_MASK = 0b0000000000011111;
    public static final int LOAD_MASK = 0b1000000000000000;
    public static final int IDX0_MASK = 0b0111110000000000;
    public static final int IDX1_MASK = 0b0000001111100000;
    public static final int JMP_MASK = 0b1111111111100000;

    public static int[] memory = new int[32];
    public static int[] reg = new int[32];
    public static int pc = 0;

    public static void main(String[] args) {
        memory[0] = 0b1001000001100110;
        memory[1] = 0b1001000001100110;
        memory[2] = 0b1001000001100110;

        reg[4] = 8;
        reg[3] = 6;

        Main myVM = new Main();
        while (pc < reg.length) {
            myVM.doCommand();
        }

        System.out.println("Done");
    }

    public void doCommand() {
        int opcode = memory[pc];

        if (opcode == 0) {
            pc++;
            return;
        }

        int cmd = opcode & CMD_MASK;
        int idx0 = (opcode & IDX0_MASK) >> 10;
        int idx1 = (opcode & IDX1_MASK) >> 5;
        int load = (opcode & LOAD_MASK) >> 15;

        System.out.println("BEFORE CMD: " + cmd + " | IDX0: " + reg[idx0] + " | IDX1:" + reg[idx1]);

        switch (cmd) {
            case 6: //Commands.MUL
                reg[idx0] = reg[idx0] * reg[idx1];
                pc++;
                break;
            default: //Command not Found
                pc++;
                break;
        }

        System.out.println("AFTER CMD: " + cmd + " | IDX0: " + reg[idx0] + " | IDX1:" + reg[idx1] + "\n");

    }

}