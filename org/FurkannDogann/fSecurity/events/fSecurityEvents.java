package org.FurkannDogann.fSecurity.events;

import org.FurkannDogann.fSecurity.commands.fSecurityCommands;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class fSecurityEvents implements Listener{	
	
	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("fSecurity");
	SimpleDateFormat setFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	String zaman = setFormat.format(new Date());
	
	@SuppressWarnings("deprecation")
	@EventHandler	
	public void playerJoinEvent(PlayerJoinEvent event) throws IOException, InvalidConfigurationException, InterruptedException {
		Player oyuncu = event.getPlayer();
		if (oyuncu.hasPermission(plugin.getConfig().getString("Ayarlar.Gereken Ýzin"))) {
			fSecurityCommands.systemVerifiedSetter = (oyuncu.getName() + "verifiedFalse");
			fSecurityCommands.systemVerified = fSecurityCommands.systemVerifiedSetter;
			oyuncu.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200000, 255));
			oyuncu.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200000, 255));
			oyuncu.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200000, 255));
			for (int i = 0; i < 11; i++) {
				oyuncu.sendMessage("");
			}
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run(){
					oyuncu.sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Lütfen Kodu Girin").replaceAll("&", "§"));
					if (plugin.getConfig().getBoolean("Ayarlar.Title Mesajlarý Kullanýlsýn Mý")) {
						oyuncu.sendTitle(plugin.getConfig().getString("Title Mesajlar.Lütfen Kodu Girin.Title").replaceAll("&", "§"), plugin.getConfig().getString("Title Mesajlar.Lütfen Kodu Girin.Subtitle").replaceAll("&", "§"));
					}
				}
		    }, 2 * 20);
		    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run(){
					if (fSecurityCommands.systemVerified.contains(oyuncu.getName() + "verifiedFalse")) {
						event.getPlayer().kickPlayer(plugin.getConfig().getString("Mesajlar.Doðrulama Zaman Aþýmýna Uðradý").replaceAll("&", "§"));
					}
				}
		    }, plugin.getConfig().getInt("Ayarlar.Kodu Girme Süresi Kaç Saniye Olsun") * 20);		    
		}
	}	
	
	@EventHandler
	public void playerQuitEvent(PlayerQuitEvent event) throws IOException {
		Player oyuncu = event.getPlayer();
		if (oyuncu.hasPermission(plugin.getConfig().getString("Ayarlar.Gereken Ýzin"))) {
			oyuncu.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			oyuncu.removePotionEffect(PotionEffectType.BLINDNESS);
			oyuncu.removePotionEffect(PotionEffectType.SLOW);		
		}
	}
	
	@EventHandler
	public void playerChatEvent(AsyncPlayerChatEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			event.getPlayer().sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Kodu Doðrulamadan Konuþamazsýn").replaceAll("&", "§"));
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);	
		}
	}
	
	@EventHandler
	public void playerCommandEvent(PlayerCommandPreprocessEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			if(event.getMessage().contains("doðrula")) {
				event.setCancelled(false);	
			}else {
				event.getPlayer().sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Kodu Doðrulamadan Komut Kullanamazsýn").replaceAll("&", "§"));
				if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
				}
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void playerBreakBlock(BlockBreakEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerPlaceEvent(BlockPlaceEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerTeleportEvent(PlayerTeleportEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			event.getPlayer().sendMessage(plugin.getConfig().getString("Mesajlar.Prefix").replaceAll("&", "§") + plugin.getConfig().getString("Mesajlar.Kodu Doðrulamadan Hareket Edemezsin").replaceAll("&", "§"));
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);
		}		
	}
	
	@EventHandler
	public void playerPickupEvent(PlayerPickupItemEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			event.setCancelled(true);
		}			
	}
	
	@EventHandler
	public void playerDropEvent(PlayerDropItemEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);
		}			
	}	
	
	@EventHandler
	public void playerPortalEvent (PlayerPortalEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerBedEnter(PlayerBedEnterEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);
		}
	}
		
	@EventHandler
	public void playerGameModeChangeEvent(PlayerGameModeChangeEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void playerOnInvOpenEvent(InventoryOpenEvent event) {
		if (fSecurityCommands.systemVerified.contains(event.getPlayer().getName() + "verifiedFalse")) {
			if (plugin.getConfig().getBoolean("Ayarlar.Ses Efektleri Kullanýlsýn Mý")) {
				((Player) event.getPlayer()).playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
			}
			event.setCancelled(true);
		}	
	}
}
