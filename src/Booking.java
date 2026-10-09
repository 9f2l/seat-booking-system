
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Booking  
{  
	static String textLine[][]=new String[0][]; //double dimensional array, sized to fit the .txt file
	int checkRev=0,checkSeat=0;
	
	void fileReader() //for reading from the .txt file
	{
		try  
		{  
			//the file to be opened for reading  
			FileInputStream fis=new FileInputStream("seats.txt");
			Scanner sc=new Scanner(fis);    //file to be scanned
			//returns true if there is another line to read
			ArrayList<String[]> rows=new ArrayList<String[]>();
			while(sc.hasNextLine())
			{
				String line = sc.nextLine().trim();
				if(line.isEmpty())
					continue;                 //skipping blank lines
				rows.add(line.split(" "));    //storing each line and each word
			}
			sc.close();     //closes the scanner
			textLine=rows.toArray(new String[0][]);
		}
		catch(IOException e)  
		{  
			e.printStackTrace();  
		}  
	}
	
	void fileWriter()  //for updating the modified data back in .txt file
	{
		try
		 {
			 //the file to be opened for writing
			 BufferedWriter writer = new BufferedWriter(new FileWriter("seats.txt"));
			 for(int i=0;i<textLine.length;i++)
			 {
				 writer.write(String.join(" ", textLine[i]));   //writing each seat's words back in .txt file
				 writer.newLine();
			 }
			 writer.close();
		 }
		 catch(IOException e)  
		 {  
			 e.printStackTrace();  
		 }  
	}
	
	void reservation(String type, String window, String aisle, String table, String email)
	{   // function for checking for reservation
		int count=0;
		for(int i=0;i<textLine.length;i++)
		{
			if(textLine[i][1].equals(type))
			{
				if(textLine[i][2].equals(window))
				{
					if(textLine[i][3].equals(aisle))    //checking if the seats available match the users input

					{
						if(textLine[i][4].equals(table))

						{
							if(textLine[i][6].equals("free"))
							{
								textLine[i][6]=email;          //updating the email address in the array, so it could be written back in .txt file later
								System.out.println("Seat reserved!");      
								System.out.println("Seat number: "+textLine[i][0]);
								if(textLine[i][2].equals("true"))
									System.out.println("Seat Type: "+textLine[i][1]+" Window seat.");
								else
									System.out.println("Seat Type: "+textLine[i][1]+" Aisle seat.");
								System.out.println("Total Cost: "+textLine[i][5]);
								System.out.println("Email: "+email);
								fileWriter();     //writing back in .txt file
								++count;
								return;
							}
						}
					}
				}
			}
		}
		if(count==0)
			System.out.println("Seat configuration not available!");
	}

	static int readInt(Scanner sc)   //reads a whole line and re-asks until it is a valid number
	{
		while(true)
		{
			try
			{
				return Integer.parseInt(sc.nextLine().trim());
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid input! Please enter a number.");
			}
		}
	}

	public static void main(String args[])
	{
		Booking obj =new Booking();
		obj.fileReader();                       //reading from the .txt file and string in an array
		Scanner sc = new Scanner(System.in);    //one scanner for the whole program

		while(true)
		{
			System.out.println("--Seat Booking System--");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("--MAIN MENU--");
			System.out.println("1-Reserve Seat");
			System.out.println("2-Cancel Seat");
			System.out.println("3-View Seat Reservation");
			System.out.println("4-Quit");
			System.out.println("Pick:");




			int input = readInt(sc);
			if(input==1)             //for making new reservations
			{
				System.out.println("To continue to reserve your seat enter your email below.");
				System.out.println("Or press Q to cancel.");
				String email="";
				while(true)
				{
					email = sc.nextLine();
					if(email.equals("")||email.equals(" "))
					{
						System.out.println("To continue to reserve your seat enter a valid your email below.");
						System.out.println("Or press Q to cancel.");
					}
					else if(email.equalsIgnoreCase("Q"))
					{
						System.out.println("Thank you for using our software.");
						return;
					}
					else
						break;
				}
				System.out.println("How many seats would you like to book? Note: You can book maximum 5 at a time!");
				int numberOfSeats;
				while(true)
				{
					numberOfSeats = readInt(sc);
					if(numberOfSeats>5 || numberOfSeats<1)
					{
						System.out.println("Enter seats within range!");
					}
					else
						break;
				}
				int i;

				for(i=1;i<=numberOfSeats;i++)    //using loop if the user wants to book multiple seats
				{
					if(i>1)
						sc.nextLine();      //waits for Enter before the next seat
					System.out.println("Configuration of seat/s: "+ i);
					String seatClass = "";
					while(true)
					{
						System.out.println("Would you like First or Standard class?; Enter f for First class and s for Standard class. ");
						System.out.println("Or press Q to cancel.");
						seatClass = sc.nextLine();
						if(seatClass.equalsIgnoreCase("f"))
						{
							seatClass="1ST";
							break;
						}
						else if(seatClass.equalsIgnoreCase("s"))
						{
							seatClass="STD";
							break;
						}
						else if(seatClass.equalsIgnoreCase("Q"))
						{
							System.out.println("Thank you for using our software.");
							return;
						}
						else
						{
							System.out.println("Invalid input!");
						}
					}
					String seatType;
					String isWindow="false";
					String isAisle="false";
					while(true)
					{
						System.out.println("Would you like Window or Aisle seat?; Enter a for Aisle class and w for Window seat. ");
						System.out.println("Or press Q to cancel.");
						seatType = sc.nextLine();

						if(seatType.equalsIgnoreCase("a"))
						{
							isAisle="true";
							break;
						}
						else if(seatType.equalsIgnoreCase("w"))
						{
							isWindow="true";
							break;
						}
						else if(seatType.equalsIgnoreCase("Q"))
						{
							System.out.println("Thank you for using our software.");
							return;
						}
						else
						{
							System.out.println("Invalid input!");
						}
					}
					String seatTable;
					String isTable;
					while(true)
					{
						System.out.println("Would you like a seat with table?; Enter y for Yes and n for No.");
						System.out.println("Or press Q to cancel.");
						seatTable = sc.nextLine();
						if(seatTable.equalsIgnoreCase("y"))
						{
							isTable="true";
							break;
						}
						else if(seatTable.equalsIgnoreCase("n"))
						{
							isTable="false";
							break;
						}
						else if(seatTable.equalsIgnoreCase("Q"))
						{
							System.out.println("Thank you for using our software.");
							return;
						}
						else
						{
							System.out.println("Invalid input!");

						}
					}
					obj.reservation(seatClass, isWindow, isAisle, isTable, email);   //calling reservation function to check and book
					System.out.println("Press Enter to continue.");
					System.out.println("    ");
					System.out.println("    ");
					//String enter = sc.nextLine();
				}
				System.out.println("Type M and press Enter to go back to the main menu, or press Enter to end the program.");
				String quit=sc.nextLine();
				if(quit.equalsIgnoreCase("m"))
				{
					System.out.println("    ");
				}
				else
				{
					System.out.println("Thank you for using our software.");
					return;
				}

			}



			else if(input==2)    //for canceling reservation
			{
				
				
					int count=0;
					System.out.println("Enter seat to be cancelled.");
					String cancelSeat = sc.nextLine();
					System.out.println("Enter registered email.");
					String cancelEmail;
					while(true)
					{
						cancelEmail = sc.nextLine();
						if(cancelEmail.equals("")||cancelEmail.equals(" "))
						{
							System.out.println("To continue enter a valid your email.");
						}
						else
							break;
					}
					for(int i=0;i<textLine.length;i++)
					{
						if(textLine[i][0].equalsIgnoreCase(cancelSeat))
						{
							if(textLine[i][6].equalsIgnoreCase(cancelEmail))
							{
								textLine[i][6]="free";
								System.out.println("Seat cancelled successfully!");
								++count;
								obj.fileWriter();    //calling to update on .txt file
								break;
							}
						}
					}

					
					if(count==0)
					{
						System.out.println("Seat not found!");

					}
					System.out.println(" ");
					System.out.println("Type M and press Enter to go back to the main menu, or press Enter to end the program.");
					String quit=sc.nextLine();
					if(quit.equalsIgnoreCase("m"))
					{
						System.out.println("    ");
					}
					else
					{
						System.out.println("Thank you for using our software.");
						return;
					}
				
			}


			else if(input==3)  // for viewing reservations
			{
				while(true)
				{
					for(int i=0;i<textLine.length;i++)
					{

						if(textLine[i][6].equals("free"))
						{
							if(textLine[i][2].equals("true"))
							{
								if(textLine[i][4].equals("true"))
								{
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Window Seat With Table, Price: "+textLine[i][5]+" AVAILABLE");
								}
								else
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Window Seat Without Table, Price: "+textLine[i][5]+" AVAILABLE");
							}
							else
							{
								if(textLine[i][4].equals("true"))
								{
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Aisle Seat With Table, Price: "+textLine[i][5]+" AVAILABLE");
								}
								else
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Aisle Seat Without Table, Price: "+textLine[i][5]+" AVAILABLE");
							}
						}
						else
						{
							if(textLine[i][2].equals("true"))
							{
								if(textLine[i][4].equals("true"))
								{
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Window Seat With Table, Price: "+textLine[i][5]+" BOOKED");
								}
								else
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Window Seat Without Table, Price: "+textLine[i][5]+" BOOKED");
							}
							else
							{
								if(textLine[i][4].equals("true"))
								{
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Aisle Seat With Table, Price: "+textLine[i][5]+" BOOKED");
								}
								else
									System.out.println(textLine[i][0]+" "+textLine[i][1]+" Aisle Seat Without Table, Price: "+textLine[i][5]+" BOOKED");
							}
						}


					}
					System.out.println(" ");
					System.out.println("Type M and press Enter to go back to the main menu, or press Enter to end the program.");
					String quit=sc.nextLine();
					if(quit.equalsIgnoreCase("m"))
					{
						System.out.println("    ");
					}
					else
					{
						System.out.println("Thank you for using our software.");
						return;
					}
					break;
				}
			}


			else if(input==4)
			{
				System.out.println("Thank you, please use our software again!");
				return;
			}
			else
				System.out.println("Invalid input! Please enter again,");

		} 

	}
}  