package ca.jamiesinn.mcirc.api;

import ca.jamiesinn.mcirc.util.MessageDirection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IRCMessageSentEvent extends Event
{
    public String message;
    public String rawMessage;
    public MessageDirection direction;

    @Override
    public HandlerList getHandlers()
    {
        return null;
    }
}
