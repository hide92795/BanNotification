package hide92795.bukkit.plugin.bannotification.listener;

import hide92795.bukkit.plugin.bannotification.BanNotification;
import hide92795.bukkit.plugin.bannotification.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerBanListener implements Listener {
	private final BanNotification plugin;

	public PlayerBanListener(BanNotification plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerBaned(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage();
		String[] commands = command.split(" ");
		if (command != null && commands[0].equals("/ban")) {
			if (commands.length == 2) {
				// non reason
				String baned = commands[1];
				String performer = player.getName();
				String message = plugin.localize.getString(Type.BAN);
				plugin.getServer().broadcastMessage(String.format(message, baned, performer));
			} else if (commands.length == 3) {
				String baned = commands[1];
				String performer = player.getName();
				String reason = commands[2];
				String message = plugin.localize.getString(Type.BAN_WITH_REASON);
				plugin.getServer().broadcastMessage(String.format(message, baned, performer, reason));
			}
		}
	}
}
