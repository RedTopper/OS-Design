package translator;

import java.text.ParseException;

public class Command {
	
	/**
	 * Prefix to unary commands
	 */
	public static final String UNARY = 		
		"@SP\n" +
		"AM=M-1\n";
	
	/**
	 * Prefix to math commands
	 */
	public static final String BINARY = 
		UNARY +	
		"D=M\n" +
		"A=A-1\n";
	
	/**
	 * Returns to a previous call
	 */
	public static final String RETURN =
		// *(LCL - 5) -> R13
		"@LCL\n" +
		"D=M\n" +
		"@5\n" +
		"A=D-A\n" +
		"D=M\n" +
		"@R13\n" +
		"M=D\n" +
		// *(SP - 1) -> *ARG
		"@SP\n" +
		"A=M-1\n" +
		"D=M\n" +
		"@ARG\n" +
		"A=M\n" +
		"M=D \n" +
		// ARG + 1 -> SP
		"D=A+1\n" +
		"@SP\n" +
		"M=D\n" +
		// *(LCL - 1) -> THAT; LCL--
		"@LCL\n" +
		"AM=M-1\n" +
		"D=M\n" +
		"@THAT\n" +
		"M=D\n" +
		// *(LCL - 1) -> THIS; LCL--
		"@LCL\n" +
		"AM=M-1\n" +
		"D=M\n" +
		"@THIS\n" +
		"M=D\n" +
		// *(LCL - 1) -> ARG; LCL--
		"@LCL\n" +
		"AM=M-1\n" +
		"D=M\n" +
		"@ARG\n" +
		"M=D\n" +
		// *(LCL - 1) -> LCL
		"@LCL\n" +
		"A=M-1\n" +
		"D=M\n" +
		"@LCL\n" +
		"M=D\n" +
		// R13 -> A
		"@R13\n" +
		"A=M\n" +
		"0;JMP\n";
		
	
	public final String name;
	protected String asm;
	protected String[] commands;
	protected int line = 0;
	
	public Command(String name, String asm) {
		this.name = name;
		this.asm = asm;
	}
	
	public void parameters(String[] commands) throws ParseException {
		this.commands = commands;
	}

	public String getAsm() {
		return asm;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String toString() {
		return name;
	}
}