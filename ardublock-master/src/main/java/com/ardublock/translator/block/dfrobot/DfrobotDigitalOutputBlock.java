package com.ardublock.translator.block.dfrobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.c_pin.PinWriteDigitalBlock;

public class DfrobotDigitalOutputBlock extends PinWriteDigitalBlock
{	
	public DfrobotDigitalOutputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

}
