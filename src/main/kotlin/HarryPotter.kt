open class HarryPotter(name: String, hp: Int, action: SpellMaster) : Wizard(name, hp, SpellMaster()) {

    fun broomAttack(target: DarkMage) {
        println("$name hat seinen Besen gerufen, um den Gegner zu verwirren.")
        println("Er darf noch einen Angriff ausführen.")
        // Hier kann man zusätzlichen Angriff ausführen
        println("$name Bitte gib einen Zauberspruch für den Angriff ein: ")
        val schaden = action.applySpell(readln())
        if (schaden != null) {
            // Hier wird der zusätzliche Schaden gegen das Ziel ausgeben
            target.getDarkDamage(schaden)
        }
    }

}