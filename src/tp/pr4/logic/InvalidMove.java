package tp.pr4.logic;

import java.io.Serializable;
import java.lang.String;

public class InvalidMove extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3053511012367260580L;

	public InvalidMove() {
        super();
    }
	
	public InvalidMove(String msg) {
        super(msg);
    }

	 public InvalidMove(String msg, Throwable cause) {
        super(msg, cause);
     }
	
	public InvalidMove(Throwable cause) {
        super(cause);
    }
}
