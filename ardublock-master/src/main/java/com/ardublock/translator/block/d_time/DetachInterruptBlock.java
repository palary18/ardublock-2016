package com.ardublock.translator.block.d_time;

//import java.util.Spliterator;

import com.ardublock.ArduBlockTool;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.renderable.RenderableBlock;

import com.ardublock.translator.block.TranslatorBlock;

public class DetachInterruptBlock extends TranslatorBlock
{
	public DetachInterruptBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	/*@Override
	public String toCode() throws SocketNullException {
		
		String ret = "\t";
		TranslatorBlock interruptPin = this.getRequiredTranslatorBlockAtSocket(0);
		
		// board type --> search the index i in boardType 
		String name = ArduBlockTool.getBoardName();
				
		int i = 0;
		int j = 0;
		boolean nameFind = false;

		search:
			for ( ; i < ProcInterruptBlock.boardType.length; i++) {
				for (j = 0; j < ProcInterruptBlock.boardType[i].length; j++) {
					if (ProcInterruptBlock.boardType[i][j] == name) {
						nameFind = true;
						break search;
					}
				}
			}
		
		int boardTypeRow = i;
		if (nameFind) {
			//System.out.println(name +" Trouv� " +  " � l'index " + i + ", " + j);
		} else {
			//System.out.println(name + "n'existe pas dans le tableau � deux dimensions");
			boardTypeRow = 0;
		}
				
		// interrupt or PCinterrupt
		int iPin = Integer.parseInt(interruptPin.toCode());
		if (!(iPin > ProcInterruptBlock.interruptNumber[boardTypeRow].length-1)) {
			if (ProcInterruptBlock.interruptNumber[boardTypeRow][iPin] == null) {
				if (ProcInterruptBlock.pcintNumber[boardTypeRow][iPin] == null) {
					throw new BlockException(blockId, "there is no interrupt possible on this pin");
				}
				else {
					iPin = ProcInterruptBlock.pcintNumber[boardTypeRow][iPin];
					ret = ret + "PCintPort::detachInterrupt(" + String.valueOf(iPin) + ");";
				}
			}
			else {
				iPin = ProcInterruptBlock.interruptNumber[boardTypeRow][iPin];
				ret = ret + "\tdetachInterrupt(" + String.valueOf(iPin) + ");";
			}
		}
		else {
			throw new BlockException(blockId, "there is no interrupt possible on this pin");
		}
		
		return ret;
	}*/
	public String toCode() throws SocketNullException
	{
	String ret = "";
	String interruptPinValue = null;
	String interruptModeValue = null;
	
	TranslatorBlock interrupt = this.getRequiredTranslatorBlockAtSocket(0);
	String tyty = interrupt.toCode();
	
	Iterable<RenderableBlock> allRenderableBlock = translator.getWorkspace().getRenderableBlocks();//

	//all renderable blocks listing
	for (RenderableBlock renderableBlock:allRenderableBlock)//
	{
		Block block = renderableBlock.getBlock();
		
		//search first block of each stacking
		if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
		{
			// searching the interruption
			if (block.getGenusName().equals("procedure_interrupt") && block.getBlockLabel().equals(interrupt.label))
			{
				Iterable<BlockConnector> allSockets = block.getSockets();
				for (BlockConnector socket:allSockets)
				{
					// getting the pin name
					if (socket.getLabel().equals("#")) {
						Block interruptPin = translator.getBlock(socket.getBlockID());
						interruptPinValue = interruptPin.getBlockLabel();
					}
					// getting the mode
					if (socket.getLabel().equals("mode")) {
						Block interruptMode = translator.getBlock(socket.getBlockID());
						interruptModeValue = interruptMode.getBlockLabel();
					}
				}
				break;
			}
		}
		
	}
	
	// board type --> search the index i in boardType 
	int boardTypeRow = ProcInterruptBlock.boardType();
			
	// interrupt or PCinterrupt
	if (!(interruptPinValue == null)) {				// if the interrupt pin exists then we want to detach the quoted interrupt
		int iPin = Integer.parseInt(interruptPinValue);
		if (!(iPin > ProcInterruptBlock.interruptNumber[boardTypeRow].length-1)) {
			if (ProcInterruptBlock.interruptNumber[boardTypeRow][iPin] == null) {
				if (ProcInterruptBlock.pcintNumber[boardTypeRow][iPin] == null) {
					throw new BlockException(blockId, "there is no interrupt possible on this pin");
				}
				else {
					iPin = ProcInterruptBlock.pcintNumber[boardTypeRow][iPin];
					//translator.addHeaderFile("PinChangeInt.h");
					ret = "\tPCintPort::detachInterrupt(" + String.valueOf(iPin) + ");\n";
					//translator.addSetupCommand(setupCode);
				}
			}
			else {
				iPin = ProcInterruptBlock.interruptNumber[boardTypeRow][iPin];
				ret = "\tdetachInterrupt(" + String.valueOf(iPin) + ");\n";
				//translator.addSetupCommand(setupCode);
			}
		}
		else {
			throw new BlockException(blockId, "there is no interrupt possible on this pin");
		}
	}
	else {											// if null then we want to detach all interrupts
		ret = "\tnoInterrupts();\n";
	}

	return ret;

	}
}
