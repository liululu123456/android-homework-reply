package com.thoughtworks.training.reply.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thoughtworks.training.reply.data.Email
import com.thoughtworks.training.reply.data.EmailsRepository
import com.thoughtworks.training.reply.data.EmailsRepositoryImpl
import kotlinx.coroutines.flow.first

class ReplyHomeViewModel(private val emailsRepository: EmailsRepository = EmailsRepositoryImpl()) :
    ViewModel() {
    private val emailsStates = mutableStateListOf<Email>()

     val emailsLiveData: MutableLiveData<List<Email>> = MutableLiveData()

    suspend fun fetchAllEmailData(){
        val allEmails = emailsRepository.getAllEmails().first()
        emailsLiveData.postValue(allEmails)
        emailsStates.clear()
        emailsStates.addAll(allEmails)
        }


}

data class ReplyHomeUIState(
    val emails: List<Email> = emptyList(),
    val selectedEmails: Set<Long> = emptySet(),
    val openedEmail: Email? = null,
    val isDetailOnlyOpen: Boolean = false,
    val loading: Boolean = false,
    val error: String? = null
)
