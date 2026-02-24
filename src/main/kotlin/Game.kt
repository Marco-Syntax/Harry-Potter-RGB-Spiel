import actions.*

fun startBattle(heroes: MutableList<Wizard>, enemies: MutableList<DarkMage>) {
    while (!gameOver) {
        printRoundStatus(heroes, enemies)

        val poisonedHero = heroes.random()

        executeHeroAttacks(heroes, poisonedHero)
        if (gameOver) break

        executeEnemyAttacks(heroes)
        if (gameOver) break

        executeNaginiBonusPhase(poisonedHero)
        if (gameOver) break

        executePotionPhase(heroes)
        executeSpecialAttackPhase()

        round++
    }

    println("Das Spiel ist vorbei!")
}

private fun printRoundStatus(heroes: MutableList<Wizard>, enemies: MutableList<DarkMage>) {
    println("         ----Runde: $round----")
    println()
    println("$blue-----Dein Team-----$reset")
    heroes.forEach { println("$blue${it.name} – Lebenspunkte: ${it.hp}$reset") }
    println()
    Thread.sleep(2000)
    println("$red------Gegner Team-----$reset")
    enemies.forEach { println("$red${it.name} – Lebenspunkte: ${it.hp}$reset") }
    Thread.sleep(2000)
}

private fun executeHeroAttacks(heroes: MutableList<Wizard>, poisonedHero: Wizard) {
    for (hero in heroes) {
        println("$blue${hero.name} startet seinen Angriff$reset")

        if (hero.hp <= 0) {
            println("${hero.name} hat 0 Lebenspunkte und kann nicht angreifen.")
            continue
        }

        if (lordVoldemort.hp == 0 && nagini.hp > 0 && !nagini.isDead) {
            println("${nagini.name} wird jetzt angegriffen")
            hero.attack(nagini)
            if (nagini.hp <= 0) {
                nagini.isDead = true
                gameOver = true
            }
        } else {
            hero.attack(lordVoldemort)
        }

        poisonedHero.inflictPoison()

        if (checkVictory() || checkDefeat()) break
    }
}

private fun checkVictory(): Boolean {
    val allEnemiesDefeated = (lordVoldemort.hp <= 0 && nagini.hp <= 0)
            || (lordVoldemort.isDead && nagini.isDead)

    if (allEnemiesDefeated) {
        printVictoryBanner()
        gameOver = true
        return true
    }
    return false
}

private fun checkDefeat(): Boolean {
    if (harryPotter.hp <= 0 && ronWesley.hp <= 0 && albusDumbledore.hp <= 0) {
        println("Das Team der Dunklen Magie hat gewonnen!")
        gameOver = true
        return true
    }
    return false
}

private fun executeEnemyAttacks(heroes: MutableList<Wizard>) {
    when {
        lordVoldemort.hp > 0 -> {
            println()
            println("$red${lordVoldemort.name} startet seinen Angriff$reset")
            lordVoldemort.mysterySpell(heroes.random())
        }
        lordVoldemort.hp == 0 && nagini.hp > 0 -> {
            lordVoldemort.isDead = true
            nagini.mysterySpell(heroes.random())
        }
        nagini.hp <= 0 -> {
            nagini.isDead = true
        }
    }
}

private fun executeNaginiBonusPhase(poisonedHero: Wizard) {
    if (lordVoldemort.hp <= 0 && !naginiBonusAttack) {
        lordVoldemort.isDead = true
        Thread.sleep(2000)
        println()
        println("$red${lordVoldemort.name} ist gestorben und hat Nagini herbeigerufen.")
        println("$red Nagini führt eine Bonusattacke (Flächenzauber) aus und verletzt alle Zauberer.$reset")
        println()
        nagini.getAreaSpell(harryPotter, ronWesley, albusDumbledore)
        Thread.sleep(2000)
        println()
        naginiBonusAttack = true

    } else if (lordVoldemort.hp == 300 && !gameOver) {
        println()
        Thread.sleep(2000)
        println("${lordVoldemort.name} wurde schwer verletzt und ruft Nagini zur Hilfe. Sie beißt zu!")
        nagini.applySnakeAttack(poisonedHero)
        printSnakeAsciiArt()
        naginiSnakeBite = true
        println()
        Thread.sleep(1000)
        poisonedHero.isPoisoned = true
        poisonedHero.inflictPoison()
    }

    if (naginiSnakeBite && !gameOver) {
        poisonedHero.useElixir()
    }
}

