# 🌙 Poslední směna

**Poslední směna** je textová adventura vytvořená v jazyce Java, vyvíjená jako studentský projekt. Hráč se v ní ocitá v roli pracovníka během jeho poslední noční směny, která se nečekaně zvrtne. Cílem hry je prozkoumávat okolí, sbírat užitečné předměty, komunikovat s ostatními postavami, čelit nepřátelům a úspěšně přežít až do rána.

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) 
![Gson](https://img.shields.io/badge/Gson-JSON-8A2BE2?style=for-the-badge)

## 🎮 Ovládání (Příkazy)
Hra je plně ovládána prostřednictvím textové konzole zadáváním příkazů. Níže je uveden přehled všech dostupných akcí:

| Příkaz | Popis |
| :--- | :--- |
| `jdi <směr>` | Přesun do jiné lokace (dostupné směry: `sever`, `jih`, `vychod`, `zapad`). |
| `rozhledni se` | Vypíše detailní informace o aktuální lokaci, dostupných předmětech, postavách a možných východech. |
| `prozkoumej <objekt>` | Zobrazí podrobný popis konkrétního předmětu nebo postavy. |
| `vezmi <předmět>` | Sebere předmět z aktuální lokace a přidá jej do inventáře. |
| `poloz <předmět>` | Odstraní předmět z inventáře a zanechá jej v aktuální lokaci. |
| `inventar` | Vypíše aktuální obsah hráčova inventáře. |
| `pouzij <předmět>` | Použije specifikovaný předmět (např. klíč k odemčení dveří). |
| `mluv <postava>` | Zahájí rozhovor s vybranou postavou (NPC). |
| `utok <cíl>` | Zahájí souboj s nepřítelem. |
| `kryj se` | Obranná mechanika v boji, která snižuje obdržené poškození. |
| `prebit` | Doplní munici do zbraně (vyžaduje náboje v inventáři). |
| `napoveda` | Zobrazí seznam všech dostupných příkazů a základní nápovědu přímo ve hře. |
| `konec` | Okamžitě ukončí probíhající hru. |

## ⚙️ Herní mechaniky
- **Průzkum a správa inventáře:** Hráč musí logicky procházet mapou, nacházet klíčové předměty a spravovat svůj omezený inventář pro úspěšný postup hrou.
- **Tahový soubojový systém:** Při setkání s nepřáteli hra přechází do tahového režimu. Hráč musí strategicky volit mezi útokem, obranou (`kryj se`) a správou munice (`prebit`).
- **Dialogový systém:** Komunikace s NPC postavami slouží k odhalování příběhového pozadí a získávání nápověd či předmětů.
- **Data-driven architektura:** Celý herní svět (lokace, předměty, postavy) je dynamicky načítán ze souboru `gamedata.json` pomocí knihovny **Gson**. Tento přístup umožňuje snadnou rozšiřitelnost a úpravu hry bez nutnosti zásahu do zdrojového kódu (Java tříd).

## 🚀 Instalace a spuštění
Pro úspěšnou kompilaci a spuštění hry je vyžadováno **Java Development Kit (JDK) verze 17 nebo novější** a knihovna Gson pro zpracování JSON dat.

### Spuštění přes IDE (Doporučeno)
1. Naklonujte tento repozitář nebo stáhněte projekt do svého počítače.
2. Otevřete projekt ve vývojovém prostředí (např. IntelliJ IDEA, Eclipse).
3. Přidejte knihovnu `gson` do závislostí projektu (Dependencies).
4. Ujistěte se, že konfigurační soubor `gamedata.json` je umístěn ve správném adresáři (zpravidla v kořenovém adresáři projektu nebo ve složce `res`/`resources`), aby jej program mohl bez problémů načíst.
5. Spusťte hlavní třídu `Main.java` ze složky `src`.

### Spuštění přes příkazovou řádku
Pokud preferujete spuštění přes terminál, můžete využít následující příkazy (předpokládá se spuštění v kořenovém adresáři projektu):

```bash
# Kompilace zdrojových kódů
javac -d out -cp "lib/gson.jar" src/**/*.java

# Spuštění hry
java -cp "out;lib/gson.jar" Main
```
*(Poznámka: Cesta ke knihovně `gson.jar` a oddělovače cest se mohou lišit v závislosti na vašem operačním systému)*
