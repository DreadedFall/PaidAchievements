package io.github.dreadedfall.PaidAchievements;

import java.util.logging.Logger;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class PaidAchievementsMain extends JavaPlugin
{
	private Economy econ = null;
	private static final Logger log = Logger.getLogger("Minecraft");
	
	public boolean setupEcon()
	{
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
	}
	
	@Override
	public void onEnable()
	{
		if (!setupEcon() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		else
		{
			log.info(String.format("[%s] - Enabled"));
		}
	}
	
	@Override
	public void onDisable()
	{
		log.info(String.format("[%s] - Disabled"));
	}
}
