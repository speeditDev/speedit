package com.speedit.server.exception

import graphql.GraphQLException
import graphql.kickstart.spring.error.ThrowableGraphQLError
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException

@Component
class GraphqlExceptionHandler {
    @ExceptionHandler(GraphQLException::class)
    fun handle(e: GraphQLException): ThrowableGraphQLError {
        return ThrowableGraphQLError(e)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handle(e: ConstraintViolationException): ThrowableGraphQLError {
        return ThrowableGraphQLError(e, e.message)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handle(e: BadRequestException): ThrowableGraphQLError? {
        return ThrowableGraphQLError(e)
    }

    @ExceptionHandler(InvalidException::class)
    fun handle(e: InvalidException): ThrowableGraphQLError? {
        return ThrowableGraphQLError(e, "internal server error")
    }

    @ExceptionHandler(RuntimeException::class)
    fun handle(e: RuntimeException): ThrowableGraphQLError {
        return ThrowableGraphQLError(e, "internal server error")
    }
}