package de.raidcraft.locations;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.NestedCommand;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class LocationCommands {

    public LocationCommands(RCLocationsPlugin plugin) {

    }


    @Command(
            aliases = {"rclocations", "rclocs"},
            desc = "Dient als Basis Command für RCLocations"
    )
    @NestedCommand(SubCommands.class)
    @CommandPermissions("rclocations.admin")
    public void baseCommand(CommandContext args, CommandSender sender) {

    }

    @Data
    public static class SubCommands {

        private final RCLocationsPlugin plugin;

        @Command(
                aliases = {"reload"},
                desc = "Reloads all locations from disk."
        )
        @CommandPermissions("rclocations.admin.reload")
        public void reload(CommandContext args, CommandSender sender) {

            getPlugin().reload();
            sender.sendMessage(ChatColor.GREEN + "Locations wurden erfolgreich neu geladen.");
        }
    }
}
