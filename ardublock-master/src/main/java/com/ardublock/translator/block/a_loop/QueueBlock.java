package com.ardublock.translator.block.a_loop;

import java.util.ArrayList;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.GetGlobalVarBooleanBlock;
import com.ardublock.translator.block.GetGlobalVarNumberBlock;
import com.ardublock.translator.block.GetGlobalVarStringBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.TranslatorBlockFactory;
import com.ardublock.translator.block.e_number.VariableBooleanBlock;
import com.ardublock.translator.block.e_number.VariableNumberBlock;
import com.ardublock.translator.block.e_number.VariableStringBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.j_subroutine.ProcGetBooleanBlock;
import com.ardublock.translator.block.j_subroutine.ProcGetNumberBlock;
import com.ardublock.translator.block.j_subroutine.ProcGetStringBlock;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.codeblocks.BlockStub;

public class QueueBlock extends TranslatorBlock
{
	public QueueBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();					

	/*private enum queueManagement {
		"push LI", "enqueue FI", "dequeue/pop FO", "copy FO", "overwrite", "reset";
	}*/
	public String toCode() throws SocketNullException
	{
		/////////////////////////////////////////////////////////////////////////////////
		//// Define the queue														/////
		/////////////////////////////////////////////////////////////////////////////////
		TranslatorBlock tbQueue = getRequiredTranslatorBlockAtSocket(0);
		String queueName = tbQueue.label;
		String stubName = queueName + "qhandle";
		translator.addQueue(queueName);
		
		/////////////////////////////////////////////////////////////////////////////////
		//// Create the queue														/////
		/////////////////////////////////////////////////////////////////////////////////

		// research of the parent of the stub queue : parent = the declaration queue block
		// to read the length value of the queue
		ArrayList<Long> stubs = translator.getWorkspace().getEnv().getBlockStubs(stubName);
		String length = "";
        for (Long stub : stubs) {
            BlockStub blockStub = ((BlockStub) translator.getWorkspace().getEnv().getBlock(stub));
            Block stubParent = blockStub.getParent();
    		BlockConnector connector = stubParent.getSocketAt(0);
    		Block blQueue = translator.getBlock(connector.getBlockID());
    		TranslatorBlock tbQueueDeclare =  translatorBlockFactory.buildTranslatorBlock(
    				translator, blQueue.getBlockID(), 
    				blQueue.getGenusName(), 
    				"", 
    				"", 
    				blQueue.getBlockLabel());
    		length = tbQueueDeclare.toCode();
        }

        /*// what is the kind of the elements stored in the queue
		TranslatorBlock tbVar = getRequiredTranslatorBlockAtSocket(1);
		String queueKind = translator.getBlock(tbVar.blockId).getPlugKind();
		if (queueKind.equals("number")) queueKind = "int *";
		if (queueKind.equals("boolean")) queueKind = "char *";
		if (queueKind.equals("string")) queueKind = "char *";
		
		String queueSetup = "x" + 					// create the queue
				queueName + 			
				" = xQueueCreate( " + 
				length +
				", sizeof(" + 
				queueKind + 
				") );";

		translator.addSetupCommand(queueSetup);*/
        
        // what is the kind of the elements stored in the queue
        // it must be a variable
        TranslatorBlock tbVar = getRequiredTranslatorBlockAtSocket(1);
        String queueKind = translator.getBlock(tbVar.blockId).getPlugKind();
		if (queueKind.equals("number"))
		{
			if (!(tbVar instanceof VariableNumberBlock || tbVar instanceof GetGlobalVarNumberBlock) && !(tbVar instanceof ProcGetNumberBlock))
			{
				throw new BlockException(blockId, "IN/OUT must be var");
			}
		}
		if (queueKind.equals("boolean"))
		{
			if (!(tbVar instanceof VariableBooleanBlock || tbVar instanceof GetGlobalVarBooleanBlock) && !(tbVar instanceof ProcGetBooleanBlock))
			{
				throw new BlockException(blockId, "IN/OUT must be var");
			}
		}
		if (queueKind.equals("string"))
		{
			if (!(tbVar instanceof VariableStringBlock || tbVar instanceof GetGlobalVarStringBlock) && !(tbVar instanceof ProcGetStringBlock))
			{
				throw new BlockException(blockId, "IN/OUT must be var");
			}
		}
        
        
		String queueSetup = "x" + 					// create the queue
				queueName + 			
				" = xQueueCreate( " + 
				length +
				", 2 );";							// more simple than before : the sizeof of the pointer for any type is 2
		translator.addSetupCommand(queueSetup);

		/////////////////////////////////////////////////////////////////////////////////
		//// Write the method														/////
		/////////////////////////////////////////////////////////////////////////////////
		String retPrefix = "\txQueue";
		String retSuffix = " );\n";
		Block bl = translator.getBlock(blockId);
		if (bl.getGenusName().startsWith("qOK-"))
		{
			retPrefix = "xQueue";
			retSuffix = " )";
		}
		
		String ret = "";
		// test if the semaphore_give is in an interrupt procedure
		// also use "SemaphoreGiveFromISR" command
		Block block = translator.getBlock(blockId);
		if (translator.testGenusBlock(block, "procedure_interrupt"))//isISR)
		{
			ret = textIfISR(tbVar, queueName, retPrefix, retSuffix);
		}
		else {
			ret = textIfNotISR(tbQueue, tbVar, queueName, retPrefix, retSuffix);			
		}

		return ret;
	}
	
