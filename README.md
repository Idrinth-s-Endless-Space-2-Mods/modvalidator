# Mod validator

[![Build Status](https://travis-ci.org/Idrinth-s-Endless-Space-2-Mods/modvalidator.svg?branch=master)](https://travis-ci.org/Idrinth-s-Endless-Space-2-Mods/modvalidator)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/ccee5c155dcb4c41af5a7444e5d378bf)](https://www.codacy.com/gh/Idrinth-s-Endless-Space-2-Mods/modvalidator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Idrinth-s-Endless-Space-2-Mods/modvalidator&amp;utm_campaign=Badge_Grade)

Download the jar of the latest release to use.

## Validations

### Schema Validation

The Tool will go through every xml file in a mod and validate this according to the schema stated in it. This finds typos and is usually stricter than necessary, but builds a basis on what a mod could even be considered valid.

### Logic Validation

Going through the base game files and the selected mod, a list of properties and modifiers is kept. Comparing those, it is checked that no unknown properties are modified. This prevents effects from unexpectedly not showing up because a property was slightly differently typed.

#### Manually added subtypes

Some types do not provide a way to find a parent type, even with that parent obviously existing and providing properties. Manually covered are currently:

- ClassStarSystem is a parent of ClassColonizedStarSystem
- ClassColonizedStarSystem is a parent of ClassExploitedStarSystem
- ClassPlanet is a parent of ClassColonizedPlanet
- ClassGarrison is a parent of ClassGarrisonFleet