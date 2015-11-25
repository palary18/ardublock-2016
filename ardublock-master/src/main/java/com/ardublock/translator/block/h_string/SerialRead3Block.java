package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialRead3Block extends TranslatorBlock
{
	public SerialRead3Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		setupReadEnvironment(translator);
		

		
		String ret = codePrefix + "__serial3Read()";
		return ret;
	}
	
	private static void setupReadEnvironment(Translator t)
	{
		t.addSetupCommand("Serial3.begin(38400);");
		t.addDefinitionCommand("int __serial3Read() {\n" +
				"\tint data;\n" +
				"\tif (Serial3.available() > 0) {\n" +
				"\t\tdata = Serial3.read();\n\t}\n" +
				"\treturn data;\n}\n");
	}

}

