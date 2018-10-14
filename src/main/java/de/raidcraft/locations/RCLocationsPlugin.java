package de.raidcraft.locations;

import de.raidcraft.api.BasePlugin;
import de.raidcraft.api.config.ConfigurationBase;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Player;

/**
 * Plugin for testing various stuff and creating proof of concepts.
 */
@Getter
public class RCLocationsPlugin extends BasePlugin {

    private LocalConfiguration config;

    @Override
    public void enable() {
        this.config = configure(new LocalConfiguration(this));
        registerCommands(LocationCommands.class);
    }

    @Override
    public void disable() {
    }

    public class LocalConfiguration extends ConfigurationBase<RCLocationsPlugin> {

        public LocalConfiguration(RCLocationsPlugin plugin) {
            super(plugin, "config.yml");
        }
    }

}