package com.ardublock.translator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.workspace.Workspace;
import com.ardublock.translator.adaptor.BlockAdaptor;
import com.ardublock.translator.adaptor.OpenBlocksAdaptor;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.TranslatorBlockFactory;
import com.ardublock.translator.block.exception.SocketNullException;


public class Translator
{
	private static final String variableGlobalSuffix = "G";
	private static final String variableLocalSuffix = "L";
		
	private Set<String> preHeaderFileSet;
	private Set<String> headerFileSet;
	private Set<String> definitionSet;
	private LinkedHashSet<String> setupSet;
	private BlockAdaptor blockAdaptor;
	
	private Set<Long> inputPinSet;
	private Set<Long> outputPinSet;
	
	private Map<String, String> globalVariableSet;
	private Map<String, String> numberVariableSet;
	private Map<String, String> realVariableSet;
	private Map<String, String> booleanVariableSet;
	private Map<String, String> stringVariableSet;
	
	private Map<String, String> numberLocalVariableSet;
	private Map<String, String> realLocalVariableSet;
	private Map<String, String> booleanLocalVariableSet;
	private Map<String, String> stringLocalVariableSet;
	private boolean delayLocalVariableSet;
	private boolean semaphoreLocalVariableSet;
	
	private Map<String, String> numberListLocalVariableSet;
	private Map<String, String> booleanListLocalVariableSet;
	private Map<String, String> stringListLocalVariableSet;
	
	private Set<String> taskLoopSet;
	private Set<String> semaphoreSet;
	private Set<String> queueSet;
	private Map<String, String> taskLoopPrioritySet;
	
	private Workspace workspace;
	
	private int variableCnt;
	public Translator(Workspace ws)
	{
		workspace = ws;
		reset();
	}
	
	// code creation for header
	public String translateHeader(Long blockId) throws SocketNullException
	{	
		String headerCommand = "";
		if (!preHeaderFileSet.isEmpty())
		{
			for (String file:preHeaderFileSet)
			{
				headerCommand = headerCommand  + file + "\n"; 
			}
			headerCommand = headerCommand + "\n";
		}
		
		if (!headerFileSet.isEmpty())
		{
			for (String file:headerFileSet)
			{
				headerCommand = headerCommand  + "#include <" + file + ">\n";
			}
			headerCommand = headerCommand + "\n";
		}
		
		if (!definitionSet.isEmpty())
		{
			for (String command:definitionSet)
			{
				headerCommand = headerCommand + command + "\n"; 
			}
			headerCommand = headerCommand + "\n";
		}
		
		if (!semaphoreSet.isEmpty())
		{
			for (String command:semaphoreSet)
			{
				headerCommand = headerCommand + "xSemaphoreHandle x" + command + ";\n"; 
			}
			headerCommand = headerCommand + "\n";
		}

		if (!queueSet.isEmpty())
		{
			for (String command:queueSet)
			{
				headerCommand = headerCommand + "xQueueHandle x" + command + ";\n"; 
			}
			headerCommand = headerCommand + "\n";
		}

		if (!taskLoopSet.isEmpty())
		{
			for (String command:taskLoopSet)
			{
				headerCommand = headerCommand + "xTaskHandle x" + command + "Handle;\n"; 
			}
			headerCommand = headerCommand + "\n";
		}
		return headerCommand;
	}
	
	// code creation for setup
	public String translateSetup(Long blockId) throws SocketNullException
	{	
		String setupCommand = "";
		setupCommand = setupCommand + "void setup() {\n";
		
		if (!inputPinSet.isEmpty())
		{
			for (Long pinNumber:inputPinSet)
			{
				setupCommand = setupCommand + "\tpinMode( " + pinNumber + " , INPUT);\n";
			}
		}
		if (!outputPinSet.isEmpty())
		{
			for (Long pinNumber:outputPinSet)
			{
				setupCommand = setupCommand + "\tpinMode( " + pinNumber + " , OUTPUT);\n";
			}
			setupCommand = setupCommand + "\n";
		}
		
		if (!setupSet.isEmpty())
		{
			for (String command:setupSet)
			{
				setupCommand = setupCommand + "\t" + command + "\n";
			}
			setupCommand = setupCommand + "\n";
		}
		
		if (!taskLoopPrioritySet.isEmpty())
		{
			for (String mapTask : taskLoopPrioritySet.keySet()) {
				String mapPriority = taskLoopPrioritySet.get(mapTask);
				setupCommand = setupCommand + "\txTaskCreate( " +
						"v" + mapTask + "Task, " +
						"(signed char*)\"" + mapTask + "\", " +
						"200, " +
						"NULL, " + 
						mapPriority + ", " +
						"&x" + mapTask + "Handle );\n"; 
			}
			setupCommand = setupCommand + "\tvTaskStartScheduler();\n\n";
		}
	
		setupCommand = setupCommand + "}\n\n";
		return setupCommand;	
	}
	
