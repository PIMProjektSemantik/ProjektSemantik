# ProjektSemantik

## Einrichtung git

1. Macht euch einen GitHub-Account auf [https://github.com/](https://github.com/)

2. Installiert git auf eurem PC: [http://git-scm.com/downloads](http://git-scm.com/downloads)

3. Öffnet die Eingabeaufforderung und setzt folgende Kommandos ab (bitte gleiche E-Mail-Adresse wie die eures GitHub-Accounts):

	```shell
	git config --global user.name "YOUR NAME"
	```

	```shell
	git config --global user.email "YOUR EMAIL ADDRESS"
	```

4. Schreibt mir euren GitHub-Benutzernamen, damit ich euch zum Team hinzufügen kann.

## Einrichtung unseres Repositorys

1. Erstellt euch auf eurem Computer einen stinknormalen Ordner. Meiner heißt "Projekt Semantik"

2. Bewegt euch mit der Kommandozeile da hinein, sodass ihr euch **in** diesem Ordner befindet

3. Um hier ein Git-Repo zu initialisieren:

	```shell
	git init
	```

4. Um unser Repo als Online-Repo zu setzen:

	```shell
	git remote add origin https://github.com/PIMProjektSemantik/ProjektSemantik.git
	```

5. Um euch den aktuellsten Stand runterzuladen:

	```shell
	git pull origin master
	```

## Grundlegende Git-Befehle und Operationen

### Eigene Version vom Server aktualisieren (Pull)

Bevor ihr an etwas arbeitet, solltet ihr euch den neusten Stand vom Online-Repository besorgen. Das geht so:

```shell
git pull origin master
```

### Server von eigener Version aktualisieren (Commit & Push)

Der Arbeitsablauf ist so, dass ihr Änderungen lokal als neue Version definiert (Commit) und sie, wenn ihr fertig seid, auf den Server schiebt (Push). Wenn ihr etwas korrigiert / ein neues Feature implementiert oder eine neue Datei angelegt habt, macht ihr folgendes:

Anzeigen, was lokal geändert wurde:

```shell
git status
```

Eine geänderte oder neue Datei als Teil des nächsten Commits definieren:

```shell
git add "NEUE_ODER_GEÄNDERTE_DATEI"
```

Wenn ihr dafür zu faul seid, geht auch ein:

```shell
git add  *
```

Nachdem ihr nun alle geänderten Files dem nächsten Commit hinzugefügt habt, macht ihr ein:

```shell
git commit
```

Nun seht ihr ein Fensterchen, wo alle geaddeten Dateien drinstehen. Dort beschreibt ihr, was in diesem Commit geschehen ist. Im Falle des VI (ist glaube ich der Standardeditor):

1. Mit Pfeiltasten nach unten zur noch leeren Nachricht navigieren
2. Einfügemodus mit "SHIFT + i" starten
3. Nachricht schreiben
4. Einfügemodus mit "ESC" beenden
5. Nachricht mit ":wq + ENTER" speichern

Danach ist der Commit erledigt. Ihr könnt danach auch beliebig viele weitere Commits machen und sie erst z.B. am Ende des Tages auf das Online-Repo schieben. Das geht so:

```shell
git push origin master
```

Ihr werden nach eurem GitHub-Namen sowie -Passwort gefragt, gebt das ein, drückt Enter und im Browser könnt ihr nun sehen, dass in der Commit-Liste alle neuen Commits drinstehen.

## Probleme

Bei Fragen, Problemen oder Verbesserungen dieses Ablaufs bitte mich oder Steffen kontaktieren. Obige Lösung ist meine Art, Git und GitHub zu benutzen. Habe mir das aber erst vor kurzem selber beigebracht, das geht bestimmt noch besser ;).