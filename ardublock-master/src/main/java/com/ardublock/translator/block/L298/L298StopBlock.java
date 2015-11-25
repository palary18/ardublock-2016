package com.ardublock.translator.block.L298;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class L298StopBlock extends TranslatorBlock
{	
	public L298StopBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		String ret0,ret1;
		// premier argument Control
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret0 = tb.toCode();
		if (ret0.compareTo("\"PWM\"")==0) translator.addDefinitionCommand("//Arduino PWM Speed Control\n" +
								"int E1 = 5;\n" +
								"int M1 = 4;\n" +
								"int E2 = 6;\n" +                    
								"int M2 = 7;\n") ;	
		else if (ret0.compareTo("\"PLL\"")==0) translator.addDefinitionCommand("//Arduino PLL Speed Control\n" +
				"int E1 = 4;\n" +
				"int M1 = 5;\n" +
				"int E2 = 7;\n" +                    
				"int M2 = 6;\n" );	
		translator.addSetupCommand("    pinMode(M1, OUTPUT);\n" +  
								   "    pinMode(M2, OUTPUT);\n" );
		// deuxième argument Moteur
		tb = getRequiredTranslatorBlockAtSocket(1);
		ret1 = tb.toCode();

		
		if (ret1.compareTo("\"M1\"")==0) ret0 = "    digitalWrite(M1, LOW);\n";
		else if (ret1.compareTo("\"M2\"")==0) ret0 = "    digitalWrite(M2, LOW);\n";
		else if (ret1.compareTo("\"M1 + M2\"")==0) ret0 = "    digitalWrite(M1, LOW);\n    digitalWrite(M2, LOW);\n";
		
		if (ret1.compareTo("\"M1\"")==0) ret0 = ret0 + "    analogWrite(E1,0);\n";
		else if (ret1.compareTo("\"M2\"")==0) ret0 = ret0 + "    analogWrite(E2,0);\n";
		else if (ret1.compareTo("\"M1 + M2\"")==0) ret0 = ret0 + "    analogWrite(E1,0);\n    analogWrite(E2,0);\n";
		return ret0;
	}
	
}

