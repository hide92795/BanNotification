package hide92795.bukkit.plugin.bannotification;

import hide92795.bukkit.plugin.bannotification.listener.PlayerBanListener;
import hide92795.bukkit.plugin.corelib.Localize;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BanNotification extends JavaPlugin {
	public Logger logger;
	public Localize localize;

	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		logger = getLogger();
		localize = new Localize(this);
		getServer().getPluginManager().registerEvents(new PlayerBanListener(this), this);
		logger.info("BanNotification enabled!");
	}

	public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
		switch (command.getName().toLowerCase()) {
		case "bannotification-reload":
			try {
				reload();
				sender.sendMessage(localize.getString(Type.RELOADED_SETTING));
				logger.info("Reloaded successfully.");
			} catch (Exception e) {
				sender.sendMessage(localize.getString(Type.ERROR_RELOAD_SETTING));
			}
			break;
		default:
			break;
		}
		return true;
	}

	private void reload() throws Exception {
		reloadConfig();
		try {
			localize.reload(getConfig().getString("Language"));
		} catch (Exception e1) {
			logger.severe("Can't load language file.");
			try {
				localize.reload("jp");
				logger.severe("Loaded default language file.");
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
