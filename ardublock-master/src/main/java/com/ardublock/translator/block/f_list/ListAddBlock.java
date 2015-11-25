package com.ardublock.translator.block.f_list;

import java.util.StringTokenizer;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.GetGlobalVarBooleanListBlock;
import com.ardublock.translator.block.GetGlobalVarNumberListBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.VariableBooleanListBlock;
import com.ardublock.translator.block.VariableNumberListBlock;
import com.ardublock.translator.block.VariableStringListBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class ListAddBlock extends TranslatorBlock
{
	public ListAddBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		TranslatorBlock tbList = getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock tbIndex = getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock tbValue = getRequiredTranslatorBlockAtSocket(2);

		if (!(tbList instanceof VariableBooleanListBlock 
				|| tbList instanceof VariableNumberListBlock
				|| tbList instanceof VariableStringListBlock
				|| tbList instanceof GetGlobalVarBooleanListBlock
				|| tbList instanceof GetGlobalVarNumberListBlock
				))
		{
			throw new BlockException(blockId, "All items must be completed");
		}

		String ret = "\t" + tbList.toCode() + "[ " + tbIndex.toCode() + " ] = " + tbValue.toCode() + ";\n";
		
		return ret;
	}
	
}
