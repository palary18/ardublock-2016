package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.d_time.AttachInterruptBlock;
import com.ardublock.translator.block.d_time.DelayBlock;
import com.ardublock.translator.block.d_time.DelayMicrosecondsBlock;
import com.ardublock.translator.block.d_time.DetachInterruptBlock;
import com.ardublock.translator.block.d_time.DigitalChangeBlock;
import com.ardublock.translator.block.d_time.DigitalFallingBlock;
import com.ardublock.translator.block.d_time.DigitalRisingBlock;
import com.ardublock.translator.block.d_time.MicrosBlock;
import com.ardublock.translator.block.d_time.MillisBlock;
import com.ardublock.translator.block.d_time.ProcInterruptBlock;
import com.ardublock.translator.block.dfrobot.DfrobotAnalogInputBlock;
import com.ardublock.translator.block.dfrobot.DfrobotAnalogOuptutBlock;
import com.ardublock.translator.block.dfrobot.DfrobotDigitalInoutBlock;
import com.ardublock.translator.block.dfrobot.DfrobotDigitalOutputBlock;
import com.ardublock.translator.block.dfrobot.DfrobotLedBlock;
import com.ardublock.translator.block.dfrobot.DfrobotLedPwmBlock;
import com.ardublock.translator.block.dfrobot.DfrobotPushButtonBlock;
import com.ardublock.translator.block.dfrobot.DfrobotServoBlock;
import com.ardublock.translator.block.dfrobot.DfrobotToneBlock;
import com.ardublock.translator.block.dfrobot.DfrobotToneDelayBlock;
import com.ardublock.translator.block.seeedstudio.GroveJoyStickButtonBlock;
import com.ardublock.translator.block.tinker.TinkerAccmeterBlock;
import com.ardublock.translator.block.tinker.TinkerButtonBlock;
import com.ardublock.translator.block.tinker.TinkerHallBlock;
import com.ardublock.translator.block.tinker.TinkerInputPortBlock;
import com.ardublock.translator.block.tinker.TinkerLDRBlock;
import com.ardublock.translator.block.tinker.TinkerLEDBlock;
import com.ardublock.translator.block.tinker.TinkerLEDPwmBlock;
import com.ardublock.translator.block.tinker.TinkerLinearPotentiometerBlock;
import com.ardublock.translator.block.tinker.TinkerMosfetBlock;
import com.ardublock.translator.block.tinker.TinkerMosfetPwmBlock;
import com.ardublock.translator.block.tinker.TinkerOutputPortBlock;
import com.ardublock.translator.block.tinker.TinkerRelayBlock;
import com.ardublock.translator.block.tinker.TinkerRotaryPotentiometerBlock;
import com.ardublock.translator.block.tinker.TinkerServoBlock;
import com.ardublock.translator.block.tinker.TinkerThermistorBlock;
import com.ardublock.translator.block.tinker.TinkerTiltBlock;
import com.ardublock.translator.block.tinker.TinkerTouchBlock;
import com.ardublock.translator.block.xinchejian.XinchejianDigitalReadBlock;
import com.ardublock.translator.block.xinchejian.XinchejianDigitalWriteBlock;
import com.ardublock.translator.block.xinchejian.XinchejianMotorBackwardBlock;
import com.ardublock.translator.block.xinchejian.XinchejianMotorForwardBlock;

