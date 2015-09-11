import java.io.*;
import java.util.*;

public class EECSFacultyReader
{
	public static int findFirstSpaceOrZero(String n, int index)
	{
		for (int a = index;a>=0;a--)
		{
			if (n.charAt(a) == " ".charAt(0))
				return a;
		}
		return 0;
	}
	public static boolean isAnythingInteger(String n)
	{
		for(int a = 0;a<n.length();a++)
			if(n.charAt(a)=='0' || n.charAt(a)=='1' ||n.charAt(a)=='2'||n.charAt(a)=='3'||n.charAt(a)=='4'||n.charAt(a)=='5'||n.charAt(a)=='6'||n.charAt(a)=='7'||n.charAt(a)=='8'||n.charAt(a)=='9')
			{
				return true;
			}
		return false;
	}
	public static ArrayList<String> findResearchInterests(String n)
	{
		ArrayList<String> researchInterests=new ArrayList<String>();
		int a = 0;
		while(a<n.length())
		{
			if (n.charAt(a)=='(')
			{
				for(int b = a+1;b<n.length();b++)
				{
					if (n.charAt(b)==')' && !(isAnythingInteger(n.substring(a, b))) && n.substring(a,b).toUpperCase().equals(n.substring(a, b)))
					{
						researchInterests.add(n.substring(a+1,b));
						System.out.println(n.substring(a+1,b));
						a=b+1;
						break;
					}
					
						
				}
			
			}
		a+=1;
		}
		return researchInterests;
	}
	public static String getFullEmail(String info)
	{
		int indexofat = info.indexOf("@");
		
		int start = findFirstSpaceOrZero(info,indexofat);
		int end = info.indexOf(" ", indexofat);
		if(end == -1)
			end = info.length();
		return info.substring(start+1, end);
		
			
	}
	public static String processEmail(String email)
	{
		if (email.contains("."))
			return email;
		return email+".berkeley.edu";
	}
	public static void main (String [] args) throws IOException
	{
		BufferedReader buf = new BufferedReader(new FileReader("profsmiscinfo.txt"));
		String line;
		ArrayList<String> officelocations = new ArrayList<String>();
		ArrayList<String> emailaddresses = new ArrayList<String>();
		ArrayList<ArrayList<String>> researchinterests = new ArrayList<ArrayList<String>>();
		line = buf.readLine();
		line = buf.readLine();
		//System.out.println("b"+line);
		
		while ((line = buf.readLine()) != null)
		{
			
				
			int firstcomma = line.indexOf(",");
			//System.out.println(line+firstcomma);
			if(firstcomma == -1)
				officelocations.add("Office location unavailable");
			
			else if (line.substring(0,firstcomma).contains("Hall") || line.substring(0,firstcomma).contains("Building") || line.substring(0,firstcomma).contains("Laboratory"))
			{	
				String locationsubstring = line.substring(0,firstcomma);
				officelocations.add(locationsubstring);
			}
			else
				officelocations.add("Office location unavailable");
			int emailaddressindex = line.indexOf("@");
			if (emailaddressindex == -1)
				emailaddresses.add("Email not available");
			else
				emailaddresses.add(processEmail(getFullEmail(line)));
			researchinterests.add(findResearchInterests(line));
			
			
		}
		System.out.println(officelocations);
		System.out.println(emailaddresses);
		System.out.println(researchinterests);
	}
	


}