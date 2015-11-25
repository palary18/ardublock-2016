package com.ardublock.translator.block.j_subroutine;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class ProcGetBooleanBlock extends TranslatorBlock
{
	public ProcGetBooleanBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		return codePrefix + label + codeSuffix;
	}

}
