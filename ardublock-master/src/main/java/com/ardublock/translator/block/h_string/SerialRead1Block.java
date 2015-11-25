package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialRead1Block extends TranslatorBlock
{
	public SerialRead1Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		setupReadEnvironment(translator);
		

		
		String ret = codePrefix + "__serial1Read()";
		return ret;
	}
	
	private static void setupReadEnvironment(Translator t)
	{
		t.addSetupCommand("Serial1.begin(38400);");
		t.addDefinitionCommand("int __serial1Read() {\n" +
				"\tint data;\n" +
				"\tif (Serial1.available() > 0) {\n" +
				"\t\tdata = Serial1.read();\n\t}\n" +
				"\treturn data;\n}\n");
	}

}

