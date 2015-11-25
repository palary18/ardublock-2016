package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialRead2Block extends TranslatorBlock
{
	public SerialRead2Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		setupReadEnvironment(translator);
		

		
		String ret = codePrefix + "__serial2Read()";
		return ret;
	}
	
	private static void setupReadEnvironment(Translator t)
	{
		t.addSetupCommand("Serial2.begin(38400);");
		t.addDefinitionCommand("int __serial2Read() {\n" +
				"\tint data;\n" +
				"\tif (Serial2.available() > 0) {\n" +
				"\t\tdata = Serial2.read();\n\t}\n" +
				"\treturn data;\n}\n");
	}

}

