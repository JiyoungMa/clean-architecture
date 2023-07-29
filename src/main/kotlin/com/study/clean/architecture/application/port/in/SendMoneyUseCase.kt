package com.study.clean.architecture.application.port.`in`

import org.springframework.stereotype.Component

@Component
interface SendMoneyUseCase {
    fun sendMoney(command: SendMoneyCommand)
}