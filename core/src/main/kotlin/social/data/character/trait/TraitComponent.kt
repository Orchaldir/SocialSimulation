package social.data.character.trait

data class TraitComponent(private val traits: Set<Trait>) {

    fun hasTrait(trait: Trait) = traits.contains(trait)

}