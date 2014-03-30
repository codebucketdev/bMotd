package de.codebucket.bmotd;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Commands  extends Command
{
	String cmd;
	
	public Commands(String command) 
	{
		super(command);
		this.cmd = command;
	}

	public void execute(CommandSender sender, String[] args)
	{
		if(args.length == 0)
		{
			sender.sendMessage(Main.getInstance().getPrefix() + "§eVersion 1.2 by Codebucket");
			sender.sendMessage(Main.getInstance().getPrefix() + "§eType '/"+cmd+" help' for help");
		}
		else
		{
			if(args[0].equalsIgnoreCase("add"))
			{
				if(sender.hasPermission("bmotd.add") || sender.hasPermission("bmotd.*"))
				{
					if(args.length >= 2)
					{
					    String motd = "";
					    
					    for(int i = 0; i < args.length; i++) 
						{
					    	if(i > 0)
					    	{
						    	if(motd.length() == 0)
						    	{
						    		motd = (motd + args[i]);
						    	}
						    	else
						    	{
						    		motd = (motd + " " + args[i]);
						    	}
					    	}
					    }
					    
					    Main.getInstance().addMotd(motd);
					    
					    int id = (Main.getInstance().getMotdList().size()-1);
					    
					    sender.sendMessage(Main.getInstance().getPrefix() + "§aAdded Motd §e(ID " + id + ")§a: §r" + Main.textValues(motd));
					}
					else
					{
						sender.sendMessage(Main.getInstance().getPrefix() + "§cError in command syntax!");
						sender.sendMessage(Main.getInstance().getPrefix() + "§cUsage: /"+cmd+" add <motd>");
					}
				}
				else
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§cYou don't have permission to execute this command!");
				}
			}
			else if(args[0].equalsIgnoreCase("list"))
			{
				if(sender.hasPermission("bmotd.list") || sender.hasPermission("bmotd.*"))
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§a=============== §6bMotd: List §a===============");
					
					for(int i = 0; i < Main.getInstance().getMotdList().size(); i++) 
					{
						String motd = Main.getInstance().getMotdList().get(i);
						
						if(motd.length() > 40)
						{
							motd = motd.substring(0, 39);
							motd = motd + "...";
						}
						
						String msg = Main.getInstance().getPrefix() + "§e#" + i + ". §r" + motd;
						sender.sendMessage(msg);
					}
					
					sender.sendMessage(Main.getInstance().getPrefix() + "§a========================================");
				}
				else
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§cYou don't have permission to execute this command!");
				}	
			}
			else if(args[0].equalsIgnoreCase("remove"))
			{
				if(sender.hasPermission("bmotd.remove") || sender.hasPermission("bmotd.*"))
				{
					if(args.length == 2)
					{
						int id = 0;
						
						try
						{
							id = Integer.parseInt(args[1]);
							if((Main.getInstance().getMotdList().size()-1) >= id)
							{
								String motd = Main.getInstance().getMotdList().get(id);
								Main.getInstance().removeMotd(id);
								sender.sendMessage(Main.getInstance().getPrefix() + "§aRemoved Motd §e(ID " + id + ")§a: §r" + motd);
							}
							else
							{
								sender.sendMessage(Main.getInstance().getPrefix() + "§cMotd with §eID " + id + " §cnot exists!");
								sender.sendMessage(Main.getInstance().getPrefix() + "§cUsage: /"+cmd+" remove <id>");
							}
						}
						catch(NumberFormatException ex)
						{
							sender.sendMessage(Main.getInstance().getPrefix() + "§e" + args[1] + " §cis not a valid integer!");
							sender.sendMessage(Main.getInstance().getPrefix() + "§cUsage: /"+cmd+" remove <id>");
						}
					}
					else
					{
						sender.sendMessage(Main.getInstance().getPrefix() + "§cError in command syntax!");
						sender.sendMessage(Main.getInstance().getPrefix() + "§cUsage: /"+cmd+" remove <id>");
					}
				}
				else
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§cYou don't have permission to execute this command!");
				}	
			}
			else if(args[0].equalsIgnoreCase("set"))
			{
				if(sender.hasPermission("bmotd.set") || sender.hasPermission("bmotd.*"))
				{
					if(args.length >= 3)
					{
						int id = 0;
						
						try
						{
							id = Integer.parseInt(args[1]);
							if((Main.getInstance().getMotdList().size()-1) >= id)
							{
								String motd = "";
							    
							    for(int i = 0; i < args.length; i++) 
								{
							    	if(i > 1)
							    	{
								    	if(motd.length() == 0)
								    	{
								    		motd = (motd + args[i]);
								    	}
								    	else
								    	{
								    		motd = (motd + " " + args[i]);
								    	}
							    	}
							    }
								
								Main.getInstance().setMotd(id, motd);
								sender.sendMessage(Main.getInstance().getPrefix() + "§aChanged Motd §e(ID " + id + ")§a: §r" + Main.textValues(motd));
							}
							else
							{
								sender.sendMessage(Main.getInstance().getPrefix() + "§cMotd with §eID " + id + " §cnot exists!");
								sender.sendMessage(Main.getInstance().getPrefix() + "§cUsage: /"+cmd+" set <id> <motd>");
							}
						}
						catch(NumberFormatException ex)
						{
							sender.sendMessage(Main.getInstance().getPrefix() + "§e" + args[1] + " §cis not a valid integer!");
							sender.sendMessage(Main.getInstance().getPrefix() + "§cUsage: /"+cmd+" set <id> <motd>");
						}
					}
					else
					{
						sender.sendMessage(Main.getInstance().getPrefix() + "§cError in command syntax!");
						sender.sendMessage(Main.getInstance().getPrefix() + "§cUsage: /"+cmd+" set <id> <motd>");
					}
				}
				else
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§cYou don't have permission to execute this command!");
				}	
			}
			else if(args[0].equalsIgnoreCase("clear"))
			{
				if(sender.hasPermission("bmotd.clear") || sender.hasPermission("bmotd.*"))
				{
					Main.getInstance().clearMotdList();				
					sender.sendMessage(Main.getInstance().getPrefix() + "§aMotd list sucessfully cleared.");
				}
				else
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§cYou don't have permission to execute this command!");
				}	
			}
			else if(args[0].equalsIgnoreCase("reload"))
			{
				if(sender.hasPermission("bmotd.reload") || sender.hasPermission("bmotd.*"))
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§eReloading bMotd...");
					Main.getInstance().reloadPlugin();
					sender.sendMessage(Main.getInstance().getPrefix() + "§abMotd sucessfully reloaded!");
				}
				else
				{
					sender.sendMessage(Main.getInstance().getPrefix() + "§cYou don't have permission to execute this command!");
				}	
			}
			else if(args[0].equalsIgnoreCase("help"))
			{
				sender.sendMessage(Main.getInstance().getPrefix() + "§b§n§lbMotd v1.2 command usage:");
				sender.sendMessage(Main.getInstance().getPrefix() + "§a/"+cmd+" list §e- See Motd list");
				sender.sendMessage(Main.getInstance().getPrefix() + "§a/"+cmd+" reload §e- Reload config.yml");
				sender.sendMessage(Main.getInstance().getPrefix() + "§a/"+cmd+" clear §e- Clear Motd list");
				sender.sendMessage(Main.getInstance().getPrefix() + "§a/"+cmd+" add <motd> §e- Add new Motd to Motd list");
				sender.sendMessage(Main.getInstance().getPrefix() + "§a/"+cmd+" remove <id> §e- Removes an Motd from Motd list");
				sender.sendMessage(Main.getInstance().getPrefix() + "§a/"+cmd+" set <id> <motd> §e- Changes an existing Motd from Motd list");
			}
		}
	}
}
