package io.github.estrongsfield.gonefission;
import org.bukkit.plugin.java.JavaPlugin;

public class GoneFission extends JavaPlugin{

	
	@Override
	public void onEnable()
	{
		getLogger().info("GoneFission chain of command is enabled");
		//AdMessages ls = new AdMessages();
        //getServer().getPluginManager().registerEvents(new AdChatListener(), this);
	}
	
	
	@Override
	public void onDisable()
	{
		getLogger().info("GoneFission suspended. Now entering SALT mode.");
	}
	
	
	
}


