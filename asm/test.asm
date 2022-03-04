; Instructions
;
; 0		and 	= AND
; 1		or		= OR
; 2		not		= NOT
; 3		add		= ADD
; 4		rst		= RESET
; 5		mul		= MUL
; 6		N/A 	= RESERVED
; 7		N/A 	= RESERVED
; 8		movxo	= MOV_REGRAM
; 9		movxi 	= MOV_RAMREG
; A		swp 	= MOV_REGREG
; B		jmp		= Jump
; C		jc		= JumpIfCarry
; D		jz		= JumpIfZero
; E		jo		= JumpIfOverflow
; F		ret 	= END

; data region for global variables
.data:
    i   DW  0
    one   DW  1
    length  DW  5  
    minus1    DW  -1

; this is a simple for loop example. It *should* count from 0 to 5 and then exit :)

; for (int i = 0; i != 5; i++) 
; { }

; executable region
.text:              ; main entry point
label forLoop:      ; for-loop header 
    movxi i         ; load i
    swp             ; check if i != length by subtracting and checking for 0
    movxi minus1    
    mul
    movxi length
    add
    jz loopEnd      ; i == length yielded true -> break out of loop
    movxi i         ; i was < length -> increment i by 1
    swp
    movxi one
    add
    movxo i         ; store i
    jmp forLoop     ; jump to for loop header :)
label loopEnd:
    ret             ; return