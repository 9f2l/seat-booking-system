import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;
public class seatBooking 
{ 
	int checkSeat=0;
	int checkRev=0;
	int check1Seat=0;
	int check1Rev=0;
	
	
	 int choice ()    // Main menue
	{
		Scanner sc = new Scanner(System.in);
        System.out.println("--Seat Booking System--");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("--MAIN MENU--");
        System.out.println("1-Reserve Seat");
        System.out.println("2-Cancel Seat");
        System.out.println("3-View Seat Reservation");
        System.out.println("4-Quit");
        System.out.println("Pick:");
        int input = sc.nextInt();
        return input;
	}
	
	 void menuChoice(int choice)
	{
		int input= choice;
        if(input==1)                  // for seat reservation
        {
        	System.out.println("To continue to reserve your seat enter your email below.");
        	Scanner sc = new Scanner(System.in);
        	String email = sc.nextLine();
        	System.out.println("How many seats would you like to book? Note: You can book maximum 5 at a time!");
        	int numberOfSeats = sc.nextInt();
        	if(numberOfSeats>5 || numberOfSeats<1)  // maximum 5 number of seats can be booked together
        	{
        		System.out.println("Enter seats within range!");
            	return;
        	}
        	int i;
        	for(i=1;i<=numberOfSeats;i++)         //for booking seats one by one
        	{
        		checkRev=0;
        		sc.nextLine();
        		System.out.println("Configuration of seat/s: "+ i);
        		System.out.println("Would you like First or Standard class?; Enter f for First class and s for Standard class. ");
        		String seatClass = "";
        		seatClass = sc.nextLine();
        		if(seatClass.charAt(0)=='f') 
        		{
        			seatClass="1ST";
        		}
        		else if(seatClass.charAt(0)=='s')
        		{
        			seatClass="STD";
        		}
        		else
        		{
        			System.out.println("Invalid input!");
        			return;
        		}
        		System.out.println("Would you like Window or Aisle seat?; Enter a for Aisle class and w for Window seat. ");
        		String seatType = sc.nextLine();
        		String isWindow="false";
        		String isAisle="false";
        		if(seatType.charAt(0)=='a')
        		{
        			isAisle="true";
        		}
        		else if(seatType.charAt(0)=='w')
        		{
        			isWindow="true";
        		}
        		else
        		{
        			System.out.println("Invalid input!");
        			return;
        		}
        		System.out.println("Would you like a seat with table?; Enter y for Yes and n for No.");
        		String seatTable = sc.nextLine();
        		String isTable="false";
        		if(seatTable.charAt(0)=='y')
        		{
        			isTable="true";
        		}
        		else if(seatTable.charAt(0)=='n')
        		{
        			isTable="false";
        		}
        		else
        		{
        			System.out.println("Invalid input!");
        			return;
        		}
        		reservationWriter(seatClass, isWindow, isAisle, isTable, email);  //for checking seat availability and booking
        		System.out.println("Press enter to continue.");
        	}
        }
        else if(input==2) // for seat cancellation
        {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Enter the seat number you want to cancel.");
        	String cancelSeat = sc.nextLine();
        	System.out.println("Enter the registered email.");
        	String cancelEmail = sc.nextLine();
        	cancel(cancelEmail, cancelSeat);             //for searching of entered seat and canceling them
        }
        else if(input==3)         //for viewing reservation
        {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Enter the seat number you want to search.");
        	String cancelSeat = sc.nextLine();
        	System.out.println("Enter the registered email.");
        	String cancelEmail = sc.nextLine();
        	view(cancelEmail, cancelSeat);
        }
        else if(input==4)
        {
        	System.out.println("Thank you, please use this software again");
        }
        else
        {
        	System.out.println("Invalid Input");
        }
	}
	 
	 
	 void view(String email, String seat)      //function for reading each line from .txt fine and check previous reservation
	 {
		 try  
		 {  
			 //the file to be opened for reading  
			 BufferedWriter writer = new BufferedWriter(new FileWriter("jav1.txt"));
			 FileInputStream fis=new FileInputStream("jav .txt");       
			 Scanner sc=new Scanner(fis);    //file to be scanned  
			 //returns true if there is another line to read  
			 while(sc.hasNextLine())  
			 {  
				 check1Seat=0;
				 String line = sc.nextLine();
				 String newRow = viewCheck(email, seat, line);   //each row from .txt file is sent to another function for validation
				 writer.write(newRow);         //data is written back into another .txt fine
				 writer.newLine();           

			 }  
			 if(check1Rev==0)
				 System.out.println("Seat not found.");
			 sc.close();     //closes the scanner  
			 writer.close();
			 File f1 = new File("jav.txt");
			 f1.delete();                 //deleting previous .txt file
			 File f2 = new File("jav1.txt");
			 File f3 = new File("jav.txt");
			 f2.renameTo(f3);       //naming the other .txt file to the previous one
		 }  
		 catch(IOException e)  
		 {  
			 e.printStackTrace();  
		 }  
	 }
	 
	 
	 String viewCheck(String email, String seat, String row) // each row is send here for validation
	 {
		 String newRow="";
		 String[] words = null;
		 Pattern pattern = Pattern.compile(" ");
		 words = pattern.split( row );
		 words = row.split(" ");
		 if(check1Rev==0)
		 {
			 if(words[0].equals(seat))
			 {
				 if(words[6].equals(email))
				 {
					 
					         System.out.println("Your reservation is....");
							 newRow=words[0]+" "+words[1]+" "+words[2]+" "+words[3]+" "+words[4]+" "+words[5]+" "+words[6];
							 System.out.println("Seat number: "+words[0]);
							 if(words[2].equals("true"))
								 System.out.println("Seat Type: "+words[1]+" Window seat.");
							 else
								 System.out.println("Seat Type: "+words[1]+" Asile seat.");
							 System.out.println("Total Cost: "+words[5]);
							 System.out.println("Email: "+words[6]);
							 ++check1Rev;
							 ++check1Seat;
						 
					 
				 }
			 }
		 }
		 if(check1Seat==0)
			 newRow=row;
		 return newRow;
	 }
	 
	 
	 
	 
	 void cancel(String email, String seat)  //function to cancel reservation
	 {
		 try  
		 {  
			 //the file to be opened for reading  
			 BufferedWriter writer = new BufferedWriter(new FileWriter("jav1.txt"));
			 FileInputStream fis=new FileInputStream("jav.txt");       
			 Scanner sc=new Scanner(fis);    //file to be scanned  
			 //returns true if there is another line to read  
			 while(sc.hasNextLine())  
			 {  
				 check1Seat=0;
				 String line = sc.nextLine();
				 String newRow = cancelCheck(email, seat, line);   //each row is being send for validation
				 writer.write(newRow);        // data on the new .txt file is written
				 writer.newLine();

			 }  
			 if(check1Rev==0)
				 System.out.println("Seat not found.");
			 sc.close();     //closes the scanner  
			 writer.close();
			 File f1 = new File("jav.txt");
			 f1.delete();
			 File f2 = new File("jav1.txt");  //renaming and deleting previous .txt file
			 File f3 = new File("jav.txt");
			 f2.renameTo(f3);
		 }  
		 catch(IOException e)  
		 {  
			 e.printStackTrace();  
		 }  
	 }
	 
	 
	 String cancelCheck(String email, String seat, String row)   //each row is sent here for validation
	 {
		 String newRow="";
		 String[] words = null;
		 Pattern pattern = Pattern.compile(" ");
		 words = pattern.split( row );
		 words = row.split(" ");
		 if(check1Rev==0)
		 {
			 if(words[0].equals(seat))
			 {
				 if(words[6].equals(email))
				 {
							 newRow=words[0]+" "+words[1]+" "+words[2]+" "+words[3]+" "+words[4]+" "+words[5]+" free";
							 System.out.println("Seat cancelled succesfully!");
							 ++check1Rev;
							 ++check1Seat;
						 
					 
				 }
			 }
		 }
		 if(check1Seat==0)
			 newRow=row;
		 return newRow;
	 }
	 
	
	 void reservationWriter(String seatType, String isWindow, String isAsile, String isTable, String eMail)
	 {
		 try  
		 {  
			 //the file to be opened for reading  
			 BufferedWriter writer = new BufferedWriter(new FileWriter("jav1.txt"));
			 FileInputStream fis=new FileInputStream("jav.txt");       
			 Scanner sc=new Scanner(fis);    //file to be scanned  
			 //returns true if there is another line to read  
			 while(sc.hasNextLine())  
			 {  
				 checkSeat=0;
				 String line = sc.nextLine();
				 String newRow = reservationUpdate(seatType, isWindow, isAsile, isTable, eMail, line);
				 writer.write(newRow);
				 writer.newLine();

			 }  
			 if(checkRev==0)
				 System.out.println("Seat Not reserved! No mathing seats found.");
			 sc.close();     //closes the scanner  
			 writer.close();
			 File f1 = new File("jav.txt");
			 f1.delete();
			 File f2 = new File("jav1.txt");
			 File f3 = new File("jav.txt");
			 f2.renameTo(f3);
		 }  
		 catch(IOException e)  
		 {  
			 e.printStackTrace();  
		 }  
	 }
	
