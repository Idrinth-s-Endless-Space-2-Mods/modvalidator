# Mod validator

Download the jar of the latest release to use.

## Validations

### Schema Validation

The Tool will go through every xml file in a mod and validate this according to the schema stated in it. This finds typos and is usually stricter than necessary, but builds a basis on what a mod could even be considered valid.

### Logic Validation

Going through the base game files and the selected mod, a list of properties and modifiers is kept. Comparing those, it is checked that no unknown properties are modified. This prevents effects from unexpectedly not showing up because a property was slightly differently typed.