import com.ardublock.translator.block.dfrobot.DfrobotLCDKeypadBlock;
import com.ardublock.translator.block.e_number.ConvertRealBlock;
import com.ardublock.translator.block.e_number.FalseBlock;
import com.ardublock.translator.block.e_number.NumberBlock;
import com.ardublock.translator.block.e_number.SetDigitalBlock;
import com.ardublock.translator.block.e_number.SetNumberBlock;
import com.ardublock.translator.block.e_number.SetStringBlock;
import com.ardublock.translator.block.e_number.VariableBooleanBlock;
import com.ardublock.translator.block.e_number.VariableNumberBlock;
import com.ardublock.translator.block.e_number.VariableRealBlock;
import com.ardublock.translator.block.e_number.VariableStringBlock;
import com.ardublock.translator.block.f_list.InitDigitalListBlock;
import com.ardublock.translator.block.f_list.InitListBlock;
import com.ardublock.translator.block.f_list.InitNumberListBlock;
import com.ardublock.translator.block.f_list.ListAddBlock;
import com.ardublock.translator.block.f_list.ListBlock;
import com.ardublock.translator.block.f_list.ListGetBlock;
import com.ardublock.translator.block.f_list.ListLenghtBlock;
import com.ardublock.translator.block.g_operators.AbsBlock;
import com.ardublock.translator.block.g_operators.AdditionBlock;
import com.ardublock.translator.block.g_operators.AndBlock;
import com.ardublock.translator.block.g_operators.BitwiseAndBlock;
import com.ardublock.translator.block.g_operators.BitwiseLeftBlock;
import com.ardublock.translator.block.g_operators.BitwiseNotBlock;
import com.ardublock.translator.block.g_operators.BitwiseOrBlock;
import com.ardublock.translator.block.g_operators.BitwiseRightBlock;
import com.ardublock.translator.block.g_operators.BitwiseXorBlock;
import com.ardublock.translator.block.g_operators.CosBlock;
import com.ardublock.translator.block.g_operators.DivisionBlock;
import com.ardublock.translator.block.g_operators.EqualBlock;
import com.ardublock.translator.block.g_operators.GreaterBlock;
import com.ardublock.translator.block.g_operators.GreaterEqualBlock;
import com.ardublock.translator.block.g_operators.LessBlock;
import com.ardublock.translator.block.g_operators.LessEqualBlock;
import com.ardublock.translator.block.g_operators.MaxBlock;
import com.ardublock.translator.block.g_operators.MinBlock;
import com.ardublock.translator.block.g_operators.ModuloBlock;
import com.ardublock.translator.block.g_operators.MultiplicationBlock;
import com.ardublock.translator.block.g_operators.NotBlock;
import com.ardublock.translator.block.g_operators.NotEqualBlock;
import com.ardublock.translator.block.g_operators.OrBlock;
import com.ardublock.translator.block.g_operators.PowBlock;
import com.ardublock.translator.block.g_operators.SinBlock;
import com.ardublock.translator.block.g_operators.SqrtBlock;
import com.ardublock.translator.block.g_operators.SubtractionBlock;
import com.ardublock.translator.block.g_operators.TanBlock;
import com.ardublock.translator.block.h_string.MessageBlock;
import com.ardublock.translator.block.h_string.SerialPrint1Block;
import com.ardublock.translator.block.h_string.SerialPrint1lnBlock;
import com.ardublock.translator.block.h_string.SerialPrint2Block;
import com.ardublock.translator.block.h_string.SerialPrint2lnBlock;
import com.ardublock.translator.block.h_string.SerialPrint3Block;
import com.ardublock.translator.block.h_string.SerialPrint3lnBlock;
import com.ardublock.translator.block.h_string.SerialPrintBlock;
import com.ardublock.translator.block.h_string.SerialPrintlnBlock;
import com.ardublock.translator.block.h_string.SerialRead1Block;
import com.ardublock.translator.block.h_string.SerialRead2Block;
import com.ardublock.translator.block.h_string.SerialRead3Block;
import com.ardublock.translator.block.h_string.SerialReadBlock;
import com.ardublock.translator.block.j_subroutine.ProcBlock;
import com.ardublock.translator.block.j_subroutine.ProcBooleanBlock;
import com.ardublock.translator.block.j_subroutine.ProcCallerBlock;
import com.ardublock.translator.block.j_subroutine.ProcGetBooleanBlock;
import com.ardublock.translator.block.j_subroutine.ProcGetNumberBlock;
import com.ardublock.translator.block.j_subroutine.ProcGetStringBlock;
import com.ardublock.translator.block.j_subroutine.ProcNumberBlock;
import com.ardublock.translator.block.j_subroutine.ProcReturnBlock;
import com.ardublock.translator.block.j_subroutine.ProcReturnGetterBlock;
import com.ardublock.translator.block.j_subroutine.ProcStringBlock;
import com.ardublock.translator.block.a_loop.LoopAllBlock;
import com.ardublock.translator.block.a_loop.LoopAllTasksBlock;
import com.ardublock.translator.block.a_loop.LoopBlock;
import com.ardublock.translator.block.a_loop.LoopDelayBlock;
import com.ardublock.translator.block.a_loop.LoopDelayUntilBlock;
import com.ardublock.translator.block.a_loop.LoopParallelBlock;
import com.ardublock.translator.block.a_loop.LoopResumeBlock;
import com.ardublock.translator.block.a_loop.LoopSuspendBlock;
import com.ardublock.translator.block.a_loop.QueueBlock;
import com.ardublock.translator.block.a_loop.QueueDeclareBlock;
import com.ardublock.translator.block.a_loop.QueueHandleBlock;
import com.ardublock.translator.block.a_loop.SemaphoreDeclareBlock;
import com.ardublock.translator.block.a_loop.SemaphoreGive2Block;
import com.ardublock.translator.block.a_loop.SemaphoreHandleBlock;
import com.ardublock.translator.block.a_loop.SemaphoreTake2Block;
import com.ardublock.translator.block.a_loop.SemaphoreTakeBlock;
import com.ardublock.translator.block.a_loop.SemaphoreTakeReturn2Block;
import com.ardublock.translator.block.a_loop.SemaphoreTakeReturnBlock;
import com.ardublock.translator.block.b_control.BreakBlock;
import com.ardublock.translator.block.b_control.ContinueBlock;
import com.ardublock.translator.block.b_control.DoWhileBlock;
import com.ardublock.translator.block.b_control.ForBlock;
import com.ardublock.translator.block.b_control.IfBlock;
import com.ardublock.translator.block.b_control.IfelseBlock;
import com.ardublock.translator.block.b_control.RepeatBlock;
import com.ardublock.translator.block.b_control.SwitchBlock;
import com.ardublock.translator.block.b_control.WhileBlock;
import com.ardublock.translator.block.c_pin.NoToneBlock;
import com.ardublock.translator.block.c_pin.PinReadAnalogBlock;
import com.ardublock.translator.block.c_pin.PinReadDigitalBlock;
import com.ardublock.translator.block.c_pin.PinTouchSensitiveBlock;
import com.ardublock.translator.block.c_pin.PinWriteAnalogBlock;
import com.ardublock.translator.block.c_pin.PinWriteDigitalBlock;
import com.ardublock.translator.block.c_pin.PulseInBlock;
import com.ardublock.translator.block.c_pin.ToneBlock;
import com.ardublock.translator.block.c_pin.ToneTimeBlock;
//import com.ardublock.translator.block.basic.InputPortBlock;
//import com.ardublock.translator.block.LCDKeypad;
import com.ardublock.translator.block.champol.ChampolLCDBlock;
import com.ardublock.translator.block.champol.ChampolDef_StringBlock;
import com.ardublock.translator.block.champol.ChampolLCDBClearBlock;
import com.ardublock.translator.block.champol.ChampolDef_ByteArrayBlock;
import com.ardublock.translator.block.champol.ChampolLCDSetCursorBlock;

