package de.hwp.tinyvm;

public enum Commands {
    MOVE_FROM_MEM_TO_REG(0),
    MOVE_FROM_REG_TO_MEM(1),
    MOVE_FROM_REG_TO_REG(2),
    MOVE_FROM_MEM_TO_MEM(3),
    AND(4),
    OR(5),
    MUL(6),
    DIV(7),
    SUB(8),
    ADD(9),
    HIG(10),
    EQU(11),
    NOT(12),
    RET(13),
    JIT(14),
    JSR(15),
    JMP(16),
    PRINTREG(17),
    PRINTMEM(18);

    int value;

    Commands(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
