fun main() {
    // Zauberer und Gegner erstellt
    val harryPotter: HarryPotter = HarryPotter("Harry Potter", 400, SpellMaster())
    val ronWesley: RonWesley = RonWesley("Ron Wesley", 400, SpellMaster())
    val albusDumbledore: AlbusDumbledore = AlbusDumbledore("Albus Dumbledore", 400, SpellMaster())

    val lordVoldemort: LordVoldemort = LordVoldemort("Lord Voldemort", 600)
    val nagini: Nagini = Nagini("Nagini", 300)

    var helden: MutableList<Wizard> = mutableListOf(harryPotter, ronWesley, albusDumbledore)
    var gegner: MutableList<DarkMage> = mutableListOf(lordVoldemort, nagini)

    val red = "\u001B[31m"
    val green = "\u001B[32m"
    val yellow = "\u001B[33m"
    val blue = "\u001B[34m"
    val magenta = "\u001B[35m"
    val cyan = "\u001B[36m"
    val bold = "\u001B[1m"
    val underline = "\u001B[4m"
    val backgroundYellow = "\u001B[43m"
    val reset = "\u001B[0m"

    //Bedingung für Spiel Ende
    var gameOver: Boolean = false
    // Rundenzähler
    var round: Int = 1
    //Bedingung für Bonus Attacke
    var naginiBonusAttacke = false
    var naginiSchlangenBiss = false


    println(
        "$cyan                                        _ __\n" +
                "        ___                             | '  \\\n" +
                "   ___  \\ /  ___         ,'\\_           | .-. \\        /|\n" +
                "   \\ /  | |,'__ \\  ,'\\_  |   \\          | | | |      ,' |_   /|\n" +
                " _ | |  | |\\/  \\ \\ |   \\ | |\\_|    _    | |_| |   _ '-. .-',' |_   _\n" +
                "// | |  | |____| | | |\\_|| |__    //    |     | ,'_`. | | '-. .-',' `. ,'\\_\n" +
                "\\\\_| |_,' .-, _  | | |   | |\\ \\  //    .| |\\_/ | / \\ || |   | | / |\\  \\|   \\\n" +
                " `-. .-'| |/ / | | | |   | | \\ \\//     |  |    | | | || |   | | | |_\\ || |\\_|\n" +
                "   | |  | || \\_| | | |   /_\\  \\ /      | |`    | | | || |   | | | .---'| |\n" +
                "   | |  | |\\___,_\\ /_\\ _      //       | |     | \\_/ || |   | | | |  /\\| |\n" +
                "   /_\\  | |           //_____//       .||`      `._,' | |   | | \\ `-' /| |\n" +
                "        /_\\           `------'        \\ |          `.\\  | |  `._,' /_\\\n" +
                "                                       \\|     DER MAGISCHE KAMPF           `.\\$reset"
    )
    println()
    println("      ----Der Kampf Beginnt----")
    // Hauptschleife für das Spiel
    while (!gameOver) {
        println("         ----Runde:$round----")
        println()
        println("$yellow-----Dein Team-----$reset")
        helden.forEach { println("$yellow Der Zauber ${it.name} hat Lebenspunkte: ${it.hp}$reset") }
        println()
        println("$red------Gegner Team-----$reset")
        gegner.forEach { println("$red Dein Gegner ist ${it.name} und hat Lebenspunkte: ${it.hp}$reset") }

// Jeder Zauberer in der helden-Liste greift an
        for (zauber in helden) {
            println("${zauber.name} startet seinen Angriff")
            // Überprüfen, ob der Zauberer noch Lebenspunkte hat um anzugreifen
            if (zauber.hp > 0) {
// Wenn Lord Voldemort besiegt wurde, dann Nagini angreifen
                if (lordVoldemort.hp == 0 && nagini.hp > 0 && !nagini.isDead) {
                    println("${nagini.name} wird jetzt angegriffen")
                    zauber.attack(nagini)
                    if (nagini.hp == 0) {
                        nagini.isDead = true
                        gameOver = true
                    }
                } else {
                    zauber.attack(lordVoldemort)
                }
            } else {
                println("${zauber.name} hat 0 Lebenspunkte und kann nicht angreifen.")
            }
            //hier wird das spielende geprüft
            if (lordVoldemort.hp <= 0 && nagini.hp <= 0) {
                println("Die Zauberer haben gewonnen ")
                gameOver = true
                break
            } else if (harryPotter.hp <= 0 && ronWesley.hp <= 0 && albusDumbledore.hp <= 0) {
                println("Das Team der Dunkeln Magie hat gewonnen!")
                gameOver = true
                break
            } else if (lordVoldemort.isDead && nagini.isDead) {
                println("Die Zauberer haben gewonnen!")
                gameOver = true
                break
            }

        }
//Überprüfen, ob Lord Voldemort noch Lebenspunkte hat und Angriff ausführen kann Random Angriff ausführen
        if (lordVoldemort.hp > 0) {
            println()
            println("${lordVoldemort.name} startet seinen Angriff")
            lordVoldemort.mysterySpell(helden.random())
// Wenn Lord Voldemort keine hp mehr hat, dann greift nagini an
        } else if (lordVoldemort.hp == 0 && nagini.hp > 0) {
            lordVoldemort.isDead = true
            nagini.mysterySpell(helden.random())
        } else if (nagini.hp <= 0) {
            nagini.isDead = true
        }
//Wenn Lord Voldemort gestorben ist, ruft er Nagini zur Hilfe und Nagini führt FlächenZauber aus (Bonusattacke) aus die sie einmal ausführt
        if (lordVoldemort.hp <= 0 && !naginiBonusAttacke) {
            println()
            lordVoldemort.isDead = true
            println("$red${lordVoldemort.name} ist gestorben und hat Nagini herbeigerufen.")
            println("$red Nagini taucht auf  führt eine Bonusattacke (Flächenzauber) aus die alle Zauberer verletzt.$reset")
            println()
//Nagini führt ein Flächenzauber aus und fügt allen Zauberern Schaden zu
            nagini.getAreaSpell(harryPotter, ronWesley, albusDumbledore)
            println()
            naginiBonusAttacke = true
//Zusatz Attacke für voldemort, wenn hp 300 sind
        } else if (lordVoldemort.hp == 300 && !gameOver) {
//Hier wird Nagini angreifen, wenn Lord Voldemort noch lebt und 300 hp ist als zusatzattacke
            println()
            println("${lordVoldemort.name} wurde schwer Verletzt und ruft Nagini zur Hilfe. Sie beißt zu!")
            nagini.applySnakeAttack(helden.random())
            naginiSchlangenBiss = true
            println()
        }
        //Wenn der Schlagenbiss true ist, dann wird ein Magisches elixier aufgerufen ein zufälliger Zauberer bekommt einmalig das elixier und die hp wird um 10 % erhöht
        if (naginiSchlangenBiss && !gameOver) {
            helden.random().useElixir()
            //Lambdafunktion verwendet, um den Zugriff auf false zu setzen, bei allen Zauberern
            helden.map { it.elixirAccess = false }
        }

// Wenn die hp unter oder gleich 200 ist, wird einem zufälligen Zauber ein Heiltrank gegeben.
// Der Heiltrank darf nur einmal pro Runde benutzt werden, und wenn ein Zauberer tot ist, bekommt er keinen mehr. wird mit einer range überprüft
        if ((harryPotter.hp in 1..200) || (ronWesley.hp in 1..200) || (albusDumbledore.hp in 1..200) && !gameOver) {
            // Der Beuteltrank wird benutzt und ein zufälliger lebender Zauberer wird ausgewählt mit der Lambdafunktion filter wird überprüft, ob die hp zwischen 1 und 200 liegen
            val zaubererMitHeiltrank: Wizard = helden.filter { it.hp > 0 }.random()
            println("Es wird ein lebender Zauberer ausgewählt, der eine Heilung bekommt: ${zaubererMitHeiltrank.name}")
            zaubererMitHeiltrank.bagPotion()
            println()
        }

//Wenn Nagini ihre Bonusattacke gemacht (true) hat und Ron Wesley seine hp kleiner 300 und Albus Dumbeldore seine hp kleiner 200 sind, darf Harry Potter seine Spezialattacke ausführen
        if (naginiBonusAttacke && nagini.hp > 0 && harryPotter.hp > 200) {
            //Überprüft ob Bonusattacke nicht true ist
            if (!harryPotter.attackBonus) {
                println("$yellow${harryPotter.name} darf jetzt seine Spezialattacke Fliegen benutzen")
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
                harryPotter.broomAttack(nagini)
                harryPotter.attackBonus = true
                println()
            }

            //Wenn Harry Potter seine hp kleiner 300 und Albus Dumbledore hp kleiner 300 sind, darf Ron Wesley seine Spezialattacke ausführen
        } else if (naginiBonusAttacke && nagini.hp > 0 && ronWesley.hp > 200) {
            if (!ronWesley.attackBonus) {
                println()
                println("$yellow Ron Wesley darf seine Spezialattacke anwenden und ruf seine Ratte Krätze")
                println(
                    "      /m'\n" +
                            "      (oo\\\n" +
                            "      / ._)\n" +
                            "     J _=\\=\n" +
                            "     |   /\n" +
                            "3._.' |_+_"
                )
                ronWesley.ratAttack(nagini)
                ronWesley.attackBonus = true
            }
            //Wenn Harry Potter seine hp kleiner 200 und Ron Wesley hp kleiner 200 sind, darf Albus Dumbledore seine Spezialattacke ausführen
        } else if (naginiBonusAttacke && nagini.hp > 0 && albusDumbledore.hp > 200) {
            if (!albusDumbledore.attackBonus) {
                println()
                println("$yellow Albus Dumbledore darf seine Spezialattacke anwenden und ruf seinen Phönix Fakes")
                println(
                    "                              ___,--------,____\n" +
                            "                      __--~~~~                 ~~---,_\n" +
                            "                   ,-'                  __,--,_       `\\,___,-,__\n" +
                            "                ,-'                 __/'/-~~~\\  `  ` . '    , |  `~~\\\n" +
                            "             _/`      _/~~      '~~   \\,_\\_ O /        '  '~_/'      `\\\n" +
                            "           /'        '                   =-'~~  _  /  ~   /'          `\\\n" +
                            "        _/'  /~                            ,--,____,-----|,_,-,_       `\\\n" +
                            "    _,/'    '              ,-'      _      `~'------'~~~~~--    `~~~~\\  |\n" +
                            " ,-'             /~       '    ,-~~~         _,       ,-=~~~~~~~~~~~~'| |\n" +
                            "~              .'             '         ,   '      /~`                |/\n" +
                            "                                  /' ,/'       _/~'\n" +
                            "                   ,       /    /`          _/~ \n" +
                            "        /~        /      /`               /' \n" +
                            "      .'                                /' \n" +
                            "                       /'      .      /\n" +
                            "                      `       /'     | \n" +
                            "                                    '"
                )
                albusDumbledore.phoenixStrike(nagini)
                albusDumbledore.attackBonus = true
            }
        }
        round++
    }
    // Spielende ausgeben
    println("Das Spiel ist vorbei!")
}
