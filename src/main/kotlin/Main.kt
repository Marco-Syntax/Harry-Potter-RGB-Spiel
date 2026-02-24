import actions.*
import enemies.LordVoldemort
import enemies.Nagini
import spells.AlbusDumbledore
import spells.HarryPotter
import spells.RonWesley

val harryPotter = HarryPotter("Harry Potter", 400, SpellMaster())
val ronWesley = RonWesley("Ron Wesley", 400, SpellMaster())
val albusDumbledore = AlbusDumbledore("Albus Dumbledore", 400, SpellMaster())

val lordVoldemort = LordVoldemort("Lord Voldemort", 600)
val nagini = Nagini("Nagini", 300)

var heroes: MutableList<Wizard> = mutableListOf(harryPotter, ronWesley, albusDumbledore)
var enemies: MutableList<DarkMage> = mutableListOf(lordVoldemort, nagini)

var gameOver = false
var round = 1
var naginiBonusAttack = false
var naginiSnakeBite = false

fun main() {
    playGameMusic()
    printGameTitle()
    startBattle(heroes, enemies)
}
