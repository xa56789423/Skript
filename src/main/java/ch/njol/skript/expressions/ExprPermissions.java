/**
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 * Copyright 2011-2017 Peter Güttinger and contributors
 */
package ch.njol.skript.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.eclipse.jdt.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

@Name("All Permissions")
@Description("Returns all permissions of the defined player(s). Note that the modifications to resulting list do not actually change permissions.")
@Examples("set {_permissions::*} to all permissions of the player")
@Since("2.2-dev33, 2.2-dev35 (Converted to SimplePropertyExpression)")
public class ExprPermissions extends SimplePropertyExpression<Player, String[]> {
	
	static {
		register(ExprPermissions.class, String[].class, "permissions", "players");
	}
	
	@Override
	public Class<String[]> getReturnType() {
		return String[].class;
	}
	
	@Override
	protected String getPropertyName() {
		return "permissions";
	}
	
	@Nullable
	@Override
	public String[] convert(final Player player) {
		final Set<String> permissions = new HashSet<>();
		for (final PermissionAttachmentInfo permission : player.getEffectivePermissions())
			permissions.add(permission.getPermission());
		return permissions.toArray(new String[permissions.size()]);
	}

}
