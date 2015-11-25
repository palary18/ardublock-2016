package com.ardublock.translator.block.d_time;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class DelayMicrosecondsBlock extends TranslatorBlock
{
	public DelayMicrosecondsBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "\tdelayMicroseconds( " + tb.toCode() + " );\n";
		return ret;
	}

}
