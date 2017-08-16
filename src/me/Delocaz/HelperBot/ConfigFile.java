package me.Delocaz.HelperBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile {
	private HelperBot hb;
	private YamlConfiguration lng;
	public ConfigFile(HelperBot hb) {
		this.hb = hb;
		init();
	}
	private void init() {
		hb.getConfig().addDefault("extension", "txt");
		hb.getConfig().addDefault("addhelp.newline", "/n/");
		hb.getConfig().addDefault("default", "default");
		hb.getConfig().addDefault("permissions.help", "helperbot.help");
		hb.getConfig().addDefault("permissions.pagehelp", "helperbot.help.%page");
		hb.getConfig().addDefault("permissions.addhelp", "helperbot.addhelp");
		hb.getConfig().addDefault("permissions.delhelp", "helperbot.delhelp");
		hb.getConfig().addDefault("shortcodes.player", "%player");
		hb.getConfig().addDefault("shortcodes.x", "%x");
		hb.getConfig().addDefault("shortcodes.y", "%y");
		hb.getConfig().addDefault("shortcodes.z", "%z");
		hb.getConfig().addDefault("shortcodes.level", "%level");
		hb.getConfig().addDefault("shortcodes.world", "%world");
		hb.getConfig().addDefault("shortcodes.coords", "%coords");
		hb.getConfig().addDefault("locale", "en_US");
		hb.getConfig().options().copyDefaults(true);
		hb.saveConfig();
		lng = new YamlConfiguration();
		File f = new File(hb.getDataFolder().getPath() + File.separator + "lang" + File.separator + get("locale") + ".yml");
		try {
			lng.load(f);
		} catch (FileNotFoundException e) {
			try {
				if(f.getParentFile().mkdir()) {
					if(!f.createNewFile()){
						System.out.println("Failed to create a default config file.");
					}else{
						lng.load(f);
					}
				}else{
					System.out.println("Failed to create a directory for the default config file.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InvalidConfigurationException e1) {
				e.printStackTrace();
			}
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		lng.addDefault("deletedSuccessfully", "&4%page &adeleted successfully.");
        lng.addDefault("deletedUnSuccessfully", "&4%page &cNOT deleted successfully.");
        lng.addDefault("createdSuccessfully", "&4%page &acreated successfully.");
		lng.addDefault("specifyPage", "&fPlease specify a page.");
		lng.addDefault("missingContent", "&fTell me what to put in &4%page&f!");
		lng.addDefault("noPermission", "Unknown command. Type \"help\" for help.");
		lng.options().copyDefaults(true);
		try {
			lng.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String tag) {
		return hb.getConfig().getString(tag);
	}

	public String getLang(String path) {
		return hb.colorize(lng.getString(path));
	}

	public void reload() {
		hb.reloadConfig();
		init();
	}
}
