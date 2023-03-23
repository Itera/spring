package no.itera.springbootwebexample.resources

import no.itera.springbootwebexample.models.Thingy
import no.itera.springbootwebexample.services.ThingyService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

// Note - the messages for ResponseStatusException will only be in the response if we set
// server.error.include-message property

@RestController
class ThingyResource(val thingyService: ThingyService) {
    @GetMapping(path = ["/thingies"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun allTheThings(): List<Thingy> = thingyService.allThingies()

    @GetMapping(path = ["/thingies/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun justTheThing(@PathVariable id: Int): Thingy =
        thingyService.oneThingy(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Thingy not found")

    @PostMapping(path = ["/thingies"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun oneMoreThing(@RequestBody thing: Thingy): Thingy =
        thingyService.addThingy(thing) ?: throw ResponseStatusException(HttpStatus.CONFLICT, "Thingy already exists")

    @DeleteMapping(path = ["/thingies"])
    fun oneLessThing(@RequestParam id: Int): ResponseEntity<Void> = if (thingyService.deleteThingy(id) != null) {
        ResponseEntity.noContent().build()
    } else {
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "Thingy wasn't there")
    }
}
