# BAM assembly

BAM assembly (an acronym for _Basic Accumulator Machines_) is a 4 bit instruction set architecture developed as an abstraction of the raw hex code used in LogicWorks. It offers support for comments, variables and jump labels.

## Overview

Every BAM program consists of two memory regions: the `.data` region containing all variables and constants and the `.text` or `.code` region hosting the executable code itself. Regions are declared using the corresponding keyword (i.e. `.data`) followed by a `:` character. Even though the `.data` region _must_ preceed the `.text` region in BAM assembly code after assembling the data will be stored after the last executable instruction of the program (usually the `ret` statement).

### Variables and constants

Variables and constants are declared withing the `.data` region using the `DW` (declare word) keyword. The syntax follows a simple approach: `<variable name> DW <initial value>` where `<variable name>` may be any alphanumeric word which may contain underscores (`_`) but must not start with a number. The `<initial value>` may be any signed 4 bit value in it's decimal representation. All instructions must be seperated by one or several whitespace characters including tabs and spaces or new lines. Variables are case sensitive.

### Comments

The BAM assembly dialect offers support for single line comments using the reserved `;` character. Everything starting with a `;` until the end of the line will be considered a comment and will be ignored in the output.

### Jump labels

BAM uses labels to indicate the target of a conditional or unconditional jump. These jump labels will be automatically translated in the corresponding 8 bit address once the code gets assembled. Labels can only be declared in the `.text` section and _must_ be unique. They can however be referenced multiple times in the program. The syntax to declare a label is as follows: `label <label name>:` where `<label name>` may be any alphanumeric word which may contain underscores (`_`) but must not start with a number. As a label is a zero-size instruction after assembling to hex code the next instruction after the jump label will be the first instruction executed after the jump.

## Instruction set

BAM comes with the following 14 instructions:

BAM instruction | Raw hex value | Description | ES1 equivalent | Total raw CPI | Pipelined CPI | Cycles
-|-|-|-|-|-|-
`and` | `0x0` | Performs a logical AND operation with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `AND` | `3` | `2` | ![and](/assets/and.png)
`or` | `0x1` | Performs a logical OR operation with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `OR`| `3` | `2` | ![or](/assets/or.png)
`not` | `0x2` | Performs a logical NOT operation with the contents of the accumulator register _A_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `NOT`| `3` | `2` | ![not](/assets/not.png)
`add` | `0x3` | Performs an arithmetic addition with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `ADD`| `3` | `2` | ![add](/assets/add.png)
`rst` | `0x4` | Resets the instruction pointer to `0` and continues execution from the first instruction in the executable region of the program. It does however _not_ re-initialize any variables to their initial values. | `RESET` | `2` | `2` | ![rst](/assets/rst.png)
`mul` | `0x5` | Performs an arithmetic multiplication with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_ and _B_. The upper nibble will be stored in _A_ and the lower nibble in register _B_. | `MUL`| `3` | `2` | ![mul](/assets/mul.png)
N/A | `0x6` | RESERVED | `RESERVED` | N/A | N/A | N/A
N/A | `0x7` | RESERVED | `RESERVED` | N/A | N/A | N/A
`movxo <variable>` | `0x8` | `movxo` stands for _"MOVe register to eXternal memory Out"_. The `movxo` instruction transfers data from the accumulator to external data memory. The external data memory must be specified by a variable declared in the data section. | `MOV_REGRAM` | `4` | `4` | ![mov](/assets/mov.png)
`movxi <variable>` | `0x9` | `movxi` stands for _"MOVe register from eXternal memory In"_. The `movxi` instruction transfers data from external data memory to the accumulator. The external data memory must be specified by a variable declared in the data section. | `MOV_RAMREG` | `4` | `4` | ![mov](/assets/mov.png)
`swp` | `0xA` | `swp` or SWaP exchanges the content of the accumulator (register _A_) and the flag register (register _B_). A NOP will be performed if an ALU operation is still doing it's write back. | `MOV_REGREG` | `2.5` | `2.5` | ![swp](/assets/swp.png)
`jmp <label>` | `0xB` | Jumps to the specified _label_ and continues execution from there. | `Jump` | `4` | `4` | ![jmp](/assets/jmp-nocond.png)
`jc <label>` | `0xC` | Jumps to the specified _label_ if the `Carry` flag is set (Bit 0 in register _B_) and continues execution from there. | `JumpIfCarry` | `3.5` | `3.5` | ![jmp](/assets/jmp.png)
`jz <label>` | `0xD` | Jumps to the specified _label_ if the `Zero` flag is set (Bit 3 in register _B_) and continues execution from there. | `JumpIfZero` | `3.5` | `3.5` | ![jmp](/assets/jmp.png)
`jo <label>` | `0xE` | Jumps to the specified _label_ if the `Overflow` flag is set (Bit 1 in register _B_) and continues execution from there. | `JumpIfOverflow` | `3.5` | `3.5` | ![jmp](/assets/jmp.png)
`ret` | `0xF` | Returns from the program flow and stops processing instructions. | `END` | `2` | `2` | ![ret](/assets/ret.png)
## Examples

```asm
; data region for global variables
.data:
    i   DW  0
    one   DW  1
    length  DW  5  
    minus1    DW  -1

; this is a simple for loop example. It will count from 0 to 5 and then exit :)

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
```