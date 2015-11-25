package com.ardublock.translator.block.a_loop;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.renderable.RenderableBlock;

public class SemaphoreISR extends TranslatorBlock
{
	public SemaphoreISR(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{

		String ret = "";

		Iterable<RenderableBlock> allRenderableBlock = translator.getWorkspace().getRenderableBlocks();//

		//all renderable blocks listing
		for (RenderableBlock renderableBlock:allRenderableBlock)//
		{
			Block block = renderableBlock.getBlock();
			
			//search first block of each stacking
			if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
			{
				if (block.getGenusName().equals("procedure_interrupt"))
				{
					ret = textIfISR(block, ret, "TakeFromISR");
				}
			}
		}
		
		ret = ret + ");\n";
		return ret;
	}
	
	private String textIfISR(Block bl, String aRetourner, String varPart)
	{
		while (bl != null)
		{
			/*System.out.println(blockId);
			System.out.println(bl.getBlockID());
			System.out.println(bl.getGenusName());
			System.out.println(bl.getBlockLabel());*/
			if (bl.getBlockID().equals(blockId))
			{
				aRetourner = "\txSemaphore" + 
						varPart + 
						"(x" + 
						label + 
						", (signed portBASE_TYPE*)&xHigherPriorityTaskWoken";
				translator.addSemaphoreLocalVariable();
			}
			
			Iterable<BlockConnector> allSocketsBlock = bl.getSockets();
			for (BlockConnector socketBlock:allSocketsBlock)//
			{
				Block bb = translator.getBlock(socketBlock.getBlockID());
				textIfISR(bb, aRetourner, varPart);
			}
			bl = translator.getBlock(bl.getAfterBlockID());
			
		}
		return aRetourner;
		
	}
	

}
