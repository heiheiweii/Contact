package my.tarc.mycontact

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//can see how they link together
@Database (entities = arrayOf(Contact::class), version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object{
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile //in memory and can be destroyed
        private var INSTANCE: ContactDatabase? = null


        //Context = application context - level
        fun getDatabase(context: Context) : ContactDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            //do it now.... I wait for you
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}