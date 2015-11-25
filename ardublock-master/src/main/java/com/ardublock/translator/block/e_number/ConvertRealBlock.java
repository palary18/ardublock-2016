package com.ardublock.translator.block.e_number;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class ConvertRealBlock extends TranslatorBlock
{
	public ConvertRealBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock tb;
		String ret = label;
		tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = codePrefix + ret + "( " + tb.toCode() + " )" + codeSuffix;
		return ret;
		
	}

}
