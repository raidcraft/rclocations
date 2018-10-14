# RCLocations

Dieses Plugin ermöglicht es in Quests und dem `locations` Ordner bekannte Locations in Dateien festzuhalten. Diese können dann später in Actions mit ihrem Namen referenziert werden.
Kommt es dazu, dass man die Koordinate anpassen muss, muss man nur eine Datei anpassen. Außerdem werden Quests dadurch leserlicher.

## .location.yml

In dieser Datei kann man eine einzelne Location zu einem Alias zusammenfassen. Dabei ist der eindeutige Name der Location eine Zusammensetzung aus dem Pfad zur Datei und dem Dateinamen.

Eine Location unterhalb des `locations` Ordner: `locations/ankanor/tianbaum.location.yml` hätte damit den eindeutigen Namen `ankanor.tianbaum`. 

Diese Datei kann dann in Quests verwendet werden: `!teleport ankanor.tianbaum`

```yml
x: 1
y: 2
z: 3
world: world
# Folgende Werte sind optional.
pitch: 22
yaw: 0
# Optionaler radius für die location wenn man den in einer action checken möchte
radius: 3
```

## .locations.yml

Zusätzlich zu den `.location.yml` Dateien kann man `.locations.yml` Dateien anlegen. In dieser Datei kann man mehrere Locations auf einmal zusammen fassen. Dabei wird der Key dem eindeutigen Pfad mit einem `.` angehängt.

`locations\tianbaum.locations.yml`

```yml
eingang-oben:
  x: 1
  y: 2
  z: 3
  radius: 5
  # analog einer .location.yml Datei
eingang-unten:
  ...
```

Das kann dann wie folgt referenziert werden: `tianbaum.eingang-oben`.

## Verwendung in Quests

Wie viele andere Dateien können beide Datei-Typen in Quests verwendet werden. Dabei kann dann auch die `this.*` Annotation verwendet werden.