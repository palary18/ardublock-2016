package com.ardublock.translator.block.a_loop;

import com.ardublock.ArduBlockTool;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.common.Common;
import com.ardublock.translator.block.exception.SocketNullException;

public class LoopParallelBlock extends TranslatorBlock
{
	static String boardType[][] = {
		{"uno", "ethernet", "fio", "nano", "mini", "bt", "lilypad", "pro", "atmegang"},
		{"diecimila", "mega", "megaADK"},
		{"leonardo", "yun", "micro", "esplora", "LilyPadUSB", "robotControl", "robotMotor"},
		{"arduino_due_x_dbg", "arduino_due_x"},
	};

	public LoopParallelBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		// board type --> search the index i in boardType 
		String boardName = ArduBlockTool.getBoardName();
		// arduino version
		//String versionName = ArduBlockTool.getArduinoVersion();
		
		////////////////////////////////////////////////////////
		// include freeRTOS library
		////////////////////////////////////////////////////////
		int i = 0;
		int j = 0;
		boolean nameFind = false;
		search:
			for ( ; i < boardType.length; i++) {
				for (j = 0; j < boardType[i].length; j++) {
					if (boardType[i][j].equalsIgnoreCase(boardName)) {
						nameFind = true;
						break search;
					}
				}
			}
		if (!(i == 3)) {
			Common.isLibraryExist(blockId, translator, "FreeRTOS_AVR.h");
			translator.addPreHeaderFile("#include \"FreeRTOS_AVR.h\"\n");
		}
		else {
			Common.isLibraryExist(blockId, translator, "FreeRTOS_ARM.h");
			translator.addPreHeaderFile("#include \"FreeRTOS_ARM.h\"\n");
		}
		
		////////////////////////////////////////////////////////
		// Task creation
		////////////////////////////////////////////////////////
		translator.addTaskLoop(label);
		TranslatorBlock translatorBlock = getRequiredTranslatorBlockAtSocket(0);
		translator.addTaskPriorityLoop(label, translatorBlock.toCode());
		
		String ret = "\t";
		translatorBlock = getTranslatorBlockAtSocket(1);
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		
		ret = ret.replace("\n", "\n\t");
		String localVar = this.getTranslator().translateVar();
		ret = "static void v" + label + "Task( void *parameters ) {\n" 
				+ localVar 
				+ "\tfor( ;; ) {\n"
				+ ret 
				+ "}\n"
				+ "}\n\n";
		return ret;
	}
}
