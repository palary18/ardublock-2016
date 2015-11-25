package com.ardublock.translator.block.b_control;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class BreakBlock extends TranslatorBlock
{

	public BreakBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}
	
	public String toCode() throws SocketNullException
	{
		String ret = "	Break;\n";
		return ret;
	}

}
