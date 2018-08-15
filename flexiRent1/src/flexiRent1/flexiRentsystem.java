/**
 * 
 */
package flexiRent1;
import java.text.SimpleDateFormat;
import java.util.*;



public class flexiRentsystem {
	// String Array to store properties
	
	private static property[] properties = new property[50];
	
	
	
	/**
	 * @return the properties
	 */
	public static  property[] getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(property[] properties) {
		this.properties = properties;
	}
	
	


	// Menu 
	public void flexiMenu() {
		
		String userchoice1;
		
		boolean validuserchoice = true;
		
		//creating object for this class to access instance method from static method
		
		flexiRentsystem obj = new flexiRentsystem();
		
		do {
		
		// menu for FlexiRent
				System.out.println("**** FLEXIRENT SYSTEM MENU  ****"+"\n");
				
				System.out.println("Add Property: \t\t 1");
				System.out.println("Rent Property: \t\t 2");
				System.out.println("Return Property: \t 3");
				System.out.println("Property Maintainance: \t 4");
				System.out.println("Complete Maintainance: \t 5");
				System.out.println("Display All properties:  6");
				System.out.println("Exit Property: \t\t 7");
				
				System.out.println("Enter Your Choice:");
				
				// Scanner obj
				
				Scanner choice = new Scanner(System.in);
				
				userchoice1 = choice.nextLine();
				propertyValidations val = new propertyValidations();
				if(val.isNumeric(userchoice1)) {
					
					int userchoice = Integer.parseInt(userchoice1);
					
					// choice must be in between 1 to 7
					
					if(userchoice > 0 && userchoice <= 7) {
						
						switch(userchoice) {
						
							case 1:
								
								 //calling addProperties()
								
								obj.addProperties();
								
								break;
							
							case 2:
								
								obj.checkAndCallRent();
								
								
								//System.out.println("You enter 2");
								break;
								
							
							case 3:
								System.out.println("3 enter");
								break;
							
							case 4:
								System.out.println("4 enter");
								break;
							
							case 5:
								System.out.println("5 enter");
								break;
							
							case 6:
								
								// DisplayAllProperties for display
								//obj.DisplayAllProperties();
								System.out.println("6 enter");
								break;
							
							case 7:
								System.out.println("Thank you. You are exit now.");
								
								// exitin the do-while loop
								validuserchoice = false;
								
						}
					}
					else {
						System.out.println("Please enter a valid choice");
					}
					
				}
				else {
					System.out.println("Please enter a valid number");
				}
				
				
		}
		while(validuserchoice);
				
	}
	
	private void addProperties() {
		
		String pro_no_of_beds = "0";  // initializing value now changing below
		
		boolean Invalid_property = false;
		
		// creating object for PropertiesValidations to call validation methods
		
		propertyValidations valid = new propertyValidations();
		
		// calling  method addPropertyType from propertyValidations.java
		
		String Pro_type = valid.addPropertyType();
		
		// Exit method and see main menu
		if(Pro_type == "exit") {
			return;
		}
		
		//System.out.println(Pro_type);
		
		// calling  method addPropertyStreetnumber from PropertiesValidations.java
		
				String pro_street_no = valid.addPropertyStreetnumber();
				
				// calling  method addProprtyStreetName 
				
				String pro_street_name = valid.addProprtyStreetName();
				
				// calling  method addProprtySuburb
				
				String pro_suburb = valid.addProprtySuburb();
				
				String Pro_ID = "";
				
				//Creating Property ID
				
				if(Pro_type == "Apartment") {
					Pro_ID = "A_"+ pro_street_no.toLowerCase().replaceAll("\\s+","") + "_" + pro_street_name.toLowerCase().replaceAll("\\s+","")+"_"+pro_suburb.toLowerCase().replaceAll("\\s+","");
				}
				
				else if(Pro_type == "PremiumSuite") {
					Pro_ID = "S_"+ pro_street_no.toLowerCase().replaceAll("\\s+","") + "_" + pro_street_name.toLowerCase().replaceAll("\\s+","")+"_"+pro_suburb.toLowerCase().replaceAll("\\s+","");
				}
				
				//System.out.println("work");
				//check property exists
				
				if(!valid.propertyExists(properties, Pro_ID)) {
					
					// calling  method addProprtybeds only for Apartment
					if(Pro_type == "Apartment") {
						
						//String pro_no_of_bedss = valid.addProprtybedss();
						pro_no_of_beds = valid.addProprtybeds();
						int freeIndex = valid.arrayFreeIndex(properties);
						properties[freeIndex] = new apartment(Pro_ID, pro_street_name, pro_street_no, pro_suburb, pro_no_of_beds, Pro_type);
					
					}
					else {
						
						pro_no_of_beds = "3";		// set default 
						
						// get last maintenance Date
						
						
						System.out.println("Enter Last Maintenance date");
						String validlmd = valid.lastmaintenanceDate();
						
						
						int freeIndex = valid.arrayFreeIndex(properties);
						properties[freeIndex] = new premiumSuite(Pro_ID, pro_street_name, pro_street_no, pro_suburb, pro_no_of_beds, Pro_type, validlmd);
						System.out.println("Property Added Successfully");
					}
					
					int freeIndexchk = valid.arrayFreeIndex(properties);
					for(int i = freeIndexchk-1; i>=0;i--) {
						System.out.println(properties[i]);
					}
				}
				else {
					System.out.println("Property Already Exists");
				}
	}
	
