package spells

import actions.DarkMage
import actions.SpellMaster
import actions.Wizard

class HarryPotter(name: String, hp: Int, action: SpellMaster) : Wizard(name, hp, action) {

    fun broomAttack(target: DarkMage) {
        println("$name hat seinen Besen gerufen, um den Gegner zu verwirren.")
        println("Er darf noch einen Angriff ausführen.")
        action.printSpellMenu()
        println("Wähle einen Zauber (Nummer eingeben):")

        val damage = action.selectSpell()
        if (damage != null) {
            target.getDarkDamage(damage)
        }
    }
}
