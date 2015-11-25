package com.ardublock.translator.block.tinker;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.c_pin.PinWriteDigitalBlock;
import com.ardublock.translator.block.common.Common;
import com.ardublock.translator.block.e_number.NumberBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public abstract class AbstractTinkerWriteDigitalBlock extends TranslatorBlock 
{

	AbstractTinkerWriteDigitalBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		Common.isLibraryExist(blockId, translator, "TinkerKit.h");
		translator.addHeaderFile("TinkerKit.h");
	}

	@Override
	public String toCode() throws SocketNullException
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "";
		
		if (translatorBlock instanceof NumberBlock || translatorBlock instanceof TinkerOutputPortBlock)
		{
			String number = translatorBlock.toCode();
			String setupCode = "pinMode( " + number + " , OUTPUT);";
			translator.addSetupCommand(setupCode);
			
			ret = "digitalWrite( ";
			ret = ret + number;
		}
		else
		{
			translator.addDefinitionCommand(PinWriteDigitalBlock.ARDUBLOCK_DIGITAL_WRITE_DEFINE);
			ret = "__ardublockDigitalWrite(";
			
			ret = ret + translatorBlock.toCode();
		}
		
		ret = ret + " , ";
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + translatorBlock.toCode();
		ret = ret + " );\n";
		return ret;
	}
	
	
	

}
