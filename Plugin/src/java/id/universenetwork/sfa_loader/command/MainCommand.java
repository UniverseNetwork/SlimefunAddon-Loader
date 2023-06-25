package id.universenetwork.sfa_loader.command;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import id.universenetwork.sfa_loader.AddonsLoader;
import id.universenetwork.sfa_loader.libraries.infinitylib.core.AbstractAddon;
import id.universenetwork.sfa_loader.libraries.infinitylib.core.AddonConfig;
import id.universenetwork.sfa_loader.template.AddonTemplate;
import id.universenetwork.sfa_loader.utils.LogUtils;
import id.universenetwork.sfa_loader.utils.TextUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

@SuppressWarnings("unused")
public final class MainCommand {
    @CommandMethod("slimefunaddon-loader|sfa-loader|sfal|sfa")
    @CommandDescription("Main command of SlimefunAddon-Loader")
    public void cmd(CommandSender sender) {
        TextUtils.send(sender, "");
        TextUtils.send(sender, "&aSF&dAddon &bLoader &6v" + AbstractAddon.instance()
                .getDescription().getVersion());
        TextUtils.send(sender, "&dMade By &bARVIN&a3108 &cI&fD &dfor &bUniverse&eNetwork");
        TextUtils.send(sender, "");
    }

    @CommandMethod("slimefunaddon-loader|sfa-loader|sfal|sfa reload|rl")
    @CommandPermission("sfaloader.command.reload")
    public void cmdReload(CommandSender sender) {
        LogUtils.info("&eReloading all configuration files...");
        AbstractAddon.config().reload();
        Set<AddonTemplate> loadedAddon = AddonsLoader.getLoadedAddons();
        for (AddonTemplate addon : loadedAddon) {
            AddonConfig config = addon.getConfig();
            if (config != null) config.reload();
        }
        if (sender instanceof Player) {
            LogUtils.info("&aAll configuration files have been reloaded!");
            TextUtils.send(sender, AbstractAddon.config()
                    .getString("plugin-settings.reload"));
        } else TextUtils.send(sender, AbstractAddon.config()
                .getString("plugin-settings.reload"));
    }
}