	// code creation for Loop
	public String translate(Long blockId) throws SocketNullException
	{
		//reset();
		String loopCommand = "";
				
		TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();
		Block block = workspace.getEnv().getBlock(blockId);
		
		TranslatorBlock rootTranslatorBlock = translatorBlockFactory.buildTranslatorBlock(this, blockId, block.getGenusName(), "", "", block.getBlockLabel());
		
		loopCommand = loopCommand + rootTranslatorBlock.toCode();

		return loopCommand;
	}

	// code creation for Procedure
	public String translateProc(Long blockId) throws SocketNullException
	{
		//reset();
		String procCommand = "";
		/*if (delayLocalVariableSet)
		{
			procCommand = procCommand + "portTickType xLastWakeTime = xTaskGetTickCount();\n";
			delayLocalVariableSet = false;
		}
		if (semaphoreLocalVariableSet)
		{
			procCommand = procCommand + "static signed portBASE_TYPE xHigherPriorityTaskWoken = pdFALSE;\n";
			semaphoreLocalVariableSet = false;
		}*/

		TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();
		Block block = workspace.getEnv().getBlock(blockId);
		
		TranslatorBlock rootTranslatorBlock = translatorBlockFactory.buildTranslatorBlock(this, blockId, block.getGenusName(), "", "", block.getBlockLabel());
		
		procCommand = procCommand + rootTranslatorBlock.toCode();
		
		return procCommand;
	}

	// code creation for declaration local variable
	public String translateVar() throws SocketNullException
	{
		String varCommand = "";
		if (delayLocalVariableSet)
		{
			varCommand = varCommand + "portTickType xLastWakeTime = xTaskGetTickCount();\n";
			delayLocalVariableSet = false;
		}
		if (semaphoreLocalVariableSet)
		{
			varCommand = varCommand + "static signed portBASE_TYPE xHigherPriorityTaskWoken = pdFALSE;\n";
			semaphoreLocalVariableSet = false;
		}
		if (!booleanLocalVariableSet.isEmpty())
		{
			for (String mapVar : booleanLocalVariableSet.keySet())
			{
				varCommand = varCommand + "\tboolean " + booleanLocalVariableSet.get(mapVar) + ";\n"; 
			}
			booleanLocalVariableSet.clear();
		}
		if (!numberLocalVariableSet.isEmpty())
		{
			for (String mapVar : numberLocalVariableSet.keySet())
			{
				varCommand = varCommand + "\tint " + numberLocalVariableSet.get(mapVar) + ";\n"; 
			}
			numberLocalVariableSet.clear();
		}
		if (!realLocalVariableSet.isEmpty())
		{
			for (String mapVar : realLocalVariableSet.keySet())
			{
				varCommand = varCommand + "\tfloat " + realLocalVariableSet.get(mapVar) + ";\n"; 
			}
			realLocalVariableSet.clear();
		}
		if (!stringLocalVariableSet.isEmpty())
		{
			for (String mapVar : stringLocalVariableSet.keySet())
			{
				//varCommand = varCommand + "\tString " + stringLocalVariableSet.get(mapVar) + ";\n"; 
				varCommand = varCommand + "\tchar * " + stringLocalVariableSet.get(mapVar) + ";\n";
			}
			stringLocalVariableSet.clear();
		}
		return varCommand;// = varCommand + "\n";
		
	}

/*	// code creation for Global variables
	public String translateVar(Long blockId) throws SocketNullException
	{
		//reset();
		
		TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();
		Block block = workspace.getEnv().getBlock(blockId);
		
		TranslatorBlock rootTranslatorBlock = translatorBlockFactory.buildTranslatorBlock(this, blockId, block.getGenusName(), block.getBlockLabel(), "", "");
		
		String varCommand = rootTranslatorBlock.toCode();
		
		return varCommand;
	}*/

