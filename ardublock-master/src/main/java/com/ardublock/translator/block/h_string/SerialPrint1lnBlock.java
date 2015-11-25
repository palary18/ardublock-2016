package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialPrint1lnBlock extends TranslatorBlock
{
	public SerialPrint1lnBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	public String toCode() throws SocketNullException
	{
		translator.addSetupCommand("Serial1.begin(38400);");
		int i=0;
		TranslatorBlock tb = getTranslatorBlockAtSocket(0);
		String ret = "";
		while (tb != null)
		{
			ret = ret  + "\tSerial1.print(" + tb.toCode() + ");\n";
			i=i+1;
			tb = getTranslatorBlockAtSocket(i);
		}		
		//ret = ret.replace("\n", "\n\t");
		ret = ret + "\tSerial1.println(\"\");\n";
		
		return ret;
	}
}
