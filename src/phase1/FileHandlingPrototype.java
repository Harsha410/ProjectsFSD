package phase1;

import java.io.*;
import java.util.*;

class FileHandling {
	File fileObject;
	Scanner scan = new Scanner(System.in);

	public HashSet<String> getFileLists(File fileObject) {

		String filePath;
		HashSet<String> sortedFileList;

		try {
			filePath = fileObject.getCanonicalPath();
			File tempFile = new File(filePath);
			String[] files = tempFile.list();
			sortedFileList = new HashSet<String>();
			if (files != null) {

				for (String file : files) {

					sortedFileList.add(file);
				}
				System.out.println("List of files in folder " + sortedFileList);
				return sortedFileList;
			} else {
				return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error : " + e.getMessage());
			return null;
		}

	}

	public void addFile(File fileObject) {
		try {
			if (fileObject.createNewFile()) {
				System.out.println("File created in the folder");
			} else {
				System.out.println("File already exists in the folder");
			}
		} catch (IOException e) {
			System.out.println("Error : " + e.getMessage());
		}

	}

	public void deleteFile(File fileObject) {
		try {
			if (fileObject.delete()) {
				System.out.println("File has been removed from the folder");
			} else {
				System.out.println("File doesn't exists in the folder");
			}
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}

	}

	public void searchFile(File fileObject) {
		try {
			if (fileObject.exists()) {
				System.out.println("File exists in the folder");
			} else {
				System.out.println("File doesn't exists in the folder");
			}
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}

	}

	public File getFolderPath() {
		File file = null;
		String filePath;
		try {
			System.out
					.println("Please specify the Folder path to retrieve files and do business level operations : \n");
			filePath = scan.next();
			file = new File(filePath);
			while (!file.exists()) {
				System.out.println("please enter valid path");
				filePath = scan.next();
				file = new File(filePath);
			}
			return file;
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
			return null;
		}

	}

	public File retriveFiles(int exitFlag) {
		int flag = exitFlag;
		File file = null;
		HashSet<String> fileList = null;
		char ch;
		boolean errorFlag = false;

		if (flag == 0) {
			file = getFolderPath();
			fileList = getFileLists(file);

		} else {
			System.out.println("Do you want to continue press 'y' or press any key to exit from the application");
			ch = scan.next().charAt(0);
			if ((ch == 'Y' || ch == 'y')) {
				file = getFolderPath();
				fileList = getFileLists(file);
				errorFlag = true;
			} else {
				System.out.println("Exited From Application");
				System.exit(0);

			}

		}
		return file;

	}

	public File getFilePath(String filePath) {

		System.out.println("Please specify the File Name : \n");
		String fileName = scan.next();
		File file = new File(filePath + "/" + fileName);
		return file;

	}
}

public class FileHandlingPrototype {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		char ch;
		FileHandling f = new FileHandling();
		File file, tempFile;
		String filePath = "";
		int choice, exitFlag = 0;
		boolean errorFlag = true;
//		do {

		do {

			file = f.retriveFiles(exitFlag);
			filePath = file.getAbsolutePath();
			do {

				System.out.println("Please select the operations from below list: ");
				System.out.println("1. Add a file to a folder ");
				System.out.println("2. Remove a file from a folder");
				System.out.println("3. Search for file in a folder");
				System.out.println("4. Exit from the current folder");
				errorFlag = true;
				try {
					choice = Integer.parseInt(input.readLine());
					// choice = scan.nextInt();
					switch (choice) {
					case 1:
						tempFile = f.getFilePath(filePath);
						f.addFile(tempFile);

						break;
					case 2:
						tempFile = f.getFilePath(filePath);
						f.deleteFile(tempFile);

						break;
					case 3:
						tempFile = f.getFilePath(filePath);
						f.searchFile(tempFile);

						break;

					case 4:
						errorFlag = false;
						exitFlag = 1;
						break;
					}

				} catch (Exception e) {
					System.out.println("Please Enter a valid option");

				}

			} while (errorFlag);

		} while (!errorFlag);

	}

}
