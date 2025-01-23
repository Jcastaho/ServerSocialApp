package com.straccion.util

import de.mkammerer.snowflakeid.SnowflakeIdGenerator

//generador de ID, se crea variable de entorno

private val generatorId = System.getenv("id.generator")

object IdGenerator {
    fun generateId(): Long{
        return SnowflakeIdGenerator.createDefault(generatorId.toInt()).next()
    }
}