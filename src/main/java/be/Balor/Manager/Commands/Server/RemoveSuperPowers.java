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
package be.Balor.Manager.Commands.Server;

import java.util.Map.Entry;

import org.bukkit.command.CommandSender;

import be.Balor.Manager.Commands.CommandArgs;
import be.Balor.Manager.Exceptions.PlayerNotFound;
import be.Balor.Manager.Permissions.ActionNotPermitedException;
import be.Balor.Manager.Permissions.PermissionManager;
import be.Balor.Player.ACPlayer;
import be.Balor.Tools.Type;
import be.Balor.Tools.Type.Category;
import be.Balor.Tools.CommandUtils.Users;
import be.Balor.bukkit.AdminCmd.LocaleHelper;

/**
 * @author Antoine
 * 
 */
public class RemoveSuperPowers extends ServerCommand {

	public RemoveSuperPowers() {
		super("bal_remsp", "admincmd.server.removesp");
		other = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.Balor.Manager.Commands.CoreCommand#execute(org.bukkit.command.
	 * CommandSender, be.Balor.Manager.Commands.CommandArgs)
	 */
	@Override
	public void execute(final CommandSender sender, final CommandArgs args) throws ActionNotPermitedException, PlayerNotFound {
		final ACPlayer player = Users.getACPlayer(sender, args, permNode);
		if (player == null) {
			return;
		}
		String playername;
		if (player.isOnline()) {
			playername = Users.getPlayerName(player.getHandler());
		} else {
			playername = player.getName();
		}
		if (args.hasFlag('n')) {
			player.removeAllSuperPower();
		} else if (player.isOnline()) {
			for (final Entry<Type, Object> entry : player.getPowers().entrySet()) {
				if (!entry.getKey().getCategory().equals(Category.SUPER_POWER)) {
					continue;
				}
				if (entry.getKey().getPermission() == null) {
					continue;
				}
				if (PermissionManager.hasPerm(player.getHandler(), entry.getKey().getPermission())) {
					continue;
				}
				player.removePower(entry.getKey());
			}
		} else {
			LocaleHelper.ERROR_NOT_ONLINE.sendLocale(sender, "player", playername);
			return;
		}
		LocaleHelper.REMOVE_SP.sendLocale(sender, "player", playername);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.Balor.Manager.Commands.CoreCommand#argsCheck(java.lang.String[])
	 */
	@Override
	public boolean argsCheck(final String... args) {
		return args != null;
	}

}
