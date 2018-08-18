/**
 * 
 */
package flexiRent1;

import java.util.Arrays;

/**
 * @author preet
 *
 */
public class premiumSuite extends property {

	/**
	 * @param property_id
	 * @param street_name
	 * @param street_no
	 * @param suburb
	 * @param no_of_beds
	 * @param property_type
	 * @param lastMaintenanceDate
	 */
	public premiumSuite(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String lastmaintenancedate) {
		super(property_id, street_name, street_no, suburb, no_of_beds, property_type, lastmaintenancedate);
		// TODO Auto-generated constructor stub
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
				
				//System.out.println(this.getClass());
				
				// passing values to validation class for validating data
				propertyValidations validd = new propertyValidations();
				
				// get dayname to check min days
				DateTime dayobj = new DateTime();
				String rentdayName = dayobj.getDayName(rentDate.toString());
				
				int min_no_of_days = 1;
				
				// estimated return date
				
				String estdReturnDate = dayobj.increaseDate(rentDate.toString(), numOfRentDay);
				String recordId = this.getProperty_id() + "_"+customerId+"_"+rentDate.getEightDigitDate();
				
				//System.out.println(validd.checkPropertyAvailableStatus(allprop, getProperty_id(), "status"));
				
				if(validd.validateRentdays( Integer.toString(numOfRentDay), min_no_of_days, this.getProperty_id(), allprop , rentDate.toString())) {
					
					// get next free index of rental record of this object
					//System.out.println(valid.arrayFreeIndex(this.records));
					
						
					
					if(validd.arrayFreeIndex(this.records) == 11) {
						
						// setting 10th index as null
						this.records[this.records.length - 1] = null;
						
						// right shift 1 place and add value to 0 index
						
						for( int index =this.records.length-2; index >= 0 ; index-- ) {
							this.records[index+1] = this.records[index];
						}
						
						
						// adding value to 0 index
						this.records[0] = new rentalRecord(recordId,rentDate.toString(),customerId, estdReturnDate);
						this.setProperty_status("rented");
						//System.out.println(this.getProperty_type() + " " + this.getProperty_id() +" is now rented by customer "+ customerId );
						
						// get property index
						//System.out.println(this.getDetails());
						return true;
					}
					else {
						
						// right shift 1 place and add value to 0 index
						
						for( int index =this.records.length-2; index >= 0 ; index-- ) {
							this.records[index+1] = this.records[index];
						}
						
						this.records[0] = new rentalRecord(recordId,rentDate.toString(),customerId, estdReturnDate);
						
						// set status to rented
						this.setProperty_status("rented");
						
						//System.out.println(this.toString());
						
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
		// revalidating again
		
				// get latest rent date of this property 
				
				String latestRentDate = this.records[0].getRentDate();
				
				// get estimated date so you can find late fee fine if any
				String latestEstdDate = this.records[0].getEstReturnDate();
				
				DateTime dateobj = new DateTime();
				
				double totalprice = 0, lateFine = 0;
				
				if(dateobj.compareDates(returnDate.toString(), latestRentDate)) {
					//System.out.println("you can rent me");
					// converting both dates to DateTime so can find the difference in days
					
					String[] latestrentparts = latestRentDate.split("/");
					
					String[] latestEstdDateparts = latestEstdDate.split("/");
					
					
					DateTime datelatestRent = new DateTime(Integer.parseInt(latestrentparts[0]), Integer.parseInt(latestrentparts[1]), Integer.parseInt(latestrentparts[2]));
					
					DateTime datelatestEstdDateparts = new DateTime(Integer.parseInt(latestEstdDateparts[0]), Integer.parseInt(latestEstdDateparts[1]), Integer.parseInt(latestEstdDateparts[2]));
					
					// got difference b/w rent and actual date 
					int rent_to_actual_diff = dateobj.diffDays(returnDate, datelatestRent);
					
					// got difference b/w estd and actual return date
					int estd_to_actual_diff = dateobj.diffDays(returnDate, datelatestEstdDateparts);
					
					// got difference b/w estd and rent return date
								int estd_to_rent_diff = dateobj.diffDays(datelatestEstdDateparts, datelatestRent );
								
					
					// rent date is less than actual return date
					if(rent_to_actual_diff > 0) {
						
						
						// check apartment no_of_bedrooms
						
						String no_of_beds = this.getNo_of_beds();
						int beds = Integer.parseInt(no_of_beds);
						
						
						
						if(estd_to_actual_diff > 0) {
							// property left after estd return date so fine
							
							// property on time or before returning. True case
							if(beds == 3) {
								totalprice = (estd_to_rent_diff * 554) ;
								 lateFine =  (662 * estd_to_actual_diff );
							}
							
							
						}
						else {
							if(beds == 3) {
								totalprice = rent_to_actual_diff * 554;
							}
							
						}
						
						// setting instance variables
						
						this.records[0].setRentFee(totalprice);
						this.records[0].setLateFee(lateFine);
						this.records[0].setActReturnDate(returnDate.toString());
						
						this.setProperty_status("available");
						
						return true;
						
						
					}
					else {
						return false;
					}
					
				}
				
		
		
		
		return false;
	}

	/* (non-Javadoc)
	 * @see flexiRent1.property#performMaintenance()
	 */
	@Override
	public boolean performMaintenance() {
		// revalidating
				// getting all properties
				flexiRentsystem obj = new flexiRentsystem();
				
				property[] allprop = obj.getProperties();
				
				// passing values to validation class for validating data
				propertyValidations valid = new propertyValidations();
				
				
						// property found. check status
				
				String InputpropertyStaus = valid.checkPropertyAvailableStatus(allprop, this.getProperty_id(), "status");
				
				// if available only then set to maintenance
				
				if("available".equals(InputpropertyStaus)) {
					
					// set status to maintenance
					this.setProperty_status("maintenance");
					return true;
					
				}
				
				
				
				
				return false;
	}
	
	@Override
	public boolean completeMaintenance(DateTime completionDate) {
		
		// revalidating
		
				// getting all properties
				flexiRentsystem obj = new flexiRentsystem();
						
				property[] allprop = obj.getProperties();
						
				// passing values to validation class for validating data
				propertyValidations valid = new propertyValidations();
				
				String InputpropertyStaus = valid.checkPropertyAvailableStatus(allprop, this.getProperty_id(), "status");
				if("maintenance".equals(InputpropertyStaus)) {
					
					// get last maintenance date of property so validate
					String property_last_maintenance_date = valid.checkPropertyAvailableStatus(allprop, this.getProperty_id(), "maintenancedate");
					
					DateTime dateobj = new DateTime();
					// must be greater than last maintenance date
					if(dateobj.compareDates(completionDate.toString(), property_last_maintenance_date)) {
						
						//String[] property_last_maintenance_dateparts = property_last_maintenance_date.split("/");
						// must be greater than 10 days
						//DateTime dateproperty_last_maintenance_date = new DateTime(Integer.parseInt(property_last_maintenance_dateparts[0]), Integer.parseInt(property_last_maintenance_dateparts[1]), Integer.parseInt(property_last_maintenance_dateparts[2]));
						
						// got difference b/w last maintenance and input completion date 
						//int last_to_actual_maintenance_diff = dateobj.diffDays(completionDate, dateproperty_last_maintenance_date);
						
						// must be after 10 days
						//if(last_to_actual_maintenance_diff > 10) {
							// set availabe status
							this.setProperty_status("available");
							
							// set last maintenance date
							this.setLastMaintenanceDate(completionDate.toString());
							
							return true;
						//}
						//else {
							//System.out.println("Premium suite must have 10 days interval in maintenance.");
						//}
						
						
					}
					
				}
				
				
		return false;
	}

	/* (non-Javadoc)
	 * @see flexiRent1.property#getDetails()
	 */
	@Override
	public String getDetails() {
		
		System.out.println("************************************");
		if("available".equals(this.getProperty_status())) {
			System.out.println("A new Premium Suite is available for rent.");
		}
		propertyValidations valid = new propertyValidations();
		
		int len = valid.arrayFreeIndex(this.getRecords());
		String rental_record_string = "";
		String property_details = "";
		if(len > 0) {
			for(int i = 0;i<len;i++) {
				rental_record_string += this.records[i].getDetails() + "\n----------------------------\n";
				
			}
		}
		
		if(len>0) {
			 property_details = "Property ID:" + this.getProperty_id() +"\n Address : "+ this.getStreet_no() + " "+ this.getStreet_name()+ " "
					+ this.getSuburb() + "\n Type : " + this.getProperty_type() + "\n Bedroom : " + this.getNo_of_beds() + "\n Status: "+this.getProperty_status()
					+"\n Last Maintenance date:"+this.getLastMaintenanceDate()+"\n Rental Record \n-------------------\n"+rental_record_string;
		}else {
			 property_details = "Property ID:" + this.getProperty_id() +"\n Address : "+ this.getStreet_no() + " "+ this.getStreet_name()+ " "
					+ this.getSuburb() + "\n Type : " + this.getProperty_type() + "\n Bedroom : " + this.getNo_of_beds() + "\n Status: "+this.getProperty_status()
					+"\n Last Maintenance date:"+this.getLastMaintenanceDate()+"\n Rental Record : empty";
		}
		
		System.out.println("************************************");
		
		return property_details;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  getProperty_id() + ":" + getStreet_no()	+ ":" + getStreet_name() + ":" + getSuburb() + ":"
							+ getProperty_type() + ":" + getNo_of_beds() + ":"
								+ getProperty_status() + ":" + getLastMaintenanceDate()  + ":["
										+ Arrays.toString(getRecords()) + "]";
	}

	
	
	
}
