package com.apocalypsjenl.openaudiomc.addon.plotsquared.commands;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.flag.FlagManager;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MusicCommand implements CommandExecutor
{

    public boolean onCommand ( CommandSender commandSender, Command command, String string, String[] args )
    {
        if ( command.getName( ).equalsIgnoreCase( "music" ) )
        {
            if ( commandSender instanceof Player )
            {
                PlotAPI plotAPI = new PlotAPI( );
                Player player = ( Player ) commandSender;
                if ( args.length == 2 )
                {
                    if ( args[ 0 ].equalsIgnoreCase( "add" ) )
                    {
                        if ( plotAPI.isInPlot( player ) )
                        {
                            Plot plot = plotAPI.getPlot( player );
                            if ( plot.getOwners( ).contains( player.getUniqueId( ) ) || player.hasPermission( "openaudiomc.plotsquared.bypass" ) || player.isOp( ) )
                            {
                                plot.setFlag( FlagManager.getFlag( "musicLink" ), args[ 1 ] );
                                player.sendMessage( ChatColor.GREEN + "You've set the music for this plot to " +
                                        ChatColor.GOLD + args[ 1 ] );
                                return true;
                            } else
                            {
                                player.sendMessage( ChatColor.RED + "You are not allowed to modify the flags of this plot!" );
                                return true;
                            }
                        } else
                        {
                            player.sendMessage( ChatColor.RED + "You are not inside a plot at the moment!" );
                            return true;
                        }
                    }
                } else if ( args.length == 1 )
                {
                    if ( args[ 0 ].equalsIgnoreCase( "remove" ) )
                    {
                        if ( plotAPI.isInPlot( player ) )
                        {
                            Plot plot = plotAPI.getPlot( player );
                            if ( plot.getOwners( ).contains( player.getUniqueId( ) ) || player.hasPermission( "openaudiomc.plotsquared.bypass" ) || player.isOp( ) )
                            {
                                plot.removeFlag( FlagManager.getFlag( "musicLink" ) );
                                player.sendMessage( ChatColor.GREEN + "You've removed the music from this plot" );
                                return true;
                            } else
                            {
                                player.sendMessage( ChatColor.RED + "You are not allowed to modify the flags of this plot!" );
                                return true;
                            }
                        } else
                        {
                            player.sendMessage( ChatColor.RED + "You are not inside a plot at the moment!" );
                            return true;
                        }
                    } else if ( args[ 0 ].equalsIgnoreCase( "get" ) )
                    {
                        if ( plotAPI.isInPlot( player ) )
                        {
                            Plot plot = plotAPI.getPlot( player );
                            if ( plot.getFlags( ).containsKey( FlagManager.getFlag( "musicLink" ) ) )
                            {
                                player.sendMessage( ChatColor.GREEN + "This music is playing right now: " +
                                        ChatColor.GOLD + plot.getFlags( ).get( FlagManager.getFlag( "musicLink" ) ) );
                                return true;
                            } else
                            {
                                player.sendMessage( ChatColor.RED + "This plot doesn't have music on it!" );
                                return true;
                            }
                        } else
                        {
                            player.sendMessage( ChatColor.RED + "You are not inside a plot at the moment!" );
                            return true;
                        }
                    }
                } else
                {
                    player.sendMessage( ChatColor.GREEN + "Music help:" );
                    player.sendMessage( ChatColor.GREEN + "/music add <url> " + ChatColor.GOLD +
                            "- Add music to your plot! Currently you can add youtube, soundcloud and mp3 links" );
                    player.sendMessage( ChatColor.GREEN + "/music remove    " + ChatColor.GOLD +
                            "- Removes the music of your plot" );
                    player.sendMessage( ChatColor.GREEN + "/music get         " + ChatColor.GOLD +
                            "- Show which music url is playing at the moment on the plot you're standing at" );
                    return true;
                }
            }
        }
        return false;
    }
}
