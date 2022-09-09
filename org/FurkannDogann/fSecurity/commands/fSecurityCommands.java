package org.FurkannDogann.fSecurity.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.FurkannDogann.fSecurity.main.fSecurityMain;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class fSecurityCommands implements CommandExecutor{

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("fSecurity");
	public static String systemVerified;
	public static String systemVerifiedSetter;
	SimpleDateFormat setFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	String zaman = setFormat.format(new Date());
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender oyuncu, Command komut, String label, String[] args) {
		if (oyuncu instanceof Player) {
			Player entity = (Player) oyuncu;
			if (komut.getName().equalsIgnoreCase("doðrula")) {
				if (entity.hasPermission(plugin.getConfig().getString("Ayarlar.Gereken Ýzin"))) {
					if (systemVerified.contains(entity.getName() + "verifiedFalse")) {
						if (args.length == 1 && args[0].equalsIgnoreCase(args[0])) {
							if (args[0].equalsIgnoreCase(plugin.getConfig().getString("Ayarlar.Kod"))) {
								fSecurityCommands.systemVerifiedSetter = (oyuncu.getName() + "verifiedTrue");
								fSecurityCommands.systemVerified = fSecurityCommands.systemVerifiedSetter;
								entity.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
								entity.removePotionEffect(PotionEffectType.BLINDNESS);
								entity.removePotionEffect(PotionEffectType.SLOW);
								entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Doðrulama Baþarýlý").replaceAll("&", "§"));
								if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
									entity.playSound(entity.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
								}
								if (plugin.getConfig().getBoolean("Ayarlar.Title Mesajlarý Kullanýlsýn Mý")) {
									entity.sendTitle(plugin.getConfig().getString("Title Mesajlar.Doðrulama Baþarýlý.Title").replaceAll("&", "§"), plugin.getConfig().getString("Title Mesajlar.Doðrulama Baþarýlý.Subtitle").replaceAll("&", "§"));
								}
							}else {
								if (plugin.getConfig().getBoolean("Ayarlar.Kodu Yanlýþ Girince Atýlsýn Mý")){
									entity.kickPlayer(plugin.getConfig().getString("Mesajlar.Girilen Kod Yanlýþ").replaceAll("&", "§"));
								}else {
									entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Girilen Kod Yanlýþ").replaceAll("&", "§"));
									if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
										entity.playSound(entity.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
									}
									if (plugin.getConfig().getBoolean("Ayarlar.Title Mesajlarý Kullanýlsýn Mý")) {
										entity.sendTitle(plugin.getConfig().getString("Title Mesajlar.Girilen Kod Yanlýþ.Title").replaceAll("&", "§"), plugin.getConfig().getString("Title Mesajlar.Girilen Kod Yanlýþ.Subtitle").replaceAll("&", "§"));
									}
								}
							}
						}else {
							entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Lütfen Kodu Girin").replaceAll("&", "§"));
							if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
								entity.playSound(entity.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
							}
						}
					}else {
						entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Doðrulama Zaten Baþarýlý").replaceAll("&", "§"));
						if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
							entity.playSound(entity.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
						}
					}					
				}else {
					entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Yetkin Yok").replaceAll("&", "§"));
					if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
						entity.playSound(entity.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
					}
					if (plugin.getConfig().getBoolean("Ayarlar.Title Mesajlarý Kullanýlsýn Mý")) {
						entity.sendTitle(plugin.getConfig().getString("Title Mesajlar.Yetkin Yok.Title").replaceAll("&", "§"), plugin.getConfig().getString("Title Mesajlar.Yetkin Yok.Subtitle").replaceAll("&", "§"));
					}
				}

			}else if (komut.getName().equalsIgnoreCase("fsecurity")) {
				if (args.length == 1 && args[0].equalsIgnoreCase(args[0])) {
					if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("yenile")) {
						if (entity.hasPermission(plugin.getConfig().getString("Ayarlar.Admin Ýzin"))) {
							plugin.reloadConfig();
							entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + "&afSecurity &7Yenileniyor..".replaceAll("&", "§"));
							entity.playSound(entity.getLocation(), Sound.ANVIL_LAND, 1.0f, 1.0f);
						    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
								@Override
								public void run(){
									entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Yenileme Baþarýlý").replaceAll("&", "§"));
									entity.playSound(entity.getLocation(), Sound.ANVIL_USE, 1.0f, 1.0f);
								}
						    }, 3 * 20);
						}else {
							entity.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Yetkin Yok").replaceAll("&", "§"));
							if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
								entity.playSound(entity.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
							}
							if (plugin.getConfig().getBoolean("Ayarlar.Title Mesajlarý Kullanýlsýn Mý")) {
								entity.sendTitle(plugin.getConfig().getString("Title Mesajlar.Yetkin Yok.Title").replaceAll("&", "§"), plugin.getConfig().getString("Title Mesajlar.Yetkin Yok.Subtitle").replaceAll("&", "§"));
							}
						}
					}
				}else {
					fSecurityMain.fSecurityHelp(entity);
				}
			}
		}
		return true;

	}

}
