package com.ardublock.translator.block.L298;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class L298InitBlock extends TranslatorBlock
{	
	public L298InitBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		String ret;
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = tb.toCode();
		if (ret.compareTo("\"PWM\"")==0) translator.addDefinitionCommand("//Arduino PWM Speed Control\n" +
								"int E1 = 5;\n" +
								"int M1 = 4;\n" +
								"int E2 = 6;\n" +                    
								"int M2 = 7;\n") ;	
		else if (ret.compareTo("\"PLL\"")==0) translator.addDefinitionCommand("//Arduino PLL Speed Control\n" +
				"int E1 = 4;\n" +
				"int M1 = 5;\n" +
				"int E2 = 7;\n" +                    
				"int M2 = 6;\n" );	
		translator.addSetupCommand("    pinMode(M1, OUTPUT);\n" +  
									"    pinMode(M2, OUTPUT);\n" );
		ret="";
		return ret;
	}
	
}