	public void checkAndCallRent() {
		
		// creating object for PropertiesValidations to call validation methods
		
				propertyValidations valid = new propertyValidations();
				
				// scanner object
				Scanner getProperty = new Scanner(System.in);
				
				// user Input for property ID
				System.out.println("ENter property ID:");
				
				String inputProID = getProperty.nextLine();
				
				// not found. print and exit the program
				if(!valid.propertyExists(properties, inputProID)) {
					System.out.println("Invalid Property ID");
				}
				else {
					// check availability status of property
					
					String InputpropertyStaus = valid.checkPropertyAvailableStatus(properties, inputProID, "status");
					if("available".equals(InputpropertyStaus)) {
						
						// found with status available
						
						// get customer ID
						Scanner otherdetails = new Scanner(System.in);
						System.out.println("Enter Customer ID: ");
						String customerID = otherdetails.nextLine();		// customer ID
						
						// get rentDate and validate it
						
						
						String correctRentDate = valid.addRentDate(inputProID);
						
						// validate rentdate 
						
						//String correctRentDate = valid.validateRentdate(inputrentDate, inputProID);
						
						//get Dayname from rentDate in  DateTime class
						DateTime dayobj = new DateTime();
						String rentdayName = dayobj.getDayName(correctRentDate);
						//System.out.println(rentdayName);
						
						String no_of_days;
						// if apartment then check dayName
						
						if(inputProID.charAt(0) == 'A') {
							// day exists in these days
							if(rentdayName == "Sunday" || rentdayName == "Monday" || rentdayName == "Tuesday" || rentdayName == "Wednesday" || rentdayName == "Thursday") {
								 // passing 2 as parameter for minimum days
								 no_of_days = valid.addrentdays(2, inputProID, properties, correctRentDate);
							}
							else {		// other days
								
								// passing 3 as parameter for minimum days
								 no_of_days = valid.addrentdays(3, inputProID, properties, correctRentDate);
							}
						}
						else {
							// 1 as minimum days
							 no_of_days = valid.addrentdays(1, inputProID, properties, correctRentDate);
						}
						
						if(no_of_days != "0") {
							// get property index
							String indexvalue = valid.checkPropertyAvailableStatus(properties, inputProID, "index");
							// parsing data to pass on rent()
							int indexval = Integer.parseInt(indexvalue);
							
							// rent date in dateformat
							
							String[] parts = correctRentDate.split("/");
							
							DateTime datecon = new DateTime(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
							
							//System.out.println(datecon);
					
							
							
							//call rent method
							
							boolean rent_status = properties[indexval].rent(customerID, datecon, Integer.parseInt(no_of_days));
							if(rent_status) {
								System.out.println(properties[indexval].getProperty_type() + " " + properties[indexval].getProperty_id() +" is now rented by customer "+ customerID );
							}
							else {
								
								System.out.println("Property couldn't be rented. Something went wrong.");
								
							}
							
							
						}
						else {
							if(inputProID.charAt(0) == 'A') {		// Apartment
								System.out.println("Apartment "+inputProID + " could not be rented");
							}
							else {
								System.out.println("Premium Suite "+inputProID + " could not be rented");
							}
						}
						
						
						
						
						
					}else {
						// getting first letter to see apartment or premium suite
						
						if(inputProID.charAt(0) == 'A') {		// Apartment
							System.out.println("Apartment "+inputProID + " could not be rented");
						}
						else {
							System.out.println("Premium Suite "+inputProID + " could not be rented");
						}
					}
					
				}
				
	}
	
	

}
