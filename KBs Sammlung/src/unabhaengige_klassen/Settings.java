package unabhaengige_klassen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * Saves Keys and Values as Pair to a File
 * @author Karsten
 *
 */
public class Settings {

	private static HashMap<String, String> _settings;
	private static File _filePath;
	private String _seperator = ":;<>:";
	private boolean _settingsChanged = false;
	
	/**
	 * Creates a Storage to save Strings to a File
	 * @param filePath String System-path for the File
	 */
	public Settings(String filePath) {
		if (filePath == null) {
			showMessage("The filePath is null");
		}
		else {
			_settings = new HashMap<String, String>();
			_filePath = new File(filePath);
			if (_filePath.exists()) {
				load();
			}
			else {
				_settingsChanged = true;
			}
		}
	}
	/**
	 * Loads the Strings from the existing File
	 */
	private void load() {
		try {
			Scanner sc = new Scanner(_filePath);
			while (sc.hasNext()) {
				String s = sc.next();
				int i = s.lastIndexOf(_seperator);
				String key = s.substring(0, (i - _seperator.length()));
				String value = s.substring(i, s.length() - 1);
				_settings.put(key, value);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			showMessage("The settings-data was not found: " + e.getClass() + "\n" + e.getStackTrace());
		}
	}
	/**
	 * Saves all given Settings to a File
	 */
	public void save() {
		if (_settingsChanged) {
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(_filePath));
				Set<String> keys = _settings.keySet();
				for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					bw.write(string + _seperator + _settings.get(string));
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				showMessage("The settings could not be saved: " + e.getClass() + "\n" + e.getStackTrace());
			}
		}
	}
	/**
	 * Adds the Strings to the Storage
	 * @param key String Specified Keyword of the Value
	 * @param value String Value
	 */
	public void add(String key, String value) {
		if (key == null || value == null) {
			showMessage("There is nothing to add, key and/or value == null");
		}
		else {
			_settings.put(key, value);
			_settingsChanged = true;
		}
	}
	/**
	 * Returns the Value of the given Key or null if key doesn't exists
	 * @param key String Keyword of the Value
	 * @return String Value
	 */
	public String get(String key) {
		return _settings.get(key);
	}
	/**
	 * GUI-Window error-message
	 * @param message String Textmessage
	 */
	private void showMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
