package com.ardublock.translator.block.dfrobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.TranslatorBlock;

public class DfrobotLCDKeypadBlock extends TranslatorBlock //LCDKeypad
{
	public DfrobotLCDKeypadBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		setupWireEnvironment(translator);
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0, "    lcd.print(", ");\n");
		String ret = translatorBlock.toCode();
		return ret;
	}
	
	public static void setupWireEnvironment(Translator t)
	{
		t.addHeaderFile("LiquidCrystal.h");
		
		t.addDefinitionCommand("LiquidCrystal lcd(8, 9, 4, 5, 6, 7);");
		/*
		LiquidCrystal lcd(rs, enable, d4, d5, d6, d7) // mode 4 bits - RW non connect�e (le plus simple!)
		LiquidCrystal lcd(rs, rw, enable, d4, d5, d6, d7) // mode 4 bits - RW utilis�e
		LiquidCrystal lcd(rs, enable, d0, d1, d2, d3, d4, d5, d6, d7) // mode 8 bits - RW non connect�e
		LiquidCrystal lcd(rs, rw, enable, d0, d1, d2, d3, d4, d5, d6, d7) //mode 8 bits - RW utilis�e
		*/
		
		//t.addSetupCommand("    lcd.begin(16,2);\n    lcd.clear();\n    lcd.print(\"Init Ok\");");
		t.addSetupCommand("    lcd.begin(16,2);\n    lcd.clear();\n");
		
	}
}