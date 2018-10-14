package de.raidcraft.locations;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.NestedCommand;
import lombok.Data;
import org.bukkit.command.CommandSender;

@Data
public class LocationCommands {

    private final RCLocationsPlugin plugin;

    @Command(
            aliases = "rclocations",
            desc = "Dient als Basis Command für RCLocations"
    )
    @NestedCommand(SubCommands.class)
    @CommandPermissions("rclocations.admin")
    public void baseCommand(CommandContext args, CommandSender sender) {

    }

    public class SubCommands {

    }
}
