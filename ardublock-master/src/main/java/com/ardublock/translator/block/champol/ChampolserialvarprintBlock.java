package com.ardublock.translator.block.champol;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.TranslatorBlock;

public class ChampolserialvarprintBlock extends TranslatorBlock
{
	public ChampolserialvarprintBlock(Long blockId, Translator translator)
	//protected ChampolserialvarprintBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
		//super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		String ret;
		translator.addSetupCommand("Serial.begin(9600);");
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = tb.toCode();		
		ret = "    Serial.print(" + ret + ");\n";
		
		//ret = ret + "Serial.println(\"\");\n";
		
		return ret;
	}
}
