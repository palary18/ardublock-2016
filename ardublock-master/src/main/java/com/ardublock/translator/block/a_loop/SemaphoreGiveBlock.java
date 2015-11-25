package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;

public class SemaphoreGiveBlock extends TranslatorBlock
{
	public SemaphoreGiveBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		translator.addSemaphore(label);
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
		
		if (translatorBlock != null) {
			if (translatorBlock.toCode().equals("1")) {
				translator.addSetupCommand("vSemaphoreCreateBinary(x" + label + ");");
			}
			else {
				translator.addSetupCommand("x" + label + " = xSemaphoreCreateCounting(" + translatorBlock.toCode() + " , 0);");
			}
		}
		else {
			translator.addSetupCommand("x" + label + " = xSemaphoreCreateMutex( );");
		}
		
		String ret = "\txSemaphoreGive(x" + label + ");\n";
		
		// test if the semaphore_give is in an interrupt procedure
		// also use "SemaphoreGiveFromISR" command
		Block block = translator.getBlock(blockId);
		if (translator.testGenusBlock(block, "procedure_interrupt"))//isISR)
		{
			ret = "\txSemaphoreGiveFromISR(x" + 
					label + 
					", (signed portBASE_TYPE*)&xHigherPriorityTaskWoken);\n";
			translator.addSemaphoreLocalVariable();
		}
		return ret;
	}
}
