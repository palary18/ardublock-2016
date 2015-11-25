package com.ardublock.translator.block.j_subroutine;

import com.ardublock.translator.Translator;
import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.codeblocks.BlockConnector;

import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class ProcBlock extends TranslatorBlock
{
	public ProcBlock(Long blockId, Translator translator)
	{
		super(blockId, translator);
	}

	public String toCode() throws SocketNullException
	{
/*		// function name
		String ret = "void ";
		Block bl = translator.getBlock(blockId);
		ret = ret + bl.getBlockLabel();
		// arguments
		int i=0;
		ret = ret + "(";
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);

		while (translatorBlock != null)
		{
			if (i > 0) {ret = ret + ", ";};
			ret = ret + translatorBlock.toCode();
			i=i+1;
			translatorBlock = getTranslatorBlockAtSocket(i);//translatorBlock.nextTranslatorBlock();
		}		
		ret = ret + ") {\n";
		// function body
		TranslatorBlock tb=nextTranslatorBlock();		
		while (tb != null)
		{
			String a = tb.label;
			//System.out.println(a);
			ret = ret + tb.toCode();
			if (a.equals("return")) {
				Translator tbSocket = tb.getTranslator();
				Long SocketId = tb.blockId;
				Block tbBlock = tbSocket.getBlock(SocketId);
				BlockConnector tbblockConnector = tbBlock.getSocketAt(0);
				String kindReturn = tbblockConnector.getKind();
				String[] t = ret.split("[.,;:?!' ]+");
				if (kindReturn.equals("number"))
				{
					ret = ret.replaceFirst(t[0], "int");
				}
				else if (kindReturn.equals("string"))
				{
					ret = ret.replaceFirst(t[0], "char *");
				}
				else if (kindReturn.equals("boolean"))
				{
					ret = ret.replaceFirst(t[0], "boolean");
				}
				//ret = ret.replaceFirst(t[0], kindReturn);
			}
			tb = tb.nextTranslatorBlock();
		}
		String localVar = this.getTranslator().translateVar();

		ret = localVar + ret + "}\n\n";
		return ret;
*/	
		
		//String ret = " ";//"void ";
		Block bl = translator.getBlock(blockId);
		String func = bl.getBlockLabel();
		// arguments
		int i=0;
		String args = "(";
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);

		while (translatorBlock != null)
		{
			if (i > 0) {args = args + ", ";};
			args = args + translatorBlock.toCode();
			i=i+1;
			translatorBlock = getTranslatorBlockAtSocket(i);//translatorBlock.nextTranslatorBlock();
		}		
		args = args + ") {\n";
		// function body
		TranslatorBlock tb = nextTranslatorBlock();
		String body = "";
		String type = "void ";
		while (tb != null)
		{
			String a = tb.label;
			//System.out.println(a);
			body = body + tb.toCode();
			if (a.equals("return")) {
				Translator tbSocket = tb.getTranslator();
				Long SocketId = tb.blockId;
				Block tbBlock = tbSocket.getBlock(SocketId);
				BlockConnector tbblockConnector = tbBlock.getSocketAt(0);
				String kindReturn = tbblockConnector.getKind();
				//String[] t = ret.split("[.,;:?!' ]+");
				if (kindReturn.equals("number") || kindReturn.equals("number-list"))
				{
					type = "int ";
				}
				else if (kindReturn.equals("string") || kindReturn.equals("string-list"))
				{
					type = "char *";
				}
				else if (kindReturn.equals("boolean") || kindReturn.equals("boolean-list"))
				{
					type = "boolean ";
				}
				//ret = ret.replaceFirst(t[0], kindReturn);
			}
			tb = tb.nextTranslatorBlock();
		}
		// local variables
		String localVar = this.getTranslator().translateVar();
		// all
		//ret = localVar + ret + "}\n\n";
		return type + func + args + localVar + body + "}\n\n";
	
	}
}
