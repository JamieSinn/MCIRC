package ca.jamiesinn.mcirc.api;

import ca.jamiesinn.mcirc.util.MessageDirection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IRCMessageSentEvent extends Event
{
    private String message;
    private String sender;
    private String rawMessage;
    private MessageDirection direction;
    private static final HandlerList handlers = new HandlerList();

    public IRCMessageSentEvent(String message, String sender, MessageDirection direction)
    {
        this.message = message;
        this.sender = sender;
        this.direction = direction;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }

    public String getMessage()
    {
        return message;
    }

    public String getSender()
    {
        return sender;
    }

    public String getRawMessage()
    {
        return rawMessage;
    }

    public MessageDirection getDirection()
    {
        return direction;
    }
}
