package com.loom.coroutine

import kotlinx.coroutines.delay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class CoroutineApplication

fun main(args: Array<String>) {
    runApplication<CoroutineApplication>(*args)
}


@RestController
@RequestMapping("/")
class TestController(private val repo: MemberRepository) {


    @PostMapping
    suspend fun test(): Member {
        delay(50)
        return repo.save(Member(null, "test"))
    }
}

interface MemberRepository : CoroutineCrudRepository<Member, Int>

@Table("member")
data class Member(
    @Id
    val id: Int?,
    val name: String
)