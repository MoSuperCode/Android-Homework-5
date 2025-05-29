package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.ContactDao
import com.example.myapplication.data.entity.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    fun getAllContacts(): Flow<List<Contact>> = contactDao.getAllContacts()

    suspend fun getContactById(id: Long): Contact? = contactDao.getContactById(id)

    suspend fun insertContact(contact: Contact): Long = contactDao.insertContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    suspend fun deleteAllContacts() = contactDao.deleteAllContacts()
}