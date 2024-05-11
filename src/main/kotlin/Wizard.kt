open class Wizard(
    var name: String, var hp: Int, var action: SpellMaster,
    var shieldActive: Boolean = false,
    var elixirAccess: Boolean = true,
    var bagPotion: Boolean = false,
    var attackBonus: Boolean = false,
    var isPoisoned: Boolean = false
) {

    open val magicSpells: MutableMap<String, Int> = mutableMapOf(
        "expecto patronum" to 50,
        "stupor" to 100,
        "expelliarmus" to 150,
        "avada kedavra" to 200,
    )

    //Hilfsfunktion
    open fun castSpell(): Int? {
        return action.applySpell(readln())
    }

    //Gibt die Liste mit Zaubersprüchen aus mit einer for i schleife aus
    private fun printSpellList() {
        var zahl = 1
        for (i in magicSpells) {
            println("$zahl. $i")
            zahl++
        }
    }

    // Führt ein Angriff aus
    open fun attack(target: DarkMage) {
        println("$blue$name hat $hp Lebenspunkte und möchte ${target.name} angreifen er hat ${target.hp} Lebenspunkte")
        println()
        printSpellList()
        println("Bitte gib ein Zauberspruch ein:$reset")
        // Hier kann man verschiedene Zaubersprüche auswählen über die Konsole
        val strike = action.applySpell(readln())
        if (strike != null) {
            target.getDarkDamage(strike)
        } else if (target.hp <= 0) {
            target.isDead = true
        }
    }

    //Hier wird dem Zauber "das Gift" abgezogen
    open fun inflictPoison() {
        if (isPoisoned) {
            println("$green$name ist vergiftet Aktuelle :$hp")
            if (hp > 0) {
                val poisonDamage = (hp * 0.1).toInt()
                println("$name hat ${hp - poisonDamage} Lebenspunkte das Gift zieht ihm 10% ab")
                hp -= poisonDamage
                println("$name hat $poisonDamage Lebenspunkte durch das Gift verloren.$reset")
                isPoisoned = true
            }
        }
    }

    //Heilt den Zauber um die hälfte seine Lebenspunkte
    fun healingMethods() {
        val healing: Int = hp / 2
        hp + healing
        println(
            "$name hat eine Heilungsmethode angewendet und erhält $healing lebenspunkte Heilung" +
                    " \nDie neuen Lebenspunkte von $name sind  $hp"
        )
    }

    //Gibt dem Zauber Schutz vor der Attacke
    fun protect() {
        val reducedDamage = castSpell()
        if (shieldActive) {
            println("$name versucht sich gegen den Angriff zu schützen.")
            if (reducedDamage != null) {
                hp -= reducedDamage
            }
        }
        println("$name hat sich erfolgreich gegen den Angriff geschützt. Aktuelle HP: $hp")

    }

    //Der Beuteltrank kann nur dreimal verwendet werden
    private var potionUses = 3
    fun bagPotion() {
        if (potionUses > 0) {
            // Heilt den Zauberer um die Hälfte seiner Lebenspunkte
            val healing = 200
            hp += healing
            println("$yellow$name hat einen Heiltrank verwendet und wurde um $healing Lebenspunkte geheilt$reset.")
            potionUses--
        } else {
            println("Der Heiltrank kann nicht mehr verwendet werden, er wurde bereits drei Mal eingesetzt.")
        }
    }

    //Steht nur einmal zur Verfügung erhöht die Lebenspunkte um 10
    private var elixirCount = 1
    fun useElixir() {
        if (elixirAccess) {
            val healing = 10
            println("${green}Naginis Biss war giftig ")
            println("$green$name bekommt ein Zauberelixier. Seine Energie wird jetzt um 10 hp erhöht.$reset")
            hp += healing
            elixirCount--
        } else {
            println("$yellow$name hat keinen Zugriff auf das Zauberelixier.$reset")
        }
    }
}
