package spells

import actions.DarkMage
import actions.SpellMaster
import actions.Wizard

class RonWesley(name: String, hp: Int, action: SpellMaster) : Wizard(name, hp, action) {

    companion object {
        private const val RAT_BONUS_DAMAGE = 50
        private const val BASE_SPELL_DAMAGE = 100
    }

    fun ratAttack(target: DarkMage) {
        val totalDamage = BASE_SPELL_DAMAGE + RAT_BONUS_DAMAGE
        println("$name wendet seinen Wandlungszauber an. Schaden wird um $RAT_BONUS_DAMAGE durch Krätze verstärkt.")
        target.hp -= totalDamage
        println("Die Attacke hat $totalDamage Schaden verursacht.")
        println("${target.name} hat noch ${target.hp} Lebenspunkte")
    }
}
