package niklas.blockplacer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CylinderPlacer implements CommandExecutor {
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

            if (strings.length != 2) {
                System.out.println("Falsche Anzahl an Argumenten");
                player.sendMessage("Falsche Anzahl an Argumenten");
                return false;
            }
            int height = 0;
            int r = 0;
            try {
                height = Integer.valueOf(strings[0]);
                r = Integer.valueOf(strings[1]);
            } catch (NumberFormatException e) {
                System.out.println("Argumente sind ungültig");
                player.sendMessage("Argumente sind ungültig");
                e.printStackTrace();
                return false;
            }

            if (r <= 0 || height <= 0) {
                System.out.println("Der übergebene Wert sollte positiv und nicht negativ oder 0 sein");
                player.sendMessage("Der übergebene Wert sollte positiv und nicht negativ oder 0 sein");
            }
            int baseX = (int) position.getX() + 20;
            int baseY = (int) position.getY();
            int baseZ = (int )position.getZ();

            for (int h = 0; h <= height; h++) {
                int x = 0;
                int y = r;
                int d = 3 - 2 * r;

                while (y >= x) {
                    world.getBlockAt( (baseX + x),  baseY + h, (int) (baseZ + y)).setType(Material.STONE);
                    world.getBlockAt((int) (baseX + x), (int) baseY + h, (int) (baseZ - y)).setType(Material.STONE);
                    world.getBlockAt((int) (baseX - x), (int) baseY + h, (int) (baseZ + y)).setType(Material.STONE);
                    world.getBlockAt((int) (baseX - x), (int) baseY + h, (int) (baseZ - y)).setType(Material.STONE);
                    world.getBlockAt((int) (baseX + y), (int) baseY + h, (int) (baseZ + x)).setType(Material.STONE);
                    world.getBlockAt((int) (baseX + y), (int) baseY + h, (int) (baseZ - x)).setType(Material.STONE);
                    world.getBlockAt((int) (baseX - y), (int) baseY + h, (int) (baseZ + x)).setType(Material.STONE);
                    world.getBlockAt((int) (baseX - y), (int) baseY + h, (int) (baseZ - x)).setType(Material.STONE);

                    x++;

                    if (d > 0) {
                        y--;
                        d = d + 4 * (x - y) + 10;
                    } else {
                        d = d + 4 * x + 6;
                    }
                }
            }
            System.out.println("Die Struktur wurde gebaut");
            player.sendMessage("Die Struktur wurde gebaut");
        }
        return true;
    }
}
