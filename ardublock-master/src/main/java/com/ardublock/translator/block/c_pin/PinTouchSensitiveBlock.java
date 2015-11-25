package com.ardublock.translator.block.c_pin;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class PinTouchSensitiveBlock extends TranslatorBlock
{
	public PinTouchSensitiveBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	private final static String TouchSensitiveFunction = "boolean ardublockReadCapacitivePin(" +
			"int pinToMeasure) {" +
			"\n\t// Variables used to translate from Arduino to AVR pin naming" +
			"\n\tvolatile uint8_t* port;" +
			"\n\tvolatile uint8_t* ddr;" +
			"\n\tvolatile uint8_t* pin;" +
			"\n\tbyte bitmask;" +
			"\n\t// Here we translate the input pin number from Arduino pin number to" +
			"\n\t// the AVR PORT, PIN, DDR, and which bit of those registers we care about." +
			"\n\tport = portOutputRegister(digitalPinToPort(pinToMeasure));" +
			"\n\tddr = portModeRegister(digitalPinToPort(pinToMeasure));" +
			"\n\tbitmask = digitalPinToBitMask(pinToMeasure);" +
			"\n\tpin = portInputRegister(digitalPinToPort(pinToMeasure));" +
			"\n\t// Discharge the pin first by setting it low and output" +
			"\n\t*port &= ~(bitmask);" +
			"\n\t*ddr  |= bitmask;" +
			"\n\tdelay(1);" +
			"\n\t// Make the pin an input with the internal pull-up on" +
			"\n\t*ddr &= ~(bitmask);" +
			"\n\t*port |= bitmask;" +
			"\n\t" +
			"\n\t// Now see how long the pin to get pulled up." +
			"\n\tuint8_t cycles = 17;" +
			"\n\tif (*pin & bitmask) { cycles =  0;}" +
			"\n\telse if (*pin & bitmask) { cycles =  1;}" +
			"\n\telse if (*pin & bitmask) { cycles =  2;}" +
			"\n\telse if (*pin & bitmask) { cycles =  3;}" +
			"\n\telse if (*pin & bitmask) { cycles =  4;}" +
			"\n\telse if (*pin & bitmask) { cycles =  5;}" +
			"\n\telse if (*pin & bitmask) { cycles =  6;}" +
			"\n\telse if (*pin & bitmask) { cycles =  7;}" +
			"\n\telse if (*pin & bitmask) { cycles =  8;}" +
			"\n\telse if (*pin & bitmask) { cycles =  9;}" +
			"\n\t// Discharge the pin again by setting it low and output" +
			"\n\t*port &= ~(bitmask);" +
			"\n\t*ddr |= bitmask;" +
			"\n\tif (cycles > ";// +
			//"" +
			//"" +
			//"" +
			//"1) { return true; }" +
			//"\n\telse { return false; }" +
			//"\n}" +
			//"\n";
	
	public String toCode() throws SocketNullException
	{

		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "ardublockReadCapacitivePin(";
		ret = ret + translatorBlock.toCode();
		ret = ret + ")";

		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);		
		translator.addDefinitionCommand(TouchSensitiveFunction + 
				translatorBlock.toCode() + 					// sensitivity adjustment
				") { return true; }" +
				"\n\telse { return false; }" +
				"\n}" +
				"\n");
		
		return codePrefix + ret + codeSuffix;
	}

}
