package com.itera.springbootwebexample.services

import com.itera.springbootwebexample.models.Thingy
import org.springframework.stereotype.Service

@Service
class ThingyService {

    // Super effective data store :)
    private val thingies = mutableListOf(
        Thingy(id = 1, name = "Thingy 1"),
        Thingy(id = 2, name = "Thingy 2"),
        Thingy(id = 3, name = "Thingy 3"),
        Thingy(id = 4, name = "Thingy 4"),
    )

    fun addThingy(thingy: Thingy): Thingy? = if (oneThingy(thingy.id) == null) {
        thingies.add(thingy)
        thingy
    } else {
        null
    }

    fun allThingies() = thingies.toList()

    fun deleteThingy(id: Int): Thingy? {
        val thingy = oneThingy(id)

        return if (thingy != null) {
            thingies.remove(thingy)
            thingy
        } else {
            null
        }
    }

    fun oneThingy(id: Int): Thingy? = thingies.find { t -> t.id == id }
}
