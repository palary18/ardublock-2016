package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;

public class SemaphoreTakeReturnBlock extends TranslatorBlock
{
	public SemaphoreTakeReturnBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		translator.addSemaphore(label);
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
		String ret = " xSemaphoreTake(x" + label + ", ";
		
		if (translatorBlock != null) {
			String time = translatorBlock.toCode();
			if (time.equals("0")) {
				ret = ret + time;
			}
			else {
				ret = ret + time + " / portTICK_RATE_MS";
			}
		}
		else {
			ret = ret + "portMAX_DELAY";
		}
		
		ret = ret + ") == pdTRUE ";
		
		// test if the semaphore_take is in an interrupt procedure
		Block block = translator.getBlock(blockId);
		if (translator.testGenusBlock(block, "procedure_interrupt"))//isISR)
		{
			ret = "xSemaphoreTakeFromISR(x" + 
					label + 
					", (signed portBASE_TYPE*)&xHigherPriorityTaskWoken)";
			translator.addSemaphoreLocalVariable();
		}
		return ret;
	}
}
