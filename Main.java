package me.TheReachFreaks.EventFirework;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {
	Updater updater = new Updater(this, 86894, this.getFile(), Updater.UpdateType.DEFAULT, false);
	private ArrayList<String> commandFirework = new ArrayList<String>();
	private ArrayList<String> fireworkArgs = new ArrayList<String>();

	/**
	 * ON ENABLE
	 */
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		if (getConfig().get("configversion").equals(1.0)) {
			getLogger().info("Config File Loaded Successfully.");
		}
		if (!getConfig().get("configversion").equals(1.0)) {
			getLogger().info("=============================================");
			getLogger().info("Config File Is Not Up To Date, Or Is Broken.");
			getLogger().info("Please Delete The Config.yml And Reload ASAP,");
			getLogger().info("To Ensure The Config File Is Woring Correctly.");
			getLogger().info("=============================================");
		}
	}
	
	//PLUGIN VERSION DECLARATION
	double version = 2.0;
	
	//PREFIX DECLARATION
	String prefix =("§e[EventFirework]§f "); 

	/**
	 * PLAYER COMMANDS
	 */
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {	
		///////////////////////////////////
		///////ALL COMMANDS GO HERE////////
        ///////////////////////////////////
		commandFirework.add("onjoin");
		commandFirework.add("onquit");
		commandFirework.add("onlevelchange");
		commandFirework.add("onrespawn");
		commandFirework.add("onachievement");
		commandFirework.add("onkick");
		commandFirework.add("ondeath");
		commandFirework.add("customize");
		commandFirework.add("reload");
		commandFirework.add("help");
		
		/////////////////////////////////////////////////////////
		///////ALL COMMAND ARGUMENTS TO CUSTOMIZE GO HERE////////
        /////////////////////////////////////////////////////////
		fireworkArgs.add("power");
		fireworkArgs.add("fade");
		fireworkArgs.add("color");
		
		if (label.equalsIgnoreCase("firework")) {
			if (args.length == 0) {
				sender.sendMessage("§6===============================");
				sender.sendMessage("§cEventFirework");
				sender.sendMessage("§fVersion: §4" + version);
				sender.sendMessage("§fCreated By: §4TheReachFreaks");
				sender.sendMessage("§fWebsite:§4 http://www.reachbot.com");
				sender.sendMessage("§6===============================");
				return true;
				
			}
			
				else if (!sender.hasPermission("firework.admin")) {
				sender.sendMessage(prefix + "§cYou Don't Have Permission To Access This Command.");
				return false;
				}
			
			 /*
			 * Arguments To /firework.
			 */
			
			if (args.length == 1 && sender.hasPermission("firework.admin")) {
				if (!commandFirework.contains(args[0])) {
					sender.sendMessage(prefix + "§cThats Not A Valid Command. §fDo /Help EventFirework For Help.");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("onjoin")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework onjoin §6(on/off)§f.");
					return false;
				}
				if (args[0].equalsIgnoreCase("ondeath")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework ondeath §6(on/off)§f.");
					return false;
				}
				if (args[0].equalsIgnoreCase("onquit")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework onquit §6(on/off)§f.");
					return false;
					}
				if (args[0].equalsIgnoreCase("onrespawn")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework onrespawn §6(on/off)§f.");
					return false;
					}
				if (args[0].equalsIgnoreCase("onlevelchange")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework onlevelchange §6(on/off)§f.");
					return false;
					}
				if (args[0].equalsIgnoreCase("onachievement")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework onachievement §6(on/off)§f.");
					return false;
					}
				if (args[0].equalsIgnoreCase("onkick")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework onkick §6(on/off)§f.");
					return false;
					}
				if (args[0].equalsIgnoreCase("customize")) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework customize §6(Power/Fade/Color)§f.");
					return false;
				}
				
				if (args[0].equalsIgnoreCase("reload")) {
					reloadConfig();
					sender.sendMessage(prefix + "§aPlugin Reloaded Successfully§f.");
					return false;
				}
				if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(prefix + "§cThis Command Has Been Moved To: §f/Help EventFirework.");
				return false;
			}
		}
			if (args.length == 2) {
				//ONJOIN
				if (args[0].equalsIgnoreCase("customize") && fireworkArgs.contains(args[1])) {
					if (args[1].equalsIgnoreCase("fade")) {
						sender.sendMessage(prefix + "§cProper Usage: §f/Firework Customize Fade §6(R G B)§f.");
					} else if (args[1].equalsIgnoreCase("color")) {
						sender.sendMessage(prefix + "§cProper Usage: §f/Firework Customize Color §6(R G B)§f.");
					} else if (args[1].equalsIgnoreCase("power")) {
						sender.sendMessage(prefix + "§cProper Usage: §f/Firework Customize Power §6(1-4) §f.");
					}
					return false;
				}
				//ONJOIN
				if (args[0].equalsIgnoreCase("onjoin")) {
					if (sender.hasPermission("firework.admin") || sender.isOp()) {
					if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
						getConfig().set("fireworkonjoin", Boolean.TRUE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Join§f Has Been §aEnabled.");
						return true;
						} 
					else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
						getConfig().set("fireworkonjoin", Boolean.FALSE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Join§f Has Been §cDisabled.");
						return true;
							} 
						}
					
					} 
				//ONLEVELCHANGE
				if (args[0].equalsIgnoreCase("onlevelchange")) {
					if (sender.hasPermission("firework.admin") || sender.isOp()) {
					if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
						getConfig().set("fireworkonlevelchange", Boolean.TRUE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Level Change§f Has Been §aEnabled.");
						return true;
						} 
					else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
						getConfig().set("fireworkonlevelchange", Boolean.FALSE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Level Change§f Has Been §cDisabled.");
						return true;
							} 
						}
					
					} 
				//ONACHIEVEMENT
				if (args[0].equalsIgnoreCase("onachievement")) {
					if (sender.hasPermission("firework.admin") || sender.isOp()) {
					if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
						getConfig().set("fireworkonachievement", Boolean.TRUE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Achievement§f Has Been §aEnabled.");
						return true;
						} 
					else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
						getConfig().set("fireworkonachievement", Boolean.FALSE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Achievement§f Has Been §cDisabled.");
						return true;
							} 
						}
					
					} 
				//ONRESPAWN
				if (args[0].equalsIgnoreCase("onrespawn")) {
					if (sender.hasPermission("firework.admin") || sender.isOp()) {
					if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
						getConfig().set("fireworkonrespawn", Boolean.TRUE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Respawn§f Has Been §aEnabled.");
						return true;
						} 
					else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
						getConfig().set("fireworkonrespawn", Boolean.FALSE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Respawn§f Has Been §cDisabled.");
						return true;
							} 
						}
					}
				//ONKICK
				if (args[0].equalsIgnoreCase("onkick")) {
					if (sender.hasPermission("firework.admin") || sender.isOp()) {
					if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
						getConfig().set("fireworkonkick", Boolean.TRUE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Kick§f Has Been §aEnabled.");
						return true;
						} 
					else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
						getConfig().set("fireworkonkick", Boolean.FALSE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Kick§f Has Been §cDisabled.");
						return true;
							} 
						}
					}
				//ONQUIT
				else if (args[0].equalsIgnoreCase("onquit")) {
					if (sender.hasPermission("firework.admin") || sender.isOp()) {
						if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
						getConfig().set("fireworkonquit", Boolean.TRUE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Quit§f Has Been §aEnabled§f.");
						return true;
					} 
					else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
						getConfig().set("fireworkonquit", Boolean.FALSE);
						saveConfig();
						sender.sendMessage(prefix + "§6Firework On Quit§f Has Been §cDisabled§f.");
						return true;
						} 	
					}
				}
				//ONDEATH
				else if (args[0].equalsIgnoreCase("ondeath")) {
					if (sender.hasPermission("firework.admin") || sender.isOp()) {
						if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("true")) {
						getConfig().set("fireworkondeath", Boolean.TRUE);
						saveConfig();
						reloadConfig();
						sender.sendMessage(prefix + "§6Firework On Death§f Has Been §aEnabled§f.");
						return true;
					} 
					else if (args[1].equalsIgnoreCase("off") || args[1].equalsIgnoreCase("false")) {
						getConfig().set("fireworkonquit", Boolean.FALSE);
						saveConfig();
						sender.sendMessage("§6Firework On Death§f Has Been §cDisabled§f.");
						return true;
					} 
					else {
						sender.sendMessage(prefix + "§cProper Usage: §f/Firework ondeath §6(on/off)§f.");
						if (args.length == 0) {
							sender.sendMessage(prefix + "c4Proper Usage: §f/Firework ondeath §6(on/off)§f.");
							}
						}	
					}
				}
		} 
			//POWER (Firework Customization)
			
			else if (args.length == 3 && (sender.hasPermission("firework.admin") || sender.isOp())) {
			if (args[1].equalsIgnoreCase("power")) {
				if (args[2].equalsIgnoreCase("1")) {
					sender.sendMessage(prefix + "Firework §cPower §fHas Been Set To §c1§f.");
					getConfig().set("power", "1");
					saveConfig();
					reloadConfig();
					return false;
				}
				else if (args[2].equalsIgnoreCase("2")) {
					sender.sendMessage(prefix + "Firework §cPower §fHas Been Set To §c2§f.");
					getConfig().set("power", "2");
					saveConfig();
					reloadConfig();
					return false;
				}
				else if (args[2].equalsIgnoreCase("3")) {
					sender.sendMessage(prefix + "Firework §cPower §fHas Been Set To §c3§f.");
					getConfig().set("power", "3");
					saveConfig();
					reloadConfig();
					return false;
				}
				else if (args[2].equalsIgnoreCase("4")) {
					sender.sendMessage(prefix + "Firework §cPower §fHas Been Set To §c4§f.");
					getConfig().set("power", "4");
					saveConfig();
					reloadConfig();
					return false;
						}
				else {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework Customize Power §6(1-4)§f.");
					return false;
						}
							}
								}
									}
			//COLOR (Firework Customization)
			 if (args.length == 5 && (sender.hasPermission("firework.admin")) || sender.isOp()) { 
				if (args[1].equalsIgnoreCase("color")) {
					if (args.length > 5) {
						sender.sendMessage(prefix + "§4Too Many Arguments. §cProper Usage: §f/Firework Customize Color §6(R G B)§f.");
						return false;
					}
					if (args.length < 5) {
						sender.sendMessage(prefix + "§4Not Enough Arguments. §cProper Usage: §f/Firework Customize Color §6(R G B)§f.");
						return false;
					}
					if (args.length == 5) {
						int r = 1;
						int g = 2;
						int b = 3;
					try {
						r = Integer.parseInt(args[2]);
						g = Integer.parseInt(args[3]);
						b = Integer.parseInt(args[4]);
					} catch (Exception e) {
						sender.sendMessage(prefix + "§cProper Usage: §f/Firework Customize Color §6(R G B)§f.");
						return false;
					}
					getConfig().set("ColorRed", r);
					getConfig().set("ColorGreen", g);
					getConfig().set("ColorBlue", b);
					saveConfig();
					reloadConfig();
					sender.sendMessage(prefix + "§fFirework §cColor §fHas Been §aSuccessfully §fChanged.");
					return false;	
						}
					}
			//FADE (Firework Customization)
				else if (args[1].equalsIgnoreCase("fade")) {
					if (args.length > 5) {
						sender.sendMessage(prefix + "§4Too Many Arguments. §cProper Usage: §f/Firework Customize Fade §6(R G B)§f.");
						return false;
					}
					if (args.length < 5) {
						sender.sendMessage(prefix + "§4Not Enough Arguments. §cProper Usage: §f/Firework Customize Fade §6(R G B)§f.");
						return false;
					}
					int r = 1;
					int g = 2;
					int b = 3;
				try {
					r = Integer.parseInt(args[2]);
					g = Integer.parseInt(args[3]);
					b = Integer.parseInt(args[4]);
				} 
				catch (Exception e) {
					sender.sendMessage(prefix + "§cProper Usage: §f/Firework Customize Fade §6(R G B)§f.");
					return false;
				}
					getConfig().set("FadeColorRed", r);
					getConfig().set("FadeColorGreen", g);
					getConfig().set("FadeColorBlue", b);
					saveConfig();
					reloadConfig();
					sender.sendMessage(prefix + "§fFirework §cFade §fHas Been §aSuccessfully §fChanged.");
					return false;	
							}
						}
			else {
				sender.sendMessage(prefix + "§cThats Not A Valid Command. §fDo /Help EventFirework For Help.");
				return false;
			}
		return false;
	}

	//MESSAGE TO PLAYER IF CONFIG IS WRONG
	
	
	
	
	/**
	 * JOIN FIREWORK
	 */

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e) {
		Player player = (Player) e.getPlayer();
		if (!getConfig().get("configversion").equals(1.0) && player.isOp()) {
			player.sendMessage(prefix + "§cThe Config File Is Either Broken Or Out Of Date.");
			player.sendMessage("§4Please Delete The config.yml And Reload The Server.");
		}
		if (getConfig().getBoolean("fireworkonjoin", true)) {
			if (player.hasPermission("firework.onjoin")) {
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								Firework f = (Firework) e
										.getPlayer()
										.getWorld()
										.spawn(e.getPlayer().getLocation(),
												Firework.class);
								FireworkMeta fm = f.getFireworkMeta();
								//FIREWORK COLOR
								int colorRed = (int) getConfig().get("ColorRed");
								int colorGreen = (int) getConfig().get("ColorGreen");
								int colorBlue = (int) getConfig().get("ColorBlue");
								Color x = Color.fromRGB(colorRed, colorGreen, colorBlue);
								//FADE COLOR
								int FadeColorRed = (int) getConfig().get("FadeColorRed");
								int FadeColorGreen = (int) getConfig().get("FadeColorGreen");
								int FadeColorBlue = (int) getConfig().get("FadeColorBlue");
								Color x1 = Color.fromRGB(FadeColorRed, FadeColorGreen, FadeColorBlue);
								fm.addEffect(FireworkEffect.builder()
										.flicker(true).trail(true)
										.with(Type.BALL_LARGE)
										.withColor(x)
										.withFade(x1).build());
								fm.setPower(Integer.parseInt(getConfig().get("power").toString()));
								f.setFireworkMeta(fm);
							}
						}, 20);
			}		
		}
	}
	
	/**
	 * DEATH FIREWORK
	 */
	
	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent e) {
		Player player = (Player) e.getEntity().getPlayer();
		if (getConfig().getBoolean("fireworkondeath", true)) {
			if (player.hasPermission("firework.ondeath")) {
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								Firework f = (Firework)
										player
										.getPlayer()
										.getWorld()
										.spawn(player.getLocation(),
												Firework.class);
								FireworkMeta fm = f.getFireworkMeta();
								int colorRed = (int) getConfig().get("ColorRed");
								int colorGreen = (int) getConfig().get("ColorGreen");
								int colorBlue = (int) getConfig().get("ColorBlue");
								Color x = Color.fromRGB(colorRed, colorGreen, colorBlue);
								
								int FadeColorRed = (int) getConfig().get("FadeColorRed");
								int FadeColorGreen = (int) getConfig().get("FadeColorGreen");
								int FadeColorBlue = (int) getConfig().get("FadeColorBlue");
								Color x1 = Color.fromRGB(FadeColorRed, FadeColorGreen, FadeColorBlue);
								fm.addEffect(FireworkEffect.builder()
										.flicker(true).trail(true)
										.with(Type.BALL_LARGE)
										.withColor(x)
										.withFade(x1).build());
								fm.setPower(Integer.parseInt(getConfig().get("power").toString()));
								f.setFireworkMeta(fm);
							}
						}, 20);
			}		
		}
	}
	
	/**
	 * LEVEL CHANGE FIREWORK
	 */
	
	@EventHandler
	public void onPlayerLevelChange(final PlayerLevelChangeEvent e) {
		Player player = (Player) e.getPlayer();
		if (getConfig().getBoolean("fireworkonlevelchange", true)) {
			if (player.hasPermission("firework.onlevelchange")) {
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								Firework f = (Firework)
										player
										.getPlayer()
										.getWorld()
										.spawn(player.getLocation(),
												Firework.class);
								FireworkMeta fm = f.getFireworkMeta();
								int colorRed = (int) getConfig().get("ColorRed");
								int colorGreen = (int) getConfig().get("ColorGreen");
								int colorBlue = (int) getConfig().get("ColorBlue");
								Color x = Color.fromRGB(colorRed, colorGreen, colorBlue);
								
								int FadeColorRed = (int) getConfig().get("FadeColorRed");
								int FadeColorGreen = (int) getConfig().get("FadeColorGreen");
								int FadeColorBlue = (int) getConfig().get("FadeColorBlue");
								Color x1 = Color.fromRGB(FadeColorRed, FadeColorGreen, FadeColorBlue);
								fm.addEffect(FireworkEffect.builder()
										.flicker(true).trail(true)
										.with(Type.BALL_LARGE)
										.withColor(x)
										.withFade(x1).build());
								fm.setPower(Integer.parseInt(getConfig().get("power").toString()));
								f.setFireworkMeta(fm);
							}
						}, 20);
			}		
		}
	}
	
	/**
	 * ACHIEVEMENT FIREWORK
	 */
	
	@EventHandler
	public void onPlayerAchievement(final PlayerAchievementAwardedEvent e) {
		Player player = (Player) e.getPlayer();
		if (getConfig().getBoolean("fireworkachievement", true)) {
			if (player.hasPermission("firework.onachievement")) {
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								Firework f = (Firework)
										player
										.getPlayer()
										.getWorld()
										.spawn(player.getLocation(),
												Firework.class);
								FireworkMeta fm = f.getFireworkMeta();
								int colorRed = (int) getConfig().get("ColorRed");
								int colorGreen = (int) getConfig().get("ColorGreen");
								int colorBlue = (int) getConfig().get("ColorBlue");
								Color x = Color.fromRGB(colorRed, colorGreen, colorBlue);
								
								int FadeColorRed = (int) getConfig().get("FadeColorRed");
								int FadeColorGreen = (int) getConfig().get("FadeColorGreen");
								int FadeColorBlue = (int) getConfig().get("FadeColorBlue");
								Color x1 = Color.fromRGB(FadeColorRed, FadeColorGreen, FadeColorBlue);
								fm.addEffect(FireworkEffect.builder()
										.flicker(true).trail(true)
										.with(Type.BALL_LARGE)
										.withColor(x)
										.withFade(x1).build());
								fm.setPower(Integer.parseInt(getConfig().get("power").toString()));
								f.setFireworkMeta(fm);
							}
						}, 20);
			}		
		}
	}

	/**
	 * QUIT FIREWORK
	 */

	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent e) {
		Player player = (Player) e.getPlayer();
		if (getConfig().getBoolean("fireworkonquit", true)) {
			if (player.hasPermission("firework.onquit")) {
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								Firework f = (Firework) e
										.getPlayer()
										.getWorld()
										.spawn(e.getPlayer().getLocation(),
												Firework.class);
								FireworkMeta fm = f.getFireworkMeta();
								int colorRed = (int) getConfig().get("ColorRed");
								int colorGreen = (int) getConfig().get("ColorGreen");
								int colorBlue = (int) getConfig().get("ColorBlue");
								Color x = Color.fromRGB(colorRed, colorGreen, colorBlue);
								int FadeColorRed = (int) getConfig().get("FadeColorRed");
								int FadeColorGreen = (int) getConfig().get("FadeColorGreen");
								int FadeColorBlue = (int) getConfig().get("FadeColorBlue");
								Color x1 = Color.fromRGB(FadeColorRed, FadeColorGreen, FadeColorBlue);
								fm.addEffect(FireworkEffect.builder()
										.flicker(true).trail(true)
										.with(Type.BALL_LARGE)
										.withColor(x)
										.withFade(x1).build());
								fm.setPower(Integer.parseInt(getConfig().get("power").toString()));
								f.setFireworkMeta(fm);
							}
						}, 20);
			}		
		}
	}
	
	/**
	 * RESPAWN FIREWORK
	 */
	
	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent e) {
		Player player = (Player) e.getPlayer();
		if (getConfig().getBoolean("fireworkonrespawn", true)) {
			if (player.hasPermission("firework.onrespawn")) {
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								Firework f = (Firework) e
										.getPlayer()
										.getWorld()
										.spawn(e.getPlayer().getLocation(),
												Firework.class);
								FireworkMeta fm = f.getFireworkMeta();
								int colorRed = (int) getConfig().get("ColorRed");
								int colorGreen = (int) getConfig().get("ColorGreen");
								int colorBlue = (int) getConfig().get("ColorBlue");
								Color x = Color.fromRGB(colorRed, colorGreen, colorBlue);
								int FadeColorRed = (int) getConfig().get("FadeColorRed");
								int FadeColorGreen = (int) getConfig().get("FadeColorGreen");
								int FadeColorBlue = (int) getConfig().get("FadeColorBlue");
								Color x1 = Color.fromRGB(FadeColorRed, FadeColorGreen, FadeColorBlue);
								fm.addEffect(FireworkEffect.builder()
										.flicker(true).trail(true)
										.with(Type.BALL_LARGE)
										.withColor(x)
										.withFade(x1).build());
								fm.setPower(Integer.parseInt(getConfig().get("power").toString()));
								f.setFireworkMeta(fm);
							}
						}, 20);
			}		
		}
	}
	
	/**
	 * KICK FIREWORK
	 */
	
	@EventHandler
	public void onKick(final PlayerKickEvent e) {
		Player player = (Player) e.getPlayer();
		if (getConfig().getBoolean("fireworkonkick", true)) {
			if (player.hasPermission("firework.onkick")) {
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(this, new Runnable() {
							public void run() {
								Firework f = (Firework) e
										.getPlayer()
										.getWorld()
										.spawn(e.getPlayer().getLocation(),
												Firework.class);
								FireworkMeta fm = f.getFireworkMeta();
								int colorRed = (int) getConfig().get("ColorRed");
								int colorGreen = (int) getConfig().get("ColorGreen");
								int colorBlue = (int) getConfig().get("ColorBlue");
								Color x = Color.fromRGB(colorRed, colorGreen, colorBlue);
								int FadeColorRed = (int) getConfig().get("FadeColorRed");
								int FadeColorGreen = (int) getConfig().get("FadeColorGreen");
								int FadeColorBlue = (int) getConfig().get("FadeColorBlue");
								Color x1 = Color.fromRGB(FadeColorRed, FadeColorGreen, FadeColorBlue);
								fm.addEffect(FireworkEffect.builder()
										.flicker(true).trail(true)
										.with(Type.BALL_LARGE)
										.withColor(x)
										.withFade(x1).build());
								fm.setPower(Integer.parseInt(getConfig().get("power").toString()));
								f.setFireworkMeta(fm);
							}
						}, 20);
			}		
		}
	}	
}