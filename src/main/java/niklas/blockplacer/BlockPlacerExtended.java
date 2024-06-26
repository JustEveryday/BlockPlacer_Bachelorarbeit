package niklas.blockplacer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockPlacerExtended implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        System.out.println("ein block geht");
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            World world = player.getWorld();
            Location position = player.getLocation();
            position.setX(position.getX() + 5);

            Block block = world.getBlockAt(position);
            block.setType(Material.STONE);
        } else {
            System.out.println("Der Befehl kann nur von einem Spieler ausgeführt werden!");
        }
        return true;
    }
}


