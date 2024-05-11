open class AlbusDumbledore(name: String, hp: Int, action: SpellMaster) : Wizard(name, hp, SpellMaster()) {

    //Fawkes attacke anwenden
    fun phoenixStrike(ziel: DarkMage) {
        println("$name hat sein Phönix FAWKES gerufen und führt seine Attacke Feuerball aus")
        val phoenixStrike: Int = 100
        ziel.hp -= phoenixStrike
        println("Die Attacke hat $phoenixStrike schaden verursacht bei ${ziel.name}")
    }

}
