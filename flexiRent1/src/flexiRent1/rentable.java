/**
 * 
 */
package flexiRent1;


public interface rentable {
	
	boolean rent(String customerId, DateTime rentDate, int numOfRentDay);
	
	public abstract boolean returnProperty(DateTime returnDate);
	
}
