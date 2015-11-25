package com.ardublock.translator.block.f_list;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class ListLenghtBlock extends TranslatorBlock
{
	public ListLenghtBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		int i=0;
		//String index = "";
		TranslatorBlock tbList = getRequiredTranslatorBlockAtSocket(0);
		String kind = tbList.getTranslator().getBlock(blockId).getSocketAt(0).getKind();
		kind = kind.replace("-list", "");
		
		String ret = "(sizeof(" + tbList.toCode() + ") / sizeof(" + kind + "))";
		
		return ret;
	}
	
}
