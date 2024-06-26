package niklas.blockplacer;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockPlacer extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getCommand("Block").setExecutor(new BlockPlacerExtended());
        getCommand("Mauer").setExecutor(new WallPlacer());
        getCommand("Quader").setExecutor(new CuboidPlacer());
        getCommand("Kreis").setExecutor(new CirclePlacer());
        getCommand("Zylinder").setExecutor(new CylinderPlacer());
        getCommand("Ball").setExecutor(new BallPlacer());
        getCommand("Pyramide").setExecutor(new PyramidPlacer());
        getCommand("BlockEigenschaften").setExecutor(new BlockProperties());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.CREATIVE);
    }

    @Override
    public void onDisable() {
    }
}


