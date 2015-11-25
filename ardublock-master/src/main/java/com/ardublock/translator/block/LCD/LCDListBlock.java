package com.ardublock.translator.block.LCD;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class LCDListBlock extends TranslatorBlock 
{
	public LCDListBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException
	{
    	TranslatorBlock tCol = this.getRequiredTranslatorBlockAtSocket(0);
    	TranslatorBlock tRow = this.getRequiredTranslatorBlockAtSocket(1);
    	
    	TranslatorBlock tGND = this.getTranslatorBlockAtSocket(2);
    	TranslatorBlock tVcc = this.getTranslatorBlockAtSocket(3);

		if (tGND != null) {
			Long output = Long.parseLong(tGND.toCode());
			translator.addOutputPin(output);
			translator.addSetupCommand("digitalWrite(" + output + ", HIGH);\n");
		}

		if (tVcc != null) {
			Long output = Long.parseLong(tVcc.toCode());
			translator.addOutputPin(output);
			translator.addSetupCommand("digitalWrite(" + output + ", HIGH);\n");
		}
		
		translator.addSetupCommand("lcd.begin(" + tCol.toCode() + ", " + tRow.toCode() + ");\n\tlcd.clear();\n");

		String list = "";
    	TranslatorBlock tb;
    	for(int i = 4; i < 7; i++) {
    		tb = this.getTranslatorBlockAtSocket(i);
    		if (tb != null) {
    			list = list + ", " + tb.toCode();//lcdValue.add(tb.toCode());
			}
		}
    	int j = 7;
    	String dataPins = "";
    	tb = this.getTranslatorBlockAtSocket(j);
    	while (tb != null) {
    		if (tb != null) {
    			dataPins = ", " + tb.toCode() + dataPins;//lcdValue.add(tb.toCode());
    		}
    		j++;
    		tb = this.getTranslatorBlockAtSocket(j);
    	}
    	list = list.substring(1) + dataPins;
    	translator.addDefinitionCommand("LiquidCrystal lcd(" + list + ");");
    	
    	return null;

	}
			
}