package com.ardublock.translator.block.champol;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.TranslatorBlock;

public class ChampolSerialCRBlock extends TranslatorBlock
{
	public ChampolSerialCRBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		String ret;
		translator.addSetupCommand("Serial.begin(9600);");
		
		ret = "    Serial.println(\"\");\n";
		
		return ret;
	}
}