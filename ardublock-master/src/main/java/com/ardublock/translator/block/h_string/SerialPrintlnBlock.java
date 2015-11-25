package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialPrintlnBlock extends TranslatorBlock
{
	public SerialPrintlnBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	public String toCode() throws SocketNullException
	{
		translator.addSetupCommand("Serial.begin(9600);");
		int i=0;
		TranslatorBlock tb = getTranslatorBlockAtSocket(0);
		String ret = "";
		while (tb != null)
		{
			ret = ret  + "\tSerial.print(" + tb.toCode() + ");\n";
			i=i+1;
			tb = getTranslatorBlockAtSocket(i);
		}		
		//ret = ret.replace("\n", "\n\t");
		ret = ret + "\tSerial.println(\"\");\n";
		
		return ret;
	}
}
