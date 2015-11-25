package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableNumberListBlock extends TranslatorBlock
{
	protected VariableNumberListBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		String localVariableName = translator.getNumberListLocalVariable(label);
		if (localVariableName == null)
		{
			localVariableName = translator.buildLocalVariableName(label);
			translator.addNumberListLocalVariable(label, localVariableName);
			//translator.addDefinitionCommand("int " + internalVariableName + ";");
			//translator.addSetupCommand("\t" + internalVariableName + " = 0;");
		}
		return codePrefix + localVariableName + codeSuffix;
	}

}
