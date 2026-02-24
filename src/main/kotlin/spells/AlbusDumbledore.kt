package spells

import actions.DarkMage
import actions.SpellMaster
import actions.Wizard

class AlbusDumbledore(name: String, hp: Int, action: SpellMaster) : Wizard(name, hp, action) {

    companion object {
        private const val PHOENIX_DAMAGE = 100
    }

    fun phoenixStrike(target: DarkMage) {
        println("$name hat seinen Phönix Fawkes gerufen und führt die Attacke Feuerball aus.")
        target.hp -= PHOENIX_DAMAGE
        println("Die Attacke hat $PHOENIX_DAMAGE Schaden bei ${target.name} verursacht.")
    }
}