private fun executePotionPhase(heroes: MutableList<Wizard>) {
    val needsHealing = heroes.any { it.hp in 1..200 }
    if (!needsHealing || gameOver) return

    val healTarget = heroes.filter { it.hp > 0 }.random()
    Thread.sleep(2000)
    println("${yellow}Ein lebender Zauberer wird für eine Heilung ausgewählt: ${healTarget.name}$reset")
    healTarget.bagPotion()
    println()
}

private fun executeSpecialAttackPhase() {
    if (!naginiBonusAttack || nagini.hp <= 0) return

    when {
        harryPotter.hp > 200 && !harryPotter.attackBonus -> {
            Thread.sleep(2000)
            println("$yellow${harryPotter.name} darf jetzt seine Spezialattacke Fliegen benutzen$reset")
            printBroomAsciiArt()
            harryPotter.broomAttack(nagini)
            harryPotter.attackBonus = true
            println()
            Thread.sleep(2000)
        }
        ronWesley.hp > 200 && !ronWesley.attackBonus -> {
            println()
            Thread.sleep(2000)
            println("$yellow Ron Wesley darf seine Spezialattacke anwenden und ruft seine Ratte Krätze$reset")
            printRatAsciiArt()
            ronWesley.ratAttack(nagini)
            Thread.sleep(2000)
            ronWesley.attackBonus = true
        }
        albusDumbledore.hp > 200 && !albusDumbledore.attackBonus -> {
            println()
            Thread.sleep(2000)
            println("$yellow Albus Dumbledore darf seine Spezialattacke anwenden und ruft seinen Phönix Fawkes$reset")
            printPhoenixAsciiArt()
            albusDumbledore.phoenixStrike(nagini)
            Thread.sleep(2000)
            albusDumbledore.attackBonus = true
        }
    }
}

// --- ASCII Art Helpers ---

private fun printVictoryBanner() {
    println(
        "$magenta _____    ___    __  __ ____   ______ ____                       __  __ ___     ____   ______ _   __   \n" +
                "/__  /   /   |  / / / // __ ) / ____// __ \\                     / / / //   |   / __ ) / ____// | / /   \n" +
                "  / /   / /| | / / / // __  |/ __/  / /_/ /                    / /_/ // /| |  / __  |/ __/  /  |/ /    \n" +
                " / /__ / ___ |/ /_/ // /_/ // /___ / _, _/                    / __  // ___ | / /_/ // /___ / /|  /     \n" +
                "/____//_/  |_|\\____//_____//_____//_/ |_|                    /_/ /_//_/  |_|/_____//_____//_/ |_/      \n" +
                "                       ______ ______ _       __ ____   _   __ _   __ ______ _   __                     \n" +
                "                      / ____// ____/| |     / // __ \\ / | / // | / // ____// | / /                     \n" +
                "                     / / __ / __/   | | /| / // / / //  |/ //  |/ // __/  /  |/ /                      \n" +
                "                    / /_/ // /___   | |/ |/ // /_/ // /|  // /|  // /___ / /|  /                       \n" +
                "                    \\____//_____/   |__/|__/ \\____//_/ |_//_/ |_//_____//_/ |_/   $reset"
    )
}

private fun printSnakeAsciiArt() {
    println(
        "$green           /^\\/^\\\n" +
                "         _|__|  O|\n" +
                "\\/     /~     \\_/ \\\n" +
                " \\____|__________/  \\\n" +
                "        \\_______      \\\n" +
                "                `\\     \\                 \\\n" +
                "                  |     |                  \\\n" +
                "                 /      /                    \\\n" +
                "                /     /                       \\\\\n" +
                "              /      /                         \\ \\\n" +
                "             /     /                            \\  \\\n" +
                "           /     /             _----_            \\   \\\n" +
                "          /     /           _-~      ~-_         |   |\n" +
                "         (      (        _-~    _--_    ~-_     _/   |\n" +
                "          \\      ~-____-~    _-~    ~-_    ~-_-~    /\n" +
                "            ~-_           _-~          ~-_       _-~\n" +
                "               ~--______-~                ~-___-~\n$reset"
    )
}

