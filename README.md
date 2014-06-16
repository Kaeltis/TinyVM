TinyVM
======

Commands:
```
MOVE_FROM_MEM_TO_REG (Rx),Ry -- Moves the content of (Rx) to Ry
MOVE_FROM_REG_TO_MEM Rx,(Ry) -- Moves the content of Rx to (Ry)
MOVE_FROM_REG_TO_REG Rx,Ry -- Moves the content of Rx to Ry
MOVE_FROM_MEM_TO_MEM (Rx),(Ry) -- Moves the content of (Rx) to (Ry)
AND Rx,Ry -- Rx = Rx & Ry
OR Rx,Ry -- Rx = Rx | Ry
MUL Rx,Ry -- Rx = Rx * Ry
DIV Rx,Ry -- Rx = Rx / Ry
SUB Rx,Ry -- Rx = Rx - Ry
ADD Rx,Ry -- Rx = Rx + Ry
HIG Rx,Ry -- Rx = Rx > Ry
EQU Rx,Ry -- Rx = Rx == Ry
NOT Rx,Ry -- Rx = ~Ry
RET -- Pops pc from stack and returns +1
JIT -- Jumps to memory address if R0 != 0
JSR -- Jumps to memory address and pushes current pc to stack
JMP -- Jumps to memory address
PRINTREG Rx -- Prints content of Rx to the console
PRINTMEM (Rx) -- Prints content of (Rx) to the console
LOAD -- Loads value to R0
```
