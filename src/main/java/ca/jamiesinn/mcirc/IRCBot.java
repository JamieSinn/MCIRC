package ca.jamiesinn.mcirc;

import ca.jamiesinn.mcirc.api.IRCMessageSentEvent;
import ca.jamiesinn.mcirc.util.MessageDirection;
import com.dthielke.herochat.Channel;
import com.dthielke.herochat.Herochat;
import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;
import java.util.List;

public class IRCBot extends PircBot
{
    private String nick;
    private String channel;
    private String server;
    private MCIRC plugin;

    public IRCBot(String nick, String channel, String server, MCIRC plugin)
    {
        this.nick = nick;
        this.channel = "#" + channel;
        this.server = server;
        this.plugin = plugin;
        init();
    }

    public void init()
    {

        try
        {
            this.setName(nick);
            this.connect(server);
            this.joinChannel(channel);

        }
        catch (IOException | IrcException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        IRCMessageSentEvent event = new IRCMessageSentEvent(message, sender, MessageDirection.MINECRAFT);
        List<String> hc = plugin.getConfigMananger().getHerochatChannels();
        for(String s : hc)
        {
            Channel c = Herochat.getChannelManager().getChannel(s);
            c.announce("[IRC] " + sender + ": " + message);
        }
    }

    public void sendChatMessage(String sender, String message)
    {
        String messageFormatted = "[Minecraft] " + sender + ": " + message;
        sendMessage(channel, messageFormatted);
    }

}
