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
NOT Rx,Ry -- Rx = !Ry
RET (N/A)
JIT (N/A)
JSR (N/A)
JMP (N/A)
PRINTREG Rx -- Print content of Rx
PRINTMEM (Rx) -- Print content of (Rx)
ORG (Rx) -- Set memory address to (Rx)
DATA Value -- Sets content of current address to Value
```
