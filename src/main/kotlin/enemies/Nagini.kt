package enemies

import actions.DarkMage
import actions.Wizard
import actions.green
import actions.reset

class Nagini(name: String, hp: Int) : DarkMage(name, hp) {

    companion object {
        private const val SNAKE_BITE_DAMAGE = 100
    }

    fun applySnakeAttack(target: Wizard) {
        if (target.hp <= 0) {
            println("${target.name} wurde bereits besiegt.")
            return
        }

        Thread.sleep(2000)
        println("$green$name hat Attacke Schlangenbiss gegen ${target.name} angewendet. Schaden: $SNAKE_BITE_DAMAGE Punkte$reset")
        target.hp -= SNAKE_BITE_DAMAGE
        println("$green${target.name} hat noch ${target.hp} Lebenspunkte und wurde vergiftet$reset")
    }
}
