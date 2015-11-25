
package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.common.Common;
import com.ardublock.translator.block.e_number.NumberBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class ServoBlock extends TranslatorBlock
{

	protected ServoBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(translatorBlock instanceof NumberBlock))
		{
			throw new BlockException(this.blockId, "the Pin# of Servo must a number");
		}
		
		
		NumberBlock pinNumberBlock = (NumberBlock)translatorBlock;
		String pinNumber = pinNumberBlock.toCode();
		String servoName = "servo_pin_" + pinNumber;
		
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String ret = "\t" + servoName + ".write( " + translatorBlock.toCode() + " );\n";
		Common.isLibraryExist(blockId, translator, "Servo.h");
		translator.addHeaderFile("Servo.h");
		translator.addDefinitionCommand("Servo " + servoName + ";");
		translator.addSetupCommand(servoName + ".attach(" + pinNumber + ");");
		return ret;
	}

}
