/**
 * 
 */
package flexiRent1;

import java.util.*;
import java.sql.Date;

public class propertyValidations {
	
	// addPropertyTypeValidation method

	public  String addPropertyType() {

		String property_type;
		boolean property_type_valid = true;

		// ask for property type details
		System.out.println("Enter Property Type(Must be A for Apartment or PS for Premium Suite or exit to see main menu(Case Sensitive))");

		// scanner object
		Scanner _property = new Scanner(System.in);

		do {

			// Getting user Input for type
			property_type = _property.nextLine();

			// validating and setting variable and exiting loop
			if ("A".equals(property_type)) {
				property_type = "Apartment";
				property_type_valid = false;
			}
			// validating and setting variable and exiting loop
			else if ("PS".equals(property_type)) {
				property_type = "PremiumSuite";
				property_type_valid = false;
			}
			
			// condition for exit
			else if("exit".equals(property_type)) {
				
				property_type = "exit";
				property_type_valid = false;
			}
			// error. continue loop
			else {
				System.out.println("Error.Enter Property Type(Must be A for Apartment or PS for Premium Suite or exit to see main menu(Case Sensitive))");
			}
		}

		while (property_type_valid);

		return property_type;
	}
	
	public  String addPropertyStreetnumber() {
		String streetNumber;
		
		// ask for streetNumber
		
				System.out.println("Enter Street Number");
				
				// scanner object
				Scanner streetno = new Scanner(System.in);
				streetNumber = streetno.nextLine();

				
		return streetNumber;
	}
	
	public  String addProprtyStreetName() {
		String streetName;
		
		// ask for streetNumber
		
				System.out.println("Enter Street Name");
				
				// scanner object
				Scanner streetnm = new Scanner(System.in);
				streetName = streetnm.nextLine();

				
		return streetName;
	}
	
	public  String addProprtySuburb() {
		String suburb;
		boolean suburb_valid = true;
		
		// ask for streetNumber
		
				System.out.println("Enter Suburb (Must be mel)");
				
				// scanner object
				Scanner sub = new Scanner(System.in);
				do {
					suburb = sub.nextLine();
					
					if("mel".equals(suburb)) {
						suburb_valid = false;
					}
					else {
						System.out.println("Must be \"mel\"");
					}
						
				}
				while(suburb_valid);
				
				

				
		return suburb;
	}
	
	public  String addProprtybeds() {
		String beds;
		boolean beds_valid = true;
		
		// ask for streetNumber
		
				System.out.println("Enter No. of Beds");
				
				// scanner object
				Scanner bed = new Scanner(System.in);
				do {
					beds = bed.nextLine();
				
					// check if number is digit
					if(isNumeric(beds)) {
						
						int beeds = Integer.parseInt(beds);
						
						// no. of beds must be from 1 to 3
						
						if(beeds > 0 && beeds < 4) {
							
							// converting back to string
							
							beds = Integer.toString(beeds);
							beds_valid = false;
						}
						else {
							System.out.println("No. of Beds must be in 1 to 3(inclusive)");
						}
						
						
					}
					
					else {
						System.out.println("No. of Beds must be in 1 to 3(inclusive)");
					}
					
					
				}while(beds_valid);
				

				
		return beds;
	}
	
	// method to check if String is number
		public boolean isNumeric(String strNum) {
		    return strNum.matches("\\d+?");
		}
		
		
	public String lastmaintenanceDate() {
		
		String lmd;
		boolean last_main_date_valid = true;
		Scanner obje = new Scanner(System.in);
		do {
			lmd = obje.nextLine();
			
			DateTime dateValidate = new DateTime();
			if(dateValidate.dateValidate(lmd)) {
				last_main_date_valid = false;
			}
			else {
				System.out.println("Date must be accurate and in dd/mm/yyyy");
			}
		} 
		
		while(last_main_date_valid);
		
		return lmd;
		
	}
	
	
	// get Free index of array to store data
	public int arrayFreeIndex(Object[] arr) {
		
		int openArray = 0;
		
		if(arr instanceof property[]) {
			for(int i = 0; i<arr.length; i++) {
				
			    if(arr[i] == null)
			    {
			        openArray = i;
			        break;
			    }
			}
		}
		
		else {
			for(int i = 0; i<arr.length; i++) {
							
						    if(arr[i] == null)
						    {
						        openArray = i;
						        break;
						    }
						    // check full array condition
						    
						    else if(arr[(arr.length) - 1] != null) {
						    	// sending 11 as index which doesn't exist in array
						    	openArray = 11;
						    	break;
						    }
						}
		}
		return openArray;
	}
	
	//method for checking if property exists
	
