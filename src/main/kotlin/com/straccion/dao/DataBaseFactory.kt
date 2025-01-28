package com.straccion.dao

import com.straccion.dao.follows.FollowsTable
import com.straccion.dao.post.PostTable
import com.straccion.dao.post_comments.PostCommentsTable
import com.straccion.dao.post_likes.PostLikesTable
import com.straccion.dao.user.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DataBaseFactory {
    fun init(){
        Database.connect(createHikariDataSource())
        transaction {
            SchemaUtils.create(UserTable, FollowsTable, PostTable, PostLikesTable, PostCommentsTable)
        }
    }
    private fun createHikariDataSource(): HikariDataSource {
        val driverClass = "org.postgresql.Driver"
        val jdbcUrl  = "jdbc:postgresql://localhost:5432/socialmediadb"
        val hikariConfig = HikariConfig().apply {
            driverClassName = driverClass
            username= "postgres"
            password = "1234"
            setJdbcUrl(jdbcUrl)
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()

        }
        return HikariDataSource(hikariConfig)
    }

    suspend fun <T> dbQuery(block: suspend () -> T) =
        newSuspendedTransaction(Dispatchers.IO){block()}
}