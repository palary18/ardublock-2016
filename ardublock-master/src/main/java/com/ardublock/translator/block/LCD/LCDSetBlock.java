package com.ardublock.translator.block.LCD;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.TranslatorBlockFactory;
import com.ardublock.translator.block.common.Common;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;
import edu.mit.blocks.renderable.RenderableBlock;

public class LCDSetBlock extends TranslatorBlock
{
	public LCDSetBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();					
	
	public String toCode() throws SocketNullException
	{
		Iterable<RenderableBlock> allRenderableBlock = translator.getWorkspace().getRenderableBlocks();//

		//all renderable blocks listing
		for (RenderableBlock renderableBlock:allRenderableBlock)//
		{
			Block block = renderableBlock.getBlock();
			
			//search first block of each stacking
			if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
			{
				if(block.getBlockLabel().equals("lcd"))
				{			
					TranslatorBlock textTB = Code(block, 0);
					TranslatorBlock initTB = Code(block, 1);
					if (initTB == null)
					{
						throw new BlockException(block.getBlockID(), "give an init list");
					}
					Common.isLibraryExist(blockId, translator, "LiquidCrystal.h");
					translator.addHeaderFile("LiquidCrystal.h");
					initTB.toCode();	// Gives the initialisation of the LCD : begin, clear
					// add text init less tab to Setup ; 
					translator.addSetupCommand(InitText(textTB).trim());
				}
			}
		}
		
		int i=0;
		TranslatorBlock tb = this.getTranslatorBlockAtSocket(i);
		String ret = "";
		/*if (tb != null) {
			ret = InitText(tb);// "\t" + InitText(tb);
		}*/
		while (tb != null)
		{
			ret = ret + InitText(tb);
			i=i+1;
			tb = getTranslatorBlockAtSocket(i);
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
	
	private String InitText(TranslatorBlock tb) throws SocketNullException
	{
		String ret = "";
		//TranslatorBlock tb = this.getTranslatorBlockAtSocket(1);
		
		if (tb != null) {
			String text = new String(tb.toCode());
			if (text.length() < 3) {
				return ret + "\tlcd.print(" + text + ");\n";
			}
			while (text.charAt(1) == '#') {
				char ch = text.charAt(2);
				switch (ch) {
	            case 'A': ret = ret + "\tlcd.autoscroll();\n"; 			break;
	            case 'a': ret = ret + "\tlcd.noAutoscroll();\n"; 		break;
	            case 'B': ret = ret + "\tlcd.blink();\n";            	break;
	            case 'b': ret = ret + "\tlcd.noBlink();\n";         	break;
	            case 'c': case 'C': ret = ret + "\tlcd.clear();\n";  	break;
	            case 'D': ret = ret + "\tlcd.display();\n";          	break;
	            case 'd': ret = ret + "\tlcd.noDisplay();\n";        	break;
	            case 'h': case 'H': ret = ret + "\tlcd.home();\n";   	break;
	            case 'L': ret = ret + "\tlcd.scrollDisplayLeft();\n";	break;
	            case 'R': ret = ret + "\tlcd.scrollDisplayRight();\n";	break;
	            case 'l': ret = ret + "\tlcd.leftToRight();\n";      	break;
	            case 'r': ret = ret + "\tlcd.rightToLeft();\n";      	break;
	            case 'U': ret = ret + "\tlcd.cursor();\n";          	break;
	            case 'u': ret = ret + "\tlcd.noCursor();\n";         	break;
	            case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
	            	int row = ch - '0' - 1;
					int col = (text.charAt(3) - '0') * 10;
		            col += (text.charAt(4) - '0') - 1;
		            ret = ret + "\tlcd.setCursor(" + col + ", " + row + ");\n";
		            text = text.substring(3, text.length());
					text = "\"" + text;
					break;
	            //case '(': ret = ret + "\tlcd.setCursor(";
	            default : return ret + "\tlcd.print(" + text + ");\n";
				}
				
				text = text.substring(3, text.length());
				text = "\"" + text;
			}
			ret = ret + "\tlcd.print(" + text + ");\n";
		}
		return ret;
	}
	
}
