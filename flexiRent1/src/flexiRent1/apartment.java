/**
 * 
 */
package flexiRent1;

import java.util.Arrays;

public class apartment extends property{

	/**
	 * @param property_id
	 * @param street_name
	 * @param street_no
	 * @param suburb
	 * @param no_of_beds
	 * @param property_type
	 * @param property_status
	 * @param lastMaintenanceDate
	 */
	public apartment(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type) {
		
		super(property_id, street_name, street_no, suburb, no_of_beds, property_type);
		
	}

	
	/* (non-Javadoc)
	 * @see flexiRent1.property#rent(java.lang.String, flexiRent1.DateTime, int)
	 */
	
	@Override
	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		// validating 1 more time
		
		// getting all properties
		flexiRentsystem obj = new flexiRentsystem();
		property[] allprop = obj.getProperties();
		
		// passing values to validation class for validating data
		propertyValidations valid = new propertyValidations();
		
		// get dayname to check min days
		DateTime dayobj = new DateTime();
		String rentdayName = dayobj.getDayName(rentDate.toString());
		
		int min_no_of_days;
		if(rentdayName == "Sunday" || rentdayName == "Monday" || rentdayName == "Tuesday" || rentdayName == "Wednesday" || rentdayName == "Thursday") {
			 // passing 2 for minimum days
			min_no_of_days = 2;
		}
		else {		// other days
			
			//3  for minimum days
			min_no_of_days = 3;
		}
		
		// estimated return date
		
		String estdReturnDate = dayobj.increaseDate(rentDate.toString(), numOfRentDay);
		String recordId = this.getProperty_id() + "_"+customerId+"_"+rentDate.getEightDigitDate();
		
		if(valid.validateRentdays( Integer.toString(numOfRentDay), min_no_of_days, this.getProperty_id(), allprop , rentDate.toString())) {
			
			// get next free index of rental record of this object
			//System.out.println(valid.arrayFreeIndex(this.records));
				
			
			if(valid.arrayFreeIndex(this.records) == 11) {
				
				// setting 10th index as null
				this.records[this.records.length - 1] = null;
				
				// right shift 1 place and add value to 0 index
				
				for( int index =this.records.length-2; index >= 0 ; index-- ) {
					this.records[index+1] = this.records[index];
				}
				
				
				// adding value to 0 index
				this.records[0] = new rentalRecord(recordId,rentDate.toString(),customerId, estdReturnDate);
				//this.setProperty_status("rented");
				//System.out.println(this.getProperty_type() + " " + this.getProperty_id() +" is now rented by customer "+ customerId );
				
				// get property index
				System.out.println(this.toString());
				return true;
			}
			else {
				
				// right shift 1 place and add value to 0 index
				
				for( int index =this.records.length-2; index >= 0 ; index-- ) {
					this.records[index+1] = this.records[index];
				}
				
				this.records[0] = new rentalRecord(recordId,rentDate.toString(),customerId, estdReturnDate);
				
				// set status to rented
				//this.setProperty_status("rented");
				// get property index
				System.out.println(this.toString());
				
				return true;
			}
				
			
		}
		
		
		
		return false;
	}

	/* (non-Javadoc)
	 * @see flexiRent1.property#returnProperty(flexiRent1.DateTime)
	 */
	@Override
	public boolean returnProperty(DateTime returnDate) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see flexiRent1.property#performMaintenance()
	 */
	@Override
	public boolean performMaintenance() {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see flexiRent1.property#getDetails()
	 */
	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  getProperty_id() + ":" + getStreet_no()	+ ":" + getStreet_name() + ":" + getSuburb() + ":"
								+ getProperty_type() + ":" + getNo_of_beds() + ":"
									+ getProperty_status() + ":["
									+ Arrays.toString(getRecords()) + "]";
	}
	
	


	@Override
	public boolean completeMaintenance(DateTime completionDate) {
		// TODO Auto-generated method stub
		return false;
	}


	
	
	

}
