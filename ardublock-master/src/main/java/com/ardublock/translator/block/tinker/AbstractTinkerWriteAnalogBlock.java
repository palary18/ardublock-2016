package com.ardublock.translator.block.tinker;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.common.Common;
import com.ardublock.translator.block.exception.SocketNullException;

public abstract class AbstractTinkerWriteAnalogBlock extends TranslatorBlock 
{

	AbstractTinkerWriteAnalogBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		Common.isLibraryExist(blockId, translator, "TinkerKit.h");
		translator.addHeaderFile("TinkerKit.h");
	}
	
	public String toCode() throws SocketNullException
	{
		String ret = "analogWrite(";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String outputPin = translatorBlock.toCode();
		ret = ret + outputPin;
		ret = ret + ", ";
		
		translator.addSetupCommand("pinMode(" + outputPin +  ", OUTPUT);");
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + translatorBlock.toCode();
		ret = ret + ");\n";
		return ret;
	}
}
