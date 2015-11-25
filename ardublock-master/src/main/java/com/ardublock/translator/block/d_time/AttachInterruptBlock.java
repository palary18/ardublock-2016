package com.ardublock.translator.block.d_time;

import com.ardublock.ArduBlockTool;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.renderable.RenderableBlock;

public class AttachInterruptBlock extends TranslatorBlock
{
	public AttachInterruptBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException {
	String ret = "";
	String interruptPinValue = null;
	String interruptModeValue = null;
	
	TranslatorBlock interrupt = this.getRequiredTranslatorBlockAtSocket(0);
	
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
			}
		}
		
	}
	
	// board type --> search the index i in boardType 
	int boardTypeRow = ProcInterruptBlock.boardType();
			
	// interrupt or PCinterrupt
	if (!(interruptPinValue == null)) {
		int iPin = Integer.parseInt(interruptPinValue);
		if (!(iPin > ProcInterruptBlock.interruptNumber[boardTypeRow].length-1)) {
			if (ProcInterruptBlock.interruptNumber[boardTypeRow][iPin] == null) {
				if (ProcInterruptBlock.pcintNumber[boardTypeRow][iPin] == null) {
					throw new BlockException(blockId, "there is no interrupt possible on this pin");
				}
				else {
					iPin = ProcInterruptBlock.pcintNumber[boardTypeRow][iPin];
					//translator.addHeaderFile("PinChangeInt.h");
					ret = "\tPCintPort::attachInterrupt(" + String.valueOf(iPin) + ", &" + interrupt.label + ", " + interruptModeValue + ");\n";
					//translator.addSetupCommand(setupCode);
				}
			}
			else {
				iPin = ProcInterruptBlock.interruptNumber[boardTypeRow][iPin];
				ret = "\tattachInterrupt(" + String.valueOf(iPin) + ", " + interrupt.label + ", " + interruptModeValue + ");\n";
				//translator.addSetupCommand(setupCode);
			}
		}
		else {
			throw new BlockException(blockId, "there is no interrupt possible on this pin");
		}
	}
	else {
		ret = "\tinterrupts();\n";
	}
	return ret;

	}
}
