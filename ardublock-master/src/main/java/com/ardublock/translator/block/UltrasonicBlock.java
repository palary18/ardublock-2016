package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;

public class UltrasonicBlock extends TranslatorBlock
{
	protected UltrasonicBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	private final static String ultraSonicFunction = "int ardublockUltrasonicSensor(" +
			"int trigPin, int echoPin) {" +
			"\n\tint duration;" +
			"\n\tpinMode(trigPin, OUTPUT);" +
			"\n\tdigitalWrite(trigPin, LOW);" +
			"\n\tdelayMicroseconds(2);" +
			"\n\tdigitalWrite(trigPin, HIGH);" +
			"\n\tdelayMicroseconds(20);" +
			"\n\tdigitalWrite(trigPin, LOW);" +
			"\n\tpinMode(echoPin, INPUT);" +
			"\n\tduration = pulseIn(echoPin, HIGH);" +
			"\n\tduration = duration / 59;" +
			"\n\treturn duration;\n}" +
			"\n";
	
	public String toCode() throws SocketNullException
	{
		String trigPin;
		String echoPin;
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		trigPin = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		echoPin = translatorBlock.toCode();
		
		//translator.addSetupCommand("digitalWrite( " + trigPin + " , LOW );");
		
		translator.addDefinitionCommand(ultraSonicFunction);
		
		String ret = "ardublockUltrasonicSensor( " + trigPin + " , " + echoPin + " )";
		

		return codePrefix + ret + codeSuffix;
	}
	
}
