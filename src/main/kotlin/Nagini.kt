class Nagini (name:String,hp:Int): DarkMage (name, hp) {

    fun applySnakeAttack(target: Wizard) {
        val schlagenBiss = 100
        if (target.hp > 0) {
            Thread.sleep(2000)
            println("$green$name hat Attacke Schlagenbiss gegen ${target.name} angewendet. Schaden: $schlagenBiss punkte$reset")
            target.hp -= schlagenBiss
            println("$green${target.name} hat noch ${target.hp} lebenspunkte und wurde vergiftet$reset")
        } else if (target.hp == 0) {
            println("${target.name} wurde besiegt")
        }
    }

}
