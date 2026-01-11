package osrsvibes;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("osrsvibes")
public interface OsrsVibesConfig extends Config
{
	@ConfigItem(
			keyName = "enabled",
			name = "Enable vibrations",
			description = "Master on/off switch"
	)
	default boolean enabled()
	{
		return false;
	}
}
