package com.dicipulus.app.fileController;

import java.io.File;

import org.apache.commons.io.FileUtils;

public final class DelteFiles {

	public static void deleteFiles(String pathNow){
		File folder = new File(pathNow);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				boolean isSuccess = org.springframework.util.FileSystemUtils
						.deleteRecursively(listOfFiles[i].getAbsoluteFile());
			} else if (listOfFiles[i].isDirectory()) {
				// do nothing
			}
		}
	}
}
