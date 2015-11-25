package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.common.Common;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.renderable.RenderableBlock;

public class StepperBlock extends TranslatorBlock
{
	protected StepperBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();					
	
	public String toCode() throws SocketNullException
	{
		String stepperName = translator.getBlock(blockId).getBlockLabel();
		
		Iterable<RenderableBlock> allRenderableBlock = translator.getWorkspace().getRenderableBlocks();//

		//all renderable blocks listing
		for (RenderableBlock renderableBlock:allRenderableBlock)//
		{
			Block block = renderableBlock.getBlock();
			
			//search first block of each stacking
			if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
			{
				if(block.getInitialLabel().equals(stepperName))//.substring(4)))
				{			
					Common.isLibraryExist(blockId, translator, "Stepper.h");
					translator.addHeaderFile("Stepper.h");
					
					TranslatorBlock angleTB = Code(block, 0);
					String command = "Stepper " + stepperName + "(" + angleTB.toCode() + ", ";
					
					TranslatorBlock pinTB = Code(block, 1);
					command = command + pinTB.toCode() + ", ";
					pinTB = Code(block, 4);
					command = command + pinTB.toCode() + ");";
					translator.addDefinitionCommand(command);
					
					pinTB = Code(block, 2);
					Long output = Long.parseLong(pinTB.toCode());
					translator.addOutputPin(output);
					translator.addSetupCommand("digitalWrite(" + output + ", HIGH);");
					
					pinTB = Code(block, 3);
					output = Long.parseLong(pinTB.toCode());
					translator.addOutputPin(output);
					translator.addSetupCommand("digitalWrite(" + output + ", HIGH);");
					
					
								
					//command = command.substring(0, command.length()-2) + ");";
				}
			}
		}
		
		//int i=0;
		TranslatorBlock NbTB = this.getTranslatorBlockAtSocket(0);
		TranslatorBlock speedTB = this.getTranslatorBlockAtSocket(1);
		String ret = "";
		
		if (speedTB != null)
		{
			ret = ret + "\t" + stepperName + ".setSpeed( " + speedTB.toCode() + " );\n";
		}
		if (NbTB != null)
		{
			ret = ret + "\t" + stepperName + ".step( " + NbTB.toCode() + " );\n";
		}
		if (!(NbTB != null && speedTB != null))
		{
			throw new BlockException(this.blockId, "gives a number of steps or a speed.");
		}

		return ret;
	}
	
	private TranslatorBlock Code(Block block, int index)
	{
		BlockConnector connector = block.getSocketAt(index);
		Block bl = translator.getBlock(connector.getBlockID());
		if (bl != null) {
			return translatorBlockFactory.buildTranslatorBlock(translator, bl.getBlockID(), bl.getGenusName(), "", "", bl.getBlockLabel());
		}
		else {
			return null;
		}		
	}
	
}