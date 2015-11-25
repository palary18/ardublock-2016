package com.ardublock.translator.block.e_number;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class VariableBooleanBlock extends TranslatorBlock
{
	public VariableBooleanBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		String localVariableName = translator.getBooleanLocalVariable(label);
		if (localVariableName == null)
		{
			localVariableName = translator.buildLocalVariableName(label);
			translator.addBooleanLocalVariable(label, localVariableName);
			//translator.addDefinitionCommand("int " + internalVariableName + ";");
			//translator.addSetupCommand("\t" + internalVariableName + " = 0;");
		}
		return codePrefix + localVariableName + codeSuffix;
	}


}
