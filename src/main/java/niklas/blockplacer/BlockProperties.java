package niklas.blockplacer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockProperties implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        World world;
        Location posS;
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            world = player.getWorld();
            posS = player.getLocation();


            if (world == null || posS == null) {
                System.out.println("Die Welt oder die Position ist null");
                return false;
            }

            posS.setX(posS.getX() + 3);
            Block targetBlock1 = world.getBlockAt(posS);
            posS.setX(posS.getX() + 5);
            Block targetBlock2 = world.getBlockAt(posS);

            targetBlock1.setType(Material.OAK_STAIRS);
            targetBlock2.setType(Material.OAK_STAIRS);

            Stairs treppe1 = (Stairs) targetBlock1.getBlockData();
            Stairs treppe2 = (Stairs) targetBlock2.getBlockData();

            treppe1.setFacing(BlockFace.NORTH);
            treppe2.setFacing(BlockFace.WEST);

            treppe1.setHalf(Stairs.Half.BOTTOM);
            treppe2.setHalf(Bisected.Half.TOP);

            treppe1.setShape(Stairs.Shape.STRAIGHT);
            treppe2.setShape(Stairs.Shape.OUTER_RIGHT);

            treppe1.setWaterlogged(false);
            treppe2.setWaterlogged(true);

            targetBlock1.setBlockData(treppe1);
            targetBlock2.setBlockData(treppe2);

            System.out.println("Die Strukturen wurde gebaut");
            player.sendMessage("Die Strukturen wurde gebaut");
        }
        return true;
    }
}
