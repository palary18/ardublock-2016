package com.ardublock.translator.block.h_string;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class SerialPrintBlock extends TranslatorBlock
{
	public SerialPrintBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		translator.addSetupCommand("Serial.begin(9600);");
		int i=0;
		TranslatorBlock tb = getTranslatorBlockAtSocket(0);
		//tb=this.nextTranslatorBlock();		
		String ret = "";
		while (tb != null)
		{
			ret = ret + "\tSerial.print(" + tb.toCode() + ");\n";
			i=i+1;
			tb = getTranslatorBlockAtSocket(i);
		}		
		//ret = ret.replace("\n", "\n\t");
		ret = ret + "\tSerial.print(\"\\t\");\n";
		
		return ret;
	}
}
