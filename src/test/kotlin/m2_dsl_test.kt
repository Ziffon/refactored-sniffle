
package ru.otus.okb.first


import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import ru.otus.okb.first.NewDsl as NewDsl1


class NewDsl{
    @Test
    fun NewDsl() {
        val VehicleType = IVehicle {
            Mark = "Toyota"
            //Model = "1"
            //HorsePower = 1
            TypicVehicle{
                Mark = "Toyota"
                Type = "Car"
                Model = "Camry"
                HorsePower = 140

                TypicCar{
                    Mark = "Toyota"
                    Type = "Sport Car"
                    Model = "Supra"
                    HorsePower = 600
                }
                TypicCar{
                    Mark = "Toyota"
                    Type = "PremiumCar"
                    Model = "Crown"
                    HorsePower = 220
                }
            }
            TypicVehicle{
                Mark = "Toyota"
                Type = "Truck"
                Model = "Tundra"
                HorsePower = 360
            }
            TypicVehicle{
                Mark = "Toyota"
                Type = "Bus"
                Model = "Alphard"
                HorsePower = 150

                TypicCar{
                    Mark = "Toyota"
                    Type = "MiniBus"
                    Model = "Hiace"
                    HorsePower = 120
                }
            }

        }
        assertEquals("Toyota", VehicleType.Mark)
        //assertTrue("Toyota must contain Bus") {
          //VehicleType.Transporting.first{ it.Type == "Bus"} != null
        //}


    }


}

private fun IVehicle(function: VehicleTypeDsl.() -> Unit): Vehicle = VehicleTypeDsl().apply (function).build()

abstract class VehicleDsl {
    var Mark: String = ""
    var Model: String = ""
    var Type: String = ""
    var HorsePower: Int = 0
    var Transporting: MutableList<Vehicles> = mutableListOf()


    fun add(veh:Vehicles){
        Transporting.add(veh)
    }
    fun add(vehd: VehicleDsl){
        add(vehd.build())

    }

    abstract fun build():Vehicles



}

class VehicleTypeDsl: VehicleDsl() {
    override fun build(): Vehicle = Vehicle(Mark, Model, Type, HorsePower, Transporting.toList())
    fun TypicVehicle(veh: Vehicles) = add(veh)
    fun TypicVehicle (block: TypicVehicleDsl.() -> Unit ) = add(TypicVehicleDsl().apply{block})




}

class TypicVehicleDsl: VehicleDsl() {
    override fun build(): TypicCars = TypicCars(Mark, Model, Type, HorsePower, Transporting.toList())
    fun TypicCar(veh: Vehicles) = add(veh)
    fun TypicCar (block: TypicVehicleDsl.() -> Unit) = add(TypicCarDsl().apply {block})

}

class TypicCarDsl: VehicleDsl() {
    override fun build(): Car = Car(Mark, Model, Type, HorsePower, Transporting.toList())

}


sealed interface IVehicles
sealed class Vehicles(
    val Mark: String,
    val Model: String,
    val Type: String,
    val HorsePower: Int,
    val Transporting: List<Vehicles> = emptyList()
)
class Vehicle (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)
class Car (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)
class SportCar (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)
class Truck (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)
class Bus (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)
class MiniBus (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)
class TypicCars (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)
class TypicVehiclles (Mark: String, Model: String, Type: String, HorsePower: Int, Transporting: List<Vehicles> = emptyList()):Vehicles(Mark, Model, Type, HorsePower, Transporting)






