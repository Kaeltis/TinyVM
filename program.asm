LOAD 123 # ZAHL ZUR ÜBERPRÜFUNG

MOVE_FROM_REG_TO_REG 0,4 # ZüZ nach Reg4 kopieren (Divisor)
MOVE_FROM_REG_TO_REG 0,10 # ZüZ nach Reg10 kopieren (Zu überprüfende Zahl)

LOAD 100 # Speicherstelle
MOVE_FROM_REG_TO_REG 0,6 # 100 nach Reg6 Kopieren (Speicherstelle)

LOAD 1 # 1 in Reg0 laden
MOVE_FROM_REG_TO_REG 0,5 # 1 nach Reg5 kopieren (Divisor immer -1)

MOVE_FROM_REG_TO_REG 10,1 # ZüZ nach Reg1 kopieren (Aktuelle Zahl)
MOVE_FROM_REG_TO_REG 10,2 # ZüZ nach Reg2 kopieren (Divident / Rest)
MOVE_FROM_REG_TO_REG 10,3 # ZüZ nach Reg3 kopieren (Wert)

DIV 3,4 # Reg3 = Wert/Divisor
MUL 3,4 # Reg3 = Wert*Divisor
SUB 2,3 # Reg2 = Rest - Wert

MOVE_FROM_REG_TO_REG 4,7 # Divisor nach Reg7 kopieren (backup)
SUB 4,5 # Reg3 = Reg4-Reg5 # Divisor um 1 verringern

MOVE_FROM_REG_TO_REG 2,0 # Zur überprüfung in Reg0 schieben

JIT 7 # Falls kein Teiler, wiederholen, sonst

MOVE_FROM_REG_TO_MEM 7,6 # Divisor an aktuelle Speicherstelle kopieren
PRINTMEM 6
ADD 6,5 # Speicherstelle um 1 erhöhen

MOVE_FROM_REG_TO_REG 4,0 # Divisor nach Reg0 kopieren
JIT 7 # Wiederholen, falls Divisor noch nicht 0
