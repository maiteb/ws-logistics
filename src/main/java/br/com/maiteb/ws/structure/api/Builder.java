package br.com.maiteb.ws.structure.api;

/**
 * Interface that specifies a builder for some class.
 * 
 * @author Maitê Balhester
 *
 * @param <T>
 *            class that will be used for build the object.
 */
public interface Builder<T> {

	/**
	 * Build an object for the generic class.
	 * 
	 * @return a new object.
	 */
	public T build();

}
