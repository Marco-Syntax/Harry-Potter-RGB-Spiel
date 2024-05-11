package actions

open class DarkMage(var name: String, var hp: Int, var isDead: Boolean = false) {
    //Ist eine Methode die eine Random Attacke ausführt
    fun mysterySpell(auf: Wizard) {
        if (auf.hp > 0) {
            val attackList = listOf(
                { fireAttack(auf)},
                { deathCurse(auf)},
                { darkMagicStrike(auf)},
            )
            val mysterySpell = attackList.random()
            mysterySpell()
        }
    }

    //Eine Attacke die Schaden verursacht
    private fun fireAttack(target: Wizard) {
        val fireAttack = 150
        if (target.hp > 0) {
            println("$name hat Attacke Feuersturm gegen ${target.name} angewendet. Schaden: $fireAttack punkte")
            target.hp -= fireAttack
            println("${target.name} hat noch ${target.hp} lebenspunkte")
        }
    }

    //Eine Attacke die Schaden verursacht
    private fun deathCurse(target: Wizard) {
        val deathCurse: Int = 150
        if (target.hp > 0) {
            println("$name hat Attacke Fluch des Todes gegen ${target.name} angewendet. Schaden: $deathCurse punkte")
            target.hp -= deathCurse
            println("${target.name} hat noch ${target.hp} lebenspunkte")
        }
    }

    //Ist eine Methode
    fun callNagini() {
        if (hp <= 100) {
            println("Enemies.Nagini kämpft nun an deiner Seite")
        } else {
            println("$name kann Enemies.Nagini noch nicht rufen, weil die lebenspunkte noch zu hoch sind ")
        }

    }

    //Eine Attacke die Schaden verursacht
    private fun darkMagicStrike(target: Wizard) {
        val darkMagicStrike: Int = 150
        if (target.hp > 0) {
            println("$name hat Attacke Dementorenattacke gegen ${target.name} angewendet. Schaden: $darkMagicStrike punkte")
            target.hp -= darkMagicStrike
            println("${target.name} hat noch ${target.hp} lebenspunkte")
        }
    }

    //Die Attacke greift alle Zauber an und verursacht schaden
    //Attacke fügt allen Zauber schaden zu
    fun getAreaSpell(target1: Wizard, target2: Wizard, target3: Wizard) {
        val areaSpell: Int = 100
        if (target1.hp > 0 || target2.hp > 0 || target3.hp > 0) {
            println("$red$name hat Attacke Flächenzauber angewendet. Schaden: $areaSpell punkte$reset")
            target1.hp -= areaSpell
            target2.hp -= areaSpell
            target3.hp -= areaSpell
            println("$red${target1.name} hat $areaSpell Schaden erhalten und hat noch ${target1.hp} lebenspunkte")
            println("${target2.name} hat $areaSpell Schaden erhalten und hat noch ${target2.hp} lebenspunkte")
            println("${target3.name} hat $areaSpell Schaden erhalten und hat noch ${target3.hp} lebenspunkte$reset")
        } else if (target1.hp == 0 || target2.hp == 0 || target3.hp == 0) {
            println(" Zauber ist schon besiegt")
        }

    }

    //Hilfsfunktion
    open fun getDarkDamage(darkDamage: Int) {
        hp -= darkDamage
        if (hp <= 0) {
            hp = 0
            println("$red${this.name} wurde besiegt ")
        } else {
            println("$red$name hat $darkDamage Schaden erhalten. Aktuelle HP: $hp$reset")
        }
    }
}