package com.example.testingsqldelight.data

import com.example.testingsqldelight.PersonDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import crashcourse.persondb.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PersonDataSourceImpl( db: PersonDatabase ) : PersonDataSource{

    private val quries = db.personEntityQueries

    override suspend fun getPersonById(id: Long): PersonEntity? {
        return withContext(Dispatchers.IO){
            quries.getPersonById(id).executeAsOneOrNull()
        }
    }

    override fun getAllPersons(): Flow<List<PersonEntity>> {
        return quries.getAllPersons().asFlow().mapToList()
    }

    override suspend fun deletePersonById(id: Long) {
        withContext(Dispatchers.IO){
            quries.deletePerson(id)
        }
    }

    override suspend fun insertPerson(firstName: String, lastName: String, id: Long?) {
        withContext(Dispatchers.IO){
            quries.insertPerson(id, firstName, lastName)
        }
    }


}