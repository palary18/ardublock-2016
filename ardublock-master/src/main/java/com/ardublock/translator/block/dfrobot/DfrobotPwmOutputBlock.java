package com.ardublock.translator.block.dfrobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.c_pin.PinWriteAnalogBlock;

public class DfrobotPwmOutputBlock extends PinWriteAnalogBlock
{
	public DfrobotPwmOutputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
}
