package com.ardublock.translator.block.c_pin;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class PulseInBlock extends TranslatorBlock
{
	public PulseInBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
		TranslatorBlock pinBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String portNum = pinBlock.toCode();
		Long input = Long.parseLong(portNum);
		translator.addInputPin(input);

		TranslatorBlock valueBlock = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock timeoutBlock = this.getRequiredTranslatorBlockAtSocket(2);
		String ret = "pulseIn(" + pinBlock.toCode() + ", " + valueBlock.toCode() + ", " + timeoutBlock.toCode() + ")";
		return ret;
	}
}
