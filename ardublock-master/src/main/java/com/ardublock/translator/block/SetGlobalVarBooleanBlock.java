package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.renderable.RenderableBlock;

public class SetGlobalVarBooleanBlock extends TranslatorBlock
{
	protected SetGlobalVarBooleanBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		String internalVariableName = translator.getBooleanVariable(label.substring(4));
		String varName = translator.getBlock(blockId).getBlockLabel();
		String childElement = null;	// valeur de la variable				
		
		Iterable<RenderableBlock> allRenderableBlock = translator.getWorkspace().getRenderableBlocks();

		//all renderable blocks listing
		for (RenderableBlock renderableBlock:allRenderableBlock)//
		{
			Block block = renderableBlock.getBlock();
			
			//search first block of each stacking
			if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
			{
				if(block.getBlockLabel().equals(varName.substring(4)))		// substring removes the four letters "set " at the begenning of the varName
				{
					BlockConnector socketConnector = block.getSocketAt(0);
					Block blockChild = translator.getBlock(socketConnector.getBlockID());
					
					if (blockChild != null) {
						TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();					
						TranslatorBlock rootTB = translatorBlockFactory.buildTranslatorBlock(translator, blockChild.getBlockID(), blockChild.getGenusName(), "", "", blockChild.getBlockLabel());
						childElement = rootTB.toCode();
					}
						
				}
			}
		}
		
		if (internalVariableName == null)
		{
			internalVariableName = translator.buildVariableName(label.substring(4));
			translator.addGlobalVariable("b"+label.substring(4), internalVariableName);
			String definition = internalVariableName;
			definition = "boolean " + definition;

			if (childElement != null) {
				definition = definition + " = " + childElement;
			}
			translator.addDefinitionCommand(definition + ";");
		}
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "\t" + internalVariableName + " = " + tb.toCode() + ";\n";
		return codePrefix + ret + codeSuffix;
	}

}
