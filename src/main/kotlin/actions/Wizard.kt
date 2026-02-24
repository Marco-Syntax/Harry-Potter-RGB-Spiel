package actions

open class Wizard(
    var name: String,
    var hp: Int,
    var action: SpellMaster,
    var shieldActive: Boolean = false,
    var elixirAccess: Boolean = true,
    var attackBonus: Boolean = false,
    var isPoisoned: Boolean = false
) {

    private companion object {
        const val MAX_POTION_USES = 3
        const val POTION_HEALING = 200
        const val ELIXIR_HEALING = 10
        const val POISON_PERCENTAGE = 0.1
    }

    private var potionUses = MAX_POTION_USES

    open fun attack(target: DarkMage) {
        println("$blue$name hat $hp Lebenspunkte und möchte ${target.name} angreifen (${target.hp} HP)")
        println()
        action.printSpellMenu()
        println("Wähle einen Zauber (Nummer eingeben):$reset")

        val damage = action.selectSpell()
        if (damage != null) {
            target.getDarkDamage(damage)
        }
        if (target.hp <= 0) {
            target.isDead = true
        }
    }

    fun inflictPoison() {
        if (!isPoisoned || hp <= 0) return
        val poisonDamage = (hp * POISON_PERCENTAGE).toInt()
        println("$green$name ist vergiftet. Aktuelle HP: $hp")
        hp -= poisonDamage
        println("$name hat $poisonDamage Lebenspunkte durch das Gift verloren. Neue HP: $hp$reset")
    }

    fun bagPotion() {
        if (potionUses > 0) {
            hp += POTION_HEALING
            potionUses--
            println("$yellow$name hat einen Heiltrank verwendet und wurde um $POTION_HEALING Lebenspunkte geheilt.$reset")
        } else {
            println("Der Heiltrank kann nicht mehr verwendet werden, er wurde bereits drei Mal eingesetzt.")
        }
    }

    fun useElixir() {
        if (elixirAccess) {
            println("${green}Naginis Biss war giftig!")
            println("$green$name bekommt ein Zauberelixier. Energie wird um $ELIXIR_HEALING HP erhöht.$reset")
            hp += ELIXIR_HEALING
        } else {
            println("$yellow$name hat keinen Zugriff auf das Zauberelixier.$reset")
        }
    }
}
