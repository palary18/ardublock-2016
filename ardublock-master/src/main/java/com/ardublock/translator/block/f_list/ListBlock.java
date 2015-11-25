package com.ardublock.translator.block.f_list;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class ListBlock extends TranslatorBlock
{
	public ListBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		int i=0;
		TranslatorBlock tb = getTranslatorBlockAtSocket(0, "{", "");
		String ret = "";
		while (tb != null)
		{
			ret = ret + tb.toCode();
			i=i+1;
			tb = getTranslatorBlockAtSocket(i, ", ", "");
		}		
		ret = ret + "}";
		
		return ret;
	}
	
}
