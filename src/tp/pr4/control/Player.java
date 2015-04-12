package tp.pr4.control;
import tp.pr4.logic.*;
public interface Player {

	Move getMove(ReadOnlyBoard board, Counter colour);
	
}
