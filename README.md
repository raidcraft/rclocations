# RCLocations

Ein kleines Plugin was es ermöglicht in Dateien Locations zu definieren.

## How-To

Um wichtige Locations leichter zu benennen und zusammen zufassen wäre es super wenn man `.location.yml` Dateien angelegen kann.

Diese Datei kann dann in Quests verwendet werden: `!player.teleport this.meine-location`

```yml
x: 1
y: 2
z: 3
pitch: 22
yaw: 0
world: world
# Optionaler radius für die location wenn man den in einer action checken möchte
radius: 3
```

Zusätzlich wäre eine `.locations.yml` Datei cool in der man wie in der `.items.yml` Datei mehrere Locations zusammen fassen kann.

`tianbaum.locations.yml`

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

Das kann dann wie folgt referenziert werden: `this.tianbaum.eingang-oben`