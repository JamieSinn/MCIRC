package ca.jamiesinn.mcirc;

import org.bukkit.plugin.java.JavaPlugin;

public class MCIRC extends JavaPlugin
{
    private Config config;
    private IRCBot bot;

    @Override
    public void onEnable()
    {
        config = new Config(this);
        bot = new IRCBot(config.getIrcbotConfig().get("botName"), config.getIrcbotConfig().get("botChannel"), config.getIrcbotConfig().get("botNetwork"));
    }

    @Override
    public void onDisable()
    {
        bot.disconnect();
    }

    public Config getConfigMananger()
    {
        return config;
    }

    public IRCBot getBot()
    {
        return bot;
    }
}
