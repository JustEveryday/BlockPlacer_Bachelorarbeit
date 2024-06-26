package niklas.blockplacer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CuboidPlacer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            World world = player.getWorld();
            Location position = player.getLocation();

            if (world == null || position == null) {
                System.out.println("Die Welt oder die Position ist null");
                player.sendMessage("Die Welt oder die Position ist null");
                return false;
            }

            if (strings.length != 3) {
                System.out.println("Falsche Anzahl an Argumenten");
                player.sendMessage("Falsche Anzahl an Argumenten");
                return false;
            }

            int height = 0;
            int width = 0;
            int lenght = 0;
            try {
                height = Integer.parseInt(strings[0]);
                width = Integer.parseInt(strings[1]);
                lenght = Integer.parseInt(strings[2]);
            } catch (NumberFormatException e) {
                System.out.println("Argumente sind ungültig");
                player.sendMessage("Argumente sind ungültig");
                e.printStackTrace();
                return false;
            }

            if (height <= 0 || width <= 0 || lenght <= 0) {
                System.out.println("Der übergebene Wert sollte positiv und nicht negativ oder 0 sein");
                player.sendMessage("Der übergebene Wert sollte positiv und nicht negativ oder 0 sein");
            }

            double yStart = position.getY();
            double zStart = position.getZ();
            double xStart = position.getX();

            position.setX(position.getX() + 2);

            for (int y = 0; y < height; y++) {
                position.setY(yStart + y);

                for (int z = 0; z < width; z++) {
                    System.out.println(position);
                    position.setZ(zStart + z);

                    for (int x = 0; x < lenght; x++) {
                        position.setX(xStart + x);
                        world.getBlockAt(position).setType(Material.STONE);
                        position.setX(xStart + x);
                    }
                }
            }
            System.out.println("Die Struktur wurde gebaut");
            player.sendMessage("Die Struktur wurde gebaut");
        }
        return true;
    }
}
