package com.gsc.order.handles

import com.gsc.order.exceptions.BusinessException
import javassist.NotFoundException
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.*
import javax.validation.ConstraintViolationException


@ControllerAdvice
class ControlExceptionHandler {

    val CONSTRAINT_VALIDATION_FAILED = "Constraint validation failed"

    @ExceptionHandler(Throwable::class)
    fun handleException(eThrowable: Throwable): ResponseEntity<Any?>? {
        val ex: BusinessException = BusinessException(message = Optional.ofNullable(eThrowable.message).orElse(eThrowable.toString()),
                description = getRootException(eThrowable))
        val responseHeaders = HttpHeaders()
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).body(ex)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(exMethod: MethodArgumentTypeMismatchException,
                                         request: WebRequest?): ResponseEntity<Any?>? {
        val error = "${exMethod.name}  should be  ${exMethod.requiredType?.name?:""}"
        val ex = BusinessException(message = CONSTRAINT_VALIDATION_FAILED,
                description = error)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(exMethod: ConstraintViolationException, request: WebRequest?): ResponseEntity<Any?>? {
        val errors = mutableListOf<String>()
        exMethod.constraintViolations.forEach { errors.add("${it.rootBeanClass.name} ${it.propertyPath}: ${it.message}") }
        val ex = BusinessException(message = CONSTRAINT_VALIDATION_FAILED, description = errors.toString())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleException(e: NotFoundException): ResponseEntity<Any?>? {
        val ex = BusinessException(message = Optional.ofNullable(e.message).orElse(e.toString()), description = getRootException(e))
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex)
    }

    private fun getRootException(ex: Throwable) = String.format("%s in class: %s Line: %s", ExceptionUtils.getRootCauseMessage(ex), ExceptionUtils.getRootCause(ex).getStackTrace()[0].getClassName(), ExceptionUtils.getRootCause(ex).getStackTrace()[0].getLineNumber());
}