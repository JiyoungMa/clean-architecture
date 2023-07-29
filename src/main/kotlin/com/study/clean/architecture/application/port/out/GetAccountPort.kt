package com.study.clean.architecture.application.port.out

import com.study.clean.architecture.domain.*
import org.springframework.stereotype.Component

@Component
interface GetAccountPort {
    fun findAccountById(id: Long) : Account
}