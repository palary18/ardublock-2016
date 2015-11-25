package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialReadBlock extends TranslatorBlock
{
	public SerialReadBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		setupReadEnvironment(translator);
		

		
		String ret = codePrefix + "__serialRead()";
		return ret;
	}
	
	private static void setupReadEnvironment(Translator t)
	{
		t.addSetupCommand("Serial.begin(9600);");
		t.addDefinitionCommand("int __serialRead() {\n" +
				"\tint data;\n" +
				"\tif (Serial.available() > 0) {\n" +
				"\t\tdata = Serial.read();\n\t}\n" +
				"\treturn data;\n}\n");
	}

}

