package com.ardublock.translator.block.a_loop;

import java.util.ArrayList;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.TranslatorBlockFactory;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.codeblocks.BlockStub;

public class SemaphoreGive2Block extends TranslatorBlock
{
	public SemaphoreGive2Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();					

	public String toCode() throws SocketNullException
	{
		
		/////////////////////////////////////////////////////////////////////////////////
		//// Define the semaphore handle name										/////
		/////////////////////////////////////////////////////////////////////////////////
		TranslatorBlock tbSemaphore = getRequiredTranslatorBlockAtSocket(0);
		String semName = tbSemaphore.label;
		String stubName = semName + "semdeclare";

		/////////////////////////////////////////////////////////////////////////////////
		//// Create the semaphore													/////
		/////////////////////////////////////////////////////////////////////////////////

		// research of the parent of the stub semaphore : parent = the declaration semaphore block
		// to read the type of the semaphore
		ArrayList<Long> stubs = translator.getWorkspace().getEnv().getBlockStubs(stubName);
		String type = "";
		for (Long stub : stubs) {
			BlockStub blockStub = ((BlockStub) translator.getWorkspace().getEnv().getBlock(stub));
			Block stubParent = blockStub.getParent();
			BlockConnector connector = stubParent.getSocketAt(0);
			Block blSemaphore = translator.getBlock(connector.getBlockID());
			if (blSemaphore != null){
				TranslatorBlock tbSemaphoreDeclare =  translatorBlockFactory.buildTranslatorBlock(
					translator, blSemaphore.getBlockID(), 
					blSemaphore.getGenusName(), 
					"", 
					"", 
					blSemaphore.getBlockLabel());
				type = tbSemaphoreDeclare.toCode();
			}
		}



		if (type != "") {
			if (type.equals("1")) {
				translator.addSetupCommand("vSemaphoreCreateBinary(x" + semName + ");");
			}
			else {
				translator.addSetupCommand("x" + semName + " = xSemaphoreCreateCounting(" + type + " , 0);");
			}
		}
		else {
			translator.addSetupCommand("x" + semName + " = xSemaphoreCreateMutex( );");
		}


		/////////////////////////////////////////////////////////////////////////////////
		//// Write the method														/////
		/////////////////////////////////////////////////////////////////////////////////
		String ret = "\txSemaphoreGive(x" + semName + ");\n";

		// test if the semaphore_give is in an interrupt procedure
		// also use "SemaphoreGiveFromISR" command
		Block block = translator.getBlock(blockId);
		if (translator.testGenusBlock(block, "procedure_interrupt2"))//isISR)
		{
			ret = "\txSemaphoreGiveFromISR(x" + 
					semName + 
					", (signed portBASE_TYPE*)&xHigherPriorityTaskWoken);\n";
			translator.addSemaphoreLocalVariable();
		}
		return ret;
	}
}

