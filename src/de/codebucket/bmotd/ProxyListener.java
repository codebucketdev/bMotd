package de.codebucket.bmotd;

import java.util.Random;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyListener implements Listener 
{
	int id = 0;
	private Random r = new Random();

	@EventHandler
	public void onPing(ProxyPingEvent e) 
	{
		if (Main.getInstance().getMotdList().size() > 0) 
		{
			ServerPing response = e.getResponse();
			response.setDescription(getMotd());
			e.setResponse(response);
		}
	}

	private String getMotd() 
	{
		if (Main.getInstance().isRandom()) 
		{
			String motd = (String) Main.getInstance().getMotdList().get(this.r.nextInt(Main.getInstance().getMotdList().size()));
			return motd.replaceAll("/n", "\n");
		}

		if (this.id >= Main.getInstance().getMotdList().size()) 
		{
			this.id = 0;
		}

		String motd = (String) Main.getInstance().getMotdList().get(this.id);

		if (this.id < Main.getInstance().getMotdList().size())
		{
			this.id += 1;
		}
		if (this.id >= Main.getInstance().getMotdList().size()) 
		{
			this.id = 0;
		}

		return motd.replaceAll("/n", "\n");
	}
}