	public BlockAdaptor getBlockAdaptor()
	{
		return blockAdaptor;
	}
	
	private void reset()
	{
		preHeaderFileSet = new HashSet<String>();
		headerFileSet = new HashSet<String>();
		definitionSet = new HashSet<String>();
		setupSet = new LinkedHashSet<String>();
		inputPinSet = new HashSet<Long>();
		outputPinSet = new HashSet<Long>();
		
		globalVariableSet = new HashMap<String, String>();
		numberVariableSet = new HashMap<String, String>();
		realVariableSet = new HashMap<String, String>();
		booleanVariableSet = new HashMap<String, String>();
		stringVariableSet = new HashMap<String, String>();
		
		numberLocalVariableSet = new HashMap<String, String>();
		realLocalVariableSet = new HashMap<String, String>();
		booleanLocalVariableSet = new HashMap<String, String>();
		stringLocalVariableSet = new HashMap<String, String>();
		
		numberListLocalVariableSet = new HashMap<String, String>(); 
		booleanListLocalVariableSet = new HashMap<String, String>(); 
		stringListLocalVariableSet = new HashMap<String, String>(); 
		
		taskLoopSet = new HashSet<String>();
		semaphoreSet = new HashSet<String>();
		queueSet = new HashSet<String>();
		taskLoopPrioritySet = new HashMap<String, String>();
		
		blockAdaptor = buildOpenBlocksAdaptor();
		
		variableCnt = 0;
	}
	
	private BlockAdaptor buildOpenBlocksAdaptor()
	{
		return new OpenBlocksAdaptor();
	}
	
	public void addHeaderFile(String headerFile)
	{
		headerFileSet.add(headerFile);
	}
	public void addPreHeaderFile(String headerFile)
	{
		preHeaderFileSet.add(headerFile);
	}
	
	public void addSetupCommand(String command)
	{
		setupSet.add(command);
	}
	
	public void addDefinitionCommand(String command)
	{
		definitionSet.add(command);
	}
	
	public void addInputPin(Long pinNumber)
	{
		inputPinSet.add(pinNumber);
	}
	
	public void addOutputPin(Long pinNumber)
	{
		outputPinSet.add(pinNumber);
	}
	
	public String getGlobalVariable(String userVarName)
	{
		return globalVariableSet.get(userVarName);
	}
	public void addGlobalVariable(String userVarName, String internalName)
	{
		globalVariableSet.put(userVarName, internalName);
	}
	
	
	public String getNumberVariable(String userVarName)
	{
		return numberVariableSet.get(userVarName);
	}
	
	public String getRealVariable(String userVarName)
	{
		return realVariableSet.get(userVarName);
	}
	
	public String getBooleanVariable(String userVarName)
	{
		return booleanVariableSet.get(userVarName);
	}
	
	public String getStringVariable(String userVarName)
	{				
		return stringVariableSet.get(userVarName);
	}

	public void addNumberVariable(String userVarName, String internalName)
	{
		numberVariableSet.put(userVarName, internalName);
	}
	
	public void addRealVariable(String userVarName, String internalName)
	{
		realVariableSet.put(userVarName, internalName);
	}
	
	public void addBooleanVariable(String userVarName, String internalName)
	{
		booleanVariableSet.put(userVarName, internalName);
	}
	
	public void addStringVariable(String userVarName, String internalName)
	{
		stringVariableSet.put(userVarName, internalName);
	}

	public String getNumberLocalVariable(String userVarName)
	{
		return numberLocalVariableSet.get(userVarName);
	}
	
	public String getRealLocalVariable(String userVarName)
	{
		return realLocalVariableSet.get(userVarName);
	}

	public String getBooleanLocalVariable(String userVarName)
	{
		return booleanLocalVariableSet.get(userVarName);
	}
	
	public String getStringLocalVariable(String userVarName)
	{				
		return stringLocalVariableSet.get(userVarName);
	}

