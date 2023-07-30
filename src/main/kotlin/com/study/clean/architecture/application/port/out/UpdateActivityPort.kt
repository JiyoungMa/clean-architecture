package com.study.clean.architecture.application.port.out

import com.study.clean.architecture.domain.*
import org.springframework.stereotype.Component

@Component
interface UpdateActivityPort {
    fun updateActivity(activity: Activity)
}