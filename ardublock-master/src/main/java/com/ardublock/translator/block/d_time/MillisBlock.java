package com.ardublock.translator.block.d_time;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class MillisBlock extends TranslatorBlock
{
	public MillisBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		String ret = "millis()";
		return codePrefix + ret + codeSuffix;
	}
	
}
