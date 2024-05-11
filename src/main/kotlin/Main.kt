// Zauberer und Gegner erstellt
val harryPotter: HarryPotter = HarryPotter("Harry Potter", 400, SpellMaster())
val ronWesley: RonWesley = RonWesley("Ron Wesley", 400, SpellMaster())
val albusDumbledore: AlbusDumbledore = AlbusDumbledore("Albus Dumbledore", 400, SpellMaster())

val lordVoldemort: LordVoldemort = LordVoldemort("Lord Voldemort", 600)
val nagini: Nagini = Nagini("Nagini", 300)

var heroes: MutableList<Wizard> = mutableListOf(harryPotter, ronWesley, albusDumbledore)
var enemy: MutableList<DarkMage> = mutableListOf(lordVoldemort, nagini)

//Bedingung für Spiel Ende
var gameOver: Boolean = false

// Rundenzähler
var round: Int = 1

//Bedingung für Bonus Attacke
var naginiBonusAttack = false
var naginiSnakeBite = false


fun main() {
    playGameMusic()
    printGameTitle()
    startBattle(heroes, enemy)
}

