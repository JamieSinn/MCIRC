package ca.jamiesinn.mcirc;

import ca.jamiesinn.mcirc.listeners.HeroChatListener;
import com.dthielke.herochat.Herochat;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCIRC extends JavaPlugin
{
    private Config config;
    private IRCBot bot;
    private Herochat hc;

    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();
        config = new Config(this);
        bot = new IRCBot(config.getIrcbotConfig().get("botName"),
                config.getIrcbotConfig().get("botChannel"),
                config.getIrcbotConfig().get("botNetwork"),
                this);
        hookHeroChat();
        loadListeners();
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

    public void hookHeroChat()
    {
        final PluginManager pm = this.getServer().getPluginManager();
        final Plugin hcPlugin = pm.getPlugin("HeroChat");
        if (hcPlugin == null || !hcPlugin.isEnabled()) {
            this.setEnabled(false);
            getLogger().warning("Couldn't hook Herochat. Disabling.");
            return;
        }
        hc = (Herochat) hcPlugin;
    }

    public void loadListeners()
    {
        getServer().getPluginManager().registerEvents(new HeroChatListener(this), this);
    }

    public Herochat getHeroChat()
    {
        return hc;
    }
}
