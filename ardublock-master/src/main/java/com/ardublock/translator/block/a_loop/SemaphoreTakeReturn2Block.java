package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;

public class SemaphoreTakeReturn2Block extends TranslatorBlock
{
	public SemaphoreTakeReturn2Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		/////////////////////////////////////////////////////////////////////////////////
		//// Define the semaphore handle name										/////
		/////////////////////////////////////////////////////////////////////////////////
		TranslatorBlock tbSemaphore = getRequiredTranslatorBlockAtSocket(0);
		String semName = tbSemaphore.label;

		/////////////////////////////////////////////////////////////////////////////////
		//// Write the method														/////
		/////////////////////////////////////////////////////////////////////////////////
		String ret = " xSemaphoreTake(x" + semName + ", ";					// write semaphore
		
		ret = ret + tbSemaphore.toCode();
		ret = ret + ") == pdTRUE ";
		
		// test if the semaphore_take is in an interrupt procedure
		// so modify the code as "...TakeFromISR..." 
		Block block = translator.getBlock(blockId);
		if (translator.testGenusBlock(block, "procedure_interrupt"))//isISR)
		{
			ret = "xSemaphoreTakeFromISR(x" + 
					semName + 
					", (signed portBASE_TYPE*)&xHigherPriorityTaskWoken)";
			translator.addSemaphoreLocalVariable();
		}
		
		return ret;
	}
}
