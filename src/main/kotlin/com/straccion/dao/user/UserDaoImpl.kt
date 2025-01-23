package com.straccion.dao.user

import com.straccion.dao.DataBaseFactory.dbQuery
import com.straccion.model.SignUpParams
import com.straccion.security.hashPassword
import com.straccion.util.IdGenerator
import org.jetbrains.exposed.sql.*

class UserDaoImpl : UserDao {
    override suspend fun insert(params: SignUpParams): UserRow? {
        return dbQuery {
            val insertStatement = UserTable.insert {
                it[id] =IdGenerator.generateId()
                it[name] = params.name
                it[email] = params.email
                it[password] = hashPassword(params.password)
            }
            insertStatement.resultedValues?.singleOrNull()?.let {
                rowToUser(it)
            }
        }
    }

    override suspend fun findByEmail(email: String): UserRow? {
        return dbQuery {
            UserTable.selectAll().where { UserTable.email eq email }
                .map { rowToUser(it) }
                .singleOrNull()
        }
    }

    private fun rowToUser(row: ResultRow): UserRow {
        return UserRow(
            id = row[UserTable.id],
            name = row[UserTable.name],
            bio = row[UserTable.bio],
            imageUrl = row[UserTable.imageUrl],
            password = row[UserTable.password],
            followersCount = row[UserTable.followersCount],
            followingCount = row[UserTable.followingCount]
        )
    }
}