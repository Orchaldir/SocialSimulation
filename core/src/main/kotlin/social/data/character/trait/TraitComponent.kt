package social.data.character.trait

data class TraitComponent(private val traits: Set<Trait>) {

    constructor(vararg trait: Trait) : this(trait.toSet())

    fun hasTrait(trait: Trait) = traits.contains(trait)

}