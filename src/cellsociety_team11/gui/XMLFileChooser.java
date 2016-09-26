package cellsociety_team11.gui;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import cellsociety_team11.MainController;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Initializes the File Explorer (File Chooser) for choosing a new .XML File
 * Extracted from MainBorderPane class to significantly reduce clutter.
 * Might end up making static since FileChooser is non-extendable
 * 
 */
public class XMLFileChooser {
	private static final String CURRENT_DIRECTORY = ".";
	
	private FileChooser xmlFileChooser;
	private ResourceBundle resourceBundle;
	
	public XMLFileChooser(String titleProperty, List<String> extensions, ResourceBundle resourceBundle) {
		this.resourceBundle=resourceBundle;
		this.xmlFileChooser = createFileChooser(titleProperty, extensions);
	}
	
	/*
	 * Returns the current instance of FileChooser
	 */
	public FileChooser getXMLFileChooser(){
		return this.xmlFileChooser;
	}
	
	private FileChooser createFileChooser(String titleProperty, List<String> extensions){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(this.resourceBundle.getString(titleProperty));
		for (String extension : extensions){
			fileChooser.getExtensionFilters().add(new ExtensionFilter(parseFileTitleFromExtension(extension), "*" + extension));
		}
		fileChooser.setInitialDirectory(getDefaultDirectory());
		return fileChooser;
	}
	
	private File getDefaultDirectory(){
		Path dataDirectory = Paths.get(MainController.DATA_DIRECTORY);
		if (Files.exists(dataDirectory, LinkOption.NOFOLLOW_LINKS)){
			return new File(MainController.DATA_DIRECTORY);
		}else{
			return new File(CURRENT_DIRECTORY);
		}
	}
	
	private String parseFileTitleFromExtension(String extension){
		String parsedExtension = extension.replaceAll(".", "");
		String FileTitle = parsedExtension + " " + this.resourceBundle.getString("FileTitle");
		return FileTitle;
	}

}
