package social.data.character.trait

typealias TraitUpdater = (Set<Trait>) -> Set<Trait>

fun addTrait(trait: Trait): TraitUpdater = { it + trait }

fun removeTrait(trait: Trait): TraitUpdater = { it - trait }