package com.study.clean.architecture.application.port.`in`

import com.study.clean.architecture.exception.*
import jakarta.validation.constraints.*
import java.math.BigDecimal

/*
여기서 입력 유효성을 검증한다
 */
class SendMoneyCommand(
    @Positive
    val sourceAccountId: Long,
    @Positive
    val targetAccountId: Long,
    @Positive
    val amount: BigDecimal
)