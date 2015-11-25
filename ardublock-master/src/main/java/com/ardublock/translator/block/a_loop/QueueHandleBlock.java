package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;


public class QueueHandleBlock extends TranslatorBlock
{
	public QueueHandleBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		TranslatorBlock tb = getTranslatorBlockAtSocket(0);
		String ret = "";
		
		if (tb != null) {
			String time = tb.toCode();
			if (time.equals("0")) {							// no wait
				ret = ret + time;
			}
			else {
				ret = ret + time + " / portTICK_RATE_MS";	// wait the semaphore during a time and pass after
			}
		}
		else {
			ret = ret + "portMAX_DELAY";					// wait until semaphore is giving
		};
		
		//ret = ret + "\n";
		
		
		
		return ret;
	}
}
