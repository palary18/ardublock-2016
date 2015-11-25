package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;

public class WireReadBlock extends TranslatorBlock
{
	protected WireReadBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		
		setupWireEnvironment(translator);
		String ret = "__ardublockI2cReadData( ";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = getRequiredTranslatorBlockAtSocket(1);
		ret = ret + tb.toCode();
		ret = ret + " )";
		return codePrefix + ret + codeSuffix;
		
		
		
	}
	
	public static void setupWireEnvironment(Translator t)
	{
		t.addHeaderFile("Wire.h");
		t.addDefinitionCommand("boolean __ardublockIsI2cReadOk;\n" +
				"\n" +
				"void __ardublockI2cWriteData(int devAddr, int regAddr, int value) {\n" +
				"\tWire.beginTransmission(devAddr);\n" +
				"\tWire.write(regAddr);\n" +
				"\tWire.write(value);\n" +
				"\tWire.endTransmission();\n" +
				"}\n" +
				"\n" +
				"int __ardublockI2cReadData(int devAddr, int regAddr) {\n" +
				"\tint data = 0;\n" +
				"\tchar b;\n" +
				"\tWire.beginTransmission(devAddr);\n" +
				"\tWire.write(regAddr);\n" +
				"\tWire.endTransmission();\n" +
				"\tWire.requestFrom(devAddr, 1);\n" +
				"\tif (Wire.available() > 0) {\n" +
				"\t\t__ardublockIsI2cReadOk = true;\n" +
				"\t\tb = Wire.read();\n" +
				"\t\tdata = b;\n}\n" +
				"\telse {\n" +
				"\t\t__ardublockIsI2cReadOk = false;\n}\n" +
				"\treturn data;\n}\n");
		t.addSetupCommand("Wire.begin();\n\t__ardublockIsI2cReadOk = false;");
	}

}
