; BAM assembly program to calculate C = A XOR B

; data region for global variables
.data:
    A   DW  -1
    B   DW  -1
    C   DW  0

; executable region
.text:
    movxi A     
    not
    swp
    movxi B
    and         ; C = ~A & B
    movxo C
    movxi B
    not
    swp
    movxi A
    and         ; A & ~B
    swp
    movxi C
    or
    movxo C     ; C = C | (A & ~B)
    ret