import com.ardublock.translator.block.champol.ChampolserialvarprintBlock;
import com.ardublock.translator.block.champol.ChampolSerialCRBlock;


import com.ardublock.translator.block.L298.L298InitBlock;
import com.ardublock.translator.block.L298.L298SpeedBlock;
import com.ardublock.translator.block.L298.L298StopBlock;
import com.ardublock.translator.block.L298.L298RampBlock;
import com.ardublock.translator.block.L298.L298Ramp2Block;
import com.ardublock.translator.block.LCD.LCDListBlock;
import com.ardublock.translator.block.LCD.LCDSetBlock;
import com.ardublock.translator.block.LCD.LCDSetCursor;


public class TranslatorBlockFactory
{
	public TranslatorBlock buildTranslatorBlock(Translator translator, Long blockId, String blockName, String codePrefix, String codeSuffix, String label)
	{
		//System.out.println("block name : " + blockName + " captured");
		
		// Arduino control
		if (blockName.equals("loop"))
		{
			return new LoopBlock(blockId, translator);
		}
		if (blockName.equals("loop//"))
		{
			return new LoopParallelBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("resume"))
		{
			return new LoopResumeBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("suspend"))
		{
			return new LoopSuspendBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("delay//"))
		{
			return new LoopDelayBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("delay-until//"))
		{
			return new LoopDelayUntilBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("all"))
		{
			return new LoopAllBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("alltasks"))
		{
			return new LoopAllTasksBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("namerloop//"))
		{
			return new MessageBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
/*		if (blockName.equals("semaphore"))
		{
			return new SemaphoreGiveBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("takeOKsemaphore"))
		{
			return new SemaphoreTakeReturnBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("takersemaphore"))
		{
			return new SemaphoreTakeBlock(blockId, translator, ">>", codeSuffix, label);
		}
*/
		if (blockName.equals("semhandlesemdeclare"))
		{
			return new SemaphoreDeclareBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("semhandle"))
		{
			return new SemaphoreHandleBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("semgive"))
		{
			return new SemaphoreGive2Block(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("semtake"))
		{
			return new SemaphoreTake2Block(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("semtakeOK"))
		{
			return new SemaphoreTakeReturn2Block(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("qhandle"))
		{
			return new QueueDeclareBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("queuestackqhandle") || blockName.equals("queuestack"))
		{
			return new QueueHandleBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("qcmd-sendtoback") || 
				blockName.equals("qcmd-sendtofront") ||
				blockName.equals("qcmd-receive") ||
				blockName.equals("qcmd-overwrite") ||
				blockName.equals("qcmd-peek") ||
				blockName.equals("qcmd-reset") ||
				blockName.equals("qOK-sendtoback") || 
				blockName.equals("qOK-sendtofront") ||
				blockName.equals("qOK-receive") ||
				blockName.equals("qOK-overwrite") ||
				blockName.equals("qOK-peek") ||
				blockName.equals("qOK-reset"))
		{
			return new QueueBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("receiveOKqueue"))
		{
			return new SemaphoreTakeReturnBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("receiverqueue"))
		{
			return new SemaphoreTakeBlock(blockId, translator, ">>", codeSuffix, label);
		}
		
		if (blockName.equals("if"))
		{
			return new IfBlock(blockId, translator);
		}
		if (blockName.equals("ifelse"))
		{
			return new IfelseBlock(blockId, translator);
		}
		if (blockName.equals("while"))
		{
			return new WhileBlock(blockId, translator);
		}
		if (blockName.equals("dowhile"))
		{
			return new DoWhileBlock(blockId, translator);
		}
		if (blockName.equals("repeat"))
		{
			return new RepeatBlock(blockId, translator);
		}
		if (blockName.equals("for"))
		{
			return new ForBlock(blockId, translator, codePrefix, codeSuffix, label);//ForBlock(blockId, translator);
		}
		if (blockName.equals("switch"))
		{
			return new SwitchBlock(blockId, translator, codePrefix, codeSuffix, label);//ForBlock(blockId, translator);
		}
		if (blockName.equals("break"))
		{
			return new BreakBlock(blockId, translator);
		}
		if (blockName.equals("continue"))
		{
			return new ContinueBlock(blockId, translator);
		}
		
		//const number
		if (blockName.equals("digital-high") || blockName.equals("digital-high//"))
		{
			return new DigitalHighBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("digital-low") 
				|| blockName.equals("digital-low//") 
				|| blockName.equals("digital-low-INT"))
		{
			return new DigitalLowBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("digital-on"))
		{
			return new DigitalOnBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("digital-off"))
		{
			return new DigitalOffBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("digital-change-INT"))
		{
			return new DigitalChangeBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("digital-rising-INT"))
		{
			return new DigitalRisingBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("digital-falling-INT"))
		{
			return new DigitalFallingBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("digital-normal//"))
		{
			return new DigitalNormalBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("true"))
		{
			return new TrueBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("false"))
		{
			return new FalseBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
				
		if (blockName.equals("number") || blockName.equals("real_number"))
		{
			return new NumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("convert_real"))
		{
			return new ConvertRealBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		//Pin
		if (blockName.equals("pin-read-analog"))
		{
			return new PinReadAnalogBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pin-read-digital"))
		{
			return new PinReadDigitalBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pin-write-analog"))
		{
			return new PinWriteAnalogBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pin-write-digital"))
		{
			return new PinWriteDigitalBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("tone"))
		{
			return new ToneBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("tone_time"))
		{
			return new ToneTimeBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("no_tone"))
		{
			return new NoToneBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pulsein"))
		{
			return new PulseInBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("pin_00") || blockName.equals("pin_01") || blockName.equals("pin_02")
				|| blockName.equals("pin_03") || blockName.equals("pin_04") || blockName.equals("pin_05")
				|| blockName.equals("pin_06") || blockName.equals("pin_07") || blockName.equals("pin_08")
				|| blockName.equals("pin_09") || blockName.equals("pin_10") || blockName.equals("pin_11")
				|| blockName.equals("pin_12") || blockName.equals("pin_13") || blockName.equals("pin_14")
				|| blockName.equals("pin_15") || blockName.equals("pin_16") || blockName.equals("pin_17")
				|| blockName.equals("pin_18") || blockName.equals("pin_19"))
		{
			return new NumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pin_A0") || blockName.equals("pin_A1") || blockName.equals("pin_A2")
				|| blockName.equals("pin_A3") || blockName.equals("pin_A4") || blockName.equals("pin_A5"))
		{
			return new NumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pin_pwm3") || blockName.equals("pin_pwm5") || blockName.equals("pin_pwm6")
				|| blockName.equals("pin_pwm9") || blockName.equals("pin_pwm10") || blockName.equals("pin_pwm11"))
		{
			return new NumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pin_int0") || blockName.equals("pin_int1"))
		{
			return new NumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}

		//Local Variables
		if (blockName.equals("set_number") || blockName.equals("set_real_number")
				|| blockName.equals("set_number_addition") || blockName.equals("set_number_subtraction")
				|| blockName.equals("set_number_multiplication") || blockName.equals("set_number_division") 
				|| blockName.equals("set_number_bitwiseand")|| blockName.equals("set_number_bitwiseor"))
		{
			return new SetNumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		/*if (blockName.equals("set_number_addition"))
		{
			return new SetNumberAdditionBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("set_number_subtraction"))
		{
			return new SetNumberSubtractionBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("set_number_multiplication"))
		{
			return new SetNumberMultiplicationBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("set_number_division"))
		{
			return new SetNumberDivisionBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("set_number_bitwiseand"))
		{
			return new SetNumberBitwiseAndBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("set_number_bitwiseor"))
		{
			return new SetNumberBitwiseOrBlock(blockId, translator, codePrefix, codeSuffix, label);
		}	*/	

		if (blockName.equals("set_digital") || blockName.equals("set_digital_bitwiseand") || blockName.equals("set_digital_bitwiseor"))
		{
			return new SetDigitalBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		/*if (blockName.equals("set_digital_bitwiseand"))
		{
			return new SetDigitalBitwiseAndBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("set_digital_bitwiseor"))
		{
			return new SetDigitalBitwiseOrBlock(blockId, translator, codePrefix, codeSuffix, label);
		}*/
		
		if (blockName.equals("set_string"))
		{
			return new SetStringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}

		if (blockName.equals("init_number_list"))
		{
			return new InitNumberListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("init_digital_list"))
		{
			return new InitDigitalListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("init_list"))
		{
			return new InitListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}

		if (blockName.equals("variable_number"))
		{
			return new VariableNumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("variable_real_number"))
		{
			return new VariableRealBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("variable_string"))
		{
			return new VariableStringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("variable_digital"))
		{
			return new VariableBooleanBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("variable_number_list"))
		{
			return new VariableNumberListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}

		if (blockName.equals("variable_digital_list"))
		{
			return new VariableBooleanListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		
		//Time
		if (blockName.equals("delay"))
		{
			return new DelayBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("delay_microseconds"))
		{
			return new DelayMicrosecondsBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("millis"))
		{
			return new MillisBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("micros"))
		{
			return new MicrosBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("procedure_interrupt"))
		{
			return new ProcInterruptBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("namerprocedure_interrupt"))
		{
			return new MessageBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("detach_interrupt"))
		{
			return new DetachInterruptBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("attach_interrupt"))
		{
			return new AttachInterruptBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("allints"))
		{
			return new AllIntsBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
/*		if (blockName.equals("interrupt"))
		{
			return new InterruptBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("no_interrupt"))
		{
			return new NoInterruptBlock(blockId, translator, codePrefix, codeSuffix, label);
		}*/

		
		//Math
		if (blockName.equals("and"))
		{
			return new AndBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("not"))
		{
			return new NotBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("or"))
		{
			return new OrBlock(blockId, translator, codePrefix, codeSuffix, label);
		}

		if (blockName.equals("greater"))
		{
			return new GreaterBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("less"))
		{
			return new LessBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("equal"))
		{
			return new EqualBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("greater_equal"))
		{
			return new GreaterEqualBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("less_equal"))
		{
			return new LessEqualBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("not_equal"))
		{
			return new NotEqualBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("addition"))
		{
			return new AdditionBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("subtraction"))
		{
			return new SubtractionBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("multiplication"))
		{
			return new MultiplicationBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("division"))
		{
			return new DivisionBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("modulo"))
		{
			return new ModuloBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("bitwise_not"))
		{
			return new BitwiseNotBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("bitwise_and"))
		{
			return new BitwiseAndBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("bitwise_or"))
		{
			return new BitwiseOrBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("bitwise_xor"))
		{
			return new BitwiseXorBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("bitwise_left"))
		{
			return new BitwiseLeftBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("bitwise_right"))
		{
			return new BitwiseRightBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("min"))
		{
			return new MinBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("max"))
		{
			return new MaxBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("abs"))
		{
			return new AbsBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("pow"))
		{
			return new PowBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("sqrt"))
		{
			return new SqrtBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("sin"))
		{
			return new SinBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("cos"))
		{
			return new CosBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("tan"))
		{
			return new TanBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("random"))
		{
			return new RandomBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("map_common"))
		{
			return new MapCommonBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("map"))
		{
			return new MapBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		//String / Serial
		if (blockName.equals("text"))
		{
			return new MessageBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("text_equal"))
		{
			return new EqualBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("text_append"))
		{
			return new AdditionBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("serial_print"))
		{
			return new SerialPrintBlock(blockId, translator);
		}
		if (blockName.equals("serial_print_1"))
		{
			return new SerialPrint1Block(blockId, translator);
		}
		if (blockName.equals("serial_print_2"))
		{
			return new SerialPrint2Block(blockId, translator);
		}
		if (blockName.equals("serial_print_3"))
		{
			return new SerialPrint3Block(blockId, translator);
		}
		if (blockName.equals("serial_println"))
		{
			return new SerialPrintlnBlock(blockId, translator);
		}
		if (blockName.equals("serial_println_1"))
		{
			return new SerialPrint1lnBlock(blockId, translator);
		}
		if (blockName.equals("serial_println_2"))
		{
			return new SerialPrint2lnBlock(blockId, translator);
		}
		if (blockName.equals("serial_println_3"))
		{
			return new SerialPrint3lnBlock(blockId, translator);
		}
		if (blockName.equals("serial_read"))
		{
			return new SerialReadBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("serial_read_1"))
		{
			return new SerialRead1Block(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("serial_read_2"))
		{
			return new SerialRead2Block(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("serial_read_3"))
		{
			return new SerialRead3Block(blockId, translator, codePrefix, codeSuffix, label);
		}

		//List
		if (blockName.equals("list"))
		{
			return new ListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("add-to-list"))
		{
			return new ListAddBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("length"))
		{
			return new ListLenghtBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("get-list-item"))
		{
			return new ListGetBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		/*if (blockName.equals("first"))
		{
			return new ListFirstBlock(blockId, translator, codePrefix, codeSuffix, label);
		}*/
		
		//Global variables
/*		if (blockName.equals("global-var-boolean"))
		{
			return new GlobalVarBooleanBlock(blockId, translator, codePrefix, codeSuffix, label);
		}*/
		if (blockName.equals("getterglobal-var-boolean"))// || blockName.equals("getterglobal-var-boolean-list"))
		{
			return new GetGlobalVarBooleanBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterglobal-var-number"))// || blockName.equals("getterglobal-var-number-list"))
		{
			return new GetGlobalVarNumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterglobal-var-real"))
		{
			return new GetGlobalVarRealBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterglobal-var-string"))// || blockName.equals("getterglobal-var-string-list"))
		{
			return new GetGlobalVarStringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterglobal-var-boolean-list"))
		{
			return new GetGlobalVarBooleanListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterglobal-var-number-list"))
		{
			return new GetGlobalVarNumberListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterglobal-var-string-list"))
		{
			return new GetGlobalVarStringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		/*if (blockName.equals("setterglobal-var-boolean") || blockName.equals("setterglobal-var-boolean-list"))
		{
			return new SetGlobalVarBooleanBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("setterglobal-var-number") || blockName.equals("setterglobal-var-number-list"))
		{
			return new SetGlobalVarNumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("setterglobal-var-string") || blockName.equals("setterglobal-var-string-list"))
		{
			return new SetGlobalVarStringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}*/

		//Procedure
		if (blockName.equals("callerprocedure"))
		{
			return new ProcCallerBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("procedure"))
		{
			return new ProcBlock(blockId, translator);
		}
		if (blockName.equals("getterproc-param-number"))
		{
			return new ProcGetNumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("proc-param-number"))
		{
			return new ProcNumberBlock(blockId, translator, codePrefix, codeSuffix, label);
		}

		if (blockName.equals("getterproc-param-boolean"))
		{
			return new ProcGetBooleanBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("proc-param-boolean"))
		{
			return new ProcBooleanBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterproc-param-string"))
		{
			return new ProcGetStringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("proc-param-string"))
		{
			return new ProcStringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("proc-param-list"))
		{
			//return new ProcListBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("return"))
		{
			return new ProcReturnBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("getterreturn"))
		{
			return new ProcReturnGetterBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		
		//Utility
		if (blockName.equals("servo"))
		{
			return new ServoBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("lcdlist"))
		{
			return new LCDListBlock(blockId, translator);
		}
		if (blockName.equals("setterlcdinit"))
		{
			return new LCDSetBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("cursorlcdinit"))
		{
			return new LCDSetCursor(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("stepstepper"))
		{
			return new StepperBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("ultrasonic"))
		{
			return new UltrasonicBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("touch_sensitive"))
		{
			return new PinTouchSensitiveBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("wire_read"))
		{
			return new WireReadBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("wire_write"))
		{
			return new WireWriteBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("wire_isread"))
		{
			return new WireIsReadBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		//TinkerKit
		if (blockName.equals("Tinker_I0") || blockName.equals("Tinker_I1") || blockName.equals("Tinker_I2")
				|| blockName.equals("Tinker_I3") || blockName.equals("Tinker_I4") || blockName.equals("Tinker_I5"))
		{
			return new TinkerInputPortBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_O0") || blockName.equals("Tinker_O1") || blockName.equals("Tinker_O2")
				|| blockName.equals("Tinker_O3") || blockName.equals("Tinker_O4") || blockName.equals("Tinker_O5"))
		{
			return new TinkerOutputPortBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_LED"))
		{
			return new TinkerLEDBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Mosfet"))
		{
			return new TinkerMosfetBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Relay"))
		{
			return new TinkerRelayBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("Tinker_Button"))
		{
			return new TinkerButtonBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("Tinker_Accmeter"))
		{
			return new TinkerAccmeterBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Hall"))
		{
			return new TinkerHallBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_LDR"))
		{
			return new TinkerLDRBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_LinearPotentiometer"))
		{
			return new TinkerLinearPotentiometerBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_RotaryPotentiometer"))
		{
			return new TinkerRotaryPotentiometerBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Thermistor"))
		{
			return new TinkerThermistorBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Touch"))
		{
			return new TinkerTouchBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Tilt"))
		{
			return new TinkerTiltBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_LED_pwm"))
		{
			return new TinkerLEDPwmBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Mosfet_pwm"))
		{
			return new TinkerMosfetPwmBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("Tinker_Servo"))
		{
			return new TinkerServoBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		//Xinchejian
		if (blockName.equals("xcj_motor_forward"))
		{
			return new XinchejianMotorForwardBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("xcj_motor_backward"))
		{
			return new XinchejianMotorBackwardBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.endsWith("xcj_ms_digital_write"))
		{
			return new XinchejianDigitalWriteBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("xcj_ms_digital_read"))
		{
			return new XinchejianDigitalReadBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		//DFRobot
		if (blockName.equals("df_digital_viberation_sensor"))
		{
			return new DfrobotDigitalInoutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_digital_infrared_motion_sensor"))
		{
			return new DfrobotDigitalInoutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_digital_ir_receiver_module"))
		{
			return new DfrobotDigitalInoutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_adjustable_infrared_sensor_switch"))
		{
			return new DfrobotDigitalInoutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_line_tracking_sensor"))
		{
			return new DfrobotDigitalInoutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_digital_push_button"))
		{
			return new DfrobotPushButtonBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_joystick_module_button"))
		{
			return new DfrobotDigitalInoutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_capacitive_touch_sensor"))
		{
			return new DfrobotDigitalInoutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_pizeo_disk_vibration_sensor"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_analog_ambient_light_sensor"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_analog_grayscale_sensor"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_flame_sensor"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_temperature_sensor"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_joystick_module_x"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_joystick_module_y"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_mma7260_x"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_mma7260_y"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_mma7260_z"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_analog_rotation_sensor_v1"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_analog_rotation_sensor_v2"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_analog_sound_sensor"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_analog_gas_sensor"))
		{
			return new DfrobotAnalogInputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("df_servo"))
		{
			return new DfrobotServoBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_360_degree_rotation_motor"))
		{
			return new DfrobotServoBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_led"))
		{
			return new DfrobotLedBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_led_pwm"))
		{
			return new DfrobotLedPwmBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_piranha_led"))
		{
			return new DfrobotDigitalOutputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_piranha_led_pwm"))
		{
			return new DfrobotAnalogOuptutBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_relay"))
		{
			return new DfrobotDigitalOutputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_buzzer"))
		{
			return new DfrobotDigitalOutputBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_buzzer_tone"))
		{
			return new DfrobotToneBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		if (blockName.equals("df_buzzer_tone_delay"))
		{
			return new DfrobotToneDelayBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("seeed_joystick"))
		{
			return new PinReadAnalogBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("seeed_joystick_button"))
		{
			return new GroveJoyStickButtonBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		
		// LCD
		
		if (blockName.equals("df_lcd"))
		{
			return new ChampolLCDBlock(blockId, translator);
		}
		
		if (blockName.equals("df_lcd_keypad"))
		{
			return new DfrobotLCDKeypadBlock(blockId, translator);
		}
		
		if (blockName.equals("df_lcd_clear"))
		{
			return new ChampolLCDBClearBlock(blockId, translator);
		}
		
		if (blockName.equals("df_lcd_set_cursor"))
		{
			return new ChampolLCDSetCursorBlock(blockId, translator);
		}
		
		//L298
		
		if (blockName.equals("L298Init"))
		{
			return new L298InitBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("L298Speed"))
		{
			return new L298SpeedBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("L298Stop"))
		{
			return new L298StopBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("L298Ramp"))
		{
			return new L298RampBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("L298Ramp2"))
		{
			return new L298Ramp2Block(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		//CHAMPOL
		
		if (blockName.equals("serial_var_print"))
		{
			return new ChampolserialvarprintBlock(blockId, translator);
		}
		
		if (blockName.equals("serial_ln"))
		{
			return new ChampolSerialCRBlock(blockId, translator);
		}
		
				
		/*if (blockName.equals("def_string"))
		{
			return new ChampolDef_StringBlock(blockId, translator, codePrefix, codeSuffix, label);
		}
		
		if (blockName.equals("def_byte_array"))
		{
			return new ChampolDef_ByteArrayBlock(blockId, translator, codePrefix, codeSuffix, label);
		}*/
		
				
		
		/*if (blockName.equals("serial_send"))
		{
			return new SerialsendBlock(blockId, translator, codePrefix, codeSuffix, label);
		}*/
			
		
		
				
		System.err.println(blockName + " not found!");
		
		return null;
	}
}
