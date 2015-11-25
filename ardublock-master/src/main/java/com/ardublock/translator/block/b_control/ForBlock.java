package com.ardublock.translator.block.b_control;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.GetGlobalVarNumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.e_number.VariableNumberBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class ForBlock extends TranslatorBlock
{
	
	public ForBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	public String toCode() throws SocketNullException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariableNumberBlock) && !(tb instanceof GetGlobalVarNumberBlock))
		{
			throw new BlockException(blockId, "digital var must be digital var");
		}
				
		String varName = tb.toCode();		
		//translator.addDefinitionCommand("int " + varName + ";");
		//translator.addNumberLocalVariable(tb.label, varName);
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String ret = "\tfor (int " + varName + "=" + tb.toCode() + "; ";
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		ret = ret + varName + "<=" + tb.toCode() + "; ";
		tb = this.getRequiredTranslatorBlockAtSocket(3);
		ret = ret + varName + "=" + varName + "+" + tb.toCode() + ") {\n";
		
		
		tb = getTranslatorBlockAtSocket(4);
		while (tb != null)
		{
			ret = ret + tb.toCode();
			tb = tb.nextTranslatorBlock();
		}
		
		ret = ret.replace("\n", "\n\t");
		ret = ret + "}\n";
		return ret;
	}

}
	