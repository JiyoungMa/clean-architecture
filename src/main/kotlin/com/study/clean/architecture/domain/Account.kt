package com.study.clean.architecture.domain

import com.study.clean.architecture.exception.*
import java.math.BigDecimal

/*
<Account 엔티티>
현재 계좌의 상태를 보여줌
-> id, 현재 잔액 필요 + 책에서는 없지만 보통 계좌에는 상태가 있다 (활성 상태, 휴면 상태, 해지된 상태로 구분 예정)

책에서는 계좌에 ActivityWindow라는게 있어 일정 기간에 해당하는 활동만 보유한다고 했는데
엔티티 단위에서 거래 활동과 직접 연관은 짓지 않으려고 한다
-> 직접 연관을 지었을 때, 계좌 삭제시 거래활동도 같이 삭제된다거나 하는 경우를 예방하기 위해 논리적인 연관은 있어도 물리적으로 연결되는 것은 좋지않다고 생각

entity를 리턴하는 경우에 맘대로 금액이 바뀌지 않도록 val 로 하고 리턴할때는 새로운 엔티티를 생성한다
-> 변경감지로 update 하도록 구현 필요
*/
data class Account(
    val accountId: Long,
    val balanceAmount: BigDecimal = BigDecimal.ZERO
){
    private fun isWithdrawable(withdrawAmount: BigDecimal): Boolean{
        return balanceAmount >= withdrawAmount
    }

    // 비즈니스 규칙을 도메인 엔티티에 넣는다
    // -> 비즈니스 로직 바로 옆에 규칙이 위치하기 때문에 위치를 정하는 것도 쉽고 추론도 쉽다
    fun withdraw(amount: BigDecimal): Account{
        if(!isWithdrawable(amount)) throw ImpossibleActivityException("출금할 수 있는 금액을 초과하였습니다")

        return Account(
            accountId = accountId,
            balanceAmount = balanceAmount - amount
        )
    }

    fun deposit(amount: BigDecimal): Account{
        return Account(
            accountId = accountId,
            balanceAmount = balanceAmount + amount
        )
    }
}
