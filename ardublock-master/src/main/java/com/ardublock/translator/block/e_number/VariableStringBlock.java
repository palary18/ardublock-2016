package com.ardublock.translator.block.e_number;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class VariableStringBlock extends TranslatorBlock
{
	public VariableStringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		String localVariableName = translator.getStringLocalVariable(label);
		if (localVariableName == null)
		{
			localVariableName = translator.buildLocalVariableName(label);
			translator.addStringLocalVariable(label, localVariableName);
			//translator.addDefinitionCommand("int " + internalVariableName + ";");
			//translator.addSetupCommand("\t" + internalVariableName + " = 0;");
		}
		return codePrefix + localVariableName + codeSuffix;
	}

}
