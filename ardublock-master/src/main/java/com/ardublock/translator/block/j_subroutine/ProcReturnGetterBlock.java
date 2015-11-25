package com.ardublock.translator.block.j_subroutine;

import com.ardublock.translator.Translator;
//import edu.mit.blocks.codeblocks.Block;
import com.ardublock.translator.block.TranslatorBlock;

//import com.ardublock.translator.block.exception.SocketNullException;

public class ProcReturnGetterBlock extends TranslatorBlock
{
	public ProcReturnGetterBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		return codePrefix + label + codeSuffix;
	}

}
