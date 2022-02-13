/**

package ru.otus.okb.first

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KotlinDslTest{
    @Test
    fun KotlinDslTest(){

        val solarSysytem = star {
            name = "Sun"
            mass = 1e12
            planet{
                name = "Mercury"
                mass =1e5
            }
            planet{
                name = "Venus"
                mass = 1e6
            }
            planet{
                name = "Earth"
                mass = 1e6

                satellite{
                    name = "Moon"
                    mass = 1e4
                }

            }
        }
        assertEquals("Sun", solarSysytem.name)
        assertTrue("Sun must contains Earth") {
            solarSysytem.children.first{it.name == "Earth"} !=null
        }


    }
}

private fun star(function:StarDsl.() -> Unit): Star = StarDsl().apply(function).build()

abstract class SpaceObjectDsl {
    var name: String = ""
    var mass: Double = 0.0
    var children: MutableList<SpaceObject> = mutableListOf()

    fun add(so: SpaceObject){
        children.add(so)
    }

    fun add(sod: SpaceObjectDsl){
        add(sod.build())
    }

    abstract fun build() : SpaceObject
}

class StarDsl: SpaceObjectDsl() {
    override fun build(): Star = Star(name, mass, children.toList())
    fun planet(so: Planet) = add(so)
    fun planet(block: PlanetDsl.() -> Unit ) = add(PlanetDsl().apply { block })


}

class PlanetDsl: SpaceObjectDsl() {
    override fun build(): Planet = Planet(name, mass, children.toList())
    fun satellite(so: Planet) = add(so)
    fun satellite(block: PlanetDsl.() -> Unit ) = add(SatelliteDsl().apply { block })

}


class SatelliteDsl: SpaceObjectDsl() {
    override fun build(): Satellite = Satellite(name, mass, children.toList())

}

sealed interface ISpaceObject
sealed class SpaceObject(
    val name: String,
    val mass: Double,
    val children:List<SpaceObject> = emptyList()
)
class Galaxy(name: String, mass: Double,children: List<SpaceObject> = emptyList()): SpaceObject(name, mass, children)
class Star(name: String, mass: Double,children: List<SpaceObject> = emptyList()): SpaceObject(name, mass, children)
class Planet(name: String, mass: Double,children: List<SpaceObject> = emptyList()): SpaceObject(name, mass, children)
class Satellite(name: String, mass: Double,children: List<SpaceObject> = emptyList()): SpaceObject(name, mass, children)
class Asteroid(name: String, mass: Double,children: List<SpaceObject> = emptyList()): SpaceObject(name, mass, children)
**/