    /**
     * if the Queue is not in an interrupt procedure,
     * write the suitable method with or not queue name, item/buffer pointer, ticks to wait
     * @param tbqueueWait time the queue must waits
     * @param tbvar the item / buffer to enqueue or dequeue
     * @param queueName the Queue name
     * @param prefix the String prefix for the method
     * @param suffix the String suffix for the method
     */
	private String textIfNotISR(
			TranslatorBlock tbqueueWait, 
			TranslatorBlock tbvar, 
			String queueName,
			String prefix,
			String suffix) throws SocketNullException
	{
		//String retPrefix = "\txQueue";
		//String retSuffix = "";
		if ("enqueue FI".equals(label)) {			// SendToBack
			prefix = prefix + "Send";
		}
		else if ("push LI".equals(label)) {			// SendToFront
			prefix = prefix + "SendToFront";
		}
		else if ("dequeue/pop FO".equals(label)) {	// Receive
			prefix = prefix + "Receive";
		}
		else if ("copy FO".equals(label)) {			// Peek
			prefix = prefix + "Peek";
		}		
		
		if ("overwrite".equals(label)) {			// Overwrite
			prefix = prefix + "Overwrite";
			suffix = ", &" +						// parameters :
					tbvar.toCode() +  				// pvItemToQueue only
					suffix;
		}		
		else if ("reset".equals(label)) {			// Reset the queue
			prefix = prefix + "Reset";				// no parameters
			//suffix = " )";
		}
		else {
			suffix = ", &" +						// parameters for the others choices :
					tbvar.toCode() + 					// pvItemToQueue
					", " +								//
					tbqueueWait.toCode() + 				// xTicksToWaits
					suffix;

		}
    
		String ret = prefix + "(x" + 			// return the parameters :
				queueName + 						// xQueue
				suffix;								// and others ...
		
		return ret;
	}
	
    /**
     * if the Queue is in an interrupt procedure, write the suitable method
     *  with or not queue name, item/buffer pointer, HigherPriorityTaskWoken boolean
     * @param tbvar the item / buffer to enqueue or dequeue
     * @param queueName the Queue name
     * @param prefix the String prefix for the method
     * @param suffix the String suffix for the method
     */
	private String textIfISR(
			TranslatorBlock tbvar, 
			String queueName,
			String prefix,
			String suffix) throws SocketNullException
	{
		if ("enqueue FI".equals(label)) {			// SendToBack
			prefix = prefix + "Send";
		}
		else if ("push LI".equals(label)) {		// SendToFront
			prefix = prefix + "SendToFront";
		}
		else if ("dequeue/pop FO".equals(label)) {		// Receive
			prefix = prefix + "Receive";
		}
		///////////////////////////////////////////////////////////////////////////////
		//// only available on freeRTOS 8.0.1, not with the current version 7.4.2 /////
		///////////////////////////////////////////////////////////////////////////////
		else if ("overwrite".equals(label)) {			// Overwrite
			prefix = prefix + "Overwrite";
			/*throw new BlockException(blockId, 
					"You can't use \"overwrite\" in an Interrupt Block. This method is\n" +
					"only available with freeRTOS 8.0.1 and later.\n" +
					"Current version of FreeRTOS_AVR library support freeRTOS 7.4.2");	*/
		}		
		
		
		if ("copy FO".equals(label)) {				// Peek
			prefix = prefix + "Peek";
			suffix = ", &" +					// return the parameters :
					tbvar.toCode() +  				// pvItemToQueue only
					suffix;
			/*throw new BlockException(blockId, 
					"You can't use \"copy FO\" in an Interrupt Block. This method is\n" +
					"only available with freeRTOS 8.0.1 and later.\n" +
					"Current version of FreeRTOS_AVR library support freeRTOS 7.4.2");	*/
		}		
		else if ("reset".equals(label)) {				// Reset the queue
			prefix = prefix + "Reset";
			//suffix = " )";
			throw new BlockException(blockId, 
					"You can't use \"reset\" in an Interrupt Block.");	
		}		
		else {
			suffix = ", &" +						// return the parameters :
					tbvar.toCode() + 					// pvItemToQueue
					", (signed portBASE_TYPE*)&xHigherPriorityTaskWoken" + 
					suffix;							// xHigherPriorityTaskWoken

		}
		///////////////////////////////////////////////////////////////////////////////
		//// only available on freeRTOS 8.0.1, not with the current version 7.4.2 /////
		///////////////////////////////////////////////////////////////////////////////
		    
		String ret = prefix + "FromISR(x" + 	// adapt for ISR
				queueName + 
				suffix;			
		
		translator.addSemaphoreLocalVariable();
		
		return ret;
	}


}
