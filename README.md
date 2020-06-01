# Mod validator

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/ccee5c155dcb4c41af5a7444e5d378bf)](https://www.codacy.com/gh/Idrinth-s-Endless-Space-2-Mods/modvalidator?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Idrinth-s-Endless-Space-2-Mods/modvalidator&amp;utm_campaign=Badge_Grade)

Download the jar of the latest release to use.

## Validations

### Schema Validation

The Tool will go through every xml file in a mod and validate this according to the schema stated in it. This finds typos and is usually stricter than necessary, but builds a basis on what a mod could even be considered valid.

### Logic Validation

Going through the base game files and the selected mod, a list of properties and modifiers is kept. Comparing those, it is checked that no unknown properties are modified. This prevents effects from unexpectedly not showing up because a property was slightly differently typed.
