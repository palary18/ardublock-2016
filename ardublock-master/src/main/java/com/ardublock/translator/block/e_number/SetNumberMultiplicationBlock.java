package com.ardublock.translator.block.e_number;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.GetGlobalVarNumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class SetNumberMultiplicationBlock extends TranslatorBlock
{
	protected SetNumberMultiplicationBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableNumberBlock) && !(tb instanceof GetGlobalVarNumberBlock))
			{
			throw new BlockException(blockId, "var must be var");
		}
		
		String ret = "\t" + tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + " *= " + tb.toCode() + " ;\n";
		return ret;
	}

}
