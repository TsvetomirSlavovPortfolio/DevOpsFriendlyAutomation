package utility;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.Test;

import utility.Log;

public class WindowsProcess
{
	@Test
	public static void CheckProcessRunning()
	{
		//PropertyConfigurator.configure("log4j.properties");
		try 
		{
			List<String> listProcessName = new ArrayList<String>();
			listProcessName.add("chromedriver.exe");
			listProcessName.add("EXCEL.EXE");
			listProcessName.add("psr.exe");
			for (String temp : listProcessName) 
			{
				kill(temp);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Log.error(e.getMessage());
		}
	}
	
	public static void kill(String processName) throws Exception
	{
		if (isRunning(processName))
		{
			Log.info("Process is running by the name : "+processName);
			Log.info("Killing process : "+processName);
			Runtime.getRuntime().exec("taskkill /F /IM " + processName);
			Log.info("Killed process : "+processName);
		}
		else
		{
			Log.info("NO Process is running by the name : "+processName);
		}
	}

	private static boolean isRunning(String processName) throws Exception
	{
		Process listTasksProcess = Runtime.getRuntime().exec("tasklist");
		BufferedReader tasksListReader = new BufferedReader(new InputStreamReader(listTasksProcess.getInputStream()));

		String tasksLine;

		while ((tasksLine = tasksListReader.readLine()) != null)
		{
			if (tasksLine.contains(processName))
			{
				return true;
			}
		}

		return false;
	}
	
	public static void ArrayList_Iterator() 
	{		 
		List<String> crunchifyList = new ArrayList<String>();
 
		// add 4 different values to list
		crunchifyList.add("eBay");
		crunchifyList.add("Paypal");
		crunchifyList.add("Google");
		crunchifyList.add("Yahoo");
 
		// iterate via "for loop"
		System.out.println("==> For Loop Example.");
		for (int i = 0; i < crunchifyList.size(); i++) {
			System.out.println(crunchifyList.get(i));
		}
 
		// iterate via "New way to loop"
		System.out.println("\n==> Advance For Loop Example..");
		for (String temp : crunchifyList) {
			System.out.println(temp);
		}
 
		// iterate via "iterator loop"
		System.out.println("\n==> Iterator Example...");
		Iterator<String> crunchifyIterator = crunchifyList.iterator();
		while (crunchifyIterator.hasNext()) {
			System.out.println(crunchifyIterator.next());
		}
 
		// iterate via "while loop"
		System.out.println("\n==> While Loop Example....");
		int i = 0;
		while (i < crunchifyList.size()) {
			System.out.println(crunchifyList.get(i));
			i++;
		}
 
		// collection stream() util: Returns a sequential Stream with this collection as its source
		System.out.println("\n==> collection stream() util....");
		crunchifyList.forEach((temp) -> 
		{
			System.out.println(temp);
		});
	}

}