	public boolean propertyExists(property[] arr,String pro_id) {
		boolean exist = false;
		
		
			for(int i = 0; i<arr.length; i++) {
				// check if data in array 
				 if(arr[i] != null) {
				    if(pro_id.equals(arr[i].getProperty_id()))
				    {
				        // found property
				    	exist = true;
				        break;
				    }
			}
				 // if delete then need to shift. Plz check if we have delete functionality
				 else {
					 break;
				 }
			
		}
		
		return exist;
	}
	

	
	// method of checking property status
	public String checkPropertyAvailableStatus(property[] allProperties, String proID, String val) {
		// 3rd parameter is to check whether its status or any other like lastmainenance etc. 
		// must have a status and property because exists conditions already checked before 
		
		for(int i = 0; i<allProperties.length; i++) {
			
			//System.out.println(allProperties);
			
			    if(proID.equals(allProperties[i].getProperty_id()))
			    {
			    	System.out.println(proID);
			    	if("status".equals(val)) {
				        // get property status and return
				    	return allProperties[i].getProperty_status() ;
			    	}
			    	else if("maintenancedate".equals(val)) {
			    		// get property last maintenance status and return
			    		
			    		System.out.println(allProperties[i].getLastMaintenanceDate() );
			    		
			    		return allProperties[i].getLastMaintenanceDate() ;
			    		
			    	}
			    	else if("index".equals(val)) {
			    		// returning index value after [parsing
			    		return Integer.toString(i);
			    	}
			    }
		
	}
		
		return null;
	}
	
	// method for getting user Input
	public String userInput() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}
	
	public String addRentDate(String propID) {
		String inputDate;
		
		boolean rentDate_valid = true; 
		
		
		
		do {
			System.out.println("Rent date (dd/mm/yyyy)");
			
			inputDate = userInput();
			
			if(validateRentdate(inputDate, propID)) {
				
				rentDate_valid = false;
			}
			else {
				System.out.println("Date must be correct and in dd/mm/yyyy format.");
				
				
			}
		}while(rentDate_valid);
		
		return inputDate;
	}
	
	
	public boolean validateRentdate(String rentDate, String propertyID) {
		//boolean rentDate_valid = true; 
		
		
				// valid date
				DateTime obj = new DateTime();
				
				if(obj.dateValidate(rentDate)) {
					
					return true;
					
				}
				
			
			
			return false;
		
		}
	
	public boolean validateRentdays(String days, int min_days, String propertyID, property[] allProperties, String rentDate) {
		
		if(isNumeric(days) ) {
			
			// parsing no_of_days
			
			int days_no = Integer.parseInt(days);
			
				
				// check last maintenance if premium Suite
				if(propertyID.charAt(0) == 'S') {
					
					// max days 10
					if(days_no >=  1 && days_no <=10) {
						// get last maintenance date
						
						//System.out.println(propertyID);
						String last_m_d = checkPropertyAvailableStatus(allProperties, propertyID, "maintenancedate");
						//System.out.println("from rent to validate1");
						// get next maintenance date
						DateTime dateobj = new DateTime();
						String nxt_maintenance_date = dateobj.increaseDate(last_m_d, 10);
						//System.out.println(nxt_maintenance_date);
						
						// get returning date from no_of_days
						String returningDate = dateobj.increaseDate(rentDate, days_no);
						
						//System.out.println(returningDate);
						
						if(dateobj.compareDates(nxt_maintenance_date, returningDate)) {
							//System.out.println("you can rent me");
							
							
							
							return true;
						}
						else {
							//System.out.println("You can't rent this property.");
							return false;
						}
						
					}
					
					
					
					
				}
				else {
					if(days_no >=  min_days && days_no <=28) {
						return true;
					}
					else {
						return false;
					}
					
				}
				
				
			

			
		}
		
			return false;
		
		
	}
	
	public String addrentdays(int min_no_days, String propID, property[] allProperties, String rentDate) {
		
		boolean add_days_valid = true;
		String no_of_days;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("How many days? or exit to leave the program");
			
			 no_of_days = sc.nextLine();
			 
			 if("exit".equals(no_of_days)) {
				 System.out.println("You are exit now.");
				 no_of_days = "0";
				 add_days_valid = false;
			 }
			 else {
				 
				 if(validateRentdays(no_of_days, min_no_days, propID, allProperties, rentDate)) {
					 
					
						add_days_valid = false;
					}
					
					else {
						//if apartment then different output
						if(propID.charAt(0) == 'A') {
							System.out.println("must be in " + min_no_days + " to 28(inclusive)");
						}
						else {
							System.out.println("must be in 1 to 10(inclusive) and less than next maintenance date");
						}
					}
			 }
			// validating days
			
		} while(add_days_valid);
		
		
		return no_of_days;
	}
	
	
	
}
