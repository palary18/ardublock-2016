package com.ardublock.translator.block.f_list;

import java.util.StringTokenizer;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class ListGetBlock extends TranslatorBlock
{
	public ListGetBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		int i=0;
		//String index = "";
		TranslatorBlock tbList = getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock tbIndex = getRequiredTranslatorBlockAtSocket(1);

		/*if (!(tbList instanceof VariableBooleanBlock || tbList instanceof GetGlobalVarBooleanListBlock))
		{
			throw new BlockException(blockId, "All items must be completed");
		}*/

		String ret = tbList.toCode() + "[ " + tbIndex.toCode() + " ]";
		
		return ret;
	}
	
}
