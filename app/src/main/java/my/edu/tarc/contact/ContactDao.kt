package my.tarc.mycontact

import androidx.lifecycle.LiveData
import androidx.room.*


//Provide interface of database function
//C-create R-Retrieve U-Update D-Delete
@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAllContact(): LiveData<List<Contact>>

    //suspend - execute it in a separate thread
    //why use suspend ? the function can be blocking
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}