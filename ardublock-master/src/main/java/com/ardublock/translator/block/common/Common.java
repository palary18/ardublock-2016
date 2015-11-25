package com.ardublock.translator.block.common;

import java.util.ListIterator;

import com.ardublock.ArduBlockTool;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;

public class Common {
	
	static boolean libraryExist = false;
	
	public static void isLibraryExist(Long blockId, Translator translator, String libraryName)
	{
		ListIterator<?> li = ArduBlockTool.libraryList.listIterator();
		while(li.hasNext()) {
			if (li.next().toString().equals(libraryName)) {
				libraryExist = true;
			}
		}
		if (!libraryExist) {
			throw new BlockException(blockId, "the " + libraryName.replaceAll(".h", "") + " library is not installed");
		}
	}
}
