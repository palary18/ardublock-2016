package com.ardublock.translator.block.d_time;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.basic.ConstBlock;

public class DigitalRisingBlock extends ConstBlock
{

	public DigitalRisingBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		this.setCode("RISING");
	}
}
