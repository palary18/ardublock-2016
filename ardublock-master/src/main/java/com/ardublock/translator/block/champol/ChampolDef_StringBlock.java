package com.ardublock.translator.block.champol;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.TranslatorBlock;

public class ChampolDef_StringBlock extends TranslatorBlock
{
	public ChampolDef_StringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		String ret;
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = tb.toCode();
		if (ret.startsWith("\"@")) ret = ret.substring(2,ret.length()-1);
		ret = "String " + ret + ";";
		translator.addDefinitionCommand(ret);
		ret="";
		return ret;
	}
	
	public static void setupWireEnvironment(Translator t)
	{
		//t.addHeaderFile("String.h");
		//t.addDefinitionCommand("LiquidCrystal lcd(12, 11, 5, 4, 3, 2);");
		//t.addSetupCommand("lcd.begin(16,2);");
	}
}