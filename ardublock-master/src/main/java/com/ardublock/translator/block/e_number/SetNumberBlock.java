package com.ardublock.translator.block.e_number;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.GetGlobalVarNumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.j_subroutine.ProcGetNumberBlock;

public class SetNumberBlock extends TranslatorBlock
{
	public SetNumberBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableNumberBlock) 
				&& !(tb instanceof GetGlobalVarNumberBlock) 
				&& !(tb instanceof ProcGetNumberBlock)
				&& !(tb instanceof VariableRealBlock))
		{
			throw new BlockException(blockId, "var must be var");
		}
		
		String compound = label;
		compound =  compound.substring(compound.length()-2, compound.length()) + " ";
		String ret = "\t" + tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + compound + tb.toCode() + ";\n";
		return ret;
	}

}
