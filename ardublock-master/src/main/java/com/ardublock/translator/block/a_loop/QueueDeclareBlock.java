package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class QueueDeclareBlock extends TranslatorBlock
{
	public QueueDeclareBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		translator.addQueue(label);
		TranslatorBlock tb = getRequiredTranslatorBlockAtSocket(0);
		String ret = "";
		if (tb != null)
		{
			ret = ret + tb.toCode();
		}
		return ret;
	}
}
