package dev.slne.surf.lobby.jar.command.subcommand.setting

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.slne.surf.lobby.jar.service.JumpAndRunService
import dev.slne.surf.lobby.jar.util.PluginColor
import dev.slne.surf.lobby.jar.util.prefix
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player

class ParkourSettingMaterialListCommand(commandName: String) : CommandAPICommand(commandName) {
    init {
        withPermission("jumpandrun.command.setting.listmaterial")
        executesPlayer(PlayerCommandExecutor { player: Player, args: CommandArguments? ->
            val materialCount: Int = JumpAndRunService.jumpAndRun.materials.size
            val header: Component =
                Component.text("Materialien im Jump And Run: ", PluginColor.LIGHT_GRAY)
                    .append(Component.text("(", PluginColor.DARK_GRAY))
                    .append(Component.text(materialCount, NamedTextColor.YELLOW))
                    .append(Component.text(") ", PluginColor.DARK_GRAY))

            val materialList = this.getComponent(materialCount)

            player.sendMessage(prefix.append(header.append(materialList)))
        })
    }

    private fun getComponent(materialCount: Int): Component {
        var materialList: Component = Component.text("")
        var current = 0

        for (material in JumpAndRunService.jumpAndRun.materials) {
            current++
            val materialComponent: Component = Component.text(material.name, NamedTextColor.WHITE)

            materialList = if (current < materialCount) {
                materialList.append(materialComponent).append(
                    Component.text(
                        ", ",
                        NamedTextColor.GRAY
                    )
                )
            } else {
                materialList.append(materialComponent)
            }
        }
        return materialList
    }
}
