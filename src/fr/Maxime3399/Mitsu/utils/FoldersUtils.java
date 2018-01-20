package fr.Maxime3399.Mitsu.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import fr.Maxime3399.Mitsu.MainClass;

public class FoldersUtils {
	
	public static boolean typeExist(String type){
		
		boolean result = false;
		
		for(String s : findFoldersInDirectory(MainClass.getInstance().getConfig().getString("Directory"))){

			if(s.equalsIgnoreCase(type)){
				
				result = true;
				
			}
			
		}
		
		return result;
		
	}
	
	public static List<String> findFoldersInDirectory(String directoryPath) {
	    File directory = new File(directoryPath);
		
	    FileFilter directoryFileFilter = new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    };
			
	    File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
	    List<String> foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
	    for (File directoryAsFile : directoryListAsFile) {
	        foldersInDirectory.add(directoryAsFile.getName());
	    }

	    return foldersInDirectory;
	}
	
}
