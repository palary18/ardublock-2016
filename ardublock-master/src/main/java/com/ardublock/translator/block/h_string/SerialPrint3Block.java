package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialPrint3Block extends TranslatorBlock
{
	public SerialPrint3Block(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		translator.addSetupCommand("Serial3.begin(38400);");
		int i=0;
		TranslatorBlock tb = getTranslatorBlockAtSocket(0);
		//tb=this.nextTranslatorBlock();		
		String ret = "";
		while (tb != null)
		{
			ret = ret + "\tSerial3.print(" + tb.toCode() + ");\n";
			i=i+1;
			tb = getTranslatorBlockAtSocket(i);
		}		
		//ret = ret.replace("\n", "\n\t");
		ret = ret + "\tSerial3.print(\"\\t\");\n";
		
		return ret;
	}
}
