package ca.jamiesinn.mcirc.listeners;

import ca.jamiesinn.mcirc.MCIRC;
import ca.jamiesinn.mcirc.api.IRCMessageSentEvent;
import ca.jamiesinn.mcirc.util.MessageDirection;
import com.dthielke.herochat.Channel;
import com.dthielke.herochat.ChannelChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class HeroChatListener implements Listener
{
    private MCIRC plugin;

    public HeroChatListener(MCIRC plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatChannelMessage(ChannelChatEvent e)
    {
        Channel channel = e.getChannel();
        if (!plugin.getConfigMananger().getHerochatChannels().contains(channel.getName())) return;
        IRCMessageSentEvent ircsent = new IRCMessageSentEvent(e.getMessage(), e.getSender().getName(), MessageDirection.IRC);
        plugin.getBot().sendChatMessage(e.getSender().getName(), e.getMessage());
        Bukkit.getServer().getPluginManager().callEvent(ircsent);
    }
}
