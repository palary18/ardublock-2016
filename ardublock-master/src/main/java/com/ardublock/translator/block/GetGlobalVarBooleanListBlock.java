package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.renderable.RenderableBlock;

public class GetGlobalVarBooleanListBlock extends TranslatorBlock
{
	protected GetGlobalVarBooleanListBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();					
	
	public String toCode() throws SocketNullException
	{
		String internalVariableName = translator.getBooleanVariable(label);
		String varName = translator.getBlock(blockId).getBlockLabel();
		String labelChild = null;	// valeur de la variable	
		
		Iterable<RenderableBlock> allRenderableBlock = translator.getWorkspace().getRenderableBlocks();//

		//all renderable blocks listing
		for (RenderableBlock renderableBlock:allRenderableBlock)//
		{
			Block block = renderableBlock.getBlock();
			
			//search first block of each stacking
			if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
			{
				if(block.getBlockLabel().equals(varName))
				{			
					TranslatorBlock sizeTB = Code(block, 1);
					TranslatorBlock listTB = Code(block, 0);
					if (sizeTB == null && listTB == null)
					{
						throw new BlockException(block.getBlockID(), "give a list or an index");
					}

					labelChild = (sizeTB != null) ? " [ " + sizeTB.toCode() + " ]" : " [ ]";
					labelChild = labelChild + ((listTB != null) ? " = " + listTB.toCode() : "");
				}
			}
		}
		
		if (internalVariableName == null)
		{
			internalVariableName = translator.buildVariableName(label);
			translator.addBooleanVariable(label, internalVariableName);
			String definition = internalVariableName;
			definition = "boolean " + definition;

			if (labelChild != null) {
				definition = definition + labelChild;
			}
			translator.addDefinitionCommand(definition + ";");
		}
		String ret = internalVariableName;
		return codePrefix + ret + codeSuffix;
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
