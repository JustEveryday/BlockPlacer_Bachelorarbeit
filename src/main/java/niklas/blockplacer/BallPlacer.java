package niklas.blockplacer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BallPlacer implements CommandExecutor {
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

            if (strings.length != 1) {
                System.out.println("Falsche Anzahl an Argumenten");
                player.sendMessage("Falsche Anzahl an Argumenten");
                return false;
            }

            int r = 0;
            try {
                r = Integer.valueOf(strings[0]);
            } catch (NumberFormatException e) {
                System.out.println("Argumente sind ungültig");
                player.sendMessage("Argumente sind ungültig");
                e.printStackTrace();
                return false;
            }

            if (r <= 0) {
                System.out.println("Der übergebene Wert sollte positiv und nicht negativ oder 0 sein");
                player.sendMessage("Der übergebene Wert sollte positiv und nicht negativ oder 0 sein");
            }

            int baseX = (int) position.getX() + 20;
            int baseY = (int) position.getY();
            int baseZ = (int )position.getZ();

            for(int x = baseX - r; x <= baseX + r; x++){
                for(int y = baseY - r; y <= baseY + r; y++){
                    for(int z = baseZ - r; z <= baseZ + r; z++){
                        if((x-baseX)*(x-baseX)+(y-baseY)*(y-baseY)+(z-baseZ)*(z-baseZ) < r*r){
                            position.setX(x);
                            position.setY(y);
                            position.setZ(z);
                            world.getBlockAt(position).setType(Material.STONE);
                        }
                    }
                }
            }
            System.out.println("Die Struktur wurde gebaut");
            player.sendMessage("Die Struktur wurde gebaut");
        }
        return true;
    }
}
