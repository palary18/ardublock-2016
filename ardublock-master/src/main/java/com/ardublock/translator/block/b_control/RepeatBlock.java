package com.ardublock.translator.block.b_control;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class RepeatBlock extends TranslatorBlock
{

		public RepeatBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

public String toCode() throws SocketNullException
	{
		String localVariableName = translator.buildLocalVariableName("i");
		//translator.addNumberLocalVariable("i", localVariableName);
	
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		
				
//		String varName = tb.toCode();		
		//translator.addDefinitionCommand("int " + varName + ";");
//		translator.addNumberLocalVariable(tb.label, varName);
//		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String ret = "\tfor (int " + localVariableName + "=0" + "; ";
//		tb = this.getRequiredTranslatorBlockAtSocket(2);
		ret = ret + localVariableName + "<" + tb.toCode() + "; ";
//		tb = this.getRequiredTranslatorBlockAtSocket(3);
		ret = ret + localVariableName + "++" + ") {\n";
		
		
		tb = getTranslatorBlockAtSocket(1);
		while (tb != null)
		{
			ret = ret + tb.toCode();
			tb = tb.nextTranslatorBlock();
		}
		
		ret = ret.replace("\n", "\n\t");
		ret = ret + "}\n";
		return ret;
/*		String varName = translator.buildVariableName();
		translator.addDefinitionCommand("int " + varName + ";");
		String ret = "for (" + varName + "=0; " + varName + "< ( ";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + translatorBlock.toCode();
		ret = ret + " ); ++" + varName + " )\n{\n";
		
		
		translatorBlock = getTranslatorBlockAtSocket(1);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		
		ret = ret + "}\n\n";
		return ret;*/
	}

}
	