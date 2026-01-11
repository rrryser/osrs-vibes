package osrsvibes;

import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import com.google.inject.Provides;
import net.runelite.client.config.ConfigManager;

@PluginDescriptor(
		name = "OSRS Vibes",
		description = "OSRS → Intiface → Lovense integration",
		tags = {"lovense", "intiface", "vibration"}
)
public class OsrsVibesPlugin extends Plugin
{
	private OsrsVibesConfig config;

	@Inject
	private Client client;

	@Override
	protected void startUp()
	{
		System.out.println("OSRS Vibes started");

		if (config.enabled())
		{
			intiface.connect();
			intiface.vibrate(0.5);
		}
	}


	@Override
	protected void shutDown()
	{
		System.out.println("OSRS Vibes stopped");
		intiface.disconnect();
		System.out.println("OSRS Vibes stopped");
	}

	@Provides
	OsrsVibesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(OsrsVibesConfig.class);
	}

	@Inject
	private IntifaceClient intiface;

}
