package com.ardublock.translator.block.c_pin;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.e_number.NumberBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class PinWriteAnalogBlock extends TranslatorBlock
{
	public static final String ARDUBLOCK_ANALOG_WRITE_DEFINE = "void __ardublockAnalogWrite(int pinNumber, int value)\n{\npinMode(pinNumber, OUTPUT);\nanalogWrite(pinNumber, value);\n}\n";

	public PinWriteAnalogBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
				
		if (translatorBlock instanceof NumberBlock)
		{
			String portNum = translatorBlock.toCode();
			Long output = Long.parseLong(portNum);
			translator.addOutputPin(output);

			translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			String value = translatorBlock.toCode();
			String ret = "\tanalogWrite(" + portNum + ", " + value + ");\n";
			return ret;
		}
		else
		{
			translator.addDefinitionCommand(ARDUBLOCK_ANALOG_WRITE_DEFINE);
			String ret = "\t__ardublockAnalogWrite(";
			
			ret = ret + translatorBlock.toCode();
			ret = ret + ", ";
			translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			ret = ret + translatorBlock.toCode();
			ret = ret + ");\n";
			return ret;
		}

		
		
		/*String portNum = translatorBlock.toCode();
		Long output = Long.parseLong(portNum);
		//String setupCode = "\tpinMode(" + number + ", INPUT);";
		//translator.addSetupCommand(setupCode);
		translator.addOutputPin(output);

		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String value = translatorBlock.toCode();
		
		//String setupCode = "\tpinMode(" + portNum + ", OUTPUT);";
		//translator.addSetupCommand(setupCode);
		
		String ret = "\tanalogWrite(" + portNum + ", " + value + ");\n";
		return ret;*/
	}
	
}


