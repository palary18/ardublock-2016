package com.ardublock.translator.block.c_pin;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class PinReadAnalogBlock extends TranslatorBlock
{
	//public static final String ARDUBLOCK_ANALOG_READ_DEFINE = "void __ardublockAnalogRead(int pinNumber)\n{\npinMode(pinNumber, INPUT);\nanalogRead(pinNumber);\n}\n";

	public PinReadAnalogBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		//if (translatorBlock instanceof NumberBlock)
		//{
			String ret = "analogRead(";
			ret = ret + translatorBlock.toCode();
			ret = ret + ")";
			return codePrefix + ret + codeSuffix;
		//}
		//else
		//{
		//	throw new BlockException(blockId, "analog pin# must be a number");
			
/*			translator.addDefinitionCommand(ARDUBLOCK_ANALOG_READ_DEFINE);
			String ret = "\t__ardublockAnalogRead(";
			
			ret = ret + translatorBlock.toCode();
			ret = ret + ", ";
			translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			ret = ret + translatorBlock.toCode();
			ret = ret + ");\n";
			return ret;*/
		//}
	}

}
