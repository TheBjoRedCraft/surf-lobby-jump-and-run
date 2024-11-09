package dev.slne.surf.lobby.jar.command.subcommand;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.slne.surf.lobby.jar.JumpAndRunProvider;
import dev.slne.surf.lobby.jar.PluginInstance;
import dev.slne.surf.lobby.jar.util.PluginColor;
import net.kyori.adventure.text.Component;

public class ParkourToggleSoundCommand extends CommandAPICommand {
  private final JumpAndRunProvider provider = PluginInstance.instance().jumpAndRunProvider();

  public ParkourToggleSoundCommand(String commandName) {
    super(commandName);

    executesPlayer((player, args) -> {
      provider.querySound(player.getUniqueId()).thenAccept(sound -> {
        if(sound == null){
          sound = true;
        }

        if(sound) {
          provider.setSound(player, false);

          player.sendMessage(PluginInstance.prefix().append(Component.text("Dir werden nun").append(Component.text(" Sounds abgespielt.", PluginColor.GOLD))));
        }else{
          provider.setSound(player, true);

          player.sendMessage(PluginInstance.prefix().append(Component.text("Dir werden nun").append(Component.text(" nicht mehr Sounds abgespielt.", PluginColor.GOLD))));
        }
      });
    });
  }
}
