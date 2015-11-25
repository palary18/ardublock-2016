package com.ardublock.translator.block.e_number;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;

public class VariableNumberBlock extends TranslatorBlock
{
	public VariableNumberBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		String localVariableName = translator.getNumberLocalVariable(label);
		if (localVariableName == null)
		{
			localVariableName = translator.buildLocalVariableName(label);
			//String toto[] = null;
			//toto[1] = localVariableName;
			/*Block bl = translator.getBlock(blockId);
			Block plugBl = translator.getBlock(bl.getPlugBlockID());
			if (plugBl.getGenusName().contains("convert_int")) {
				String localVariableCastName = plugBl.getGenusName().replace("convert_", "") + localVariableName;
				//localVariableName = "int32_t " + localVariableName;
			}*/
			
			translator.addNumberLocalVariable(label, localVariableName);
			//translator.addDefinitionCommand("int " + internalVariableName + ";");
			//translator.addSetupCommand("\t" + internalVariableName + " = 0;");
		}
		return codePrefix + localVariableName + codeSuffix;
	}

}
