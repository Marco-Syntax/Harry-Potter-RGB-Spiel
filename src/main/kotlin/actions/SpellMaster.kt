package actions

import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.FloatControl

open class SpellMaster {

    data class Spell(val name: String, val damage: Int)

    open val spells: List<Spell> = listOf(
        Spell("Expecto Patronum", 50),
        Spell("Stupor", 100),
        Spell("Expelliarmus", 150),
        Spell("Avada Kedavra", 200)
    )

    fun printSpellMenu() {
        spells.forEachIndexed { index, spell ->
            println("  ${index + 1}. ${spell.name} (${spell.damage} HP)")
        }
    }

    open fun selectSpell(): Int? {
        var attempts = 0
        val maxAttempts = 3

        while (attempts < maxAttempts) {
            val input = readln().trim()
            val choice = input.toIntOrNull()

            if (choice != null && choice in 1..spells.size) {
                val selected = spells[choice - 1]
                println("${blue}Zauber: ${selected.name}, Schaden: ${selected.damage} HP$reset")
                return selected.damage
            }

            attempts++
            if (attempts < maxAttempts) {
                println("Ungültige Eingabe. Wähle eine Nummer (1-${spells.size}). Versuch ${attempts + 1} von $maxAttempts.")
            }
        }

        println("Drei Versuche erreicht. Angriff wurde abgebrochen.")
        return null
    }
}

fun printGameTitle() {
    println(
        "$yellow                                        _ __\n" +
                "        ___                             | '  \\\n" +
                "   ___  \\ /  ___         ,'\\_           | .-. \\        /|\n" +
                "   \\ /  | |,'__ \\  ,'\\_  |   \\          | | | |      ,' |_   /|\n" +
                " _ | |  | |\\/  \\ \\ |   \\ | |\\_|    _    | |_| |   _ '-. .-',' |_   _\n" +
                "// | |  | |____| | | |\\_|| |__    //    |     | ,'_`. | | '-. .-',' `. ,'\\_\n" +
                "\\\\_| |_,' .-, _  | | |   | |\\ \\  //    .| |\\_/ | / \\ || |   | | / |\\  \\|   \\\n" +
                " `-. .-'| |/ / | | | |   | | \\ \\//     |  |    | | | || |   | | | |_\\ || |\\_|\n" +
                "   | |  | || \\_| | | |   /_\\  \\ /      | |`    | | | || |   | | | .---'| |\n" +
                "   | |  | |\\___,_\\ /_\\ _      //       | |     | \\_/ || |   | | | |  /\\| |\n" +
                "   /_\\  | |           //_____//       .||`      `._,' | |   | | \\ `-' /| |\n" +
                "        /_\\           `------'        \\ |          `.\\  | |  `._,' /_\\\n" +
                "                                       \\|    $reset DER MAGISCHE KAMPF           "
    )
    println()
    println("      ----Der Kampf Beginnt----")
}

fun playAudio(audioPath: String) {
    val audio = File(audioPath)
    val audioInput = AudioSystem.getAudioInputStream(audio)
    val clip: Clip = AudioSystem.getClip()
    clip.open(audioInput)
    clip.start()

    if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
        val volume = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
        volume.value = volume.minimum + (0.70f * (volume.maximum - volume.minimum))
    }
}

fun playGameMusic() {
    playAudio("Sounds/Hedwig's Theme.wav")
}

const val red = "\u001B[31m"
const val green = "\u001B[32m"
const val yellow = "\u001B[33m"
const val blue = "\u001B[34m"
const val magenta = "\u001B[35m"
const val reset = "\u001B[0m"
