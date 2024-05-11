open class DarkMage(var name: String, var hp: Int, var isDead: Boolean = false) {
    //Ist eine Methode die eine Random Attacke ausführt
    fun mysterySpell(auf: Wizard) {
        if (auf.hp > 0) {
            val angriffListe = listOf(
                { fireAttack(auf)},
                { deathCurse(auf)},
                { darkMagicStrike(auf)},
            )
            val mysterySpell = angriffListe.random()
            mysterySpell()
        }
    }

    //Eine Attacke die Schaden verursacht
    private fun fireAttack(ziel: Wizard) {
        val fireAttack = 150
        if (ziel.hp > 0) {
            println("$name hat Attacke Feuersturm gegen ${ziel.name} angewendet. Schaden: $fireAttack punkte")
            ziel.hp -= fireAttack
            println("${ziel.name} hat noch ${ziel.hp} lebenspunkte")
        }
    }

    //Eine Attacke die Schaden verursacht
    private fun deathCurse(ziel: Wizard) {
        val deathCurse: Int = 150
        if (ziel.hp > 0) {
            println("$name hat Attacke Fluch des Todes gegen ${ziel.name} angewendet. Schaden: $deathCurse punkte")
            ziel.hp -= deathCurse
            println("${ziel.name} hat noch ${ziel.hp} lebenspunkte")
        }
    }

    //Ist eine Methode
    fun callNagini() {
        if (hp <= 100) {
            println("Nagini kämpft nun an deiner Seite")
        } else {
            println("$name kann Nagini noch nicht rufen, weil die lebenspunkte noch zu hoch sind ")
        }

    }

    //Eine Attacke die Schaden verursacht
    private fun darkMagicStrike(ziel: Wizard) {
        val darkMagicStrike: Int = 150
        if (ziel.hp > 0) {
            println("$name hat Attacke Dementorenattacke gegen ${ziel.name} angewendet. Schaden: $darkMagicStrike punkte")
            ziel.hp -= darkMagicStrike
            println("${ziel.name} hat noch ${ziel.hp} lebenspunkte")
        }
    }

    //Die Attacke greift alle Zauber an und verursacht schaden
    //Attacke fügt allen Zauber schaden zu
    fun getAreaSpell(ziel1: Wizard, ziel2: Wizard, ziel3: Wizard) {
        val areaSpell: Int = 100
        if (ziel1.hp > 0 || ziel2.hp > 0 || ziel3.hp > 0) {
            println("$red$name hat Attacke Flächenzauber angewendet. Schaden: $areaSpell punkte$reset")
            ziel1.hp -= areaSpell
            ziel2.hp -= areaSpell
            ziel3.hp -= areaSpell
            println("$red${ziel1.name} hat $areaSpell Schaden erhalten und hat noch ${ziel1.hp} lebenspunkte")
            println("${ziel2.name} hat $areaSpell Schaden erhalten und hat noch ${ziel2.hp} lebenspunkte")
            println("${ziel3.name} hat $areaSpell Schaden erhalten und hat noch ${ziel3.hp} lebenspunkte$reset")
        } else if (ziel1.hp == 0 || ziel2.hp == 0 || ziel3.hp == 0) {
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