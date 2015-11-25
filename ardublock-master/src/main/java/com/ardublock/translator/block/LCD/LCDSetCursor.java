
package com.ardublock.translator.block.LCD;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class LCDSetCursor extends TranslatorBlock
{

	public LCDSetCursor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		String ret = "\tlcd.setCursor(";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		/*if (!(translatorBlock instanceof NumberBlock))
		{
			throw new BlockException(this.blockId, "the Pin# of Servo must a number");
		}*/
		
		ret = ret + translatorBlock.toCode();
		
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		ret = ret + ", " + translatorBlock.toCode() + " );\n";
		return ret;
	}

}
