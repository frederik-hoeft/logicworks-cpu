# BAM assembly

BAM assembly (an acronym for _Basic Accumulator Machines_) is a 4 bit instruction set architecture developed as an abstraction of the raw hex code used in LogicWorks.
The assembly dialect comes with the following 14 instructions:

BAM instruction | Raw hex value | Description | ES1 equivalent | Total raw CPI | Pipelined CPI | Cycles
-|-|-|-|-|-|-
`and` | `0x0` | Performs a logical AND operation with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `AND` | `3` | `2` | ![and](/assets/and.png)
`or` | `0x1` | Performs a logical OR operation with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `OR`| `3` | `2` | ![or](/assets/or.png)
`not` | `0x2` | Performs a logical NOT operation with the contents of the accumulator register _A_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `NOT`| `3` | `2` | ![not](/assets/not.png)
`add` | `0x3` | Performs an arithmetic addition with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_. Contents in register _B_ will be overridden with flags after the operations completes. | `ADD`| `3` | `2` | ![add](/assets/add.png)
`rst` | `0x4` | descr. | `RESET` | `2` | `2` |
`mul` | `0x5` | Performs an arithmetic multiplication with the contents of the accumulator register _A_ and the flag register _B_. The result will be stored in register _A_ and _B_. The upper nibble will be stored in _A_ and the lower nibble in register _B_. | `MUL`| `3` | `2` | ![mul](/assets/mul.png)
N/A | `0x6` | RESERVED | `RESERVED` | N/A | N/A | N/A
N/A | `0x7` | RESERVED | `RESERVED` | N/A | N/A | N/A
`movxo <variable>` | `0x8` | MOVe register to eXtended memory Out. Moves data ... TODO | `MOV_REGRAM` | `4` | `4` | 
`movxi <variable>` | `0x9` | MOVe register from eXtended memory In. | `MOV_RAMREG` | `4` | `4` |
`swp` | `0xA` | SWaP registers. | `MOV_REGREG` | `2` | `2` |
`jmp <label>` | `0xB` | descr. | `Jump` | `4` | `4` |
`jc <label>` | `0xC` | descr. | `JumpIfCarry` | `3.5` | `3.5` |
`jz <label>` | `0xD` | descr. | `JumpIfZero` | `3.5` | `3.5` |
`jo <label>` | `0xE` | descr. | `JumpIfOverflow` | `3.5` | `3.5` |
`ret` | `0xF` | descr. | `END` | `2` | `2` |
