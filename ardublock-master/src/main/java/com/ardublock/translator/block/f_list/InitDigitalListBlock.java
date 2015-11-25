package com.ardublock.translator.block.f_list;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.VariableNumberListBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class InitDigitalListBlock extends TranslatorBlock
{
	public InitDigitalListBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableNumberListBlock))
		{
			throw new BlockException(blockId, "var must be var");
		}
		
		String ret = "\t" + tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + "[ " + tb.toCode() + " ] = ";
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		ret = ret + tb.toCode() + " ;\n";
		
		return ret;
	}

}
