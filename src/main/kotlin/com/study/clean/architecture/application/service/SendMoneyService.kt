package com.study.clean.architecture.application.service

import com.study.clean.architecture.application.port.`in`.*
import com.study.clean.architecture.application.port.out.*
import com.study.clean.architecture.domain.*
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
//@Transactional
class SendMoneyService(
    private val updateAccountStatePort: UpdateAccountStatePort,
    private val getAccountPort: GetAccountPort,
    private val updateActivityPort: UpdateActivityPort
) : SendMoneyUseCase {

    override fun sendMoney(command: SendMoneyCommand) {
        val sourceAccount = getAccountPort.findAccountById(command.sourceAccountId)
        val targetAccount = getAccountPort.findAccountById(command.targetAccountId)

        val resultSourceAccount = sourceAccount.withdraw(command.amount)
        val resultTargetAccount = targetAccount.deposit(command.amount)

        val activity = Activity.from(sourceAccount.accountId, targetAccount.accountId, command.amount)

        updateAccountStatePort.updateAccount(resultSourceAccount)
        updateAccountStatePort.updateAccount(resultTargetAccount)
        updateActivityPort.updateActivity(activity)
    }
}