package com.apocalypsjenl.openaudiomc.addon.plotsquared;

import com.apocalypsjenl.openaudiomc.addon.plotsquared.commands.MusicCommand;
import com.apocalypsjenl.openaudiomc.addon.plotsquared.listeners.Listeners;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.flag.Flag;
import com.intellectualcrafters.plot.flag.StringFlag;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PlotSquaredHook extends JavaPlugin implements Listener
{

    @Override
    public void onEnable ( )
    {
        Plugin plotSquared = getServer( ).getPluginManager( ).getPlugin( "PlotSquared" );
        Plugin openAudioMC = getServer( ).getPluginManager( ).getPlugin( "OpenAudioMc" );

        if ( !( plotSquared != null && plotSquared.isEnabled( ) ) )
        {
            getLogger( ).warning( "PlotSquared not found! Can't work without it!" );
            getServer( ).getPluginManager( ).disablePlugin( this );
        }

        if ( !( openAudioMC != null && openAudioMC.isEnabled( ) ) )
        {
            getLogger( ).warning( "OpenAudioMC not found! Can't work without it!" );
            getServer( ).getPluginManager( ).disablePlugin( this );
        }

        Flag musicLinkFlag = new StringFlag( "musicLink" )
        {
            @Override
            public String getValueDescription ( )
            {
                return "If present you can listen to music that the player has selected";
            }
        };

        new PlotAPI( ).addFlag( musicLinkFlag );

        getServer( ).getPluginManager( ).registerEvents( new Listeners( ), this );

        getCommand( "music" ).setExecutor( new MusicCommand( ) );
    }
}
