package de.raidcraft.locations;

import de.raidcraft.api.BasePlugin;
import de.raidcraft.api.config.ConfigurationBase;
import de.raidcraft.api.config.Setting;
import de.raidcraft.api.locations.Locations;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Player;

/**
 * Plugin for testing various stuff and creating proof of concepts.
 */
@Getter
public class RCLocationsPlugin extends BasePlugin {

    private LocationManager locationManager;
    private LocalConfiguration config;

    @Override
    public void enable() {
        this.config = configure(new LocalConfiguration(this));
        this.locationManager = new LocationManager(this);
        registerCommands(LocationCommands.class);
    }

    @Override
    public void disable() {
    }

    @Override
    public void reload() {
        getLocationManager().reload();
    }

    public class LocalConfiguration extends ConfigurationBase<RCLocationsPlugin> {

        @Setting("debug-location-loading")
        public boolean debugLocationLoading = false;

        public LocalConfiguration(RCLocationsPlugin plugin) {
            super(plugin, "config.yml");
        }
    }

}
