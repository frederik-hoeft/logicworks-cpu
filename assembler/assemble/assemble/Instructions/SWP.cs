﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ES1Assembler.Instructions;

internal class SWP : SimpleOp
{
    protected override OpCode OpCode => OpCode.SWP;
}
