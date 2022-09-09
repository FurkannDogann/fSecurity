package org.FurkannDogann.fSecurity.main;

import org.FurkannDogann.fSecurity.commands.fSecurityCommands;
import org.FurkannDogann.fSecurity.events.fSecurityEvents;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class fSecurityMain extends JavaPlugin implements Listener, CommandExecutor{
	
	public FileConfiguration fSecurityConfig = this.getConfig();
	
	public void onEnable() {
		consoleSender();
		enableCommands();
		enableEvents();
		loadConfig();
	}
	
	public void onDisable() {}
	
	public void consoleSender() {
        Bukkit.getConsoleSender().sendMessage("--------------------------------------");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("fSecurity Aktif Ediliyor..");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("Main Yuklendi.");
		Bukkit.getConsoleSender().sendMessage("Komutlar Yuklendi.");
		Bukkit.getConsoleSender().sendMessage("Eventler Yuklendi.");
		Bukkit.getConsoleSender().sendMessage("Config.yml & Data.yml & Plugin.yml Yuklendi.");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("fSecurity Basariyla Aktif Edildi!");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("--------------------------------------");		
	}
	
	public void enableCommands() {
		Bukkit.getServer().getPluginCommand("doğrula").setExecutor(new fSecurityCommands());
		Bukkit.getServer().getPluginCommand("fsecurity").setExecutor(new fSecurityCommands());		
	}
	
	public void enableEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new fSecurityEvents(), this);
	}
	
	public void loadConfig() {
		fSecurityConfig.options().copyDefaults(true);
		saveConfig();		
	}
	
	@SuppressWarnings("deprecation")
	public static void fSecurityHelp(Player oyuncu) {
		oyuncu.playSound(oyuncu.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
		oyuncu.sendTitle("&6fSecurity".replaceAll("&", "§"), "&aFurkanDogan &eTarafından Geliştirildi".replaceAll("&", "§"));
		oyuncu.sendMessage("&0&l&m-[ &6fSecurity Yardım Menüsü &0&l&m]-".replaceAll("&", "§"));
		oyuncu.sendMessage("");
		oyuncu.sendMessage("&4➜ &eEklenti Bilgileri".replaceAll("&", "§"));
		oyuncu.sendMessage("");
		oyuncu.sendMessage("&c● &aGeliştirici: &bFurkanDogan".replaceAll("&", "§"));
		oyuncu.sendMessage("&c● &aVersiyon: &b1.0.0".replaceAll("&", "§"));
		oyuncu.sendMessage("");
		oyuncu.sendMessage("&4➜ &eEklenti Komutları".replaceAll("&", "§"));
		oyuncu.sendMessage("");
		oyuncu.sendMessage("&c● &a/fsecurity: &dYardım Menüsünü Gösterir.".replaceAll("&", "§"));
		oyuncu.sendMessage("&c● &a/fsecurity yenile: &dEklenti Yenilenir.".replaceAll("&", "§"));
		oyuncu.sendMessage("&c● &a/doğrula: &dGüvenlik Kodunu Girmenizi Sağlar.".replaceAll("&", "§"));
		oyuncu.sendMessage("");
		oyuncu.sendMessage("&0&l&m-[ &6fSecurity Yardım Menüsü &0&l&m]-".replaceAll("&", "§"));
	}
}

