package com.ardublock.translator.block.f_list;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class InitListBlock extends TranslatorBlock
{
	public InitListBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		TranslatorBlock tbVar = getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock tbSize = getTranslatorBlockAtSocket(1);
		TranslatorBlock tbValue = getTranslatorBlockAtSocket(2);

		String kind = tbVar.getTranslator().getBlock(tbVar.blockId).getPlugKind();
		kind = kind.replace("-list", " ");
		kind = kind.replace("number", "int");
		
		String size = (tbSize != null) ? " [ " + tbSize.toCode() + " ]" : " [ ]";
		String val = (tbValue != null) ? " = " + tbValue.toCode() : "";
		
		if (tbSize == null && tbValue == null)
		{
			throw new BlockException(blockId, "Give a size and / or a value");
		}
		
		String ret = kind + tbVar.toCode() + size + val + ";\n";
		
/*		if (!(tbVar instanceof VariableBooleanListBlock || tbVar instanceof VariableNumberListBlock || tbVar instanceof VariableStringListBlock))
		{
			translator.addSetupCommand(ret);
			//throw new BlockException(blockId, "Use a local var");
			return "";
		}*/
		
		return "\t" + ret;
	}

}
