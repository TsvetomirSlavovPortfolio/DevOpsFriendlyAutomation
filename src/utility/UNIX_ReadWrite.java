package utility;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class UNIX_ReadWrite 
{
	//Site Refer - http://www.programmerclubhouse.com/index.php/reading-and-writing-linux-files-using-jsch-java/
	//http://www.jcraft.com/jsch/examples/
	
	public static void main(String[] args) throws IOException 
	{
		
		String str_Directory = "JEE/CPQDomain/logs/";
		String str_Host = "ilcm32906.eaas.amdocs.com";
		String str_Username = "pcicpq1";
		String str_Password = "Unix11!";
		int int_SSHPort = 22; 
		String str_File_Read = "weblogic.20170507_040530.log";
		String str_FileRead_Content = "";
		

		ExecuteFileFromLinux_Channel_exec("Automation/Scripts/","Echo_Execute.sh");
		//ExecuteFileFromLinux_Channel_exec("Automation/Scripts/","./Count_Error_In_File.sh");
		//ListFileFromLinux_Channel_sftp("/users/pcicpq1/Automation/Scripts/");
		//FileCopierOverNetwork("/users/pcicpq1/Automation/Scripts/","C:\\UTS\\UNIX_Files\\");


		str_FileRead_Content = ReadFileFromLinux(str_Host, str_Username, str_Password, int_SSHPort, str_Directory, str_File_Read);
		System.out.println("\n----------------------------------  Printing Logs Content  - Start----------------------------------  \n");
		System.out.println("str_FileRead_Content :- "+str_FileRead_Content);
		System.out.println("\n----------------------------------  Printing Logs Content  - End----------------------------------  \n");


/*		String str_FileWrite_Content = "Write this content to linux file";
		String str_File_Write = "weblogic.20170507_040530_Write.log";
		WriteFileToLinux(str_Host, str_Username, str_Password, int_SSHPort, str_FileWrite_Content, str_Directory, str_File_Write);*/
		
	}

	
	public static String ReadFileFromLinux(String str_Host, String str_Username, String str_Password, int int_Port, String str_FileDirectory, String str_FileName)
	{
		JSch obj_JSch = new JSch();
		Session obj_Session = null;
		StringBuilder obj_StringBuilder = new StringBuilder();
		try
		{
			obj_Session = obj_JSch.getSession(str_Username, str_Host);
			obj_Session.setPort(int_Port);
			obj_Session.setPassword(str_Password);
			Properties obj_Properties = new Properties();
			obj_Properties.put("StrictHostKeyChecking", "no");
			obj_Session.setConfig(obj_Properties);
			obj_Session.connect();
			System.out.println("Sucessfully Connected to UNIX Env");
			Channel obj_Channel = obj_Session.openChannel("sftp");
			obj_Channel.connect();
			ChannelSftp obj_SFTPChannel = (ChannelSftp) obj_Channel;
			obj_SFTPChannel.cd(str_FileDirectory);
			InputStream obj_InputStream = obj_SFTPChannel.get(str_FileName);
			char[] ch_Buffer = new char[0x10000];
			Reader obj_Reader = new InputStreamReader(obj_InputStream, "UTF-8");
			int int_Line = 0;
			do
			{
				int_Line = obj_Reader.read(ch_Buffer, 0, ch_Buffer.length);
				if (int_Line > 0)
				{ 
					obj_StringBuilder.append(ch_Buffer, 0, int_Line);
				}
			}
			while (int_Line >= 0);
			obj_Reader.close();
			obj_InputStream.close();
			obj_SFTPChannel.exit();
			obj_Channel.disconnect();
			obj_Session.disconnect();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return obj_StringBuilder.toString();
	}

	public static void WriteFileToLinux(String str_Host, String str_Username, String str_Password, int int_Port, String str_Content, String str_FileDirectory, String str_FileName)
	{
		JSch obj_JSch = new JSch();
		Session obj_Session = null;
		try
		{
			obj_Session = obj_JSch.getSession(str_Username, str_Host);
			obj_Session.setPort(int_Port);
			obj_Session.setPassword(str_Password);
			Properties obj_Properties = new Properties();
			obj_Properties.put("StrictHostKeyChecking", "no");
			obj_Session.setConfig(obj_Properties);
			obj_Session.connect();
			Channel obj_Channel = obj_Session.openChannel("sftp");
			obj_Channel.connect();
			ChannelSftp obj_SFTPChannel = (ChannelSftp) obj_Channel;
			obj_SFTPChannel.cd(str_FileDirectory);
			InputStream obj_InputStream = new ByteArrayInputStream(str_Content.getBytes());
			obj_SFTPChannel.put(obj_InputStream, str_FileDirectory + str_FileName );
			obj_SFTPChannel.exit();
			obj_InputStream.close();
			obj_Channel.disconnect();
			obj_Session.disconnect();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static List<String> ExecuteFileFromLinux_Channel_exec(String scriptFilePath,String command)
	{
		JSch jsch				=	null;
		Session session 		= 	null;
		ChannelExec channelExec = 	null;
		List<String> result = new ArrayList<String>();
		try
		{
			jsch = new JSch();

			session = jsch.getSession("pcicpq1", "ilcm32906.eaas.amdocs.com");
			session.setPort(22);
			session.setPassword("Unix11!");
			Properties obj_Properties = new Properties();
			obj_Properties.put("StrictHostKeyChecking", "no");
			session.setConfig(obj_Properties);
			session.connect();
			System.out.println("Connected to UNIX Env");
			
			
			Channel obj_Channel = session.openChannel("sftp");
			obj_Channel.connect();
			ChannelSftp obj_SFTPChannel = (ChannelSftp) obj_Channel;
			obj_SFTPChannel.cd(scriptFilePath);
			
			
			channelExec = (ChannelExec)session.openChannel("exec");
			InputStream in = channelExec.getInputStream();
			
			System.out.println("Execute Command :- "+command);
			channelExec.setCommand("sh "+(scriptFilePath+command));
			//channelExec.setCommand(command);

			
			channelExec.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null)
			{
				result.add(line);
			}
			int exitStatus = channelExec.getExitStatus();
			channelExec.disconnect();
			session.disconnect();

			if(exitStatus < 0)
			{
				System.out.println("result - "+result);
				System.out.println("Done, but exit status not set!");
				System.out.println("exitStatus - "+exitStatus);
			}
			else if(exitStatus > 0)
			{
				System.out.println("result - "+result);
				System.out.println("Done, but with error!");
				System.out.println("exitStatus - "+exitStatus);
			}
			else
			{
				System.out.println("result - "+result);
				System.out.println("Done!");
				System.out.println("exitStatus - "+exitStatus);
			}
			channelExec.disconnect();
			session.disconnect();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
		finally
		{
			System.out.println("Finally Block Called - Closing all connections");
			channelExec.disconnect();
			session.disconnect();
		}
		return result;
	}

	public static void ListFileFromLinux_Channel_sftp(String scriptFilePath)
	{
		//Function Call - ListFileFromLinux_Channel_sftp("/users/pcicpq1/Automation/Scripts/"); 
		JSch 		jsch 		= null;
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		try
		{
			jsch = new JSch();
			session = jsch.getSession("pcicpq1", "ilcm32906.eaas.amdocs.com");
			session.setPort(22);
			session.setPassword("Unix11!");
			Properties obj_Properties = new Properties();
			obj_Properties.put("StrictHostKeyChecking", "no");
			session.setConfig(obj_Properties);
			session.connect();
			System.out.println("Connected to UNIX Env");


			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;
			channelSftp.cd(scriptFilePath);
			@SuppressWarnings("rawtypes")
			Vector filelist = channelSftp.ls(scriptFilePath);
			for(int i=0; i<filelist.size();i++)
			{
				LsEntry entry = (LsEntry) filelist.get(i);
				System.out.println(entry.getFilename());
			}
			channelSftp.exit();
			channel.disconnect();
			session.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
		finally
		{
			System.out.println("Finally Block Called - Closing all connections");
			channelSftp.exit();
			channel.disconnect();
			session.disconnect();
		}
	}

	public static void FileCopierOverNetwork(String copyFrom ,String copyTo)
	{
		//Function Call - ListFileFromLinux_Channel_sftp("/users/pcicpq1/Automation/Scripts/"); 
		JSch 		jsch 		= null;
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		try
		{
			jsch = new JSch();
			session = jsch.getSession("pcicpq1", "ilcm32906.eaas.amdocs.com");
			session.setPort(22);
			session.setPassword("Unix11!");
			Properties obj_Properties = new Properties();
			obj_Properties.put("StrictHostKeyChecking", "no");
			session.setConfig(obj_Properties);
			session.connect();
			System.out.println("Connected to UNIX Env");


			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;
			channelSftp.get(copyFrom, copyTo);
			channelSftp.exit();
			channel.disconnect();
			session.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}
		finally
		{
			System.out.println("Finally Block Called - Closing all connections");
			channelSftp.exit();
			channel.disconnect();
			session.disconnect();
		}
	}

}


