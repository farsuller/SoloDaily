package com.solo.solodaily.domain.usecases

import com.solo.solodaily.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager,
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
