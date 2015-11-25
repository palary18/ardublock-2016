package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;

public class SemaphoreTakeBlock extends TranslatorBlock
{
	public SemaphoreTakeBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		translator.addSemaphore(label);										// semaphore declaration
		String ret = "\txSemaphoreTake(x" + label + ", ";					// write semaphore
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);	// get waiting time
		if (translatorBlock != null) {
			String time = translatorBlock.toCode();
			if (time.equals("0")) {											// no wait
				ret = ret + time;
			}
			else {
				ret = ret + time + " / portTICK_RATE_MS";					// wait the semaphore during a time and pass after
			}
		}
		else {
			ret = ret + "portMAX_DELAY";									// wait until semaphore is giving
		}
		ret = ret + ");\n";
		
		// test if the semaphore_take is in an interrupt procedure
		// so modify the code as "...TakeFromISR..." 
		Block block = translator.getBlock(blockId);
		if (translator.testGenusBlock(block, "procedure_interrupt"))//isISR)
		{
			ret = "\txSemaphoreTakeFromISR(x" + 
					label + 
					", (signed portBASE_TYPE*)&xHigherPriorityTaskWoken);\n";
			translator.addSemaphoreLocalVariable();
		}
		
		return ret;
	}
}