	 String reservationUpdate(String seatType, String isWindow, String isAsile, String isTable,String email, String row)
	 {                                       //function to check and make new reservations
		 String newRow="";
		 String[] words = null;
		 Pattern pattern = Pattern.compile(" ");
		 words = pattern.split( row );
		 words = row.split(" ");
		 if(checkRev==0)
		 {
			 if(words[1].equals(seatType))
			 {
				 if(words[2].equals(isWindow))
				 {
					 if(words[3].equals(isAsile))

					 {
						 if(words[4].equals(isTable))

						 {
							 if(words[6].equals("free"))
							 {
								 newRow=words[0]+" "+words[1]+" "+words[2]+" "+words[3]+" "+words[4]+" "+words[5]+" "+email;
								 System.out.println("Seat reserved!");
								 System.out.println("Seat number: "+words[0]);
								 if(words[2].equals("true"))
									 System.out.println("Seat Type: "+words[1]+" Window seat.");
								 else
									 System.out.println("Seat Type: "+words[1]+" Asile seat.");
								 System.out.println("Total Cost: "+words[5]);
								 System.out.println("Email: "+email);
								 ++checkRev;
								 ++checkSeat;
							 }
						 }
					 }
				 }
			 }
		 }
		 if(checkSeat==0)
			 newRow=row;
		 return newRow;

		
	}
	
	public static void main(String args[])
	{ 
        int input;
        seatBooking obj = new seatBooking();
        input= obj.choice();
        obj.menuChoice(input);

		
	} 
}