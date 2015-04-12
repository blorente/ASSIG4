package tp.pr4.control;

public enum Instruction {
	MOVE, UNDO, RESTART, EXIT, ERROR, PLAY_C4, PLAY_CO, PLAY_G, PLAYER, HELP;
	
	
	public String toString(Instruction inst) {
		String instruction = "";
		switch (inst) {
		case ERROR:
			instruction = "Error";
			break;
		case EXIT:
			instruction = "Exit";
			break;
		case HELP:
			instruction = "Help";
			break;
		case MOVE:
			instruction = "Move";
			break;
		case PLAYER:
			instruction = "Player";
			break;
		case PLAY_C4:
			instruction = "Connect 4";
			break;
		case PLAY_CO:
			instruction = "Complica";
			break;
		case PLAY_G:
			instruction = "Gravity";
			break;
		case RESTART:
			instruction = "Reset";
			break;
		case UNDO:
			instruction = "Undo";
			break;
		default:
			break;
			
		}
		return instruction;
	}
	
	public Instruction StringToInstruction(String inst) {
		Instruction instruction = PLAY_C4;
		switch(inst) {
		case "Connect 4":
			instruction = PLAY_C4;
			break;
		case "Gravity":
			instruction = PLAY_G;
			break;
		case "Complica":
			instruction = PLAY_CO;
		}
		return instruction;
		
	}
}

