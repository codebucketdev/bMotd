package de.codebucket.bmotd;

import java.util.ArrayList;
import java.util.List;


import net.craftminecraft.bungee.bungeeyaml.pluginapi.ConfigurablePlugin;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;

public class Main extends ConfigurablePlugin
{
	private boolean random;
	private String prefix = "§b[§6b§aMotd§b] §r";
	private ArrayList<String> motdlist = new ArrayList<>();
	
	private static Main instance;
	
	@Override
	public void onEnable() 
	{
		instance = this;
		
		this.saveDefaultConfig();
		this.reloadConfig();
		
		random = this.getConfig().getBoolean("random");
		
		for(String m : this.getConfig().getStringList("motdlist"))
		{
			m = textValues(m);
			motdlist.add(m);
		}
		
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ProxyListener());
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Commands("bmotd"));
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new Commands("bm"));
	}
	
	@Override
	public void onDisable() 
	{
		
	}
	
	public void reloadPlugin()
	{
		motdlist.clear();
		this.reloadConfig();
		
		for(String m : this.getConfig().getStringList("motdlist"))
		{
			m = textValues(m);
			motdlist.add(m);
		}
	}
	
	public static Main getInstance()
	{
		return instance;
	}
	
	public String getPrefix()
	{
		return this.prefix;
	}
	
	public ArrayList<String> getMotdList()
	{
		return this.motdlist;
	}
	
	public void setMotdList(ArrayList<String> motdlist)
	{
		this.motdlist = motdlist;
	}
	
	public boolean containsMotd(String motd)
	{
		return this.motdlist.contains(motd);	
	}
	
	public void addMotd(String motd)
	{
		this.motdlist.add(textValues(motd));
		List<String> list = this.getConfig().getStringList("motdlist");
		list.add(textUmlauts(motd));
		this.getConfig().set("motdlist", list);
		this.saveConfig();
	}
	
	public void removeMotd(int id)
	{
		this.motdlist.remove(id);
		List<String> list = this.getConfig().getStringList("motdlist");
		list.remove(id);
		this.getConfig().set("motdlist", list);
		this.saveConfig();
	}
	
	public void setMotd(int index, String motd)
	{
		this.motdlist.set(index, textValues(motd));
		List<String> list = this.getConfig().getStringList("motdlist");
		list.set(index, textUmlauts(motd));
		this.getConfig().set("motdlist", list);
		this.saveConfig();
	}
	
	public void clearMotdList()
	{
		this.motdlist.clear();
		List<String> list = this.getConfig().getStringList("motdlist");
		list.clear();
		this.getConfig().set("motdlist", list);
		this.saveConfig();
	}
	
	public static String textValues(String line)
	{
		line = line.replaceAll("&0", ChatColor.BLACK.toString());
		line = line.replaceAll("&1", ChatColor.DARK_BLUE.toString());
		line = line.replaceAll("&2", ChatColor.DARK_GREEN.toString());
		line = line.replaceAll("&3", ChatColor.DARK_AQUA.toString());
		line = line.replaceAll("&4", ChatColor.DARK_RED.toString());
		line = line.replaceAll("&5", ChatColor.DARK_PURPLE.toString());
		line = line.replaceAll("&6", ChatColor.GOLD.toString());
		line = line.replaceAll("&7", ChatColor.GRAY.toString());
		line = line.replaceAll("&8", ChatColor.DARK_GRAY.toString());
		line = line.replaceAll("&9", ChatColor.BLUE.toString());
		line = line.replaceAll("&a", ChatColor.GREEN.toString());
		line = line.replaceAll("&b", ChatColor.AQUA.toString());
		line = line.replaceAll("&c", ChatColor.RED.toString());
		line = line.replaceAll("&d", ChatColor.LIGHT_PURPLE.toString());
		line = line.replaceAll("&e", ChatColor.YELLOW.toString());
		line = line.replaceAll("&f", ChatColor.WHITE.toString());
		line = line.replaceAll("&m", ChatColor.STRIKETHROUGH.toString());
		line = line.replaceAll("&n", ChatColor.UNDERLINE.toString());
		line = line.replaceAll("&l", ChatColor.BOLD.toString());
		line = line.replaceAll("&k", ChatColor.MAGIC.toString());
		line = line.replaceAll("&o", ChatColor.ITALIC.toString());
		line = line.replaceAll("&r", ChatColor.RESET.toString());
		line = line.replaceAll("&&", "&");
		line = line.replaceAll("%a", "ä");
		line = line.replaceAll("%A", "Ä");
		line = line.replaceAll("%o", "ö");
		line = line.replaceAll("%O", "Ö");
		line = line.replaceAll("%u", "ü");
		line = line.replaceAll("%U", "Ü");
		line = line.replaceAll("%s", "ß");
		
		return line;
	}
	
	public static String textUmlauts(String line)
	{
		line = line.replace("ä", "%a");
		line = line.replace("Ä", "%A");
		line = line.replace("ö", "%o");
		line = line.replace("Ö", "%O");
		line = line.replace("ü", "%u");
		line = line.replace("Ü", "%U");
		line = line.replace("ß", "%s");
		
		return line;
	}
	
	public static String rawText(String line)
	{
		line = line.replaceAll("§0", "");
		line = line.replaceAll("§1", "");
		line = line.replaceAll("§2", "");
		line = line.replaceAll("§3", "");
		line = line.replaceAll("§4", "");
		line = line.replaceAll("§5", "");
		line = line.replaceAll("§6", "");
		line = line.replaceAll("§7", "");
		line = line.replaceAll("§8", "");
		line = line.replaceAll("§9", "");
		line = line.replaceAll("§a", "");
		line = line.replaceAll("§b", "");
		line = line.replaceAll("§c", "");
		line = line.replaceAll("§d", "");
		line = line.replaceAll("§e", "");
		line = line.replaceAll("§f", "");
		line = line.replaceAll("§m", "");
		line = line.replaceAll("§n", "");
		line = line.replaceAll("§l", "");
		line = line.replaceAll("§k", "");
		line = line.replaceAll("§o", "");
		line = line.replaceAll("§r", "");
		
		return line;
	}
	
	public boolean isRandom()
	{
		return this.random;
	}
}
