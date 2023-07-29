package com.study.clean.architecture.domain

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

/*
<Activity>
계좌간의 활동을 저장하는 activity
-> 무조건 로직의 방향은 ownerAccount -> targetAccount로 송금하는 개념
*/
data class Activity(
    val activityId: UUID,
    val sourceAccountId: Long,
    val targetAccountId: Long,
    val datetime: ZonedDateTime,
    val amount: BigDecimal
){
    companion object{
        fun from(sourceAccountId: Long, targetAccountId: Long, amount: BigDecimal): Activity{
            return Activity(
                activityId = UUID.randomUUID(),
                sourceAccountId = sourceAccountId,
                targetAccountId = targetAccountId,
                datetime = ZonedDateTime.now(),
                amount = amount
            )
        }
    }
}