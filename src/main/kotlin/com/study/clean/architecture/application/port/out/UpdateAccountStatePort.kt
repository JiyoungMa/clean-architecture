package com.study.clean.architecture.application.port.out

import com.study.clean.architecture.domain.*
import org.springframework.stereotype.Component

@Component
interface UpdateAccountStatePort {
    fun updateAccount(account: Account)
}