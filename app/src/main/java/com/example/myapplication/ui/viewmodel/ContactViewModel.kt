package com.example.myapplication.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.database.ContactDatabase
import com.example.myapplication.data.entity.Contact
import com.example.myapplication.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactRepository
    val allContacts: Flow<List<Contact>>

    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.getAllContacts()
    }

    fun insertContact(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String
    ) = viewModelScope.launch {
        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            email = email,
            createdAt = Date()
        )
        repository.insertContact(contact)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch {
        repository.updateContact(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch {
        repository.deleteContact(contact)
    }

    fun deleteAllContacts() = viewModelScope.launch {
        repository.deleteAllContacts()
    }

    suspend fun getContactById(id: Long): Contact? {
        return repository.getContactById(id)
    }
}