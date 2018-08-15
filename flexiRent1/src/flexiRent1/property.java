/**
 * 
 */
package flexiRent1;

/**
 *
 */
public abstract class property implements rentable, maintenable{
	
	// instance variables
	
	private String property_id;
	
	private String street_name;
	
	private String street_no;
	
	private String suburb;
	
	private String no_of_beds;
	
	private String property_type;
	
	private String property_status;		// available, rented, maintenance	
	
	private String lastMaintenanceDate;		// using DateTime class but making string to save
	
	protected rentalRecord[] records = new rentalRecord[2];
	
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
	public property(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type) {
		
		this.property_id = property_id;
		this.street_name = street_name;
		this.street_no = street_no;
		this.suburb = suburb;
		this.no_of_beds = no_of_beds;
		this.property_type = property_type;
		this.property_status = "available";
		
		// setting current Date as first maintenance date
		DateTime ob = new DateTime();	
		this.lastMaintenanceDate = ob.getFormattedDate();	
	}
	
	public property(String property_id, String street_name, String street_no, String suburb, String no_of_beds,
			String property_type, String lastmaintenance) {
		
		this.property_id = property_id;
		this.street_name = street_name;
		this.street_no = street_no;
		this.suburb = suburb;
		this.no_of_beds = no_of_beds;
		this.property_type = property_type;
		this.property_status = "available";	
		this.lastMaintenanceDate = lastmaintenance;	
	}

	/**
	 * @return the property_id
	 */
	public String getProperty_id() {
		return property_id;
	}

	/**
	 * @param property_id the property_id to set
	 */
	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}

	/**
	 * @return the street_name
	 */
	public String getStreet_name() {
		return street_name;
	}

	/**
	 * @param street_name the street_name to set
	 */
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	/**
	 * @return the street_no
	 */
	public String getStreet_no() {
		return street_no;
	}

	/**
	 * @param street_no the street_no to set
	 */
	public void setStreet_no(String street_no) {
		this.street_no = street_no;
	}

	/**
	 * @return the suburb
	 */
	public String getSuburb() {
		return suburb;
	}

	/**
	 * @param suburb the suburb to set
	 */
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	/**
	 * @return the no_of_beds
	 */
	public String getNo_of_beds() {
		return no_of_beds;
	}

	/**
	 * @param no_of_beds the no_of_beds to set
	 */
	public void setNo_of_beds(String no_of_beds) {
		this.no_of_beds = no_of_beds;
	}

	/**
	 * @return the property_type
	 */
	public String getProperty_type() {
		return property_type;
	}

	/**
	 * @param property_type the property_type to set
	 */
	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	/**
	 * @return the property_status
	 */
	public String getProperty_status() {
		return property_status;
	}

	/**
	 * @param property_status the property_status to set
	 */
	public void setProperty_status(String property_status) {
		this.property_status = property_status;
	}

	/**
	 * @return the lastMaintenanceDate
	 */
	public String getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	
	/**
	 * @param lastMaintenanceDate the lastMaintenanceDate to set
	 */
	public void setLastMaintenanceDate(String lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	

	/**
	 * @return the records
	 */
	public rentalRecord[] getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(rentalRecord[] records) {
		this.records = records;
	}
	
		
//	public abstract boolean rent(String customerId, DateTime rentDate, int numOfRentDay);
//
//	public abstract boolean returnProperty(DateTime returnDate);
//	
//	public abstract boolean performMaintenance();
//	
//	public boolean completeMaintenance(DateTime completionDate){
//		return true;
//	}

	public abstract String getDetails();

	
	
	
}
