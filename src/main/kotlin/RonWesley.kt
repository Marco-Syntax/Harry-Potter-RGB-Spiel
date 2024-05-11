open class RonWesley(name: String, hp: Int, action: SpellMaster) : Wizard(name, hp, SpellMaster()) {

    //ruf seine Ratte Krätze und kann extra schaden verursachen
    fun ratAttack(ziel: DarkMage) {
        println("$name hat sein Wandlungzauber (100 Punkte schaden) angewendet seine Attacke wird um 50 Schadenspunkte verstärkt durch seine Ratte Krätze")
        val wandlungZauber: Int = 50
        ziel.hp -= wandlungZauber + 100
        println("Die Attacke hat ${wandlungZauber + 100} schaden verursacht")
        println("${ziel.name} hat noch ${ziel.hp} Lebenspunkte")
    }
}