package com.apocalypsjenl.openaudiomc.addon.plotsquared.listeners;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.flag.FlagManager;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.plotsquared.bukkit.events.PlayerEnterPlotEvent;
import com.plotsquared.bukkit.events.PlayerLeavePlotEvent;
import com.plotsquared.bukkit.events.PlotFlagAddEvent;
import com.plotsquared.bukkit.events.PlotFlagRemoveEvent;
import me.mindgamesnl.openaudiomc.publicApi.WebConnectEvent;
import net.openaudiomc.actions.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Listeners implements Listener
{

    @EventHandler
    public void onPlayerEnterPlot ( PlayerEnterPlotEvent event )
    {
        Plot plot = event.getPlot( );

        if ( plot.getFlags( ).containsKey( FlagManager.getFlag( "musicLink" ) ) )
        {
            Command.stop( event.getPlayer( ).getName( ) );
            Command.playNormalSound( event.getPlayer( ).getName( ), ( String ) plot.getFlags( ).get( FlagManager.getFlag( "musicLink" ) ) );
        }
    }

    @EventHandler
    public void onPlayerLeavePlot ( PlayerLeavePlotEvent event )
    {
        Command.stop( event.getPlayer( ).getName( ) );
    }

    @EventHandler
    public void onPlotFlagRemove ( PlotFlagRemoveEvent event )
    {
        if ( event.getFlag( ).getName( ).equals( "musicLink" ) )
        {
            for ( PlotPlayer plotPlayer : event.getPlot( ).getPlayersInPlot( ) )
            {
                Command.stop( plotPlayer.getName( ) );
            }
        }
    }

    @EventHandler
    public void onPlotFlagAdd ( PlotFlagAddEvent event )
    {
        if ( event.getFlag( ).getName( ).equals( "musicLink" ) )
        {
            for ( PlotPlayer plotPlayer : event.getPlot( ).getPlayersInPlot( ) )
            {
                Command.stop( plotPlayer.getName( ) );
                Command.playNormalSound( plotPlayer.getName( ), ( String ) event.getPlot( ).getFlags( ).get( FlagManager.getFlag( "musicLink" ) ) );
            }
        }
    }

    @EventHandler
    public void onSocketConnect ( WebConnectEvent event )
    {
        if ( new PlotAPI( ).isInPlot( event.getPlayer( ) ) )
        {
            Plot plot = new PlotAPI( ).getPlot( event.getPlayer( ).getLocation( ) );
            if ( plot.getFlags( ).containsKey( FlagManager.getFlag( "musicLink" ) ) )
            {
                Command.stop( event.getPlayer( ).getName( ) );
                Command.playNormalSound( event.getPlayer( ).getName( ), ( String ) plot.getFlags( ).get( FlagManager.getFlag( "musicLink" ) ) );
            }
        }
    }
}
