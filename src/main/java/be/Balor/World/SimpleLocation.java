/************************************************************************
 * This file is part of AdminCmd.									
 *																		
 * AdminCmd is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by	
 * the Free Software Foundation, either version 3 of the License, or		
 * (at your option) any later version.									
 *																		
 * AdminCmd is distributed in the hope that it will be useful,	
 * but WITHOUT ANY WARRANTY; without even the implied warranty of		
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			
 * GNU General Public License for more details.							
 *																		
 * You should have received a copy of the GNU General Public License
 * along with AdminCmd.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/
package be.Balor.World;

import org.bukkit.Location;
import org.bukkit.World;

import be.Balor.Manager.Exceptions.WorldNotLoaded;

/**
 * @author Balor (aka Antoine Aflalo)
 * 
 */
public class SimpleLocation {
	private String world;
	private double x;
	private double y;
	private double z;
	private float pitch;
	private float yaw;

	public SimpleLocation(final Location loc) {
		setLocationValue(loc);
	}

	public void setLocationValue(final Location loc) {
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
		yaw = loc.getYaw();
		pitch = loc.getPitch();
		world = loc.getWorld().getName();
	}

	public Location getLocation() throws WorldNotLoaded {
		final World w = ACWorld.getWorld(world).getHandle();
		if (w == null) {
			throw new WorldNotLoaded(world);
		} else {
			return new Location(w, x, y, z, yaw, pitch);
		}
	}

	/**
	 * 
	 */
	public SimpleLocation() {
	}

	/**
	 * @return the world
	 */
	public String getWorld() {
		return world;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @return the pitch
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * @return the yaw
	 */
	public float getYaw() {
		return yaw;
	}

	/**
	 * @param world
	 *            the world to set
	 */
	public void setWorld(final String world) {
		this.world = world;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(final double x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(final double y) {
		this.y = y;
	}

	/**
	 * @param z
	 *            the z to set
	 */
	public void setZ(final double z) {
		this.z = z;
	}

	/**
	 * @param pitch
	 *            the pitch to set
	 */
	public void setPitch(final float pitch) {
		this.pitch = pitch;
	}

	/**
	 * @param yaw
	 *            the yaw to set
	 */
	public void setYaw(final float yaw) {
		this.yaw = yaw;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(pitch);
		result = prime * result + ((world == null) ? 0 : world.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(yaw);
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SimpleLocation)) {
			return false;
		}
		final SimpleLocation other = (SimpleLocation) obj;
		if (Float.floatToIntBits(pitch) != Float.floatToIntBits(other.pitch)) {
			return false;
		}
		if (world == null) {
			if (other.world != null) {
				return false;
			}
		} else if (!world.equals(other.world)) {
			return false;
		}
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		if (Float.floatToIntBits(yaw) != Float.floatToIntBits(other.yaw)) {
			return false;
		}
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) {
			return false;
		}
		return true;
	}

}
