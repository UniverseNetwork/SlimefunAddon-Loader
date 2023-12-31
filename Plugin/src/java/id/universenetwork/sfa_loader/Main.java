package id.universenetwork.sfa_loader;

import id.universenetwork.sfa_loader.command.MainCommand;
import id.universenetwork.sfa_loader.libraries.infinitylib.core.AbstractAddon;
import id.universenetwork.sfa_loader.managers.CommandManager;
import id.universenetwork.sfa_loader.managers.LibraryManager;
import id.universenetwork.sfa_loader.utils.LogUtils;
import org.bukkit.Bukkit;

@SuppressWarnings("unused")
public final class Main extends AbstractAddon {
    @Override
    protected void enable() {
        LibraryManager.init();
        CommandManager.init();
        CommandManager.register(new MainCommand());
        if (Bukkit.getPluginManager().isPluginEnabled("Slimefun")) {
            if (config().getBoolean("loader-settings.disable-all-addons"))
                LogUtils.warning("All addons are disabled by default, " +
                        "you can enable them manually using commands.");
            else AddonsLoader.loadEnabledAddons();
        } else LogUtils.severe("Slimefun can't be found, all addons can't be loaded!");
    }

    @Override
    protected void disable() {
        AddonsLoader.unloadAllAddons();
    }
}