	public void addBooleanLocalVariable(String userVarName, String internalName)
	{
		booleanLocalVariableSet.put(userVarName, internalName);
	}
	public void addNumberLocalVariable(String userVarName, String internalName)
	{
		numberLocalVariableSet.put(userVarName, internalName);
	}
	public void addRealLocalVariable(String userVarName, String internalName)
	{
		realLocalVariableSet.put(userVarName, internalName);
	}
	public void addDelayLocalVariable()
	{
		delayLocalVariableSet = true;
	}
	public void addSemaphoreLocalVariable()
	{
		semaphoreLocalVariableSet = true;
	}

	public void addStringLocalVariable(String userVarName, String internalName)
	{
		stringLocalVariableSet.put(userVarName, internalName);
	}

/*	public String getListLocalVariable(String userVarName)
	{
		return listLocalVariableSet.get(userVarName);
	}
	
	public void addListLocalVariable(String userVarName, String internalName)
	{
		listLocalVariableSet.put(userVarName, internalName);
	}
*/

	public void addBooleanListLocalVariable(String userVarName, String internalName)
	{
		booleanListLocalVariableSet.put(userVarName, internalName);
	}
	public void addNumberListLocalVariable(String userVarName, String internalName)
	{
		numberListLocalVariableSet.put(userVarName, internalName);
	}
	public void addStringListLocalVariable(String userVarName, String internalName)
	{
		stringListLocalVariableSet.put(userVarName, internalName);
	}

	public String getBooleanListLocalVariable(String userVarName)
	{
		return booleanListLocalVariableSet.get(userVarName);
	}
	public String getNumberListLocalVariable(String userVarName)
	{
		return numberListLocalVariableSet.get(userVarName);
	}
	public String getStringListLocalVariable(String userVarName)
	{				
		return stringListLocalVariableSet.get(userVarName);
	}


	public void addTaskLoop(String taskLoopName)
	{
		taskLoopSet.add(taskLoopName);
	}
	public void addSemaphore(String semaphoreName)
	{
		semaphoreSet.add(semaphoreName);
	}
	public void addQueue(String semaphoreName)
	{
		queueSet.add(semaphoreName);
	}
	public void addTaskPriorityLoop(String taskLoopName, String Priority)
	{
		taskLoopPrioritySet.put(taskLoopName, Priority);
	}
	public String getTaskPriorityLoop(String taskLoopName)
	{				
		return taskLoopPrioritySet.get(taskLoopName);
	}

	public String buildVariableName()
	{
		return buildVariableName("");
	}
	
	public String buildVariableName(String reference)
	{
		variableCnt = variableCnt + 1;
		String varName = "";
		int i;
		for (i=0; i<reference.length(); ++i)
		{
			char c = reference.charAt(i);
			if (Character.isLetter(c) || Character.isDigit(c) || (c == '_'))
			{
				varName = varName + c;
			}
		}
		varName = varName + "_" + variableGlobalSuffix + variableCnt;
		return varName;
	}
	
	public String buildLocalVariableName()
	{
		return buildLocalVariableName("");
	}
	
	public String buildLocalVariableName(String reference)
	{
		variableCnt = variableCnt + 1;
		String varName = "";
		int i;
		for (i=0; i<reference.length(); ++i)
		{
			char c = reference.charAt(i);
			if (Character.isLetter(c) || Character.isDigit(c) || (c == '_'))
			{
				varName = varName + c;
			}
		}
		varName = varName + "_" + variableLocalSuffix + variableCnt;
		return varName;
	}
	
	public Workspace getWorkspace() {
		return workspace;
	}
	
	public Block getBlock(Long blockId) {
		return workspace.getEnv().getBlock(blockId);
	}
	
	public boolean testGenusBlock(Block bl, String genusName)
	{
		while (bl != null)						// move up to first block
		{
			if (bl.getGenusName().equals(genusName))
			{
				return true;					// it's the required BlockGenus 
			}
			while (bl.hasPlug())				// move to block left
			{
				bl = this.getBlock(bl.getPlug().getBlockID());
				return testGenusBlock(bl, genusName);	// Iteration test
			}
			bl = this.getBlock(bl.getBeforeBlockID());
		}
		return false;							// it's not an interrupt block
	}

}
