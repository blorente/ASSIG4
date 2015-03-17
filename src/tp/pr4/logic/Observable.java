
package tp.pr4.logic;

public interface Observable<T> {
	
	/**
	 * Adds an observer
	 * 
	 * @param o
	 * 			An observer
	 */
	void addObserver(T o);
	
	/**
	 * Removes an observer
	 * 
	 * @param o
	 * 			An observer
	 */
	void removeObserver(T o);

}
