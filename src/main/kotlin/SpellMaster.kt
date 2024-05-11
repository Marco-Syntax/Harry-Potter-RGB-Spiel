import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.FloatControl

open class SpellMaster {
    open val magicSpellMap: MutableMap<String, Int> = mutableMapOf(
        "expecto patronum" to  50,
        "stupor" to  100,
        "expelliarmus" to  150,
        "avada kedavra" to  200
    )
//Hier wird der Schaden von der map genommen man gibt den key ein und bekommt den Value wert als zauberSpruchschaden und man hat 3 versuche es wird über die readline eingeben
    open fun applySpell(spellName: String): Int? {
        var spellDamage = magicSpellMap[spellName]
        var attempts = 1

        while (spellDamage == null && attempts <= 3) {
            println("Bitte gib einen gültigen Zauberspruch ein: ")
            val benutzereingabe = readln()
            spellDamage = magicSpellMap[benutzereingabe]
            attempts++

            if (spellDamage == null && attempts <= 3) {
                println("Zauber: $benutzereingabe nicht gefunden. Versuch $attempts von 3.")
            }
        }
        if (spellDamage == null) {
            println("Drei Versuche wurden erreicht. Angriff wurde abgebrochen.")
        } else {
            println("${blue}Zauber: $spellName, Schaden: $spellDamage hP$reset")
        }
        return spellDamage
    }
}


//Außerhalb der klasse
fun printGameTitle(){
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
fun playAudio (audioPath : String) {
    val audio: File = File(audioPath)
    val audioInput: AudioInputStream = AudioSystem.getAudioInputStream(audio)
    val clip: Clip = AudioSystem.getClip()
    clip.open(audioInput)
    clip.start()

    if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
        val volume: FloatControl = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl

        volume.value = volume.minimum+(0.70f*(volume.maximum-volume.minimum))
    } else {
        println("Master Gain Control not Supported")
    }
}
fun playGameMusic(){
    val audioPath = "Sounds/Hedwig's Theme.wav"
    playAudio(audioPath)
}

// variabel Farben
const val red = "\u001B[31m"
const val green = "\u001B[32m"
const val yellow = "\u001B[33m"
const val blue = "\u001B[34m"
const val magenta = "\u001B[35m"
const val reset = "\u001B[0m"




