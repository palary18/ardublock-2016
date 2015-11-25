package com.ardublock.translator.block.L298;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class L298RampBlock extends TranslatorBlock
{	
	public L298RampBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException
	{
		String ret0,ret1,ret2,ret3,ret4;
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
		// deuxi�me argument Moteur
		tb = getRequiredTranslatorBlockAtSocket(1);
		ret1 = tb.toCode();
		
		// troisi�me argument Direction
		tb = getRequiredTranslatorBlockAtSocket(2);
		ret2 = tb.toCode();
		if (ret2.compareTo("\"FW\"")==0) ret2 = "HIGH"; 
		else if (ret2.compareTo("\"BW\"")==0) ret2 = "LOW"; 
		
		// quatri�me argument Delay
		tb = getRequiredTranslatorBlockAtSocket(2);
		ret3 = tb.toCode();
		
		// cinqui�me argument Increment
		tb = getRequiredTranslatorBlockAtSocket(3);
		ret4 = tb.toCode();
		
		ret0 = "    for(int value = 0 ; value <= 255; value+=" + "5" +"){\n"; 
		if (ret1.compareTo("\"M1\"")==0) ret0 =ret0 + "        digitalWrite(M1," + ret2 +");\n";
		else if (ret1.compareTo("\"M2\"")==0) ret0 =ret0 + "        digitalWrite(M2,"+ ret2 + ");\n";
		else if (ret1.compareTo("\"M1 + M2\"")==0) ret0 =ret0 + "        digitalWrite(M1," + ret2 +");\n        digitalWrite(M2," + ret2 +");\n";
		
		if (ret1.compareTo("\"M1\"")==0) ret0 = ret0 + "        analogWrite(E1," + "value);\n";
		else if (ret1.compareTo("\"M2\"")==0) ret0 = ret0 + "        analogWrite(E2," + "value);\n";
		else if (ret1.compareTo("\"M1 + M2\"")==0) ret0 = ret0 + "        analogWrite(E1," + "value);\n        analogWrite(E2," + "value);\n";
			
		ret0 = ret0 + "        delay(" + ret4 + ");\n    }\n";

		return ret0;
	}
	
}

