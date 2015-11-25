package com.ardublock.translator.block.j_subroutine;

import com.ardublock.translator.Translator;
//import edu.mit.blocks.codeblocks.Block;

import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class ProcReturnBlock extends TranslatorBlock
{
	public ProcReturnBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{

		String ret = "\treturn ";
		String isList = translator.getBlock(blockId).getSocketAt(0).getKind();
		if (isList.contains("list")) ret = ret + "*";
		TranslatorBlock tb = getRequiredTranslatorBlockAtSocket(0, ret, ";\n");
		ret = tb.toCode();
		/*if (tb != null) {
			return ret;
		}
		else {
			return "";
		}*/
		return ret;
		
	}
}