private fun printBroomAsciiArt() {
    println(
        "            _            _.,----,\n" +
                " __  _.-._ / '-.        -  ,._  \\) \n" +
                "|  `-)_   '-.   \\       / < _ )/\" }\n" +
                "/__    '-.   \\   '-, ___(c-(6)=(6)\n" +
                " , `'.    `._ '.  _,'   >\\    \"  )\n" +
                " :;;,,'-._   '---' (  ( \"/`. -='/\n" +
                ";:;;:;;,  '..__    ,`-.`)'- '--'\n" +
                ";';:;;;;;'-._ /'._|   Y/   _/' \\\n" +
                "      '''\"._ F    |  _/ _.'._   `\\\n" +
                "             L    \\   \\/     '._  \\\n" +
                "      .-,-,_ |     `.  `'---,  \\_ _|\n" +
                "      //    'L    /  \\,   (\"--',=`)7\n" +
                "     | `._       : _,  \\  /'`-._L,_'-._\n" +
                "     '--' '-.\\__/ _L   .`'         './/\n" +
                "                 [ (  /\n" +
                "                  ) `{\n" +
                "                  \\__)"
    )
}

private fun printRatAsciiArt() {
    println(
        "      ,::////;::-.\n" +
                "      /:'///// ``::>/|/\n" +
                "    .',  ||||    `/( e\\\n" +
                "-==~-'`-Xm````-mm-' `-_\\"
    )
}

private fun printPhoenixAsciiArt() {
    println(
        "                   /T /I          \n" +
                "                              / |/ | .-~/    \n" +
                "                          T\\ Y  I  |/  /  _  \n" +
                "         /T               | \\I  |  I  Y.-~/  \n" +
                "        I l   /I       T\\ |  |  l  |  T  /   \n" +
                " __  | \\l   \\l  \\I l __l  l   \\   `  _. |    \n" +
                " \\ ~-l  `\\   `\\  \\  \\\\ ~\\  \\   `. .-~   |    \n" +
                "  \\   ~-. \"-.  `  \\  ^._ ^. \"-.  /  \\   |    \n" +
                ".--~-._  ~-  `  _  ~-_.-\"-.\" ._ /._ .\" ./    \n" +
                " >--.  ~-.   ._  ~>-\"    \"\\\\   7   7   ]     \n" +
                "^.___~\"--._    ~-{  .-~ .  `\\ Y . /    |     \n" +
                " <__ ~\"-.  ~       /_/   \\   \\I  Y   : |\n" +
                "   ^-.__           ~(_/   \\   >._:   | l______     \n" +
                "       ^--.,___.-~\"  /_/   !  `-.~\"--l_ /     ~\"-.  \n" +
                "              (_/ .  ~(   /'     \"~\"--,Y   -=b-. _) \n" +
                "               (_/ .  \\  :           / l      c\"~o \\\n" +
                "                \\ /    `.    .     .^   \\_.-~\"~--.  ) \n" +
                "                 (_/ .   `  /     /       !       )/  \n" +
                "                  / / _.   '.   .':      /        ' \n" +
                "                  ~(_/ .   /    _  `  .-<_      -Row\n" +
                "                    /_/ . ' .-~\" `.  / \\  \\          ,z=.\n" +
                "                    ~( /   '  :   | K   \"-.~-.______//\n" +
                "                      \"-,.    l   I/ \\_    __{--->._(==.\n" +
                "                       //(     \\  <    ~\"~\"     //\n" +
                "                      /' /\\     \\  \\     ,v=.  ((\n" +
                "                    .^. / /\\     \"  }__ //===-  `\n" +
                "                   / / ' '  \"-.,__ {---(==-\n" +
                "                 .^ '       :  T  ~\"   ll\n" +
                "                / .  .  . : | :!        \\\\ \n" +
                "               (_/  /   | | j-\"          ~^\n" +
                "                 ~-<_(_.^-~\"               "
    )
}
