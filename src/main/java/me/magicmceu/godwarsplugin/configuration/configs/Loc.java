package me.magicmceu.godwarsplugin.configuration.configs;

import de.exlll.configlib.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Configuration
public class Loc {
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;
    public String world;

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public static Loc fromLocation(Location location) {
        Loc loc = new Loc();
        loc.x = location.getX();
        loc.y = location.getY();
        loc.z = location.getZ();
        loc.yaw = location.getYaw();
        loc.pitch = location.getPitch();
        loc.world = location.getWorld().getName();
        return loc;
    }
}
