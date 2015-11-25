package com.ardublock.translator.block.d_time;

import java.awt.List;
import java.util.Iterator;
import java.util.ListIterator;

import com.ardublock.ArduBlockTool;
import com.ardublock.translator.Translator;
import edu.mit.blocks.codeblocks.Block;
import javax.swing.JOptionPane;

import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.common.Common;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;

public class ProcInterruptBlock extends TranslatorBlock
{
	static Integer no = null;
	
	public static String boardType[][] = {
			{"uno", "ethernet", "fio", "nano", "mini", "bt", "lilypad", "pro", "atmegang"},
			{"diecimila", "mega", "megaADK"},
			{"leonardo", "yun", "micro", "esplora", "LilyPadUSB", "robotControl", "robotMotor"},
			{"arduino_due_x_dbg", "arduino_due_x"},
	}; 
	
	public static Integer interruptNumber[][] = { 
			//	pin number
			//	0,	1,	2,	3,	4,	5,	6,	7,	8,	9,	10,	11,	12,	13,	14,	15,	16,	17,	18,	19,	20,	21,	22,	23,	24,	25,	26,	27,	28,	29,	30,	31,	32,	33,	34,	35,	36,	37,	38,	39,	40,	41,	42,	43,	44,	45,	46,	47,	48,	49,	50,	51,	52,	53,	54,	55,	56,	57,	58,	59,	60,	61,	62,	63,	64,	65,	66,	67,	68,	69,	70,	71
			{	// Uno, Ethernet, Fio, Nano, Mini, BT, LilyPad, Pro or Pro Mini
				no,	no, 0,	1,	no,	no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no
				},
			{	// Mega2560, mega ADK
				no,	no, 4,	5,	no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, 3,	2,	1,	0,	no,	no, no,	no, no,	no, no,	no, no,	no,	no, no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no
				},
			{	// Leonardo, yun, micro, esplora, LilyPad USB, Arduino Robot Control, Arduino Robot Motor
				2,	3, 	1,	0,	no,	no, no,	6,	no, no,	no, no,	no, no,	no, no,	no, no,	5,	4,	3,	2,	no, no
				},
			{	// Due
				0,	1,	2,	3,	4,	5,	6,	7,	8,	9,	10,	11,	12,	13,	14,	15,	16,	17,	18,	19,	20,	21,	22,	23,	24,	25,	26,	27,	28,	29,	30,	31,	32,	33,	34,	35,	36,	37,	38,	39,	40,	41,	42,	43,	44,	45,	46,	47,	48,	49,	50,	51,	52,	53,	54,	55,	56,	57,	58,	59,	60,	61,	62,	63,	64,	65,	66,	67,	68,	69,	70,	71
				}
			};
			
	public static Integer pcintNumber[][] = { 
			//	pin number
			//	0,	1,	2,	3,	4,	5,	6,	7,	8,	9,	10,	11,	12,	13,	14,	15,	16,	17,	18,	19,	20,	21,	22,	23,	24,	25,	26,	27,	28,	29,	30,	31,	32,	33,	34,	35,	36,	37,	38,	39,	40,	41,	42,	43,	44,	45,	46,	47,	48,	49,	50,	51,	52,	53,	54,	55,	56,	57,	58,	59,	60,	61,	62,	63,	64,	65,	66,	67,	68,	69,	70,	71
			{	// Uno, Ethernet
				0,	1,	2,	3,	4,	5,	6,	7,	8,	9,	10,	11,	12,	13,	14,	15,	16,	17,	18,	19
				},
			{	// Mega2560, mega ADK
				0,	no, no,	no, no,	no, no,	no, no,	no, 10,	11,	12,	13, 14,	15, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no,	no, no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no,	no, no,	50,	51,	52,	53,	no, no, no,	no, no,	no, no,	no, 62,	63,	64,	65,	66,	67,	68,	69
				},
			{	// Leonardo, yun
				no,	no, no,	no, no,	no, no,	no, 8,	9,	10,	11,	no,	no, no,	no, no,	no, no,	no, no,	no, no,	no
				},
			{	// Due
				no,	no,	no, no,	no,	no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no,	no, no,	no, no,	no, no,	no, no, no,	no, no,	no, no
				}
			};
			
	
	public ProcInterruptBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException
	{
		// interruption name
		String ret = "void ";
		Block bl = translator.getBlock(blockId);
		String InterruptName = bl.getBlockLabel();
		ret = ret + InterruptName;
		
		// arguments
		ret = ret + "() {\n";
		TranslatorBlock interruptPin = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock interruptMode = this.getRequiredTranslatorBlockAtSocket(1);

		int boardTypeRow = boardType();
						
		// interrupt or PCinterrupt
		int iPin = Integer.parseInt(interruptPin.toCode());
		if (!(iPin > interruptNumber[boardTypeRow].length-1)) {
			if (interruptNumber[boardTypeRow][iPin] == null) {
				if (pcintNumber[boardTypeRow][iPin] == null) {
					throw new BlockException(blockId, "there is no interrupt possible on this pin");
				}
				else {
					iPin = pcintNumber[boardTypeRow][iPin];
					
					Common.isLibraryExist(blockId, translator, "PinChangeInt.h");
					translator.addHeaderFile("PinChangeInt.h");					
					String setupCode = "PCintPort::attachInterrupt(" + String.valueOf(iPin) + ", &" + InterruptName + ", " + interruptMode.toCode() + ");";
					translator.addSetupCommand(setupCode);
				}
			}
			else {
				iPin = interruptNumber[boardTypeRow][iPin];
				String setupCode = "attachInterrupt(" + String.valueOf(iPin) + ", " + InterruptName + ", " + interruptMode.toCode() + ");";
				translator.addSetupCommand(setupCode);
			}
		}
		else {
			throw new BlockException(blockId, "there is no interrupt possible on this pin");
		}
		
		// function body
		TranslatorBlock tb=nextTranslatorBlock();		
		String body = "";
		while (tb != null)
		{
			String a = tb.label;
			//System.out.println(a);
			body = body + tb.toCode();
			if (a.equals("return")) {
				throw new BlockException(blockId, "No 'return' from an interrupt function");
			}
			if (a.equals("Delay") || a.equals("delay") || a.equals("millis") || a.equals("micros")) {
				JOptionPane.showMessageDialog(null,"'delay' won't work and the value returned by 'millis' will not increment.");
			}
			if (a.equals("Serial reveive message")) {
				JOptionPane.showMessageDialog(null,"Serial data received while in the function may be lost.");
			}
			tb = tb.nextTranslatorBlock();
		}
		
		////////////////////////////////////////////////////////////////////////////////
		//// queue or semaphore patch												////
		////////////////////////////////////////////////////////////////////////////////
		if (body.contains("FromISR("))//xSemaphoreGiveFromISR(") || (body.contains("xSemaphoreTakeFromISR("))) 
		{
			body = body + "\n\tif( xHigherPriorityTaskWoken == 1 ) { vPortYield(); }\n";
		}
		////////////////////////////////////////////////////////////////////////////////
		
		String localVar = this.getTranslator().translateVar();
		ret = ret + localVar + body + "}\n\n";
		
		return ret;
	}
	
	public static int boardType( )
	{
		// board type --> search the index i in boardType 
		String name = ArduBlockTool.getBoardName();
		int i = 0;
		int j = 0;
		
		search:
			for ( ; i < boardType.length; i++) {
				for (j = 0; j < boardType[i].length; j++) {
					if (boardType[i][j] == name) {
						break search;
					}
				}
			}
		if (i == 4) i = 0;
		return i;
	}

}
