package com.straccion.repository.user

import com.straccion.dao.user.UserDao
import com.straccion.generateToken
import com.straccion.model.AuthResponse
import com.straccion.model.AuthResponseData
import com.straccion.model.SignInParams
import com.straccion.model.SignUpParams
import com.straccion.security.hashPassword
import com.straccion.util.Response
import io.ktor.http.*

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun signUp(params: SignUpParams): Response<AuthResponse> {
        return if (userAlreadyExist(params.email)){
            Response.Error(
                code = HttpStatusCode.Conflict,
                data = AuthResponse(
                    errorMessage = "Este correo ya existe!"
                )
            )
        }else{
            val insertedUser = userDao.insert(params)
            if (insertedUser == null){
                Response.Error(
                    code = HttpStatusCode.InternalServerError,
                    data = AuthResponse(
                        errorMessage = "Ooops, lo sentimos, no se pudo registrar, intente mas tarde"
                    )
                )
            }else{
                Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = insertedUser.id,
                            name = insertedUser.name,
                            bio = insertedUser.bio,
                            token = generateToken(params.email)
                        )
                    )
                )
            }
        }
    }

    override suspend fun signIn(params: SignInParams): Response<AuthResponse> {
        val user = userDao.findByEmail(params.email)
        return if (user == null){
            Response.Error(
                code = HttpStatusCode.NotFound,
                data = AuthResponse(
                    errorMessage = "Credenciales invalidas, no hay usuario con ese email!"
                )
            )
        }else{
            val hashedPassword = hashPassword(params.password)
            if (user.password == hashedPassword){
                Response.Success(
                    data = AuthResponse(
                        data = AuthResponseData(
                            id = user.id,
                            name = user.name,
                            bio = user.bio,
                            token = generateToken(params.email)
                        )
                    )
                )
            }else{
                Response.Error(
                    code = HttpStatusCode.Forbidden,
                    data = AuthResponse(
                        errorMessage = "Contraseña invalida"
                    )
                )
            }
        }
    }

    private suspend fun userAlreadyExist(email: String): Boolean {
        return userDao.findByEmail(email) != null
    }
}