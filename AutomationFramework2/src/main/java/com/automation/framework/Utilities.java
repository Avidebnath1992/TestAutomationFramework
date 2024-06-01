package com.automation.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.io.BufferedWriterWriter;

public class Utilities {
	public static String def_Loc = System.getProperty("user.dir") + "\\";
	public static String pss = "";

	/**
	 * Method to copy file across directory
	 * 
	 * @param sourceFileloc
	 * @param destFileloc
	 * @throws IOException
	 */
	public static void copyFile(String sourceFileloc, String destFileloc) throws IOException {
		File sourceFile = new File(sourceFileloc);
		File destFile = new File(destFileloc);

		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new RandomAccessFile(sourceFile, "rw").getChannel();
			destination = new RandomAccessFile(destFile, "rw").getChannel();

			long position = 0;
			long count = source.size();
			source.transferTo(position, count, destination);
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
/**
 * Method to get data from Properties File
 * @param fileloc
 * @param delimeter
 * @return
 * @throws Throwable
 */
	public static Map<String,String> readPropertyFile(String fileloc,String delimeter)throws Throwable{
		Map<String,String>map=new HashMap<String, String>();
		BufferedReader reader= new BufferedReader(new FileReader(fileloc));
		String line;
		while((line=reader.readLine())!=null) {
			if(line.contains(delimeter)) {
				String key=line.split(delimeter)[0].toString();
				String value=line.split(delimeter)[1].toString();
				map.put(key, value);
			}
		}
		reader.close();
		return map;
	}
	/**
	 * Method to create an empty directory with a specific name.
	 * @param dirPath
	 */

	public static void createDir(String dirPath) {
		Path path = Paths.get(dirPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				System.out.println("Directory has been created successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to execute any windows command locally.
	 * @param winCommand windows command as String
	 * <p> Example:<br>
	 * a.executeCmdCommand("net use \\\\ IP/user: Administrator password/persistent:no");<br
	 * @throws Exception
	 */
	public static void executeCmdCommandNoWait(String winCommand) {
		if (!winCommand.equals(null) && !winCommand.isEmpty() && !winCommand.equals("")) {
			try {
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", winCommand);
				builder.redirectErrorStream(true);
				Process p = builder.start();
				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while (true) {
					line = r.readLine();
					if (line == null) {
						break;
					}
					if(line.contains("report.html")) {break;}
					if(line.contains("Starting Chromedriver")) {
						System.out.println("Noted Session Info:" + line);
					}
					System.out.println(line);
				}
			} catch (Exception err) {
				System.out.println("Unable to execute command: '" + winCommand + "' Exception Log: " + err.toString());
			}
		} else {
			System.out.println("Invalid windows Command: " + winCommand);
		}
	}

	/**
	 * Method to execute any windows command locally.
	 * @param winCommand windows command as String
	 * <p> Example:<br>
	 * a.executeCmdCommand("net use \\\\ IP/user: Administrator password/persistent:no");<br
	 * @throws Exception
	 */
	public static void executeCmdCommand(String winCommand) {
		if (!winCommand.equals(null) && !winCommand.isEmpty() && !winCommand.equals("")) {
			try {
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", winCommand);
				builder.redirectErrorStream(true);
				Process p = builder.start();
				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while (true) {
					line = r.readLine();
					if (line == null) {
						break;
					}
				}
			} catch (Throwable th) {
				System.out.println("Unable to execute command: '" + winCommand + "' Exception Log: " + th.toString());
			}
		} else {
			System.out.println("Invalid windows Command: " + winCommand);
		}
	}

	/**
	 * Method to check a file is present. could be in a network path
	 * @param filePath  <br> Full qualified path (with extension) of the file as String
	 * @param uname     <br> Username to connect to network path . leave blank if the path is blank
	 * @param password <br> password to connect to network path. leave blank if the path is blank
	 * @return true if the path is valid and is a file;else false
	 * @throws Throwable
	 */
	public static boolean checkFilePresent(String filePath, String uname, String password) throws Throwable {
		if (filePath.startsWith("\\\\")) {
			if (!uname.isEmpty() && !password.isEmpty() && !uname.equals("") && !password.equals("")
					&& !uname.equals(null) && !password.equals(null)) {
				String[] str = filePath.split(("\\\\"));
				String path = "";
				path = filePath.replace("\\" + str[str.length - 1], "");
				executeCmdCommand("not use " + path + " /user:" + uname + " " + password + " /persistent:no");
				Thread.sleep(1000);
				File f = new File(filePath);
				if (f.exists()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			File f = new File(filePath);
			if (f.exists()) {
				return true;
			} else {
				return false;
			}
		}
	}
	/**
	 * Method to get file count in a local directory.
	 * @param dirPath
	 * @return
	 * @throws Throwable
	 */
	public static int getDirFileCount(String dirPath)throws Throwable{
		int filecount=-1;
		try {
			File directory =new File(dirPath);
			if(directory!=null && directory.isDirectory()) {
				filecount=directory.list().length;
				System.out.println(filecount);
			}else {
				Reporter.warningStep("Directory::" + dirPath+":: not exists.");
			}
		}catch(Throwable th) {
			Reporter.warningStep("Unable to fetch Dir File Count For::" + dirPath);
		}return filecount;
	}
	/**
	 * Method to delete a specific file in a specific folder.
	 * @param folderPath
	 * @param fileName
	 * @throws Throwable
	 */
	
	public static void deleteFile(String folderPath,String fileName)throws Throwable{
		String methodName="deleteFile";
		try {
			File f = new File(folderPath+"/"+fileName);
			f.delete();
			System.out.println("File :: "+ fileName +" has been deleted. ");
			Reporter.add2LogNote("INFO", "PASS", methodName+":: Deleted File:" +folderPath+"\\"+fileName);
		}catch(Throwable th) {
			SuperAction.catchErrorWithMessage(th, "deleteFile");
		}
	}
	
	/**
	 * Method to delete all file under a specific folder.
	 * @param folderPath
	 * @throws Throwable
	 */
	
	public static void deleteAllFilesInFolder(String folderPath)throws Throwable{
		String path=folderPath;
		try {
			File file = new File(path);
			File[] files= file.listFiles();
			for(File f:files) {
				if(f.isFile() && f.exists()) {
					f.delete();
					Reporter.add2LogNote("INFO", "PASS", "Deleted File: "+ folderPath+"/"+f);
				}else {
					Reporter.add2LogNote("SEVERE", "Fail", "Unable to Deleted File: "+ folderPath+"/"+f);	
				}
			}
		}catch(Throwable th) {
			SuperAction.catchErrorWithMessage(th, "deleteAllFilesInFolder");
		}
	}
	/**
	 * Method to Create and write text to a text file.
	 * @param sbInclude <br> String to be written
	 * @param FileName <br> The name of the file
	 * @throws IOException signals that an I/O exception has occurred.
	 */
	public static void createTextFile(StringBuilder sbInclude, String FileName) throws IOException{
		BufferedWriter output= null;
		try {
			File file=new File(FileName);
			output= new BufferedWriter(new FileWriter(file));
			output.write(sbInclude.toString());
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(output!=null) {
				output.close();
			}
		}
	}
/**
 * Method to Write Data to a Text File
 * @param FilePath
 * @param str
 * @throws Throwable
 */
	public static void writeData2Text(String FilePath,String str) throws Throwable{
		String line=null;
		String allLine="";
		try {
			File file= new File(FilePath);
			if(!file.exists()) {
				file.createNewFile();
			}
			else {
				BufferedReader br= new BufferedReader(new FileReader(FilePath));
				allLine="";
				try {
					line= br.readLine();
					while(line!=null) {
						allLine=(allLine+line+System.lineSeparator());
						line=br.readLine();
					}
					br.close();
				}catch(Exception e) {
					System.out.println(e.toString());
				}
			}
			FileWriter fw=new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw=new BufferedWriter(fw);
			bw.append(allLine+str);
			bw.close();
		}catch(Throwable th) {
			SuperAction.catchErrorWithMessage(th, "WriteData2Text");
		}
	}
	/**
	 * Method to replace a String in a file line by line
	 * @param filePath
	 * @param oldString
	 * @param newString
	 */
	public static void replaceStringInFile(String filePath,String oldString, String newString) {
		File fileToBeModified =new File(filePath);
		String oldContent="";
		BufferedReader reader = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			reader =new BufferedReader(new FileReader(fileToBeModified));
			String line=reader.readLine();
			while(line!=null) {
				oldContent = oldContent+line+System.lineSeparator();
				line=reader.readLine();
			}
			reader.close();
			String newContent=oldContent.replaceAll(oldString, newString);
			
			fw=new FileWriter(filePath);
			bw=new BufferedWriter(fw);
			bw.write(newContent);
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(bw!=null)
					bw.close();
				if(fw!=null)
					fw.close();
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	/**
	 * Method to create a ZIP file from a Directory
	 * @param dirPath
	 * @param zipFilePath
	 * @throws Throwable
	 */
	public static void createZipFileFromDir(String dirPath,String zipFilePath) throws Throwable{
		String ZipFile = zipFilePath;
		String srcDir=dirPath;
		if(zipFilePath==null || zipFilePath=="") {
			ZipFile=srcDir+".zip";
		}
		FileOutputStream fos = new FileOutputStream(ZipFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		addDirToZipArchive(zos,new File(srcDir),null);
		zos.flush();
		fos.flush();
		zos.close();
		fos.close();
	}
	/**
	 * 
	 * @param zos
	 * @param fileToZip
	 * @param parrentDirectoryName
	 * @throws Exception
	 */
	public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName)throws Exception{
		if(fileToZip==null || !fileToZip.exists()) {
			return;
		}
		String zipEntryName=fileToZip.getName();
		if(parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) {
			zipEntryName=parrentDirectoryName+"/"+fileToZip.getName();
		}
		if(fileToZip.isDirectory()) {
			System.out.println("+" + zipEntryName);
			for(File file : fileToZip.listFiles()) {
				addDirToZipArchive(zos, file, zipEntryName);
			}
		}else {
			System.out.println("   "+zipEntryName);
			byte[] buffer =new byte[1024];
			FileInputStream fis =new FileInputStream(fileToZip);
			zos.putNextEntry(new ZipEntry(zipEntryName));
			int length;
			while((length=fis.read(buffer))>0) {
				zos.write(buffer,0,length);
			}
			zos.closeEntry();
			fis.close();
		}
	}
}
