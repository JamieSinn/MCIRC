package ca.jamiesinn.mcirc;

import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Config
{
    private MCIRC plugin;
    private List<String> herochatChannels = new ArrayList<>();
    private HashMap<String, String> ircbotConfig = new HashMap<>();

    public Config(MCIRC plugin)
    {
        this.plugin = plugin;
        init();
    }

    private void init()
    {
        herochatChannels.addAll(plugin.getConfig().getStringList("minecraft.channels"));

        if(plugin.getConfig().isConfigurationSection("irc"))
        {
            ConfigurationSection section = plugin.getConfig().getConfigurationSection("irc");
            for(String config : section.getKeys(false))
                ircbotConfig.put(config, section.getString(config));
        }

    }

    public HashMap<String, String> getIrcbotConfig()
    {
        return ircbotConfig;
    }

    public List<String> getHerochatChannels()
    {
        return herochatChannels;
    }
}
