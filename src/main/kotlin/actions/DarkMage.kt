package actions

open class DarkMage(var name: String, var hp: Int, var isDead: Boolean = false) {

    companion object {
        private const val ATTACK_DAMAGE = 150
        private const val AREA_SPELL_DAMAGE = 100
    }

    fun mysterySpell(target: Wizard) {
        if (target.hp <= 0) return

        val attacks = listOf(
            { fireAttack(target) },
            { deathCurse(target) },
            { darkMagicStrike(target) }
        )
        attacks.random()()
    }

    private fun fireAttack(target: Wizard) {
        if (target.hp <= 0) return
        println("$name hat Attacke Feuersturm gegen ${target.name} angewendet. Schaden: $ATTACK_DAMAGE Punkte")
        target.hp -= ATTACK_DAMAGE
        println("${target.name} hat noch ${target.hp} Lebenspunkte")
    }

    private fun deathCurse(target: Wizard) {
        if (target.hp <= 0) return
        println("$name hat Attacke Fluch des Todes gegen ${target.name} angewendet. Schaden: $ATTACK_DAMAGE Punkte")
        target.hp -= ATTACK_DAMAGE
        println("${target.name} hat noch ${target.hp} Lebenspunkte")
    }

    private fun darkMagicStrike(target: Wizard) {
        if (target.hp <= 0) return
        println("$name hat Attacke Dementorenattacke gegen ${target.name} angewendet. Schaden: $ATTACK_DAMAGE Punkte")
        target.hp -= ATTACK_DAMAGE
        println("${target.name} hat noch ${target.hp} Lebenspunkte")
    }

    fun getAreaSpell(target1: Wizard, target2: Wizard, target3: Wizard) {
        val targets = listOf(target1, target2, target3)
        if (targets.all { it.hp <= 0 }) {
            println("Alle Zauberer sind bereits besiegt.")
            return
        }

        println("$red$name hat Attacke Flächenzauber angewendet. Schaden: $AREA_SPELL_DAMAGE Punkte$reset")
        targets.forEach { target ->
            target.hp -= AREA_SPELL_DAMAGE
            println("$red${target.name} hat $AREA_SPELL_DAMAGE Schaden erhalten und hat noch ${target.hp} Lebenspunkte$reset")
        }
    }

    open fun getDarkDamage(darkDamage: Int) {
        hp -= darkDamage
        if (hp <= 0) {
            hp = 0
            println("$red${name} wurde besiegt$reset")
        } else {
            println("$red$name hat $darkDamage Schaden erhalten. Aktuelle HP: $hp$reset")
        }
    }
}
