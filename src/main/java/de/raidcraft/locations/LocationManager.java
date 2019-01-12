package de.raidcraft.locations;

import de.raidcraft.api.BasePlugin;
import de.raidcraft.api.config.ConfigLoader;
import de.raidcraft.api.locations.ConfiguredLocation;
import de.raidcraft.api.locations.LocationProvider;
import de.raidcraft.api.locations.Locations;
import de.raidcraft.api.quests.Quests;
import de.raidcraft.util.CaseInsensitiveMap;
import de.raidcraft.util.ConfigUtil;
import lombok.Data;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

@Data
public class LocationManager implements LocationProvider {

    private final RCLocationsPlugin plugin;
    private final Map<String, ConfiguredLocation> locations = new CaseInsensitiveMap<>();

    private int failed = 0;

    public LocationManager(RCLocationsPlugin plugin) {
        this.plugin = plugin;
        Locations.registerLocationProvider(this);
        load();

        Quests.registerQuestLoader(new LocationConfigLoader(plugin));
        Quests.registerQuestLoader(new LocationsConfigLoader(plugin));
    }

    private void load() {
        failed = 0;
        ConfigUtil.loadRecursiveConfigs(getPlugin(), "locations", new LocationConfigLoader(getPlugin()));
        ConfigUtil.loadRecursiveConfigs(getPlugin(), "locations", new LocationsConfigLoader(getPlugin()));
        getPlugin().getLogger().info("Loaded " + locations.size() + "/" + (locations.size() + failed) + " locations.");
    }

    public void reload() {
        locations.clear();
        load();
    }

    @Override
    public Optional<ConfiguredLocation> getLocation(String name) {
        return Optional.ofNullable(locations.get(name));
    }

    @Override
    public Optional<ConfiguredLocation> getLocation(String name, Player player) {
        return getLocation(name);
    }

    @Override
    public boolean isLocation(String name) {
        return locations.containsKey(name);
    }

    @Override
    public boolean registerLocation(String name, ConfiguredLocation location) {
        if (locations.containsKey(name)) {
            getPlugin().getLogger().warning("Cannot register duplicate location with name " + name);
            failed++;
            return false;
        }
        locations.put(name, location);
        if (getPlugin().getConfig().debugLocationLoading) {
            getPlugin().getLogger().info("Registered named location: " + name + " (" + location.getLocation().toString() + ")");
        }
        return true;
    }

    @Override
    public boolean unregisterLocation(String name) {
        return locations.remove(name) != null;
    }

    public class LocationConfigLoader extends ConfigLoader {

        public LocationConfigLoader(BasePlugin plugin) {
            super(plugin, "location");
        }

        @Override
        public void loadConfig(String id, ConfigurationSection config) {
            registerLocation(id, config);
        }
    }

    public class LocationsConfigLoader extends ConfigLoader {

        public LocationsConfigLoader(BasePlugin plugin) {
            super(plugin, "locations");
        }

        @Override
        public void loadConfig(String id, ConfigurationSection config) {
            for (String key : config.getKeys(false)) {
                registerLocation(id + "." + key, config.getConfigurationSection(key));
            }
        }
    }

    private void registerLocation(String id, ConfigurationSection config) {
        Optional<ConfiguredLocation> location = Locations.fromConfig(config);
        if (!location.isPresent()) {
            failed++;
            if (getPlugin().getConfig().debugLocationLoading) {
                getPlugin().warning("Could not parse " + id + " (" + ConfigUtil.getFileName(config) + ") as valid location config!");
            }
            return;
        }
        registerLocation(id, location.get());
    }
}
