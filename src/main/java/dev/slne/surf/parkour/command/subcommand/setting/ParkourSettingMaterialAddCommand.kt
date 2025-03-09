package dev.slne.surf.parkour.command.subcommand.setting

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import dev.slne.surf.parkour.command.argument.ParkourArgument
import dev.slne.surf.parkour.command.argument.SolidMaterialArgument
import dev.slne.surf.parkour.parkour.Parkour
import dev.slne.surf.parkour.send
import dev.slne.surf.parkour.util.Permission
import org.bukkit.Material
import org.bukkit.entity.Player

class ParkourSettingMaterialAddCommand(commandName: String) : CommandAPICommand(commandName) {
    init {
        withPermission(Permission.COMMAND_PARKOUR_SETTING_MATERIAL_ADD)
        withArguments(SolidMaterialArgument("material"))
        withArguments(ParkourArgument("parkour"))

        executesPlayer(PlayerCommandExecutor { player: Player, args: CommandArguments ->
            val material = args.getUnchecked<Material>("material")
                ?: throw CommandAPI.failWithString("Das Material wurde nicht gefunden.")
            val parkour = args.getUnchecked<Parkour>("parkour")
                ?: throw CommandAPI.failWithString("Der Parkour wurde nicht gefunden.")

            parkour.edit {
                this.availableMaterials.add(material)
            }

            player.send {
                success("Du hast ")
                info(material.name)
                success(" zur Liste der Materialien von ")
                info(parkour.name)
                success(" hinzugefügt.")
            }
        })
    }
}
