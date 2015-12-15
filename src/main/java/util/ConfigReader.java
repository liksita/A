package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by m on 25.11.15.
 */
public class ConfigReader {

	static HashMap<String, String> settings = new HashMap<>();

	private static void readSettings() throws IOException {

		String absolutePath = new File(".").getAbsolutePath().replace(".", "").trim();
		File configFile = new File(absolutePath, "Config.txt");
		if (!configFile.exists()) {
			System.out.println("no config-File");
			System.exit(0);
		}

		FileReader fr = new FileReader(configFile);
		BufferedReader br = new BufferedReader(fr);
		String zeile = "";

		while ((zeile = br.readLine()) != null) {
			if (zeile.contains("banks")) {
				settings.put("banks", zeile);
			} else if (zeile.contains("boards")) {
				settings.put("boards", zeile);
			} else if (zeile.contains("brokers")) {
				settings.put("brokers", zeile);
			} else if (zeile.contains("dice")) {
				settings.put("dice", zeile);
			} else if (zeile.contains("games")) {
				settings.put("games", zeile);
			} else if (zeile.contains("jail")) {
				settings.put("jail", zeile);
			} else if (zeile.contains("players")) {
				settings.put("players", zeile);
			} else if (zeile.contains("services")) {
				settings.put("services", zeile);
			}
		}
		br.close();
	}

	public static String getSetting(String setting) throws IOException {
		readSettings();
		return settings.get(setting);
	}

	public static void main(String[] args) {

		File file = new File("Config.txt");
		String absolutePath = file.getAbsolutePath();
		System.out.println(file.getClass().getName() + " : " + absolutePath);
		System.out.println(file.isFile());

		// System.out.println(getSetting("games"));

	}
}
