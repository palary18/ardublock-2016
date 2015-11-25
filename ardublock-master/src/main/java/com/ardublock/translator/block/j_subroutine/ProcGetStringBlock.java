package com.ardublock.translator.block.j_subroutine;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class ProcGetStringBlock extends TranslatorBlock
{
	public ProcGetStringBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode()
	{
		return codePrefix + label + codeSuffix;
	}

}
