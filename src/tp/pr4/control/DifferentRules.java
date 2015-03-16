package tp.pr4.control;

import tp.pr4.logic.*;

/**
 * This is made to simplify the Controller. Since the different GameRules are
 * singleton, we might as well have only an enum, and thus avoid creating too
 * many rules objects.
 * 
 */
public enum DifferentRules {
	CONNECT4(new Connect4Rules()), COMPLICA(new ComplicaRules());

	private final GameRules rules;

	DifferentRules(GameRules rules) {
		this.rules = rules;
	}

	public GameRules getRules() {
		return rules;
	}

	// toString method
}
