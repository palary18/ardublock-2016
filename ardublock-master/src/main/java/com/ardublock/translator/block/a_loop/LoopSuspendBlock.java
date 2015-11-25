package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class LoopSuspendBlock extends TranslatorBlock
{

	public LoopSuspendBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		String ret = "\tvTaskSuspend( ";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		if (translatorBlock != null) {
			String name = translatorBlock.toCode().toString();
			if (name.equals("#All tasks")) {
				ret = "\tvTaskSuspendAll( ";
			}
			else if (name.equals("#All tasks and INTs")) {
				name = name.replaceAll("\"", "");
				ret = "\ttaskENTER_CRITICAL( ";
			}
			else {
				name = name.replaceAll("\"", "");
				ret = ret + "x" + name + "Handle";
			}
		}
		
		ret = ret + " );\n";
		return ret;
	}

}
